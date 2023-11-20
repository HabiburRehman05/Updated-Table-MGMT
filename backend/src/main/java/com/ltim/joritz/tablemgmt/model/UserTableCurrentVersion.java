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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="userTableCurrentVersion", schema="public" )
@IdClass(UserTableCurrentVersionId.class)
public class UserTableCurrentVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="current_table_id", nullable=false)
    private int currentTableId ;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="current_table_version", nullable=false)
    private int currentTableVersion ;

    //--- ENTITY DATA FIELDS 
    @Column(name="created_by", length=50)
    private String createdBy ;

    @Column(name="created_on")
    private LocalDate createdOn ;

    @Column(name="modified_by", length=50)
    private String modifiedBy ;

    @Column(name="modified_on")
    private LocalDate modifiedOn ;


//    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="usertablecurrentversion")
    private List<UserConstraints> userconstraintsList ; 

    @ManyToOne
    @JoinColumns( { 
        @JoinColumn(name="current_table_id", referencedColumnName="track_table_id", insertable=false, updatable=false),
        @JoinColumn(name="current_table_version", referencedColumnName="track_table_version", insertable=false, updatable=false)} )
    private TrackTableVersion tracktableversion ; 

    public UserTableCurrentVersion() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setCurrentTableId( int currentTableId ) {
        this.currentTableId = currentTableId ;
    }
    public int getCurrentTableId() {
        return this.currentTableId;
    }

    public void setCurrentTableVersion( int currentTableVersion ) {
        this.currentTableVersion = currentTableVersion ;
    }
    public int getCurrentTableVersion() {
        return this.currentTableVersion;
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

//    //--- GETTERS FOR LINKS
    public List<UserConstraints> getUserconstraintsList() {
        return this.userconstraintsList;
    } 

    public TrackTableVersion getTracktableversion() {
        return this.tracktableversion;
    } 

    //--- toString specific method
	@Override
	public String toString() { 
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Usertablecurrentversion[");
		sb.append("currentTableId=").append(currentTableId);
		sb.append(separator).append("currentTableVersion=").append(currentTableVersion);
		sb.append(separator).append("createdBy=").append(createdBy);
		sb.append(separator).append("createdOn=").append(createdOn);
		sb.append(separator).append("modifiedBy=").append(modifiedBy);
		sb.append(separator).append("modifiedOn=").append(modifiedOn);
		sb.append("]");
		return sb.toString();
	}
}

