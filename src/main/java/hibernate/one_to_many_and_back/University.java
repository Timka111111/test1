package hibernate.one_to_many_and_back;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "universities")
@NamedQueries({
        @NamedQuery(name = "withStudents", query = "SELECT u FROM University u JOIN FETCH u.students s WHERE s.name = :name")
        // как сюда и join fetch teachers добавить непонятно!
})
// при запросе универа пожалуйста обязательно подгрузи студентов это  JOIN FETCH помогает нам.
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "universityRefer") // когда слева many то тут по умолчанию ленивая загрузка fetch.LAZY
    private List<Student> students;
    //fetch.LAZY говорит на о том что он загрузит студентов по необходимости.
    // если вы обратитесь к students то в этот момент он подгрузить и студентов тоже.(но вы должны быть в рамках
    // одной транзакции, иначе если обратитесь к полу students не в той транзакции словите lazy initialization exception.
    // если LAZY не нравится можно поставить EAGER но так делать не рекомендуеется потому что он начнет вытаскивать все
    // на что ссылается таблица universities.

    @OneToMany(mappedBy = "university")
    private List<Teacher> teachers;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public University() {
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public University(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("University [id = %d, title = %s, students_count = %d, teachers_count = заглушка]", id, title, students.size());
    }
}
