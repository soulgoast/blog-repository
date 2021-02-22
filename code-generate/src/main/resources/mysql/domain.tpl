package ${projectModel}.service.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
* ${DtoDesc}
*/
@Entity
@Table(name = "${tableName}")
@Data
@Accessors(chain = true)
@Where(clause = "del_flag='" + Constants.DEL_FLAG_NORMAL + "'")
@SQLDelete(sql = "update ${tableName} set del_flag='"
+ Constants.DEL_FLAG_DELETED + "',update_time=now() where id=?")
@SQLDeleteAll(sql = "update ${tableName} set del_flag='"
+ Constants.DEL_FLAG_DELETED + "',update_time=now() where id=?")
public class ${className} implements Serializable {

    private static final long serialVersionUID = ${serUid};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    <#list fields as field>
    @Column(name="${field.fieldDbName}", columnDefinition="${field.type} comment '${field.fieldComment}'")
    private ${field.type} ${field.name};

    </#list>

}
