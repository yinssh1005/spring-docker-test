package com.example.springdockertest.services;

import com.example.springdockertest.persistence.dao.IFooDao;
import com.example.springdockertest.persistence.model.Foo;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class FooServiceImpl extends AbstractService<Foo> implements IFooService {

    @Autowired
    private IFooDao dao;

    public FooServiceImpl() {
        super();
    }

    @Override
    protected PagingAndSortingRepository<Foo, Long> getDao() {
        return dao;
    }

    @Override
    public Page<Foo> findPaginated(Pageable pageable) {
        return dao.findAll(pageable);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Foo> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }
}
