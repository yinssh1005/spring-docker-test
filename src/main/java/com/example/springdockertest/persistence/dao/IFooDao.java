package com.example.springdockertest.persistence.dao;

import com.example.springdockertest.persistence.model.FooEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFooDao extends JpaRepository<FooEntity, Long> {


}
