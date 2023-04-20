package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class GroupEntityTests {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveGroup() {

        //Given
        Group group = new Group();

        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productRepository.save(product);
        group.setProducts(productList);
        group.setName("Test Group");

        //When
        groupRepository.save(group);
        Long groupId = group.getGroupId();

        //Then
        Assertions.assertNotEquals(0, groupId);
    }

    @Test
    public void testDeleteGroup() {
        //Given
        Group group = new Group();
        group.setName("Ubrania");
        Product product = new Product();
        group.getProducts().add(product);
        product.setGroup(group);

        //When
        groupRepository.save(group);
        Long groupId = group.getGroupId();
        Long productId = product.getProductId();
        groupRepository.deleteById(groupId);

        //Then
        Assertions.assertFalse(groupRepository.existsById(groupId));
        Assertions.assertEquals(productId, product.getProductId());
    }

    @Test
    public void testUpdateGroup() {
        //Given
        Group group = new Group();

        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productRepository.save(product);
        group.setProducts(productList);
        group.setName("Obuwie");

        //When
        groupRepository.save(group);
        long groupId = group.getGroupId();

        //Then
        Assertions.assertEquals("Obuwie", group.getName());

        //CleanUp
        groupRepository.deleteById(groupId);
    }
}