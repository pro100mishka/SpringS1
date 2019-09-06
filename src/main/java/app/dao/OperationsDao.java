package app.dao;

import app.context.MainApp;
import app.entity.Operation;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class OperationsDao {

    private Session session;

    private void init(){
        this.session = MainApp.mainApp.getSessionFactory().getCurrentSession();
    }

    public void getOperationsDetailsList(){
        if (session == null || !session.isOpen()) init();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = criteriaBuilder.createQuery(Operation.class);
        criteriaQuery.from(Operation.class);
        List<Operation> temp = session.createQuery(criteriaQuery).list();
        for (Operation o: temp) {
            System.out.println(o);
        }
        session.getTransaction().commit();
    }

}
