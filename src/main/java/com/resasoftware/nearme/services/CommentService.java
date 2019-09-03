package com.resasoftware.nearme.services;

import java.util.List;

import com.resasoftware.nearme.entities.Comment;

public interface CommentService extends CRUDService<Comment> {
	List<Comment> commentByEnterpriseId(Integer id) throws Exception;
}
