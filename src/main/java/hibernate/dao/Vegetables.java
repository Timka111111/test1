package hibernate.dao;

import javax.persistence.*;

@Entity
@Table(name = "vegetables")
public class Vegetables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

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

    public Vegetables() {
    }

    @Override
    public String toString() {
        return "Vegetables{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
