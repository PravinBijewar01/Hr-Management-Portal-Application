package com.hr.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.entity.Compose;

@Repository
public interface ComposeRepository extends JpaRepository<Compose, Integer>{
	
	public List<Compose> findByParentUkid(Integer parentUkid);
	

	    // 🔥 Status wise count
	    long countByStatus(String status);

	    // 🔥 Recent activity (latest 5)
	    List<Compose> findTop5ByOrderByCreatedDateDesc();

	
}
