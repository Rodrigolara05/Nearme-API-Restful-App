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
import com.resasoftware.nearme.services.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categories")
@Api(tags = "Categories", value =  "Categories", description = "Access to the Categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@ApiOperation("Return all categories")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Category>> fetchCategories()
	{
		try {
			List<Category> categories = new ArrayList<>();
			categories = categoryService.findAll();
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Category>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Return category by id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> fetchCategory(@PathVariable("id") Integer id)
	{
		try {
			Optional<Category> category = categoryService.findById(id);
			
			if(!category.isPresent()){
				return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<Category>(category.get(), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Save category")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveCategory(@Valid @RequestBody Category category)
	{
		Category saveCategory = new Category();
		try {
			saveCategory = categoryService.save(category);
			
			if (saveCategory != null) {
				return new ResponseEntity<Object>(saveCategory, HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(saveCategory, HttpStatus.INTERNAL_SERVER_ERROR);
			}
					
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Category data update")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCategory(@Valid @RequestBody Category category)
	{
		try {
			categoryService.update(category);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete category by id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id)
	{
		try {
			Optional<Category> category = categoryService.findById(id);
			
			if(!category.isPresent()){
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}else{
				categoryService.deleteById(id);
				return new ResponseEntity<String>("The category has been deleted", HttpStatus.OK);			
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Delete all Categories")
	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> allDeleteCategories()
	{
		try {
			categoryService.deleteAll();
			return new ResponseEntity<String>("All categories have been deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
