package com.resasoftware.nearme.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resasoftware.nearme.entities.Type_User;
import com.resasoftware.nearme.repository.Type_UserRepository;
import com.resasoftware.nearme.services.Type_UserService;

@Service
public class Type_UserServiceImpl implements Type_UserService {

	@Autowired
	private Type_UserRepository type_userRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Type_User> findAll() throws Exception {
		// TODO Auto-generated method stub
		return type_userRepository.findAll();
	}

	@Transactional
	@Override
	public Type_User save(Type_User t) throws Exception {
		// TODO Auto-generated method stub
		return type_userRepository.save(t);
	}

	@Transactional
	@Override
	public Type_User update(Type_User t) throws Exception {
		// TODO Auto-generated method stub
		return type_userRepository.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Type_User> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return type_userRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		type_userRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		type_userRepository.deleteAll();
	}

}
