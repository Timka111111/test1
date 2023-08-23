package hibernate.crud;

import javax.persistence.*;

/**
 * Наличие аннотации @Entity;
 * Наличие поля, помеченного аннотацией @Id, в котором будет храниться уникальный идентификатор сущности;
 * Наличие конструктора без аргументов (конструктора по-умолчанию) с модификатором доступа protected или public. Допускается объявление перегруженных конструкторов;
 * Отсутствие модификатора final в объявлении класса;
 * Отсутствие final в полях, ссылающихся на другие сущности, и в их геттерах;
 * Класс сущность должен быть верхнеуровневым классом. Перечисления и интерфейсы не могут быть сущностями;
 * Сущностями могут являться как обычные классы, так и абстрактные;
 * Доступ к полям сущности должен осуществляться через геттеры/сеттеры, сами поля не должны быть public;
 * id bigserial PRIMARY KEY. bigserial говорит нам что это лонг но плюс он еще делает автоинкремент.
 */
@Entity
@Table(name = "simple_items")
@NamedQueries({ // Именованные запросы
        @NamedQuery(name = "SimpleItem.findAll", query = "SELECT s.title FROM SimpleItem s"),
        @NamedQuery(name = "SimpleItem.findByPrice", query = "SELECT a FROM SimpleItem a WHERE a.price = :price")
})
public class SimpleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // бд генерить id и мы от бд ее получаем
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Transient
    private String notInDBase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public SimpleItem() {
    }

    public SimpleItem(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public SimpleItem(String title, int price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("SimpleItem [id = %d, title = %s, price = %d]", id, title, price);
    }
}

