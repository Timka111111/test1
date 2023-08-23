package hibernate.crud;

import com.timka.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * SELECT a FROM Article a WHERE a.id = 2
 *
 * В данном случае псевдоним a используется для доступа к атрибутам класса.
 * Возвращать можно не только объекты, но и атрибуты класса. Запрос, возвращающий атрибуты объекта, может выглядеть следующим образом:
 * SELECT a.firstname, a.lastname FROM Author a
 *
 * Если используется привязка параметров, запрос может быть таким:
 * SELECT a.firstname, a.lastname FROM Author a WHERE a.id = ?1
 *
 * В случае именованных параметров:
 * SELECT a.firstname, a.lastname FROM Author a WHERE a.id = :id
 *
 * Можно указать поставщику постоянства, что необходимо создать объекты из возвращаемых из БД значений. Например:
 * SELECT NEW com.geekbrains.Person(a.firstname, a.lastname) FROM Author a
 *
 * Класс Person не обязан являться сущностью, но должен содержать конструктор с указанной в запросе сигнатурой.
 */


public class CrudApp {
    private static SessionFactory factory; // это фабрика которая позволяет вам создавать единицы работы с базами данных
    // вся работа ведется в рамках сессии, то есть вы захотели послать запрос в базу то вы говорите я на уровне
    // хибернейта создаю сессию я поработал с бд, по посылал туда запросы, по получал ответы я эту сессию закрываю.
    // и вот чтобы вам вот эти единицы работы, эти сессии создавать вам нужна некая фабрика.

    public static void init() {
        PrepareDataApp.forcePrepareData(); // читаем там full.sql сохраняем это в базе и запускаем(но если в бд уже таблица нужная с данными можно это не выполнять)
        factory = new Configuration().configure("configs/crud/hibernate.cfg.xml").buildSessionFactory();
        // эту фабрику конфигируем по файлу. SessionFactory это громоздкий обьект поэтому никогда не пытайтесь
        // под каждый запрос создавать фабрику и потом тут уже ее закрывать. Производительно упадет до 0.
        // SessionFactory он как правило одна фабрика открывается на все приложение и работает до тех пор пока вы
        // приложение не остановите. Сессии может быть много и параллельные тоже но фабрика всегда одна.

    }

    public static void main(String[] args) {
        try {
            init();
           saveOrUpdate();
           showManyItems();

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            shutdown();
        }
    }

    public static void saveOrUpdate() {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            SimpleItem simpleItem = new SimpleItem(1L, "Book5", 111); // если укажем существующий айдишник обьект в бд новому бд то он заменить его на новый
            //SimpleItem simpleItem = session.get(SimpleItem.class, 1L);
           // simpleItem.setPrice(333);
            session.saveOrUpdate(simpleItem);
            //новый продукт для хибернейта это тот у которого отсутвует id. Обьект отсутвубщий в бд
            // должен иметь id null. Допустим хочу сохранить молоко то айди я указывать не буду так
            // как не знаю что там в бд творится какие там айдишники. Хибернейт за меня присвоить ему айдишник и
            // сохранить в бд. Когда я вытаскиваю обьект из бд там сто процента будет айди.
            // Когда я сам создаю новый обьект и присваиваю ему айдишник и говорю хибернейт сохрани его
            // то хибернейт видет что у него есть айди значить он есть в бд и мне нужно его обновить но его в бд нету
            // так как мы сами добавили айди. И в этом случае хибернейт кинет исключение Row was updated or deleted by another transaction

            session.getTransaction().commit();
        }
    }

    public static void methodRefresh() {
        SimpleItem simpleItem = readAndPrintExample();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        System.out.println(simpleItem);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        session.refresh(simpleItem); //синхронизирует сущность obj c БД. Ее поля будут иметь те значения,
        // которые находились в столбцах строки БД на момент применения данного метода.

        System.out.println(simpleItem);
        session.getTransaction().commit();
    }

    public static void namedQueries() {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<String> titles = session.createNamedQuery("SimpleItem.findAll", String.class).getResultList();
            System.out.println(titles);
            SimpleItem simpleItem = session.createNamedQuery("SimpleItem.findByPrice", SimpleItem.class)
                    .setParameter("price", 500)
                    .getSingleResult();
            System.out.println(simpleItem);
            session.getTransaction().commit();
        }

    }

    public static void updateField(String name, Long id) {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
           session.createQuery("update SimpleItem s set s.title = :n where s.id = :id")
                    .setParameter("n", name).setParameter("id", id).executeUpdate();

            session.getTransaction().commit();

        }
    }

    public static void findByName(String name) {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Integer simpleItem =  session.createQuery("select price from SimpleItem i where title = :title", Integer.class)
                    .setParameter("title", name).getSingleResult();
            System.out.println(simpleItem);
            session.getTransaction().commit();

        }
    }

    public static SimpleItem readAndPrintExample() {


        // Session это очень легковесный обьек то есть под каждое малейшие действие с базой вы можете создавать свою сессию
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            SimpleItem simpleItem = session.get(SimpleItem.class, 1L); // говорим где искать SimpleItem.class ведь в том классе указана имя таблицы и еще говорим по какому айди искать

            session.getTransaction().commit();
            return simpleItem;
        }
    }


    public static void createExample() {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            SimpleItem simpleItem = new SimpleItem("Guitar",150);
            session.save(simpleItem);
            session.getTransaction().commit();
        }
    }

    public static void updateExample() {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            SimpleItem simpleItem = session.get(SimpleItem.class, 5L);
            // изменить id так нельзя так это primary key.

            // также если у вас таблице и так 300 и вы меняете на 300 то update не будет так как там и так уже 300
            simpleItem.setPrice(300);
            session.getTransaction().commit();
        }
    }

    public static void deleteExample() {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            SimpleItem simpleItem = session.get(SimpleItem.class, 8L);
            session.delete(simpleItem);
            session.getTransaction().commit();
        }
    }

    public static void doubleReadExample() {
        try (Session session = factory.getCurrentSession()) { // контекст открылся(контекст это сессия можно сказать)
            session.beginTransaction();
            SimpleItem simpleItem = session.find(SimpleItem.class, 1L);
            SimpleItem simpleItem2 = session.find(SimpleItem.class, 1L);
            SimpleItem simpleItem3 = session.find(SimpleItem.class, 1L);
            System.out.println(simpleItem);
            System.out.println(simpleItem2);
            System.out.println(simpleItem3);
            session.getTransaction().commit(); // контекст очистился и закрылся
            // тут свой контекст
            // тут будет выполнен только один select несмотря на то что 3 раз вызываем
        }

        try (Session session = factory.getCurrentSession()) { // контекст открылся
            session.beginTransaction();
            SimpleItem simpleItem = session.find(SimpleItem.class, 1L);
            SimpleItem simpleItem2 = session.find(SimpleItem.class, 1L);
            SimpleItem simpleItem3 = session.find(SimpleItem.class, 1L);
            System.out.println(simpleItem);
            System.out.println(simpleItem2);
            System.out.println(simpleItem3);
            session.getTransaction().commit(); // контекст очистился и закрылся
            // а тут свой контекст
            // тут будет выполнен только один select несмотря на то что 3 раз вызываем
        }


    }

    public static void testId() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            SimpleItem s = session.get(SimpleItem.class, 1L);
            s.setId(2L);
            session.getTransaction().commit();
        }
    }

    public static void fillData() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            for (int i = 0; i < 30; i++) {
                SimpleItem simpleItem = new SimpleItem(Long.valueOf(i), "Item " + i, 10 * i);
                session.save(simpleItem);
            }
            session.getTransaction().commit();
        }
    }

    public static void rollbackExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            SimpleItem dragonStatue = new SimpleItem("Dragon Statue", 100);
            System.out.println(dragonStatue);
            session.save(dragonStatue);
            System.out.println(dragonStatue);
            session.getTransaction().rollback();
            System.out.println(dragonStatue);
        }
    }

    public static void autoRollback() {
        Transaction transaction = null;
        try (Session session = factory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(new SimpleItem("A", 100));
            //transaction.commit();
            int z = 10 / 0;
            System.out.println(transaction.isActive());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(transaction.isActive());

    }


    public static void showManyItems() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

          //  List<SimpleItem> items = session.createQuery("from SimpleItem").getResultList(); // запросить список from SimpleItem это hql и это означает select * from simple_items на sql
            List<SimpleItem> items = session.createQuery("Select s from SimpleItem s").getResultList(); // не короткая запись
            // в hql мы как бы обращаемся к обьектам а в sql таблицам
            System.out.println(items + "\n");

//            session.createQuery("delete from SimpleItem s where id = :id")
//                    .setParameter("id", 1L)
//                    .executeUpdate();


//            List<String> titles = session.createQuery("select title from SimpleItem").getResultList();
//
//            System.out.println(titles + "\n");



//            SimpleItem si1 = session.createQuery("select s from SimpleItem s where s.id = 3", SimpleItem.class).getSingleResult();
//            System.out.println(si1 + "\n");
//
//            List<SimpleItem> cheapItems = session.createQuery("select s from SimpleItem s where s.price < 80").getResultList();
//            System.out.println(cheapItems + "\n");

            session.getTransaction().commit();
        }
    }

    public static void shutdown() {
        factory.close();
    }
}
