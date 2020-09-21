package com.qunce.abstractdocument.test;

import com.qunce.abstractdocument.domain.Car;
import com.qunce.abstractdocument.domain.enums.Property;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName App
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:44
 * @ModifyDate 2020/9/21 14:44
 * @Version 1.0
 */
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Test
    public void shouldExecuteAppWithoutException() {

        Map<String, Object> carProperties = new HashMap<>();
        carProperties.put(Property.MODEL.getValue(), "300SL");
        carProperties.put(Property.PRICE.getValue(), 10000L);

        Map<String, Object> wheelProperties = new HashMap<>();
        wheelProperties.put(Property.TYPE.getValue(), "wheel");
        wheelProperties.put(Property.MODEL.getValue(), "15C");
        wheelProperties.put(Property.PRICE.getValue(), 100L);

        Map<String, Object> doorProperties = new HashMap<>();
        doorProperties.put(Property.TYPE.getValue(), "door");
        doorProperties.put(Property.MODEL.getValue(), "Lambo");
        doorProperties.put(Property.PRICE.getValue(), 300L);

        carProperties.put(Property.PARTS.getValue(), Arrays.asList(wheelProperties, doorProperties));

        Car car = new Car(carProperties);

        LOGGER.info("Here is our car:");
        LOGGER.info("-> model: {}", car.getModel().get());
        LOGGER.info("-> price: {}", car.getPrice().get());
        LOGGER.info("-> parts: ");
        car.getParts().forEach(p -> LOGGER.info("\t{}/{}/{}", p.getType().get(), p.getModel().get(), p.getPrice().get()));
    }

}
