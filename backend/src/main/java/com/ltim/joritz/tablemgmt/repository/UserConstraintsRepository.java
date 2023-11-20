package com.ltim.joritz.tablemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltim.joritz.tablemgmt.model.UserConstraints;

@Repository
public interface UserConstraintsRepository extends JpaRepository<UserConstraints,Integer>{

}
