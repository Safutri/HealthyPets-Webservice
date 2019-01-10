package com.mobile.healthypets.api.antrianhewan;

import com.mobile.healthypets.model.antrianhewan.AntrianHewan;

public class JSONAntrian {
	public JSONAntrian(){};
	private AntrianHewan Antrianhewan;
		
	public String getNama() {
		if(Antrianhewan == null) {
			return "";
	}
		return Antrianhewan.getNama();
	}
	 
	public String getJenis() {
		if(Antrianhewan == null) {
			return "";
	}
		return Antrianhewan.getJenis();
	}
	
	public String getRas() {
		if(Antrianhewan == null) {
			return "";
	}
		return Antrianhewan.getRas();
	}
	
	public String getUmur() {
		if(Antrianhewan == null) {
			return "";
	}
		return Antrianhewan.getUmur();
	}
	
	public String getKode_hewan() {
		if(Antrianhewan == null) {
			return "";
	}
		return Antrianhewan.getKode_hewan();
	}
	
	public String getGender() {
		if(Antrianhewan == null) {
			return "";
	}
		return Antrianhewan.getGender();
	}
	
	public String getTTL() {
		if(Antrianhewan == null) {
			return "";
	}
		return Antrianhewan.getTtl();
	}
	
	public String getWarna() {
		if(Antrianhewan == null) {
			return "";
	}
		return Antrianhewan.getWarna();
	}
	
	public Long getId() {
		if (Antrianhewan == null) {
			return 0L;	
		}
		return (Long) Antrianhewan.getId();
	}
	
	public JSONAntrian(AntrianHewan Antrianhewan) {
		this.Antrianhewan= Antrianhewan;
	}
	
	public void setAntrianHewan(AntrianHewan Antrianhewan) {
		this.Antrianhewan = Antrianhewan;
	}

}
