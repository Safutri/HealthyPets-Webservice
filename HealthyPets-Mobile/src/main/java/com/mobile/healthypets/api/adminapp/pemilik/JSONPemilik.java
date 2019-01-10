package com.mobile.healthypets.api.adminapp.pemilik;

import com.mobile.healthypets.model.pemilik.Pemilik;

public class JSONPemilik {
	
	public JSONPemilik(){};
	
	private Pemilik pemilik;
		
	public String getNama() {
		if(pemilik == null) {
			return "";
	}
		return pemilik.getNama();
	}
	 
	public String getEmail() {
		if(pemilik == null) {
			return "";   
	}
		return pemilik.getEmail();
	}
	 
	public String getAlamat() {
		if(pemilik == null) {
			return "";
	}
		return pemilik.getAlamat();
	}
	
	public String getIdentitas() {
		if(pemilik == null) {
			return "";
	}
		return pemilik.getIdentitas();
	}
	
	public String getTelp() {
		if(pemilik == null) {
			return "";
	}
		return pemilik.getTelp();
	}
		
	public Long getId() {
		if (pemilik == null) {
			return 0L;	
		}
		return pemilik.getId();
	}
		
	public JSONPemilik(Pemilik pemilik) {
		this.pemilik = pemilik;
	}
	
	public void setPemilik(Pemilik pemilik) {
		this.pemilik = pemilik;
	}
}
