package it.academy.service;

import it.academy.dao.DaoCountImpl;
import it.academy.model.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;

@Service
public class CountService {

    @Autowired
    DaoCountImpl daoCount;

    @Transactional(readOnly = true)
    public Integer read() {
        Integer read = daoCount.read();
        return read;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Integer update() {
        Integer update = daoCount.update();
        return update;
    }
}
