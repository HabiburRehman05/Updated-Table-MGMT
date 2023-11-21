package com.ltim.joritz.tablemgmt.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="trackTableVersion", schema="public" )
@IdClass(TrackTableVersionId.class)
public class TrackTableVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="track_table_id", nullable=false)
    private int        trackTableId ;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="track_table_version", nullable=false)
    private int        trackTableVersion ;

    //--- ENTITY DATA FIELDS 
    @Column(name="table_name", length=50)
    private String     tableName ;

    @Column(name="table_desc", length=2147483647)
    private String     tableDesc ;

    @Column(name="created_by", length=50)
    private String     createdBy ;

    @Column(name="created_on")
    private LocalDate  createdOn ;

    @Column(name="modified_by ", length=2147483647)
    private String     modifiedBy ;

    @Column(name="modified_on")
    private LocalDate  modifiedOn ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="tracktableversion")
    private List<TrackColumnVersion> trackcolumnversionList ; 

    @OneToMany(mappedBy="tracktableversion")
    private List<UserTableCurrentVersion> usertablecurrentversionList ; 

    public TrackTableVersion() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setTrackTableId( int trackTableId ) {
        this.trackTableId = trackTableId ;
    }
    public int getTrackTableId() {
        return this.trackTableId;
    }

    public void setTrackTableVersion( int trackTableVersion ) {
        this.trackTableVersion = trackTableVersion ;
    }
    public int getTrackTableVersion() {
        return this.trackTableVersion;
    }

    public void setTableName( String tableName ) {
        this.tableName = tableName ;
    }
    public String getTableName() {
        return this.tableName;
    }

    public void setTableDesc( String tableDesc ) {
        this.tableDesc = tableDesc ;
    }
    public String getTableDesc() {
        return this.tableDesc;
    }

    public void setCreatedBy( String createdBy ) {
        this.createdBy = createdBy ;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedOn( LocalDate createdOn ) {
        this.createdOn = createdOn ;
    }
    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public void setModifiedBy( String modifiedBy ) {
        this.modifiedBy = modifiedBy ;
    }
    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedOn( LocalDate modifiedOn ) {
        this.modifiedOn = modifiedOn ;
    }
    public LocalDate getModifiedOn() {
        return this.modifiedOn;
    }

    //--- GETTERS FOR LINKS
    public List<TrackColumnVersion> getTrackcolumnversionList() {
        return this.trackcolumnversionList;
    } 

    public List<UserTableCurrentVersion> getUsertablecurrentversionList() {
        return this.usertablecurrentversionList;
    } 

    //--- toString specific method
	@Override
	public String toString() { 
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Tracktableversion[");
		sb.append("trackTableId=").append(trackTableId);
		sb.append(separator).append("trackTableVersion=").append(trackTableVersion);
		sb.append(separator).append("tableName=").append(tableName);
		sb.append(separator).append("tableDesc=").append(tableDesc);
		sb.append(separator).append("createdBy=").append(createdBy);
		sb.append(separator).append("createdOn=").append(createdOn);
		sb.append(separator).append("modifiedBy=").append(modifiedBy);
		sb.append(separator).append("modifiedOn=").append(modifiedOn);
		sb.append("]");
		return sb.toString();
	}
}
