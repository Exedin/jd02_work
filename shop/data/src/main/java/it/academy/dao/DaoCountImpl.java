package it.academy.dao;

import it.academy.model.Count;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.io.Serializable;

@Repository
public class DaoCountImpl implements DaoCount{
    

    SessionFactory sessionFactory;

    @Autowired
    public DaoCountImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer read (){
        Count count = sessionFactory.getCurrentSession().get(Count.class, 1);
//        if (count==null){
//            Count newCount= new Count(1,1);
//            sessionFactory.getCurrentSession().save(newCount);
//            return newCount.getCount();
//        }
        return count==null ? 0 : count.getCount();
//        return count.getCount();
    }

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Integer update (){
        Count count = sessionFactory.getCurrentSession().get(Count.class, 1);
//        sessionFactory.getCurrentSession().lock(count, LockMode.PESSIMISTIC_WRITE);
        if (count==null){
            Count newCount= new Count(1,1);
            sessionFactory.getCurrentSession().save(newCount);
            return newCount.getCount();
        }
        int i = count.getCount();
        count.setCount(++i);
        sessionFactory.getCurrentSession().update(count);

        return  i;
    }


}
