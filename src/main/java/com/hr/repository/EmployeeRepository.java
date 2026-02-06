package com.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Employee findByIdAndPassword(int empId,String password);
	//public Employee findByIdAndPasswordAndRole(int empId,String password);
	

    // 🔥 Dashboard counts
    long countByDepartment(String department);
}
