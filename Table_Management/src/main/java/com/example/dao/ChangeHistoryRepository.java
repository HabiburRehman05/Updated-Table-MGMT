package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ChangeHistory;

@Repository
public interface ChangeHistoryRepository extends JpaRepository<ChangeHistory, Long> {
    // Custom queries if needed
}
