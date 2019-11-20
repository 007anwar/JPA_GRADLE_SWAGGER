package com.example.JPAGRADLE;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.JPAGRADLE.pojo.Employee;
import com.example.JPAGRADLE.repo.EmpRepo;

@SpringBootTest
class JpaGradleSonarinitApplicationTests {
	@Autowired
EmpRepo rep;
	@Test
	void save()
	{
	Employee e = new Employee();
	e.setDesignation("das");
	e.setEmail("dssa@sad.com");
	e.setName("name");
	rep.save(e);
	}

}
