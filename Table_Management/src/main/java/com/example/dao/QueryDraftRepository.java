package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.QueryDraft;

public interface QueryDraftRepository extends JpaRepository<QueryDraft, Long> {
    // You can add custom query methods here if needed
}

