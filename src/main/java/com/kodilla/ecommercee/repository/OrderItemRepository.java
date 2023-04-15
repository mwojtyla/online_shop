package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
    List<OrderItem> findAll();

    Optional<OrderItem> findById(Long Id);

    OrderItem save(OrderItem orderItem);

    void deleteById(Long Id);
}
