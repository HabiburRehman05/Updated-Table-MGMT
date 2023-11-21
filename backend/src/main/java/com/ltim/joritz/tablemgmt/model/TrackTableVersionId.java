
package com.ltim.joritz.tablemgmt.model;

import java.io.Serializable;


public class TrackTableVersionId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private int        trackTableId ;
    
    private int        trackTableVersion ;
    
    public TrackTableVersionId() {
        super();
    }

    public TrackTableVersionId( Integer trackTableId, Integer trackTableVersion ) {
        super();
        this.trackTableId = trackTableId ;
        this.trackTableVersion = trackTableVersion ;
    }
    
    //--- GETTERS & SETTERS FOR KEY FIELDS
    public void setTrackTableId( int value ) {
        this.trackTableId = value;
    }
    public int getTrackTableId() {
        return this.trackTableId;
    }

    public void setTrackTableVersion( int value ) {
        this.trackTableVersion = value;
    }
    public int getTrackTableVersion() {
        return this.trackTableVersion;
    }


    //--- equals METHOD
	@Override
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		TrackTableVersionId other = (TrackTableVersionId) obj; 
		//--- Attribute trackTableId
		if ( trackTableId != other.trackTableId ) return false ; 
		//--- Attribute trackTableVersion
		if ( trackTableVersion != other.trackTableVersion ) return false ; 
		return true; 
	} 
    //--- hashCode METHOD
	@Override
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute trackTableId
		result = prime * result + trackTableId;
		//--- Attribute trackTableVersion
		result = prime * result + trackTableVersion;
		
		return result; 
	} 
    //--- toString METHOD
	@Override
	public String toString() { 
		String separator = "|";
		StringBuilder sb = new StringBuilder();
		sb.append("Tracktableversion[");
		sb.append("trackTableId=").append(trackTableId);
		sb.append(separator).append("trackTableVersion=").append(trackTableVersion);
		sb.append("]");
		return sb.toString();
	}

}
