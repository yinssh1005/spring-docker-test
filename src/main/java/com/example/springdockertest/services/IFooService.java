package com.example.springdockertest.services;

import com.example.springdockertest.persistence.IOperations;
import com.example.springdockertest.persistence.model.FooEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFooService extends IOperations<FooEntity> {
    Page<FooEntity> findPaginated(Pageable pageable);

}
