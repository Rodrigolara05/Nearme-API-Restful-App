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

import com.resasoftware.nearme.entities.User;
import com.resasoftware.nearme.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(tags ="Users", value = "Users", description = "Access to the Users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation("Return all users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> fetchUsers()
	{
		try {
			List<User> users = new ArrayList<>();
			users = userService.findAll();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Return user by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> fetchUser(@PathVariable("id") Integer id)
	{
		try {
			Optional<User> user = userService.findById(id);
			
			if(!user.isPresent()){
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<User>(user.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Valid user")
	@GetMapping(value = "/search/{username}/{password}")
	public ResponseEntity<User> fetchUser(@PathVariable("username") String username, @PathVariable("password") String password)
	{
		try {
			User validUser = userService.validUser(username, password);
			
			if(validUser != null){
				return new ResponseEntity<User>(validUser, HttpStatus.OK);
			}else {
				return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save user")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user)
	{
		User saveUser = new User();
		
		try {
			saveUser = userService.save(user);

			if (saveUser != null) {
				return new ResponseEntity<Object>(saveUser, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(saveUser, HttpStatus.INTERNAL_SERVER_ERROR);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("User data update")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateUser(@Valid @RequestBody User user)
	{
		try {
			userService.update(user);
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Error: Put method", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete user by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id)
	{
		try {
			Optional<User> category = userService.findById(id);
			
			if(!category.isPresent()){
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}else{
				userService.deleteById(id);
				return new ResponseEntity<String>("The user has been deleted", HttpStatus.OK);			
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete all users")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> allDeleteUsers()
	{
		try {
			userService.deleteAll();
			return new ResponseEntity<String>("All users have been deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
