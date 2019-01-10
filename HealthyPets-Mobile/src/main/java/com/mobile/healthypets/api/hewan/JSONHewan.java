package com.mobile.healthypets.api.hewan;

import com.mobile.healthypets.model.hewan.Hewan;

public class JSONHewan {
	
	public JSONHewan(){};
	private Hewan hewan;
		
	public String getNama() {
		if(hewan == null) {
			return "";
	}
		return hewan.getNama();
	}
	 
	public String getEmail_klinik() {
		if(hewan == null) {
			return "";   
	}
		return hewan.getEmail_klinik();
	}
	 
	public String getJenis() {
		if(hewan == null) {
			return "";
	}
		return hewan.getJenis();
	}
	
	public String getRas() {
		if(hewan == null) {
			return "";
	}
		return hewan.getRas();
	}
	
	public String getUmur() {
		if(hewan == null) {
			return "";
	}
		return hewan.getUmur();
	}
	
	public String getGender() {
		if(hewan == null) {
			return "";
	}
		return hewan.getGender();
	}
	
	public String getTTL() {
		if(hewan == null) {
			return "";
	}
		return hewan.getTtl();
	}
	
	public String getWarna() {
		if(hewan == null) {
			return "";
	}
		return hewan.getWarna();
	}
	
	public Long getId() {
		if (hewan == null) {
			return 0L;	
		}
		return (Long) hewan.getId();
	}
	
	public Long getNo_reg() {
		if (hewan == null) {
			return 0L;	
		}
		return (Long) hewan.getNo_reg();
	}
	
	public String getKtp() {
		if(hewan == null) {
			return "";
	}
		return hewan.getKtp();
	}	
	
	public String getKode_hewan() {
		if(hewan == null) {
			return "";
	}
		return hewan.getKode_hewan();
	}		
	
	public JSONHewan(Hewan hewan) {
		this.hewan = hewan;
	}
	
	public void setHewan(Hewan hewan) {
		this.hewan = hewan;
	}

}
