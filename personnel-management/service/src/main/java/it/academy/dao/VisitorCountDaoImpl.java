package it.academy.dao;

import it.academy.model.VisitorCount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class VisitorCountDaoImpl implements VisitorCountDao {

    private SessionFactory sessionFactory;

    @Autowired
    public VisitorCountDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional (propagation = Propagation.SUPPORTS)
    public VisitorCount readCount(int id) {
        final Session currentSession = sessionFactory.getCurrentSession();
        VisitorCount visitorCount =
                currentSession.get(VisitorCount.class, id);
        return visitorCount;
    }

    @Override
    @Transactional (propagation = Propagation.SUPPORTS)
    public int updateCount(VisitorCount visitorCount) {
        sessionFactory.getCurrentSession().saveOrUpdate(visitorCount);
        return visitorCount.getCount();
    }
}
