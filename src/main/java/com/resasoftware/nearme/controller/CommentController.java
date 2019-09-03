package com.resasoftware.nearme.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resasoftware.nearme.entities.Comment;
import com.resasoftware.nearme.services.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comments")
@Api(tags ="Comments", value =  "Comments", description = "Access to the Comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@ApiOperation("Return all comment")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comment>> fetchComments()
	{
		try {
			List<Comment> comments = new ArrayList<>();
			comments = commentService.findAll();
			return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Comment>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Return comment by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Comment> fetchcomment(@PathVariable("id") Integer id)
	{
		try {
			Optional<Comment> comment = commentService.findById(id);
			
			if(!comment.isPresent()){
				return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Comment>(comment.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Return comment by enterprise")
	@GetMapping(value = "/search/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comment>> fetchcommentbyid(@PathVariable("id") Integer id)
	{
		List<Comment> comments = new ArrayList<>();
		
		try {
			comments = commentService.commentByEnterpriseId(id);
			
			if(comments.isEmpty()){
				return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Comment>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save comment")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> savecomment(@Valid @RequestBody Comment comment)
	{
		Comment savecomment = new Comment();
		try {
			savecomment = commentService.save(comment);
			
			if (savecomment != null) {
				return new ResponseEntity<Object>(savecomment, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(savecomment, HttpStatus.INTERNAL_SERVER_ERROR);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Comment data update")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatecomment(@Valid @RequestBody Comment comment)
	{
		try {
			commentService.update(comment);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete comment for id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletecomment(@PathVariable("id") Integer id)
	{
		try {
			Optional<Comment> comment = commentService.findById(id);
			
			if(!comment.isPresent()){
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}else{
				commentService.deleteById(id);
				return new ResponseEntity<String>("The comment has been deleted", HttpStatus.OK);			
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete all comment")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> allDeleteComments()
	{
		try {
			commentService.deleteAll();
			return new ResponseEntity<String>("All comments have been deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
