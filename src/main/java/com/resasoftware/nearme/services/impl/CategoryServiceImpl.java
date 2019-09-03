package com.resasoftware.nearme.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resasoftware.nearme.entities.Category;
import com.resasoftware.nearme.repository.CategoryRepository;
import com.resasoftware.nearme.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Category> findAll() throws Exception {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Transactional
	@Override
	public Category save(Category t) throws Exception {
		// TODO Auto-generated method stub
		return categoryRepository.save(t);
	}

	@Transactional
	@Override
	public Category update(Category t) throws Exception {
		// TODO Auto-generated method stub
		return categoryRepository.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Category> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		categoryRepository.deleteAll();
	}
}
