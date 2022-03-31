package services;

import entities.base.BaseEntity;
import exceptions.NoSuchUser;
import lombok.RequiredArgsConstructor;
import repositories.BasicCrud;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseServices <T extends BaseEntity,R extends BasicCrud<T>> {

    protected final R repo;

    public T add(T t) {

        return repo.add(t);
    }



    public T remove(T t,int id,Class<T> tClass) {

        if (!checkId(id,tClass)){
            throw new NoSuchUser();
        }
        return repo.remove(t);
    }



    public void update(T t,int id,Class<T> tClass) {

        if (!checkId(id,tClass)){
            throw new NoSuchUser();
        }
        repo.update(t);
    }




    public T showInfo(int id,Class<T> tClass) {

        if (!checkId(id,tClass)){
            throw new NoSuchUser();
        }
        return repo.showInfo(id, tClass);
    }


    public List<T> showAll(Class<T> tClass) {
        return repo.showAll(tClass);
    }





    public boolean checkId(int Id,Class<T> tClass){

        boolean flag = false;
        List<T> tlist = showAll(tClass);

        for (T t: tlist) {
            if (t.getId()==Id) {
                flag = true;
                break;
            }
        }
        return flag;
    }



}
