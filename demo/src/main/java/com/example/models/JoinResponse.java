package com.example.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class JoinResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	public String getSessionKey() {
		return sessionKey;
	}

	public boolean isLeave() {
		return leave;
	}

	private String sessionKey;
    private boolean leave;
    private boolean join;
    
    public boolean isJoin() {
		return join;
	}

	public JoinResponse(String sessionKey, boolean join, boolean leave){
    	this.sessionKey = sessionKey;
    	this.join = join;
    	this.leave = leave;
    }
    
    //for JPA
    public JoinResponse() {}
    
}
