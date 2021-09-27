package com.example.springdockertest.services;

import com.example.springdockertest.persistence.IOperations;
import com.example.springdockertest.persistence.model.Foo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFooService extends IOperations<Foo> {
    Page<Foo> findPaginated(Pageable pageable);

}
