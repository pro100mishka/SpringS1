package app.dao;

import app.context.MainApp;
import app.entity.Product;
import app.entity.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class ProductDAO implements DAO {

    private Session session;

    private void init(){
        this.session = MainApp.mainApp.getSessionFactory().getCurrentSession();
    }

    @Override
    public void getAllList() {
        if (session == null || !session.isOpen()) init();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        criteriaQuery.from(Product.class);
        List<Product> temp = session.createQuery(criteriaQuery).list();
        for (Product p: temp) {
            System.out.println(p);
        }
        session.getTransaction().commit();
    }

    @Override
    public void getNestedList(long id) {
        if (session == null || !session.isOpen()) init();
        session.beginTransaction();
        Product temp = session.get(Product.class,id);
        if (temp == null) {
            System.out.println("нет сушности с таким id.");
            session.getTransaction().commit();
            return;
        }
        System.out.println("Список покупателей купивших товар: " + temp.toString());
        System.out.println("------------------------");
        if (temp.getUserList().isEmpty()){
            System.out.println("Список пуст.");
        } else {
            for (User u: temp.getUserList()) {
                System.out.println(u.toString());
            }
        }
        System.out.println("------------------------");
        session.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        if (session == null || !session.isOpen()) init();
        session.beginTransaction();
        Product temp = session.get(Product.class,id);
        if (temp == null) {
            System.out.println("нет сушности с таким id.");
            return;
        }
        session.delete(temp);
        session.getTransaction().commit();
    }

}
