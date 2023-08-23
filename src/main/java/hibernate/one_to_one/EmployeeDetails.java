package hibernate.one_to_one;

import javax.persistence.*;

@Entity
@Table(name = "employees_details")
public class EmployeeDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    // Если в этом классе убрать поле employee, то получится однонаправленная связь: сотрудник
    // сможет ссылаться на свои детали, а детали нет.
    // В данном же случае прописана двунаправленная связь
    @OneToOne(mappedBy = "details") //с деталей можно ссылаться обратно на сотрудника
    private Employee employee; // в таблице employees_details нету стоблца который указал бы на сотрудника НО
    // я знаю что в классе Employee есть ссылка на меня и эта ссылка называется details
    // я могу конечно это не писать
    // @OneToOne(mappedBy = "details")
    //    private Employee employee;
    //    но тогда эта связь будет одна стороняя. Я из сотрудника попадаю на детали а из детали в сотрудника уже
    // не получится если не писать mappedBy. А если есть то я могу как из сотрудника в детали так из детали в сотрудника.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeDetails() {
    }

    @Override
    public String toString() {
        return String.format("EmployeeDetails [id = %d, email = %s, city = %s, employee.name = %s]", id, email, city, employee.getName());
    }
}
