package app.dao;

import app.context.MainApp;
import app.entity.Product;
import app.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDAO implements DAO {

    private Session session;

    private void init(){
        this.session = MainApp.mainApp.getSessionFactory().getCurrentSession();
    }

    @Override
    public void getAllList() {
        if (session == null || !session.isOpen()) init();
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            criteriaQuery.from(User.class);
            List<User> temp = session.createQuery(criteriaQuery).list();
            for (User u: temp) {
                System.out.println(u);
            }
            session.getTransaction().commit();
    }

    @Override
    public void getNestedList(long id) {
        if (session == null || !session.isOpen()) init();
        session.beginTransaction();
        User temp = session.get(User.class,id);
        System.out.println("Список продуктов купленных покупателем: " + temp.toString());
        System.out.println("------------------------");
        if (temp.getProductList().isEmpty()){
            System.out.println("Список пуст.");
        } else {
            for (Product p: temp.getProductList()) {
                System.out.println(p.toString());
            }
        }
        System.out.println("------------------------");
        session.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        if (session == null || !session.isOpen()) init();
        session.beginTransaction();
        User temp = session.get(User.class,id);
        if (temp == null) {
            System.out.println("нет сушности с таким id.");
            return;
        }
        session.delete(temp);
        session.getTransaction().commit();
    }
}
