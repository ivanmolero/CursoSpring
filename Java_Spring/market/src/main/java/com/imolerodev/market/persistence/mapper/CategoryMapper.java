package com.imolerodev.market.persistence.mapper;

import com.imolerodev.market.domain.Category;
import com.imolerodev.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") // mapstruct tiene soporte para spring
public interface CategoryMapper {

    // esta es la conversion de categoria a category
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    // esta anotación indica que es la inversa de la declarada antes,
    // no es necesario definir nuevamente mappings
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
