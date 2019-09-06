package app.context;

import app.dao.DAO;
import app.dao.OperationsDao;
import app.dao.ProductDAO;
import app.dao.UserDAO;
import app.service.OperationService;
import app.service.UserService;
import lombok.Data;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import app.service.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@Data
public class MainApp {

    public static MainApp mainApp = new MainApp();

    private SessionFactory sessionFactory;
    private BufferedReader inputReader;
    private ProductService productService;
    private UserService userService;
    private OperationService operationService;
    private DAO productDAO;
    private DAO userDAO;
    private OperationsDao operationsDao;

    private MainApp() {
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
        this.productService = new ProductService();
        this.userService = new UserService();
        this.operationService = new OperationService();
        this.productDAO = new ProductDAO();
        this.userDAO = new UserDAO();
        this.operationsDao = new OperationsDao();
    }

    public void shutdown(){
        try {
            inputReader.close();
            sessionFactory.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
