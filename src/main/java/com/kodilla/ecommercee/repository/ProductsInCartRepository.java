package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.ProductsInCart;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ProductsInCartRepository extends CrudRepository<ProductsInCart, Long> {

    @NotNull List<ProductsInCart> findAll();

}
