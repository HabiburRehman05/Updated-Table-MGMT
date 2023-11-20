
package com.ltim.joritz.tablemgmt.model;

import java.io.Serializable;

public class TrackColumnVersionId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private int        columnId ;
    
    private int        tableVersion ;
    
    public TrackColumnVersionId() {
        super();
    }

    public TrackColumnVersionId( Integer columnId, Integer tableVersion ) {
        super();
        this.columnId = columnId ;
        this.tableVersion = tableVersion ;
    }
    
    //--- GETTERS & SETTERS FOR KEY FIELDS
    public void setColumnId( int value ) {
        this.columnId = value;
    }
    public int getColumnId() {
        return this.columnId;
    }

    public void setTableVersion( int value ) {
        this.tableVersion = value;
    }
    public int getTableVersion() {
        return this.tableVersion;
    }


    //--- equals METHOD
	@Override
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		TrackColumnVersionId other = (TrackColumnVersionId) obj; 
		//--- Attribute columnId
		if ( columnId != other.columnId ) return false ; 
		//--- Attribute tableVersion
		if ( tableVersion != other.tableVersion ) return false ; 
		return true; 
	} 
    //--- hashCode METHOD
	@Override
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute columnId
		result = prime * result + columnId;
		//--- Attribute tableVersion
		result = prime * result + tableVersion;
		
		return result; 
	} 
    //--- toString METHOD
	@Override
	public String toString() { 
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Trackcolumnversion[");
		sb.append("columnId=").append(columnId);
		sb.append(separator).append("tableVersion=").append(tableVersion);
		sb.append("]");
		return sb.toString();
	}

}
