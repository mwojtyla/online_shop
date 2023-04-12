package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/shop/orders")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping
    public List<OrderDto> getOrders(){
        return new ArrayList<>();
    }

    @GetMapping("/items")
    public List<OrderItemDto> getItems(){
        return new ArrayList<>();
    }

    @GetMapping("{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId){
        return new OrderDto(1L,LocalDate.now(),"Paid", 1L,1L);
    }

    @GetMapping( "/items/{orderItemId}")
    public OrderItemDto getOrderItem(@PathVariable Long orderItemId){
        return new OrderItemDto(1L,"In progress", new BigDecimal(250), 1L, 1L, 22);
    }

    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto){
    }

    @PostMapping("/items")
    public void createOrderItem(@RequestBody OrderItemDto orderItemDto){
    }


    @PutMapping
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return new OrderDto(1L,LocalDate.now(),"In progress", 1L,1L);
    }

    @PutMapping("/items")
    public OrderItemDto updateOrderItem(@RequestBody OrderItemDto orderItemDto){
        return new OrderItemDto(1L,"Ended", new BigDecimal(300), 1L, 1L, 22);
    }


    @DeleteMapping("{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        System.out.println("The Order nr " + orderId + " removed.");
    }

    @DeleteMapping("/items/{orderItemId}")
    public void deleteOrderItem(@PathVariable Long orderItemId){
        System.out.println("The Order Item nr " + orderItemId + " removed");
    }

}
