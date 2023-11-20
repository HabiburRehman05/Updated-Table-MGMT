package com.ltim.joritz.tablemgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltim.joritz.tablemgmt.model.TrackTableVersion;
import com.ltim.joritz.tablemgmt.model.TrackTableVersionId;

@Repository
public interface TrackTableVersionRepository extends JpaRepository<TrackTableVersion,TrackTableVersionId>{

}
