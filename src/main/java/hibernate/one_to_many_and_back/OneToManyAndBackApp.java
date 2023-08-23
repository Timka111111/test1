package hibernate.one_to_many_and_back;

import com.timka.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyAndBackApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/one_to_many_and_back/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            University universityFromNamedQuery = session
                    .createNamedQuery("withStudents", University.class)
                    .setParameter("name", "Bob")
                    .getSingleResult();
            // System.out.println(universityFromNamedQuery);
            session.getTransaction().commit();
            System.out.println(universityFromNamedQuery);


//            session.beginTransaction();
//            University university = session.get(University.class, 1L);
//            session.getTransaction().commit();
//            System.out.println(university);
            // System.out.println(university); если тут комент уберу то словлю lazy initialization exception.
            // потому в рамки транзакции я не обратился к полю students и он не загрузился. в ToString у меня students.size() идет обращение
//          поэтому для начала если нужно работать со students то в транзакции надо его подрузить
            // и чтобы не обращаться ради этоого в транзакции к полям которые нужно догрузить можно использовать JOIN FETCH
//            session.getTransaction().commit();

            session.beginTransaction();
            University university = session.get(University.class, 2L);
            Student alexey = new Student("Alexey", university);
            session.save(alexey);



            session.getTransaction().commit();

//            session.beginTransaction();
//            University university = session.get(University.class, 2L);
//            System.out.println(university.getStudents());
//            session.getTransaction().commit();

//            session.beginTransaction();
//            University universityFromNamedQuery = session
//                    .createNamedQuery("withStudents", University.class)
//                    .setParameter("id", 1L)
//                    .getSingleResult();
//
//            Student s = session.get(Student.class, 1L);
//            session.getTransaction().commit();
//           System.out.println(s.getUniversityRefer().getTitle());
//           System.out.println(universityFromNamedQuery);
//            System.out.println(universityFromNamedQuery.getStudents());

//            session.beginTransaction();
//            University university = session.get(University.class, 1L);
//            System.out.println(university.getStudents());
//            System.out.println("======");
//            Student student = session.get(Student.class, 2L);
//            System.out.println(student.getUniversityRefer());
//            System.out.println("======");
//            student.setUniversityRefer(university);
//            session.saveOrUpdate(student);
//            University university1 = session.get(University.class, 1L);
//
//
//            session.getTransaction().commit();
//            System.out.println(university1.getStudents());
//            System.out.println("======");
//            System.out.println(student.getUniversityRefer());


        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
