package com.mobile.healthypets.api.antrianklien;

import com.mobile.healthypets.model.antriandrh.AntrianKlien;

public class JSONAntrianKlien {
	
	public JSONAntrianKlien(){};
	private AntrianKlien antri;
	
	public String getNama() {
		if(antri == null) {
			return "";
	}
		return antri.getNama();
	}
	 
	public String getJenis() {
		if(antri == null) {
			return "";
	}
		return antri.getJenis();
	} 
	
	public String getAntri() {
		if(antri == null) {
			return "";
	}
		return antri.getRas();
	}
	
	public String getRas() {
		if(antri == null) {
			return "";
	}
		return antri.getRas();
	}
	
	public String getUmur() {
		if(antri == null) {
			return "";
	}
		return antri.getUmur();
	}
	
	public String getGender() {
		if(antri == null) {
			return "";
	}
		return antri.getGender();
	}
	
	public String getTTL() {
		if(antri == null) {
			return "";
	}
		return antri.getTtl();
	}
	
	public String getWarna() {
		if(antri == null) {
			return "";
	}
		return antri.getWarna();
	}
	
	public String getKode_hewan() {
		if(antri == null) {
			return "";
	}
		return antri.getKode_hewan();
	}
		
	public Long getId() {
		if (antri == null) {
			return 0L;	
		}
		return (Long) antri.getId();
	}
	
	public Long getAmbs() {
		if (antri == null) {
			return 0L;	
		}
		return (Long) antri.getAmbs();
	}
	
	
	public Long getNo_reg() {
		if (antri == null) {
			return 0L;	
		}
		return (Long) antri.getNo_reg();
	}
	
	public Long getDrh_jaga() {
		if (antri == null) {
			return 0L;	
		}
		return (Long) antri.getDrh_jaga();
	}
	
	public Long getDrhPilih() {
		if (antri == null) {
			return 0L;	
		}
		return (Long) antri.getDrhPilih();
	}

	public JSONAntrianKlien(AntrianKlien antri) {
		this.antri = antri;
	}
	
	public void setAntrianKlien (AntrianKlien antri) {
		this.antri = antri;
	}
}
