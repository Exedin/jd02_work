package it.academy.service;

import it.academy.dao.DaoCountImpl;
import it.academy.model.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountService {

    @Autowired
    DaoCountImpl daoCount;

    @Transactional
    public Integer read() {
        Integer read = daoCount.read();
        return read;
    }

    @Transactional
    public Integer update() {
        Integer update = daoCount.update();
        return update;
    }
}
