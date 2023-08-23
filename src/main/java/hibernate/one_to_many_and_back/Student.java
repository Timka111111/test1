package hibernate.one_to_many_and_back;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "university_id")  // когда слева one то тут по умолчанию fetch.EAGER
    private University universityRefer;
    // можно поставить на LAZY если считаете что обьект тяжелый и загрузить надо будет если я буду обрашаться к полю universityRefer

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public University getUniversityRefer() {
        return universityRefer;
    }

    public void setUniversityRefer(University universityRefer) {
        this.universityRefer = universityRefer;
    }

    public Student() {
    }



    public Student(String name, University universityRefer) {
        this.name = name;
        this.universityRefer = universityRefer;
    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Student [id = %d, name = %s]", id, name);
    }
}
