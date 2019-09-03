package com.resasoftware.nearme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resasoftware.nearme.entities.Comment;
import com.resasoftware.nearme.entities.Enterprise;

@Repository
public interface CommetRepository extends JpaRepository<Comment, Integer>{
	List<Comment> commentByEnterpriseId(Integer id);
}
