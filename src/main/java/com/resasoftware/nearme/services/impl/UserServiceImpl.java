package com.resasoftware.nearme.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resasoftware.nearme.entities.User;
import com.resasoftware.nearme.repository.UserRepository;
import com.resasoftware.nearme.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> findAll() throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User save(User t) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.save(t);
	}

	@Override
	public User update(User t) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.save(t);
	}

	@Override
	public Optional<User> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		userRepository.deleteAll();
	}

	@Override
	public User validUser(String username, String password) {
		Optional<User> user = userRepository.findByUsername(username);
		
		if (user.isPresent()) {
			 
			if (user.get().getPassword().equals(password))
				return user.get();
		}
		return null;
	}
}
