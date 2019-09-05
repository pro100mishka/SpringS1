package dao;

import context.MainApp;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class DAO <T> {

    private Session session;

    private void init(){
        this.session = MainApp.mainApp.getSessionFactory().getCurrentSession();
    }

    public List<T> getList(Class<T> tClass){
        init();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(tClass);
        criteriaQuery.from(tClass);
        List<T> temp = session.createQuery(criteriaQuery).list();
        session.getTransaction().commit();
        return  temp;
    }

}
