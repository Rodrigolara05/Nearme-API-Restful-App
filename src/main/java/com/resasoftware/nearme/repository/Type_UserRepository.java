package com.resasoftware.nearme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resasoftware.nearme.entities.Type_User;

@Repository
public interface Type_UserRepository extends JpaRepository<Type_User, Integer>{

} 
