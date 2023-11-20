package com.ltim.joritz.tablemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltim.joritz.tablemgmt.model.TrackColumnVersion;
import com.ltim.joritz.tablemgmt.model.TrackColumnVersionId;

@Repository
public interface TrackColumnVersionRepository extends JpaRepository<TrackColumnVersion,TrackColumnVersionId>{

}
