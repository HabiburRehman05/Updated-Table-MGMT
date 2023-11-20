package com.ltim.joritz.tablemgmt.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="trackColumnVersion", schema="public" )
@IdClass(TrackColumnVersionId.class)
public class TrackColumnVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="column_id", nullable=false)
    private int        columnId ;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="table_version", nullable=false)
    private int        tableVersion ;

    //--- ENTITY DATA FIELDS 
    @Column(name="table_id")
    private Integer    tableId ;

    @Column(name="constraint_id")
    private Integer    constraintId ;

    @Column(name="column_name", length=50)
    private String     columnName ;

    @Column(name="datatype", length=50)
    private String     datatype ;

    @Column(name="created_by", length=2147483647)
    private String     createdBy ;

    @Column(name="created_on")
    private LocalDate  createdOn ;

    @Column(name="modified_by", length=2147483647)
    private String     modifiedBy ;

    @Column(name="modified_on")
    private LocalDate  modifiedOn ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="constraint_id", referencedColumnName="constraint_id", insertable=false, updatable=false)
    private UserConstraints userconstraints ; 

    @ManyToOne
    @JoinColumns( { 
        @JoinColumn(name="table_id", referencedColumnName="track_table_id", insertable=false, updatable=false),
        @JoinColumn(name="table_version", referencedColumnName="track_table_version", insertable=false, updatable=false)} )
    private TrackTableVersion tracktableversion ; 


    public TrackColumnVersion() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setColumnId( int columnId ) {
        this.columnId = columnId ;
    }
    public int getColumnId() {
        return this.columnId;
    }

    public void setTableVersion( int tableVersion ) {
        this.tableVersion = tableVersion ;
    }
    public int getTableVersion() {
        return this.tableVersion;
    }

    public void setTableId( Integer tableId ) {
        this.tableId = tableId ;
    }
    public Integer getTableId() {
        return this.tableId;
    }

    public void setConstraintId( Integer constraintId ) {
        this.constraintId = constraintId ;
    }
    public Integer getConstraintId() {
        return this.constraintId;
    }

    public void setColumnName( String columnName ) {
        this.columnName = columnName ;
    }
    public String getColumnName() {
        return this.columnName;
    }

    public void setDatatype( String datatype ) {
        this.datatype = datatype ;
    }
    public String getDatatype() {
        return this.datatype;
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
    public UserConstraints getUserconstraints() {
        return this.userconstraints;
    } 

    public TrackTableVersion getTracktableversion() {
        return this.tracktableversion;
    } 

    //--- toString specific method
	@Override
	public String toString() { 
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Trackcolumnversion[");
		sb.append("tableId=").append(tableId);
		sb.append(separator).append("columnId=").append(columnId);
		sb.append(separator).append("tableVersion=").append(tableVersion);
		sb.append(separator).append("constraintId=").append(constraintId);
		sb.append(separator).append("columnName=").append(columnName);
		sb.append(separator).append("datatype=").append(datatype);
		sb.append(separator).append("createdBy=").append(createdBy);
		sb.append(separator).append("createdOn=").append(createdOn);
		sb.append(separator).append("modifiedBy=").append(modifiedBy);
		sb.append(separator).append("modifiedOn=").append(modifiedOn);
		sb.append("]");
		return sb.toString();
	}
}
