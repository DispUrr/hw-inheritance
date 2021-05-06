import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Product book1 = new Book(1,"Чужак", 450, "Макс Фрай");
    private Product book2 = new Book(2,"Триннадцать лет пути", 405, "Кир Булычёв");
    private Product book3 = new Book(3,"Покушение на Тесея", 425, "Кир Булычёв");
    private Smartphone smartphone1 = new Smartphone(4, "Mi9 SE", 19500, "Xiaomi");
    private Smartphone smartphone2 = new Smartphone(5, "Express Music 5530", 3900, "Nokia");
    private Smartphone smartphone3 = new Smartphone(6, "Galaxy S20 Ultra", 45990, "Samsung");

    @BeforeEach
    public void manageAdd() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
    }

    @Test
    public void shouldSearchByBookName(){
        Product[] actual = manager.searchBy("Чужак");
        Product[] expected = new Product[] {book1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void  shouldSearchBySeveralBooksOneAuthor() {
        Product[] actual = manager.searchBy("Кир Булычёв");
        Product[] expected = new Product[] {book2, book3};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchBySmartphoneCreator() {
        Product[] actual = manager.searchBy("Nokia");
        Product[] expected = new Product[] {smartphone2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchBySmartphoneName() {
        Product[] actual = manager.searchBy("Mi9 SE");
        Product[] expected = new Product[] {smartphone1};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNonExistedProduct() {
        Product[] actual = manager.searchBy("Сергей Лукъяненко");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

}
