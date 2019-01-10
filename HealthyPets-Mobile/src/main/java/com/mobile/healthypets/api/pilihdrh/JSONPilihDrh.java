package com.mobile.healthypets.api.pilihdrh;

import com.mobile.healthypets.model.pilihdrh.PilihDrh;

public class JSONPilihDrh {
	private PilihDrh pilih;
	public JSONPilihDrh() {};
	
	public Long getDrh_jaga() {
		if(pilih == null) {
			return 0L;
	}
		return (Long) pilih.getDrh_jaga();
	}
	
	public Long getId() {
		if (pilih == null) {
			return 0L;	
		}
		return (Long) pilih.getId();
	}
	
	public JSONPilihDrh(PilihDrh pilih) {
		this.pilih = pilih;
	}
	
	public void setPilihDrh(PilihDrh pilih) {
		this.pilih = pilih;
	}
}
