package com.mobile.healthypets.model.pegawai;

import java.io.Serializable;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.mobile.healthypets.model.drh.DokterHewan;
import com.mobile.healthypets.model.resepsionis.Resepsionis;

@Entity
public class Pegawai implements Serializable {
	@Id private Long id;
	
	@Index 
	private String email; 
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@SuppressWarnings("rawtypes")
	public Key getKey() {
		return Key.create(Resepsionis.class, id);
	}
	 
	@SuppressWarnings("rawtypes")
	public Key gettKey() {
		return Key.create(DokterHewan.class, id);
	}
	
	protected Pegawai () {}
	
	public Pegawai(String email) {
	super();
		
		this.email = email;
//		this.salt = salt;
//		this.password = password;
	}

	private static final long serialVersionUID = 1L;

}
