package hibernate.one_to_one;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "details_id") // это поле в таблице employees это foreign key
    private EmployeeDetails details; // тут указываем класс

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public EmployeeDetails getDetails() {
        return details;
    }

    public void setDetails(EmployeeDetails details) {
        this.details = details;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return String.format("Employee [id = %d, name = %s]", id, name);
    }
}
