package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class STable{
		@Id
	    private int table_id;
		
	    private String name;

		public int getId() {
			return table_id;
		}

		public void setId(int id) {
			this.table_id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	    
	    
}
