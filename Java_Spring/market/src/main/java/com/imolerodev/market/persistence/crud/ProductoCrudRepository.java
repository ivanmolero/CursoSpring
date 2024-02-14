package com.imolerodev.market.persistence.crud;

import com.imolerodev.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    // ojo que también es posible usar un native query para poder obtener el metodo
    // si se usa una native query no es necesario seguir la regla de nombrado
    // de query methods
//    @Query(value = "select * from productos where id_categoria = ?", nativeQuery = true)

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
