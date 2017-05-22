package com.example.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ThinKP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public String getinstanceKP() {
		return instanceKP;
	}

	public String getToken() {
		return token;
	}

	private String instanceKP;
    private String token;
    private boolean join;
    
    public boolean isJoin() {
		return join;
	}

	public ThinKP(String instanceKP, String token){
    	this.instanceKP = instanceKP;
    	this.token = token;
    }
    
    //for JPA
    public ThinKP() {}
    
}
