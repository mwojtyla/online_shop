package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductsInCart;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(final Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public void saveProduct(final Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(final Long productId) {
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Product updatedProduct) throws ProductNotFoundException {
        Long productId = updatedProduct.getProductId();
        if (productRepository.findById(productId).isPresent()) {
            Product product = new Product(
                    productId,
                    updatedProduct.getName(),
                    updatedProduct.getDescription(),
                    updatedProduct.getPrice(),
                    updatedProduct.getGroup(),
                    updatedProduct.getProductsInCarts(),
                    updatedProduct.getOrderItem()
            );
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException();
        }
    }


    public void addProductsInCart(final ProductsInCart productsInCart) throws ProductNotFoundException {
        productRepository.findById(productsInCart.getProduct().getProductId()).orElseThrow(ProductNotFoundException::new).getProductsInCarts().add(productsInCart);
    }

    public void removeProductInCart(final Long productId, final Long productsInCartId) throws ProductNotFoundException {
        productRepository.findById(productId).orElseThrow(ProductNotFoundException::new).getProductsInCarts()
                .removeIf(productsInCart -> productsInCart.getProductsInCartId().longValue() == productsInCartId.longValue());
    }

    public void removeProductsInCartList(final List<ProductsInCart> productsInCart) throws ProductNotFoundException {
        for (ProductsInCart productInCart : productsInCart) {
            Product product = productInCart.getProduct();
            removeProductInCart(product.getProductId(), productInCart.getProductsInCartId());
            saveProduct(product);
        }
    }
}

