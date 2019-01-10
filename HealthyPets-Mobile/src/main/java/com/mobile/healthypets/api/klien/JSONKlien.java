package com.mobile.healthypets.api.klien;

import com.mobile.healthypets.model.klien.Klien;

public class JSONKlien {
	private Klien klien;
	 
	public JSONKlien(){}; 	
	
	public String getEmail_klinik() {
		if(klien == null) {
			return "";
		}
		return klien.getEmail_klinik();
	}
	
	public String getNama() {
		if(klien == null) {
			return "";
	}
		return klien.getNama();
	}
	
	public String getEmail() {
		if(klien == null) {
			return "";
		}
		return klien.getEmail();
	}
	
	public String getAlamat() {
		if(klien == null) {
			return "";
		}
		return klien.getAlamat();
	}
	
	public String getTelp() {
		if(klien == null) {
			return "";
		}
		return klien.getTelp();
	}
	
	public Long getNo_reg() {
		if(klien == null) {
			return 0L;
		}
		return (Long) klien.getNo_reg();
	}
	
	public String getKtp() {
		if(klien == null) {
			return "";
		}
		return klien.getKtp();
	}
	 
	public Long getId() {
		if (klien == null) {
			return 0L;	
		}
		return (Long) klien.getId();
	}
	
	public JSONKlien(Klien klien) {
		this.klien = klien;
	}
	
	public void setKlien(Klien klien) {
		this.klien = klien;
	}
	
}
