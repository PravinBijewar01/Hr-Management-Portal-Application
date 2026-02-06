package com.hr.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hr.entity.CreatePost;
import com.hr.entity.Employee;
import com.hr.repository.CreatePostRepository;
import com.hr.repository.EmployeeRepository;

public class HrServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private CreatePostRepository CreatePostRepository;
	
	@InjectMocks
	private HrService hrService;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testAddEmployee() {
		Employee employee=new Employee();
		employee.setId(1);
		employee.setEmployeeName("Pravin Bijewar");
		employee.setEmail("Pravin@gmai.com");
		
		
		when(employeeRepository.save(employee)).thenReturn(employee);
		
		Employee saveEmployee = hrService.addEmployee(employee);
		
		verify(employeeRepository).save(employee);
		
		assertEquals(employee,saveEmployee);		
	}
	
	
	@Test
	void testGetAllEmployee() {
		List<Employee> employeeList=new ArrayList<>();
		
		Employee employee1=new Employee();
		employee1.setId(1);
		employee1.setEmployeeName("Pravin Bijewar");
		employee1.setEmail("Pravin@gmai.com");
		
		
		Employee employee2=new Employee();
		employee2.setId(2);
		employee2.setEmployeeName("Kajal Bijewar");
		employee2.setEmail("Kajaln@gmai.com");
		
		employeeList.add(employee1);
		employeeList.add(employee2);
		
		when(employeeRepository.findAll()).thenReturn(employeeList);
		
		
		List<Employee> result=hrService.getAllEmployee();
		
		assertEquals(employeeList, result);		
	}
	
	
	@Test
	void testAddPost() {
		
		CreatePost createPost=new CreatePost();
		createPost.setId(1);
		createPost.setTitle("TTTT");
		
		when(CreatePostRepository.save(createPost)).thenReturn(createPost);
		
		CreatePost saveCreatePost=hrService.addPost(createPost);
		
		verify(CreatePostRepository).save(createPost);
		
		assertEquals(createPost, saveCreatePost);
		
	}
	
}
