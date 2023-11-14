package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.STable;

import jakarta.persistence.PersistenceContext;

import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
public interface TableInfoRepository {
	

    @Query(value = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'", nativeQuery = true)
    List<String> findAllTableNames();

    @Query(value = "SELECT column_name, data_type FROM information_schema.columns WHERE table_name = :tableName", nativeQuery = true)
    List<Object[]> findColumnsByTableName(@Param("tableName") String tableName);

}
