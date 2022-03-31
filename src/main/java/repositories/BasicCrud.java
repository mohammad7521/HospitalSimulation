package repositories;

import entities.base.BaseEntity;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public interface BasicCrud<T>{

    public T add(T t);


    public T remove(T t);


    public void update(T t);

    //based on id
    public T showInfo(int ID, Class<T> tClass);



    public int hqlTruncate (String tableName);



    public List<T> showAll(Class<T> tClass);
}
