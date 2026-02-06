package com.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.entity.CreatePost;
import com.hr.entity.Employee;
import com.hr.repository.CreatePostRepository;
import com.hr.repository.EmployeeRepository;

@Service
public class HrService {

	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private CreatePostRepository createPostRepository;
	
	public Employee addEmployee(Employee employee) {	
	Employee save=employeeRepo.save(employee);
		return save;
	}
	
	public List<Employee> getAllEmployee(){
		List<Employee>	findAll=employeeRepo.findAll();
		return findAll;
	}
	
	public CreatePost addPost(CreatePost createPost) {
		CreatePost save=createPostRepository.save(createPost);
		return save;
	}
}
