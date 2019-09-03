package com.resasoftware.nearme.services;

import java.util.List;
import java.util.Optional;

public interface CRUDService <T>{
	List<T> findAll() throws Exception;
	
	T save(T t) throws Exception;
	
	T update (T t) throws Exception;
	
	Optional<T> findById(Integer id) throws Exception;
	
	void deleteById(Integer id) throws Exception;
	
	void deleteAll() throws Exception;
}
