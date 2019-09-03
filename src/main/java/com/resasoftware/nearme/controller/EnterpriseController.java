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

import com.resasoftware.nearme.entities.Category;
import com.resasoftware.nearme.entities.Enterprise;
import com.resasoftware.nearme.services.EnterpriseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/enterprises")
@Api(tags ="Enterprises", value = "Enterprises", description = "Access to the Enterprises")
public class EnterpriseController {
	@Autowired
	private EnterpriseService enterpiseService;
	
	@ApiOperation("Return all enterprises")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Enterprise>> fetchEnterprises()
	{
		try {
			List<Enterprise> enterprises = new ArrayList<>();
			enterprises = enterpiseService.findAll();
			return new ResponseEntity<List<Enterprise>>(enterprises, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Enterprise>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Return enterprise by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Enterprise> fetchEnterprise(@PathVariable("id") Integer id)
	{
		try {
			Optional<Enterprise> enterprises = enterpiseService.findById(id);
			
			if(!enterprises.isPresent()){
				return new ResponseEntity<Enterprise>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Enterprise>(enterprises.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Enterprise>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Return enterprise by category")
	@GetMapping(value = "/search/{category}", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Enterprise>> fetchEnterpriseByCategory(@Valid @RequestBody Category category)
	{
		List<Enterprise> enterprises = new ArrayList<>();
		try {
			enterprises = enterpiseService.enterpriseByCategory(category);
			
			if(enterprises.isEmpty()){
				return new ResponseEntity<List<Enterprise>>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<List<Enterprise>>(enterprises, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Enterprise>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save enterprise")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveEnterprise(@Valid @RequestBody Enterprise category)
	{
		Enterprise saveEnterprises= new Enterprise();
		try {
			saveEnterprises = enterpiseService.save(category);
			
			if (saveEnterprises != null) {
				return new ResponseEntity<Object>(saveEnterprises, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(saveEnterprises, HttpStatus.INTERNAL_SERVER_ERROR);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Enterprise data update")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateEnterprise(@Valid @RequestBody Enterprise enterprise)
	{
		try {
			enterpiseService.update(enterprise);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete enterprise by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteEnterprise(@PathVariable("id") Integer id)
	{
		try {
			Optional<Enterprise> enterprise = enterpiseService.findById(id);
			
			if(!enterprise.isPresent()){
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}else{
				enterpiseService.deleteById(id);
				return new ResponseEntity<String>("The Enterprise has been deleted", HttpStatus.OK);			
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete all enterprise")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> allDeleteEnterprise()
	{
		try {
			enterpiseService.deleteAll();
			return new ResponseEntity<String>("All Enterprises have been deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
