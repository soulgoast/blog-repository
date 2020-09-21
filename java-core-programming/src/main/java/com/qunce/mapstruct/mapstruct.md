# mapstruct
## 前言
这是MapStruct的参考文档，MapStruct是一种注释处理器，用于生成类型安全，高性能和无依赖的Bean映射代码。
本指南涵盖了MapStruct提供的所有功能。如果本指南不能解决您的所有问题，请加入MapStruct Google小组以获取帮助。
## 引言
MapStruct是一个Java注释处理器，用于生成类型安全的bean映射类。您要做的就是定义一个映射器接口，该接口声明任何必需的映射方法。
在编译期间，MapStruct将生成此接口的实现。此实现使用简单的Java方法调用在源对象与目标对象之间进行映射，即没有反射或类似内容。
与手动编写映射代码相比，MapStruct通过生成繁琐且易于出错的代码来节省时间。
遵循配置方法上的约定，MapStruct使用合理的默认值，但在配置或实现特殊行为时不加理会。
与动态映射框架相比，MapStruct具有以下优点：
- 通过使用普通方法调用而不是反射来快速执行编译时类型安全：（高性能）
- 只能映射彼此映射的对象和属性，而不会将订单实体意外映射到客户DTO等。（类型安全）
- 以下情况，构建时清除错误报告。
    - 映射不完整（并非所有目标属性都被映射）
    - 映射不正确（找不到正确的映射方法或类型转换）

## 引入依赖
```pom
        <properties>
            <mapstruct.version>1.2.0.Final</mapstruct.version>
        </properties>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-jdk8</artifactId>
            <version>${mapstruct.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>${mapstruct.version}</version>
        </dependency>
```

## 定义一个映射
在本节中，您将学习如何使用MapStruct定义bean映射器，以及必须使用哪些选项。
### 基础映射
通过定义个java接口并添加需要的映射方法和`org.mapstruct.Mapper`注解来创建一个简单的映射器。
```java
@Mapper
public interface CarMapper {
    @Mappings({
        @Mapping(source = "make", target = "manufacturer"),
        @Mapping(source = "numberOfSeats", target = "seatCount")
    })
    CarDto carToCarDto(Car car);
    
    @Mapping(source = "name", target = "fullName")
    PersonDto personToPersonDto(Person person);
}
```
@Mapper注释使MapStruct代码生成器在构建时创建CarMapper接口的实现。在生成的方法实现中，源类型（例如Car）的所有可读属性
都将被复制到目标类型（例如CarDto）的相应属性中。如果属性在目标实体中具有不同的名称，则可以通过@Mapping注释指定其名称。
在实际的开发中，mapper主要用于实体类与DTO，VO之间的映射。
DTO按照现在的理解主要用户页面数据到后台业务层。当然DTO包含页面需要展示的数据，也可以用于回传到页面进行显示。
VO仅仅用于页面显示。
注意：
- 在`@mapping`中指定的属性必须按照`javabean`规范进行定义。比如，seatCount熟悉得有访问方法getSeatCount和setSeatCount。
如果没有指定访问方法将会报错，同名方法如果没有访问方法，将不会拷贝值。
- 使用Java 8或更高版本时，可以省略@Mappings包装器注释，并直接在一种方法上指定多个@Mapping注释。

MapStruct的一般原理是生成看起来像您亲自编写的代码一样的代码。特别是，这意味着这些值是通过简单的getter / setter调用而不是反射或类似方法从源复制到目标的。
如示例所示，生成的代码考虑了通过@Mapping指定的任何名称映射。如果源实体和目标实体中映射属性的类型不同，则MapStruct将应用自动转换（例如，对于price属性，另请参见隐式类型转换），
或者可选地调用/创建另一种映射方法（例如，对于驱动程序） / engine属性，另请参见映射对象引用）。仅当source和target属性是Bean的属性并且它们本身是Bean或简单属性时，MapStruct才会创建新的映射方法。
即它们不是“集合”或“地图类型”属性。通过创建包含源属性中元素的目标集合类型的新实例，将复制具有相同元素类型的集合类型的属性。
对于具有不同元素类型的集合类型的属性，每个元素将被单独映射并添加到目标集合中（请参阅映射集合）。 MapStruct考虑了源类型和目标类型的所有公共属性。这包括在超类型上声明的属性。

### 给映射器添加自定义方法
在某些情况下，可能需要手动实施从一种类型到另一种类型的特定映射，而MapStruct无法生成这种映射。
一种方法是在另一个类上实现这种方法，然后由MapStruct生成的映射器使用该方法（请参见调用其他映射器）。
另外，在使用Java 8或更高版本时，可以直接在映射器界面中实现自定义方法作为默认方法。如果参数和返回类型匹配，则生成的代码将调用默认方法。
例如，假设从Person到PersonDto的映射需要一些特殊的逻辑，而MapStruct无法生成这些逻辑。然后可以从上一个示例中定义映射器，如下所示：
```java
@Mapper
public interface CarMapper {

    @Mappings({...})
    CarDto carToCarDto(Car car);

    default PersonDto personToPersonDto(Person person) {
        //hand-written mapping logic
    }
}
```
MapStruct生成的类实现了carToCarDto（）方法。在映射驱动程序属性时，在carToCarDto（）中生成的代码将调用手动实现的
personToPersonDto（）方法。映射器也可以以抽象类的形式而不是接口的形式定义，并直接在此映射器类中实现自定义方法。
在这种情况下，MapStruct将使用所有抽象方法的实现生成抽象类的扩展。与声明默认方法相比，此方法的优点是可以在mapper类中声明
其他字段。然后可以定义前面的示例，其中从Person到PersonDto的映射需要一些特殊的逻辑，如下所示：
```java
@Mapper
public abstract class CarMapper {

    @Mappings(...)
    public abstract CarDto carToCarDto(Car car);

    public PersonDto personToPersonDto(Person person) {
        //hand-written mapping logic
    }
}
```
MapStruct将通过声明为抽象的carToCarDto（）方法的实现来生成CarMapper的子类。
在映射驱动程序属性时，在carToCarDto（）中生成的代码将调用手动实现的personToPersonDto（）方法。
### 多个源参数的映射方法
MapStruct还支持带有多个源参数的映射方法。这很有用，例如为了将多个实体组合成一个数据传输对象。下面显示了一个示例：
```java
@Mapper
public interface AddressMapper {

    @Mappings({
        @Mapping(source = "person.description", target = "description"),
        @Mapping(source = "address.houseNo", target = "houseNumber")
    })
    DeliveryAddressDto personAndAddressToDeliveryAddressDto(Person person, Address address);
}
```
所示的映射方法采用两个源参数，并返回一个组合的目标对象。与单参数映射方法一样，属性是按名称映射的。
如果多个源对象定义了一个具有相同名称的属性，则必须使用@Mapping注释指定要从中检索该属性的源参数，
如示例中的description属性所示。如果无法解决此类歧义，则会引发错误。
对于在给定的源对象中仅存在一次的属性，可以选择指定源参数的名称，因为它可以自动确定。
注意
- 使用@Mapping批注时，必须指定属性所在的参数。
- 如果所有源参数均为null，则具有多个源参数的映射方法将返回null。否则，将实例化目标对象，并且将传播提供的参数中的所有属性。
MapStruct还提供了直接引用源参数的可能性。
```java
@Mapper
public interface AddressMapper {

    @Mappings({
        @Mapping(source = "person.description", target = "description"),
        @Mapping(source = "hn", target = "houseNumber")
    })
    DeliveryAddressDto personAndAddressToDeliveryAddressDto(Person person, Integer hn);
}
```
在这种情况下，如上面的示例所示，将源参数直接映射到目标中。参数hn（非bean类型）（在本例中为java.lang.Integer）被映射到houseNumber。
### 更新已经存在的bean实例
在某些情况下，您需要的映射不会创建目标类型的新实例，而是更新该类型的现有实例。可以通过为目标对象添加参数并使用@MappingTarget标记此参数来实现这种映射。下面显示了一个示例：
```java
@Mapper
public interface CarMapper {

    void updateCarFromDto(CarDto carDto, @MappingTarget Car car);
}
```
updateCarFromDto（）方法的生成代码将使用给定CarDto对象的属性更新传递的Car实例。可能只有一个参数标记为映射目标。
您也可以将方法的返回类型设置为target参数的类型，而不是void，这将导致生成的实现更新传递的映射目标并返回它。
这样可以流畅地调用映射方法。
将清除要更新的目标bean的集合或映射类型的属性，然后使用来自相应源集合或映射的值填充。
### 具有直接现场访问的映射
MapStruct还支持没有getter / setter的公共字段的映射。如果MapStruct找不到适合该属性的合适的getter / setter方法，
它将使用这些字段作为读/写访问器。如果字段是公共或公共最终字段，则将其视为读取访问器。如果字段是静态的，
则不将其视为读取访问器。
仅当字段为公共字段时，才将其视为写访问器。如果字段是final和/或static，则不将其视为写访问器。

## 检索映射器
### 映射器工厂
可以通过org.mapstruct.factory.Mappers类检索Mapper实例。只需调用getMapper（）方法，并传递映射器的接口类型即可返回：
按照约定，映射器接口应定义一个名为INSTANCE的成员，该成员包含一个映射器类型的单个实例：

此模式使客户端无需重复实例化新实例即可非常轻松地使用映射器对象：

请注意，由MapStruct生成的映射器是线程安全的，因此可以安全地同时从多个线程访问。

### 使用依赖注入
在依赖注入框架，比如spring中使用mapstruct

## 数据类型转换
在源对象和目标对象中，映射属性并不总是具有相同的类型。例如，一个属性在源bean中可能是int类型，而在目标bean中是Long类型。
另一个示例是对其他对象的引用，这些对象应映射到目标模型中的相应类型。例如。该类Car可能具有类型为Person的属性驱动程序，
在映射Car对象时需要将其转换为PersonDto对象。在本部分中，您将学习MapStruct如何处理此类数据类型转换。
### 隐式类型转换
在许多情况下，MapStruct会自动处理类型转换。例如，如果某个属性在源Bean中为int类型，而在目标Bean中为String类型，
则生成的代码将通过分别调用String＃valueOf（int）和Integer＃parseInt（String）透明地执行转换。
当前，以下转换将自动应用：
- 在所有Java基本数据类型及其对应的包装器类型之间，例如生成的代码是null感知的，即，在将包装器类型转换为相应的基本类型时，将执行null检查。
- 在所有Java原语数字类型和包装器类型之间，例如在int和long之间，或者在byte和Integer之间。（注意：从较大的数据类型转换为较小的数据类型
（例如，从long到int）可能会导致值或精度损失。在将来的MapStruct版本中，在这种情况下将提供警告的选项。）
- 在所有Java基本类型（包括其包装器）和String之间，例如在int和String或Boolean和String之间。可以指定java.text.DecimalFormat可以理解的格式字符串。
- 在所有Java基本类型（包括其包装器）和String之间，例如在int和String或Boolean和String之间。可以指定java.text.DecimalFormat可以理解的格式字符串。
- 枚举类型和字符串之间

### 映射对象引用
### 调用其他映射器
### 将映射目标类型传递给自定义映射器

## 使用 mapStruct SPI
### 自定义访问器命名策略