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

import com.resasoftware.nearme.entities.Type_User;
import com.resasoftware.nearme.services.Type_UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/type_users")
@Api(tags ="Type of users", value = "Type of Users", description = "Access to the Type of users")
public class Type_UserController {
	@Autowired
	private Type_UserService type_userService;
	
	@ApiOperation("Return all type_users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Type_User>> fetchType_users()
	{
		try {
			List<Type_User> type_users = new ArrayList<>();
			type_users = type_userService.findAll();
			return new ResponseEntity<List<Type_User>>(type_users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Type_User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Return type_user by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Type_User> fetchType_User(@PathVariable("id") Integer id)
	{
		try {
			Optional<Type_User> type_user = type_userService.findById(id);
			
			if(!type_user.isPresent()){
				return new ResponseEntity<Type_User>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Type_User>(type_user.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Type_User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save type_user")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveType_User(@Valid @RequestBody Type_User type_user)
	{
		Type_User saveType_User = new Type_User();
		try {
			saveType_User= type_userService.save(type_user);
			
			if(saveType_User != null) {
				return new ResponseEntity<Object>(saveType_User, HttpStatus.OK);
			}else {
				return new ResponseEntity<Object>(saveType_User, HttpStatus.INTERNAL_SERVER_ERROR);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Type_user data update")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateType_User(@Valid @RequestBody Type_User type_user)
	{
		try {
			type_userService.update(type_user);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete type_user by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteType_User(@PathVariable("id") Integer id)
	{
		try {
			Optional<Type_User> type_user = type_userService.findById(id);
			
			if(!type_user.isPresent()){
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}else{
				type_userService.deleteById(id);
				return new ResponseEntity<String>("The type_user has been deleted", HttpStatus.OK);			
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete all type_users")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> allDeleteType_Users()
	{
		try {
			type_userService.deleteAll();
			return new ResponseEntity<String>("All type_users have been deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
