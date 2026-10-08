package se.iths.richard.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.iths.richard.webshop.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductListStorageTest {

    private ProductListStorage storage;

    @BeforeEach
    void setUp() {
        storage = new ProductListStorage();
    }

    @Test
    @DisplayName("Should save product to list")
    void testSaveProduct() {
        Product product = new Product("T001", "Keyboard", 599.99, "Mechanical Keyboard");

        storage.saveProduct(product);

        List<Product> products = storage.getProducts();

        assertEquals(1, products.size());
        assertEquals("T001", products.get(0).getArticleNumber());
    }

    @Test
    @DisplayName("Should return all products from list")
    void testGetProducts() {
        Product firstProduct = new Product("T001", "Keyboard", 599.99, "Mechanical Keyboard");

        Product secondProduct = new Product("T002", "Mouse", 199.99, "Super Gaming Mouse");

        storage.saveProduct(firstProduct);
        storage.saveProduct(secondProduct);

        List<Product> products = storage.getProducts();

        assertEquals(2, products.size());
        assertEquals("T001", products.get(0).getArticleNumber());
        assertEquals("T002", products.get(1).getArticleNumber());
    }

    @Test
    @DisplayName("Should return empty list when no products have been saved")
    void testGetProductsWithEmptyList() {
        List<Product> products = storage.getProducts();

        assertTrue(products.isEmpty());
    }

    @Test
    @DisplayName("Should return product with matching article number")
    void testGetProduct() {
        Product product = new Product("T001", "Keyboard", 599.99, "Mechanical Keyboard");

        storage.saveProduct(product);

        Product result = storage.getProduct("T001");

        assertEquals("T001", result.getArticleNumber());
        assertEquals("Keyboard", result.getTitle());
        assertEquals(599.99, result.getPrice());
        assertEquals("Mechanical Keyboard", result.getDescription());
    }

    @Test
    @DisplayName("Should return null when article number does not exist")
    void testGetProductWithInvalidArticleNumber() {
        Product product = new Product("T001", "Keyboard", 599.99, "Mechanical Keyboard");

        storage.saveProduct(product);

        Product result = storage.getProduct("T999");

        assertNull(result);
    }

    @Test
    @DisplayName("Should return the same product when multiple products are saved")
    void testGetProductWithMultipleProducts() {
        Product firstProduct = new Product("T001", "Keyboard", 599.99, "Mechanical Keyboard");

        Product secondProduct = new Product("T002", "Mouse", 199.99, "Super Gaming Mouse");

        storage.saveProduct(firstProduct);
        storage.saveProduct(secondProduct);

        Product result = storage.getProduct("T002");

        assertEquals("T002", result.getArticleNumber());
        assertEquals("Mouse", result.getTitle());
        assertEquals(199.99, result.getPrice());
        assertEquals("Super Gaming Mouse", result.getDescription());
    }

    @Test
    @DisplayName("Should not duplicate products when getProducts is called multiple times")
    void testGetProductsMultipleTimes() {
        Product product = new Product("T001", "Keyboard", 599.99, "Mechanical Keyboard");

        storage.saveProduct(product);

        List<Product> firstResult = storage.getProducts();
        List<Product> secondResult = storage.getProducts();

        assertEquals(1, firstResult.size());
        assertEquals(1, secondResult.size());
    }
}
