package com.resasoftware.nearme.services.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resasoftware.nearme.entities.Category;
import com.resasoftware.nearme.entities.Enterprise;
import com.resasoftware.nearme.repository.EnterpriseRepository;
import com.resasoftware.nearme.services.EnterpriseService;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Enterprise> findAll() throws Exception {
		// TODO Auto-generated method stub
		return enterpriseRepository.findAll();
	}
	
	@Transactional
	@Override
	public Enterprise save(Enterprise t) throws Exception {
		// TODO Auto-generated method stub
		return enterpriseRepository.save(t);
	}

	@Transactional
	@Override
	public Enterprise update(Enterprise t) throws Exception {
		// TODO Auto-generated method stub
		return enterpriseRepository.save(t);
	}
	
	@Transactional(readOnly = true)
	@Override
	public Optional<Enterprise> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return enterpriseRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		enterpriseRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		enterpriseRepository.deleteAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Enterprise> enterpriseByCategory(Category category) throws Exception {
		// TODO Auto-generated method stub
		return enterpriseRepository.enterpriseByCategory(category);
	}

}
