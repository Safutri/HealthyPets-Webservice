package com.mobile.healthypets.api.drh;

import com.mobile.healthypets.model.drh.DokterHewan;

public class JSONDokterHewan {
	
private DokterHewan drh;
	
	public JSONDokterHewan(){};
	
	public String getNama() {
		if(drh == null) {
			return "";
		}
		return drh.getNama();
	}
 
	public String getEmail() {
		if(drh == null) {
			return "";
		}
		return drh.getEmail();
	}
	
	public String getAlamat() {
		if(drh == null) {
			return "";
		}
		return drh.getAlamat();
	}
	
	public String getTelp() {
		if(drh == null) {
			return "";
		}
		return drh.getTelp();
	}
	
	public String getNo_praktik() {
		if(drh == null) {
			return "";
		}
		return drh.getNo_praktik();
	}
	 
	public String getNama_klinik() {
		if(drh == null) {
			return "";
		}
		return drh.getNama_klinik();
	}
	
	public String getEmail_klinik() {
		if(drh == null) {
			return "";
		}
		return drh.getEmail_klinik();
	}
	
	public Long getId() {
		if (drh == null) {
			return 0L;	
		}
		return (Long) drh.getId();
	}
	
	public JSONDokterHewan(DokterHewan drh) {
		this.drh = drh;
	}
	
	public void setDokterHewan(DokterHewan drh) {
		this.drh = drh;
	}

}
