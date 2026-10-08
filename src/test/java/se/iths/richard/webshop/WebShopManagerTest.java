package se.iths.richard.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.richard.webshop.model.Product;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WebShopManagerTest {

    @TempDir
    Path tempDir;

    private Path testFilePath;

    private ProductStorage storage;
    private WebShopManager webShopManager;

    @BeforeEach
    void setUp() {
        storage = new ProductListStorage();
        webShopManager = new WebShopManager(storage, null);
    }

    @Test
    @DisplayName("Should save two products and return a list with two products")
    void testSaveTwoProducts() {
        Product firstProduct = new Product("T001", "Keyboard", 599.99, "Mechanical Keyboard");

        Product secondProduct = new Product("T002", "Mouse", 199.99, "Super Gaming Mouse");

        webShopManager.saveProductToStorage(firstProduct);
        webShopManager.saveProductToStorage(secondProduct);

        List<Product> products = webShopManager.getProductsFromStorage();

        assertEquals(2, products.size());
    }

    @Test
    @DisplayName("Should return product with matching article number")
    void testGetProduct() {
        Product product = new Product("T001", "Keyboard", 599.99, "Mechanical Keyboard");

        webShopManager.saveProductToStorage(product);

        Product result = webShopManager.getProductFromStorage("T001");

        assertEquals("T001", result.getArticleNumber());
    }

    @Test
    @DisplayName("Should return null when article number does not exist")
    void testGetProductWithInvalidArticleNumber() {
        Product result = webShopManager.getProductFromStorage("T999");

        assertNull(result);
    }
}
