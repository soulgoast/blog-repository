package com.whty.${projectModel}.service.mapper;

import com.whty.${projectModel}.domain.${className};
import com.whty.${projectModel}.service.dto.${className}DTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {${className}} and its DTO { ${className}DTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ${className}Mapper extends EntityMapper<${className}DTO, ${className}> {



    default ${className} fromId(Long id) {
        if (id == null) {
            return null;
        }
        ${className}  ${littleClassName} = new ${className}();
        ${littleClassName}.setId(id);
        return ${littleClassName};
    }
}
