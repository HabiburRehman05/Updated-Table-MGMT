package com.example.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.STable;

@Repository
public interface TableRepo extends JpaRepository<STable, Integer> {
	
}
