package se.iths.richard.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.iths.richard.webshop.exceptions.IllegalPriceException;
import se.iths.richard.webshop.model.Product;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductFileStorageTest {

    @TempDir
    Path tempDir;

    private Path testFilePath;
    private ProductFileStorage storage;

    @BeforeEach
    void setUp() {
        testFilePath = tempDir.resolve("products.scsv");
        storage = new ProductFileStorage(testFilePath);
    }

    @Test
    @DisplayName("Should create the products file")
    void testFileCreation() {
        assertTrue(Files.exists(testFilePath));
    }

    @Test
    @DisplayName("Should save product to file")
    void testSaveProduct() throws Exception {
        Product product = new Product("T001", "Keyboard", 599.99, "Mechanical Keyboard");

        storage.saveProduct(product);

        String fileContent = Files.readString(testFilePath);

        assertEquals("T001;Keyboard;599.99;Mechanical Keyboard" + System.lineSeparator(), fileContent);
    }

    @ParameterizedTest
    @DisplayName("Should throw IllegalArgumentException when a string is blank")
    @ValueSource(strings = {"", " ", "    "})
    void testCreateProductWithBlankArticleNumber(String articleNumber) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product(articleNumber, "Keyboard", 599, "Mechanical keyboard")
        );
    }

    @ParameterizedTest
    @DisplayName("Should throw IllegalArgumentException when title is blank")
    @ValueSource(strings = {"", " ", "    "})
    void testCreateProductWithBlankTitle(String title) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product("T001", title, 599, "Mechanical keyboard")
        );
    }

    @ParameterizedTest
    @DisplayName("Should throw IllegalArgumentException when description is blank")
    @ValueSource(strings = {"", " ", "    "})
    void testCreateProductWithBlankDescription(String description) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Product("T001", "Keyboard", 599, description)
        );
    }

    @ParameterizedTest
    @DisplayName("Should throw IllegalPriceException when price is negative")
    @ValueSource(doubles = {-1, -10, -100, -0.1})
    void testCreateProductWithNegativePrice(double price) {
        assertThrows(
                IllegalPriceException.class,
                () -> new Product("T001", "Keyboard", price, "Mechanical keyboard")
        );
    }

    @ParameterizedTest
    @DisplayName("Should accept zero and positive prices")
    @ValueSource(doubles = {0, 1, 10, 100, 599.99, 1000})
    void testCreateProductWithValidPrice(double price) {
        Product product = new Product(
                "T001",
                "Keyboard",
                price,
                "Mechanical keyboard"
        );

        assertEquals(price, product.getPrice());
    }

    @Test
    @DisplayName("Should return all products from file")
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
    @DisplayName("Should return empty list when file is empty")
    void testGetProductsWithEmptyFile() {
        List<Product> products = storage.getProducts();

        assertTrue(products.isEmpty());
    }

    @Test
    @DisplayName("Should ignore product when price is invalid")
    void testGetProductsWithInvalidPrice() throws Exception {
        // I do Files.writeString so I can write to the file with an Invalid value
        // (simply because if I were to do saveProduct/Make the product it would cause compilation error)
        Files.writeString(
                testFilePath,
                "T001;Keyboard;599.99;Mechanical keyboard" + System.lineSeparator() +
                        "T002;Mouse;invalid;Gaming mouse" + System.lineSeparator() +
                        "T003;Monitor;1999.99;Gaming monitor" + System.lineSeparator()
        );

        List<Product> products = storage.getProducts();

        assertEquals(2, products.size());
        assertEquals("T001", products.get(0).getArticleNumber());
        assertEquals("T003", products.get(1).getArticleNumber());
    }

    @Test
    @DisplayName("Should ignore product when number of fields is invalid")
    void testGetProductsWithInvalidNumberOfFields() throws Exception {
        Files.writeString(
                testFilePath,
                "T001;Keyboard;599.99;Mechanical keyboard" + System.lineSeparator() +
                        "T002;Mouse;199.0" + System.lineSeparator() +
                        "T003;Monitor;1999.99;Gaming monitor" + System.lineSeparator()
        );

        List<Product> products = storage.getProducts();

        assertEquals(2, products.size());
        assertEquals("T001", products.get(0).getArticleNumber());
        assertEquals("T003", products.get(1).getArticleNumber());
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