package com.ltim.joritz.tablemgmt.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="userConstraints", schema="public" )
public class UserConstraints implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="constraint_id", nullable=false)
    private int constraintId ;

    //--- ENTITY DATA FIELDS 
    @Column(name="isCheck")
    private Boolean ischeck ;

    @Column(name="isPrimaryKey")
    private Boolean isprimarykey ;

    @Column(name="isForeignKey")
    private Boolean isforeignkey ;

    @Column(name="isNotNull")
    private Boolean isnotnull ;

    @Column(name="isUnique")
    private Boolean isunique ;

    @Column(name="relation_type", length=50)
    private String relationType ;

    @Column(name="relation_table_id")
    private Integer relationTableId ;

    @Column(name="table_version")
    private Integer    tableVersion ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="userconstraints")
    private List<TrackColumnVersion> trackcolumnversionList ; 

    @ManyToOne
    @JoinColumns( { 
        @JoinColumn(name="relation_table_id", referencedColumnName="current_table_id", insertable=false, updatable=false),
        @JoinColumn(name="table_version", referencedColumnName="current_table_version", insertable=false, updatable=false)} )
    private UserTableCurrentVersion usertablecurrentversion ; 

    public UserConstraints() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setConstraintId( int constraintId ) {
        this.constraintId = constraintId ;
    }
    public int getConstraintId() {
        return this.constraintId;
    }

    public void setIscheck( Boolean ischeck ) {
        this.ischeck = ischeck ;
    }
    public Boolean getIscheck() {
        return this.ischeck;
    }

    public void setIsprimarykey( Boolean isprimarykey ) {
        this.isprimarykey = isprimarykey ;
    }
    public Boolean getIsprimarykey() {
        return this.isprimarykey;
    }

    public void setIsforeignkey( Boolean isforeignkey ) {
        this.isforeignkey = isforeignkey ;
    }
    public Boolean getIsforeignkey() {
        return this.isforeignkey;
    }

    public void setIsnotnull( Boolean isnotnull ) {
        this.isnotnull = isnotnull ;
    }
    public Boolean getIsnotnull() {
        return this.isnotnull;
    }

    public void setIsunique( Boolean isunique ) {
        this.isunique = isunique ;
    }
    public Boolean getIsunique() {
        return this.isunique;
    }

    public void setRelationType( String relationType ) {
        this.relationType = relationType ;
    }
    public String getRelationType() {
        return this.relationType;
    }

    public void setRelationTableId( Integer relationTableId ) {
        this.relationTableId = relationTableId ;
    }
    public Integer getRelationTableId() {
        return this.relationTableId;
    }

    public void setTableVersion( Integer tableVersion ) {
        this.tableVersion = tableVersion ;
    }
    public Integer getTableVersion() {
        return this.tableVersion;
    }

    //--- GETTERS FOR LINKS
    public List<TrackColumnVersion> getTrackcolumnversionList() {
        return this.trackcolumnversionList;
    } 

    public UserTableCurrentVersion getUsertablecurrentversion() {
        return this.usertablecurrentversion;
    } 

    //--- toString specific method
	@Override
	public String toString() { 
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Userconstraints[");
		sb.append("constraintId=").append(constraintId);
		sb.append(separator).append("ischeck=").append(ischeck);
		sb.append(separator).append("isprimarykey=").append(isprimarykey);
		sb.append(separator).append("isforeignkey=").append(isforeignkey);
		sb.append(separator).append("isnotnull=").append(isnotnull);
		sb.append(separator).append("isunique=").append(isunique);
		sb.append(separator).append("relationType=").append(relationType);
		sb.append(separator).append("relationTableId=").append(relationTableId);
		sb.append(separator).append("tableVersion=").append(tableVersion);
		sb.append("]");
		return sb.toString();
	}
}
