package com.mobile.healthypets.api.lab;

import com.mobile.healthypets.model.hasillab.HasilLab;

public class JSONLab {
	public JSONLab(){};
	private HasilLab lab; 
	
	public String getK_parasit() {
		if(lab == null) {
			return "";
	}
		return lab.getK_parasit();
	}
	
	public String getK_jamur() {
		if(lab == null) {
			return ""; 
	}
		return lab.getK_jamur();
	}
	
	public String getKeadaan_feses() {
		if(lab == null) {
			return "";
	}
		return lab.getKeadaan_feses();
	}
	
	public String getP_interna() {
		if(lab == null) {
			return "";
	}
		return lab.getP_interna();
	}
	
	public String getProtozoa() {
		if(lab == null) {
			return "";
	}
		return lab.getProtozoa();
	}
	
	public String getMikroba() {
		if(lab == null) {
			return "";
	}
		return lab.getMikroba();
	}
	
	public String getWarna() {
		if(lab == null) {
			return "";
	}
		return lab.getWarna();
	}
	
	public String getBau() {
		if(lab == null) {
			return "";
	}
		return lab.getBau();
	}
	
	public String getUji_gula() {
		if(lab == null) {
			return "";
	}
		return lab.getUji_gula();
	}
	
	public String getUji_protein() {
		if(lab == null) {
			return "";
	}
		return lab.getUji_protein();
	}
	
	public String getUji_sedimentasi() {
		if(lab == null) {
			return "";
	}
		return lab.getUji_sedimentasi();
	}
	
	public String getWarna_darah() {
		if(lab == null) {
			return "";
	}
		return lab.getWarna_darah();
	}
	
	public String getSifat_darah() {
		if(lab == null) {
			return "";
	}
		return lab.getSifat_darah();
	}
	
	public String getNatif_protozoa() {
		if(lab == null) {
			return "";
	}
		return lab.getNatif_protozoa();
	}
	
	public String getNatif_bakteri() {
		if(lab == null) {
			return "";
	}
		return lab.getNatif_bakteri();
	}

	public String getBdm() {
		if(lab == null) {
			return "";
	}
		return lab.getBdm();
	}
	
	public String getBdp() {
		if(lab == null) {
			return "";
	}
		return lab.getBdp();
	}
	
	public String getNetrofil() {
		if(lab == null) {
			return "";
	}
		return lab.getNetrofil();
	}
	
	public String getEosinofil() {
		if(lab == null) {
			return "";
	}
		return lab.getEosinofil();
	}
	
	public String getBasofil() {
		if(lab == null) {
			return "";
	}
		return lab.getBasofil();
	}
	
	public String getLimfosit() {
		if(lab == null) {
			return "";
	}
		return lab.getLimfosit();
	}
	
	public String getMonosit() {
		if(lab == null) {
			return "";
	}
		return lab.getMonosit();
	}
	
	public String getStab() {
		if(lab == null) {
			return "";
	}
		return lab.getStab();
	}
	
	public String getHb() {
		if(lab == null) {
			return "";
	}
		return lab.getHb();
	}
	
	public String getHt() {
		if(lab == null) {
			return "";
	}
		return lab.getHt();
	}
	
	public String getDiagnosa() {
		if(lab == null) {
			return "";
	}
		return lab.getDiagnosa();
	}
	
	public String getDif_diag() {
		if(lab == null) {
			return "";
	}
		return lab.getDif_diag();
	}
	
	public String getPrognosa() {
		if(lab == null) {
			return "";
	}
		return lab.getPrognosa();
	}
	
	public String getTerapi() {
		if(lab == null) {
			return "";
	}
		return lab.getTerapi();
	}
	
	public String getDrh_jaga() {
		if (lab == null) {
			return "";	
		}
		return lab.getDrh_jaga();
	} 
	
	public String getTgl() {
		if(lab == null) {
			return "";
	}
		return lab.getTgl();
	}
	public String getKode_hewan() {
		if(lab == null) {
			return "";
	}
		return lab.getKode_hewan();
	}	
	public Long getId() {
		if (lab == null) {
			return 0L;	
		}
		return (Long) lab.getId();
	}
	
	public Long getIdAmb() {
		if (lab == null) {
			return 0L;	
		}
		return (Long) lab.getIdAmb();
	}
	
	public JSONLab(HasilLab lab) {
		this.lab = lab;
	}
	
	public void setHasilLab(HasilLab lab) {
		this.lab = lab;
	}
}

