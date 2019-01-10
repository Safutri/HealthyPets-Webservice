package com.mobile.healthypets.api.resepsionis;

import com.mobile.healthypets.model.resepsionis.Resepsionis;

public class JSONResepsionis {

	private Resepsionis resepsionis;
	
	public JSONResepsionis(){};
	
	public String getNama() {
		if(resepsionis == null) {
			return "";
		}
		return resepsionis.getNama();
	}
 
	public String getEmail() {
		if(resepsionis == null) {
			return "";
		}
		return resepsionis.getEmail();
	}
	
	public String getAlamat() {
		if(resepsionis == null) {
			return "";
		}
		return resepsionis.getAlamat();
	}
	
	public String getTelp() {
		if(resepsionis == null) {
			return "";
		}
		return resepsionis.getTelp();
	}
	 	
	public String getEmail_klinik() {
		if(resepsionis == null) {
			return "";
		}
		return resepsionis.getEmail_klinik();
	}
	 
	public String getNama_klinik() {
		if(resepsionis == null) {
			return "";
		}
		return resepsionis.getNama_klinik();
	}
	 
	public Long getId() {
		if (resepsionis == null) {
			return 0L;	
		}
		
		return (Long) resepsionis.getId();
	}
	
	public JSONResepsionis(Resepsionis resepsionis) {
		this.resepsionis = resepsionis;
	}
	
	public void setResepsionis(Resepsionis resepsionis) {
		this.resepsionis = resepsionis;
	}
	
}
