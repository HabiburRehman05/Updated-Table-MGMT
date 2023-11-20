package com.ltim.joritz.tablemgmt.model;

import java.io.Serializable;

public class UserTableCurrentVersionId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private int        currentTableId ;
    
    private int        currentTableVersion ;
    
    public UserTableCurrentVersionId() {
        super();
    }

    public UserTableCurrentVersionId( Integer currentTableId, Integer currentTableVersion ) {
        super();
        this.currentTableId = currentTableId ;
        this.currentTableVersion = currentTableVersion ;
    }
    
    //--- GETTERS & SETTERS FOR KEY FIELDS
    public void setCurrentTableId( int value ) {
        this.currentTableId = value;
    }
    public int getCurrentTableId() {
        return this.currentTableId;
    }

    public void setCurrentTableVersion( int value ) {
        this.currentTableVersion = value;
    }
    public int getCurrentTableVersion() {
        return this.currentTableVersion;
    }


    //--- equals METHOD
	@Override
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		UserTableCurrentVersionId other = (UserTableCurrentVersionId) obj; 
		//--- Attribute currentTableId
		if ( currentTableId != other.currentTableId ) return false ; 
		//--- Attribute currentTableVersion
		if ( currentTableVersion != other.currentTableVersion ) return false ; 
		return true; 
	} 
    //--- hashCode METHOD
	@Override
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute currentTableId
		result = prime * result + currentTableId;
		//--- Attribute currentTableVersion
		result = prime * result + currentTableVersion;
		
		return result; 
	} 
    //--- toString METHOD
	@Override
	public String toString() { 
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Usertablecurrentversion[");
		sb.append("currentTableId=").append(currentTableId);
		sb.append(separator).append("currentTableVersion=").append(currentTableVersion);
		sb.append("]");
		return sb.toString();
	}

}
