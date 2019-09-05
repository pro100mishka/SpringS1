package context;

import dao.DAO;
import lombok.Data;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Data
public class MainApp {

    public static MainApp mainApp = new MainApp();

    private SessionFactory sessionFactory;
    private BufferedReader inputReader;
    private ProductService productService;
    private DAO DAO;

    private MainApp() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
        this.productService = new ProductService();
        this.DAO = new DAO();
    }

    public void shutdown(){
        try {
            inputReader.close();
            sessionFactory.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    public static void main(String[] args) {
//        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//        Session session = factory.getCurrentSession();
//        try {
//            session.beginTransaction();
//            session.save(new Product().setName("Apple").setCost(79));
//            session.save(new Product().setName("Butter").setCost(79));
//            session.save(new Product().setName("Nut").setCost(175));
//            session.save(new Product().setName("Water").setCost(12));
//            session.save(new Product().setName("Blaster").setCost(300));
//            session.save(new User().setName("pro100gnomik"));
//            session.getTransaction().commit();
//        } finally {
//            factory.close();
//            session.close();
//        }
//        try {
//            new MainMenu(null).start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
//