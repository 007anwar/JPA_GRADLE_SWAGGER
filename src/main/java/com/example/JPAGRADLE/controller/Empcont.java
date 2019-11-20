package com.example.JPAGRADLE.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.JPAGRADLE.pojo.Employee;
import com.example.JPAGRADLE.repo.EmpRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
//@SwaggerDefinition(tags = @Tag(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Persons.", name = "tag1"))
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Persons.")
@RestController("/emp")
public class Empcont {

	private static final Logger logger = LoggerFactory.getLogger(Empcont.class);
	@Autowired
	EmpRepo repo;
	private int id;

	@ApiOperation("Creates new user")
	@PostMapping(value = "/create")
	public ResponseEntity<?> create(@ApiParam("Create new employee") Employee emp) {

		String email = emp.getEmail();
		if (email == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			Employee save = repo.save(emp);
			id = save.getId();
			logger.info("Employee saved");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	
	
	@ApiOperation("read user details")
	@GetMapping(value = "/read")
	public String read(@ApiParam("Read employee details") Employee emp) {
		id = emp.getId();
		try {
			Optional<Employee> findById = repo.findById(id);
			Employee employee = findById.get();
			return employee.toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	
	
	@ApiOperation("update user")
	@PutMapping("/update")
	public ResponseEntity<?> update(@ApiParam("Update employee details") Employee emp) {
		Optional<Employee> empfromdb = repo.findById(emp.getId());
		Employee uuserdb = empfromdb.get();
		id = emp.getId();
		String email = emp.getEmail();
		String designation = emp.getDesignation();
		String name = emp.getName();
		if (name != null) {
			uuserdb.setName(name);
		}
		if (designation != null) {
			uuserdb.setDesignation(designation);
		}
		if (email != null) {
			uuserdb.setEmail(email);
		}
		repo.save(uuserdb);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	
	
	
	
	@ApiOperation("Delete user")
	@DeleteMapping(value = "/delete")
	public ResponseEntity<?> delete(@ApiParam Employee emp) {

		try {

			id = emp.getId();
			repo.delete(emp);
			logger.info("Employee deleted");
		} catch (Exception e) {
			logger.error("Error");
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);

	}

}
