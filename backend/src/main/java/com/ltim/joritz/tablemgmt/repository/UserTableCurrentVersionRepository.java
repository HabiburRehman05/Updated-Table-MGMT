package com.ltim.joritz.tablemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltim.joritz.tablemgmt.model.UserTableCurrentVersion;
import com.ltim.joritz.tablemgmt.model.UserTableCurrentVersionId;

@Repository
public interface UserTableCurrentVersionRepository extends JpaRepository<UserTableCurrentVersion,UserTableCurrentVersionId>{

}
