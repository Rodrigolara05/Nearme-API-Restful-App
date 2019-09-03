package com.resasoftware.nearme.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.resasoftware.nearme.entities.Comment;
import com.resasoftware.nearme.repository.CommetRepository;
import com.resasoftware.nearme.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommetRepository comentaryRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Comment> findAll() throws Exception{
		return comentaryRepository.findAll();
	}

	@Transactional
	@Override
	public Comment save(Comment t) throws Exception{
		// TODO Auto-generated method stub
		return comentaryRepository.save(t);
	}

	@Transactional
	@Override
	public Comment update(Comment t) throws Exception{
		// TODO Auto-generated method stub
		return comentaryRepository.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Comment> findById(Integer id) throws Exception{
		// TODO Auto-generated method stub
		return comentaryRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception{
		// TODO Auto-generated method stub
		comentaryRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception{
		// TODO Auto-generated method stub
		comentaryRepository.deleteAll();
	}

	@Override
	public List<Comment> commentByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return comentaryRepository.commentByEnterpriseId(id);
	}
}
