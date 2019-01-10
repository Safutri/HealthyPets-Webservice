package com.mobile.healthypets.model.ambulator;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.pilihdrh.PilihDrh; 

@Entity
public class Ambulator {
@Id private Long id;
	
	@Index 
	private String tgl;
	private String sinyalmen;
	private String anamnesa;
	private String gizi;
	private String tempramen;
	private String habitat;
	private String frek_nafas;
	private String frek_pulsus;
	private String suhu_tubuh;
	private String kulit_bulu;
	private String sedir_mata;
	private String sedir_hidung;
	private String sedir_mulut;
	private String sedir_anus;
	private String k_limfase;
	private String a_nafas;
	private String a_darah;
	private String a_kelamin;
	private String a_cerna;
	private String u_saraf;
	private String ang_gerak;
	private String lain;
	@Index
	private Long drh_jaga;
	@Index 
	private Long no_reg;
	@Index 
	private String ktp;
	@Index 
	private String kode_hewan;
	
	@Load
	@Index
	private Ref<Hewan> hewan;
	
	@Load
	@Index
	private Ref<PilihDrh> pilih;
	
	
	public String getKode_hewan() {
		return kode_hewan;
	}
	public void setKode_hewan(String kode_hewan) {
		this.kode_hewan = kode_hewan;
	}
	public Long getNo_reg() {
		return no_reg;
	}
	public void setNo_reg(Long no_reg) {
		this.no_reg = no_reg;
	}
	public String getKtp() {
		return ktp;
	}
	public void setKtp(String ktp) {
		this.ktp = ktp;
	}
	
	public Ref<PilihDrh> getPilih() {
		return pilih;
	}
	public void setPilih(Ref<PilihDrh> pilih) {
		this.pilih = pilih;
	}
	public String getTgl() {
		return tgl;
	}
	public void setTgl(String tgl) {
		this.tgl = tgl;
	}
	public String getSinyalmen() {
		return sinyalmen;
	}
	public void setSinyalmen(String sinyalmen) {
		this.sinyalmen = sinyalmen;
	}
	public String getAnamnesa() {
		return anamnesa;
	}
	public void setAnamnesa(String anamnesa) {
		this.anamnesa = anamnesa;
	}
	public String getGizi() {
		return gizi;
	}
	public void setGizi(String gizi) {
		this.gizi = gizi;
	}
	public String getTempramen() {
		return tempramen;
	}
	public void setTempramen(String tempramen) {
		this.tempramen = tempramen;
	}
	public String getHabitat() {
		return habitat;
	}
	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}
	public String getFrek_nafas() {
		return frek_nafas;
	}
	public void setFrek_nafas(String frek_nafas) {
		this.frek_nafas = frek_nafas;
	}
	public String getFrek_pulsus() {
		return frek_pulsus;
	}
	public void setFrek_pulsus(String frek_pulsus) {
		this.frek_pulsus = frek_pulsus;
	}
	public String getSuhu_tubuh() {
		return suhu_tubuh;
	}
	public void setSuhu_tubuh(String suhu_tubuh) {
		this.suhu_tubuh = suhu_tubuh;
	}
	public String getKulit_bulu() {
		return kulit_bulu;
	}
	public void setKulit_bulu(String kulit_bulu) {
		this.kulit_bulu = kulit_bulu;
	}
	public String getSedir_mata() {
		return sedir_mata;
	}
	public void setSedir_mata(String sedir_mata) {
		this.sedir_mata = sedir_mata;
	}
	public String getSedir_hidung() {
		return sedir_hidung;
	}
	public void setSedir_hidung(String sedir_hidung) {
		this.sedir_hidung = sedir_hidung;
	}
	public String getSedir_mulut() {
		return sedir_mulut;
	}
	public void setSedir_mulut(String sedir_mulut) {
		this.sedir_mulut = sedir_mulut;
	}
	public String getSedir_anus() {
		return sedir_anus;
	}
	public void setSedir_anus(String sedir_anus) {
		this.sedir_anus = sedir_anus;
	}
	public String getK_limfase() {
		return k_limfase;
	}
	public void setK_limfase(String k_limfase) {
		this.k_limfase = k_limfase;
	}
	public String getA_nafas() {
		return a_nafas;
	}
	public void setA_nafas(String a_nafas) {
		this.a_nafas = a_nafas;
	}
	public String getA_darah() {
		return a_darah;
	}
	public void setA_darah(String a_darah) {
		this.a_darah = a_darah;
	}
	public String getA_kelamin() {
		return a_kelamin;
	}
	public void setA_kelamin(String a_kelamin) {
		this.a_kelamin = a_kelamin;
	}
	public String getA_cerna() {
		return a_cerna;
	}
	public void setA_cerna(String a_cerna) {
		this.a_cerna = a_cerna;
	} 
	public String getU_saraf() {
		return u_saraf;
	}
	public void setU_saraf(String u_saraf) {
		this.u_saraf = u_saraf;
	}
	public String getAng_gerak() {
		return ang_gerak;
	}
	public void setAng_gerak(String ang_gerak) {
		this.ang_gerak = ang_gerak;
	}
	public String getLain() {
		return lain;
	}
	public void setLain(String lain) {
		this.lain = lain;
	}
	public Long getDrh_jaga() {
		return drh_jaga;
	}
	public void setDrh_jaga(Long drh_jaga) {
		this.drh_jaga = drh_jaga;
	}
	public Ref<Hewan> getHewan() {
		return hewan;
	}
	public void setHewan(Ref<Hewan> hewan) {
		this.hewan = hewan;
	}
	public Long getId() {
		return id;
	}
	 
	
	@SuppressWarnings("unused")
	private Ambulator(){}
	
	public Ambulator(String tgl, String sinyalmen, String anamnesa, String gizi, String tempramen,
			String habitat, String frek_nafas, String frek_pulsus, String suhu_tubuh, 
			String kulit_bulu, String sedir_mata, String sedir_hidung, String sedir_mulut,
			String sedir_anus, String k_limfase, String a_nafas, String a_darah, String a_cerna,
			String a_kelamin, String u_saraf, String ang_gerak, String lain, Long drh_jaga,
			Ref<Hewan> hewan, Ref<PilihDrh> pilih, Long no_reg, String ktp, String kode_hewan){
		this.tgl=tgl;
		this.sinyalmen=sinyalmen;
		this.anamnesa=anamnesa;
		this.gizi=gizi;
		this.tempramen=tempramen;
		this.habitat=habitat;
		this.frek_nafas=frek_nafas;
		this.frek_pulsus=frek_pulsus;
		this.suhu_tubuh=suhu_tubuh;
		this.kulit_bulu=kulit_bulu;
		this.sedir_mata=sedir_mata;
		this.sedir_hidung=sedir_hidung;
		this.sedir_mulut=sedir_mulut;
		this.sedir_anus=sedir_anus;
		this.k_limfase=k_limfase;
		this.a_nafas=a_nafas;
		this.a_darah=a_darah;
		this.a_cerna=a_cerna;
		this.a_kelamin=a_kelamin;
		this.u_saraf=u_saraf;
		this.ang_gerak=ang_gerak;
		this.lain=lain;
		this.drh_jaga=drh_jaga;
		this.hewan=hewan;
		this.pilih = pilih;
		this.no_reg = no_reg;
		this.ktp = ktp;
		this.kode_hewan = kode_hewan;
	}
}





