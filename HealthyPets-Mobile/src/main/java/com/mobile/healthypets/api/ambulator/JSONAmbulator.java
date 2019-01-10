package com.mobile.healthypets.api.ambulator;

import com.mobile.healthypets.model.ambulator.Ambulator;

public class JSONAmbulator {

	private Ambulator ambulator;
	public JSONAmbulator(){};
	
	public String getTgl() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getTgl();
	}
	
	public String getSinyalmen() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getSinyalmen();
	}
	
	public String getAnamnesa() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getAnamnesa();
	}
	
	public String getGizi() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getGizi();
	}
	
	public String getTempramen() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getTempramen();
	}
	
	public String getHabitat() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getHabitat();
	}

	public String getFrek_nafas() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getFrek_nafas();
	}
	
	public String getFrek_pulsus() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getFrek_pulsus();
	}
	
	public String getSuhu_tubuh() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getSuhu_tubuh();
	}
	
	public String getKulit_bulu() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getKulit_bulu();
	}
	
	public String getSedir_mata() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getSedir_mata();
	}
	
	public String getSedir_hidung() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getSedir_hidung();
	}
	
	public String getSedir_mulut() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getSedir_mulut();
	}
	
	public String getSedir_anus() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getSedir_anus();
	}
	
	public String getK_limfase() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getK_limfase();
	}
	public String getA_nafas() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getA_nafas();
	}
	public String getA_darah() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getA_darah();
	}
	public String getA_kelamin() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getA_kelamin();
	}
	
	public String getA_cerna() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getA_cerna();
	}
	
	public String getU_saraf() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getU_saraf();
	}
	public String getAng_gerak() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getAng_gerak();
	}
	public String getLain() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getLain();
	}
	
	public Long getDrh_jaga() {
		if(ambulator == null) {
			return 0L;
	}
		return (Long) ambulator.getDrh_jaga();
	}
	 
	public Long getId() {
		if (ambulator == null) {
			return 0L;	
		}
		return (Long) ambulator.getId();
	}
	
	public Long getNo_reg() {
		if (ambulator == null) {
			return 0L;	
		}
		return (Long) ambulator.getNo_reg();
	}
	
	public String getKtp() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getKtp();
	}
	
	public String getKode_hewan() {
		if(ambulator == null) {
			return "";
	}
		return ambulator.getKode_hewan();
	}
	
	public JSONAmbulator(Ambulator ambulator) {
		this.ambulator = ambulator;
	}
	
	public void setAmbulator(Ambulator ambulator) {
		this.ambulator = ambulator;
	}
	
	
	
}
