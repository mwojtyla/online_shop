package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        assertNotEquals(0, groupId);
    }

    @Test
    public void testDeleteGroup() {

        //Given
        Group group = new Group();
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productRepository.save(product);
        group.setProducts(productList);
        group.setName("Ubrania");

        //When
        groupRepository.save(group);
        Long groupId = group.getGroupId();
        groupRepository.deleteById(groupId);

        //Then
        assertEquals(Optional.empty(), groupRepository.findById(groupId));
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

        //Then
        assertEquals("Obuwie", group.getName());
    }

    @Test
    public void testFindById(){

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

        //Then
        Long id = group.getGroupId();
        Optional<Group> groupOptional = groupRepository.findById(id);
        assertEquals(id,groupOptional.get().getGroupId());
    }

    @Test
    public void testSetNameGroup() {

        //Given
        Group groupTest = new Group();
        groupTest.setName("New Name");

        //When
        groupRepository.save(groupTest);
        String name = groupTest.getName();

        //Then
        assertEquals("New Name", name);
    }

    @Test
    public void testSetProducts() {

        //Given
        Group groupTest = new Group();
        Product product1 = new Product();
        product1.setName("product 1");
        product1.setGroup(groupTest);
        productRepository.save(product1);

        //When
        groupTest.getProducts().add(product1);
        Group savedGroupTest1 = groupRepository.save(groupTest);

        //Then
        assertTrue(savedGroupTest1.getProducts().contains(product1));
    }
}