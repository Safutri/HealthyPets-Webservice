package com.mobile.healthypets.model.hasillab;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.mobile.healthypets.model.ambulator.Ambulator;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.resepsionis.Resepsionis; 
  
@Entity
public class HasilLab {
	@Id private Long id;
 
	private String k_parasit;
	private String k_jamur;
	private String keadaan_feses;
	private String p_interna;
	private String protozoa;
	private String mikroba;
	private String warna;
	private String bau;
	private String uji_gula;
	private String uji_protein;
	private String uji_sedimentasi;
	private String warna_darah;
	private String sifat_darah;
	private String natif_protozoa;
	private String natif_bakteri;
	private String bdm;
	private String bdp;
	private String netrofil;
	private String eosinofil;
	private String basofil;
	private String limfosit;
	private String monosit;
	private String stab;
	private String hb;
	private String ht;
	private String diagnosa;
	private String dif_diag;
	private String prognosa;
	private String terapi;
	@Index
	private String drh_jaga;
	@Index
	private Long idAmb;
	private String tgl;
	
	@Index 
	private String kode_hewan;
	
 	@Load
	@Index
	private Ref<Hewan> hewan;
 	
 	@Load
	@Index
	private Ref<Resepsionis> resepsionis;
 	
 	@Load
	@Index
	private Ref<Ambulator> ambulator;
 	
 	
 	
	public Long getIdAmb() {
		return idAmb;
	}
	public void setIdAmb(Long idAmb) {
		this.idAmb = idAmb;
	}
	public String getKode_hewan() {
		return kode_hewan;
	}
	public void setKode_hewan(String kode_hewan) {
		this.kode_hewan = kode_hewan;
	}
	public String getDrh_jaga() {
		return drh_jaga;
	}
	public String getTgl() {
		return tgl;
	}
	public void setTgl(String tgl) {
		this.tgl = tgl;
	}
	public void setDrh_jaga(String drh_jaga) {
		this.drh_jaga = drh_jaga;
	}
	public Ref<Resepsionis> getResepsionis() {
		return resepsionis;
	}
	public void setResepsionis(Ref<Resepsionis> resepsionis) {
		this.resepsionis = resepsionis;
	}
	public Ref<Ambulator> getAmbulator() {
		return ambulator;
	}

	public void setAmbulator(Ref<Ambulator> ambulator) {
		this.ambulator = ambulator;
	}
	public String getK_parasit() {
		return k_parasit;
	}
	public void setK_parasit(String k_parasit) {
		this.k_parasit = k_parasit;
	}
	public String getK_jamur() {
		return k_jamur;
	}
	public void setK_jamur(String k_jamur) {
		this.k_jamur = k_jamur;
	}
	public String getKeadaan_feses() {
		return keadaan_feses;
	}
	public void setKeadaan_feses(String keadaan_feses) {
		this.keadaan_feses = keadaan_feses;
	}
	public String getP_interna() {
		return p_interna;
	}
	public void setP_interna(String p_interna) {
		this.p_interna = p_interna;
	}
	public String getProtozoa() {
		return protozoa;
	}
	public void setProtozoa(String protozoa) {
		this.protozoa = protozoa;
	}
	public String getMikroba() {
		return mikroba;
	}
	public void setMikroba(String mikroba) {
		this.mikroba = mikroba;
	}
	public String getWarna() {
		return warna;
	}
	public void setWarna(String warna) {
		this.warna = warna;
	}
	public String getBau() {
		return bau;
	}
	public void setBau(String bau) {
		this.bau = bau;
	}
	public String getUji_gula() {
		return uji_gula;
	}
	public void setUji_gula(String uji_gula) {
		this.uji_gula = uji_gula;
	}
	public String getUji_protein() {
		return uji_protein;
	}
	public void setUji_protein(String uji_protein) {
		this.uji_protein = uji_protein;
	}
	public String getUji_sedimentasi() {
		return uji_sedimentasi;
	}
	public void setUji_sedimentasi(String uji_sedimentasi) {
		this.uji_sedimentasi = uji_sedimentasi;
	}
	public String getWarna_darah() {
		return warna_darah;
	}
	public void setWarna_darah(String warna_darah) {
		this.warna_darah = warna_darah;
	}
	public String getSifat_darah() {
		return sifat_darah;
	}
	public void setSifat_darah(String sifat_darah) {
		this.sifat_darah = sifat_darah;
	}
	public String getNatif_protozoa() {
		return natif_protozoa;
	}
	public void setNatif_protozoa(String natif_protozoa) {
		this.natif_protozoa = natif_protozoa;
	}
	public String getNatif_bakteri() {
		return natif_bakteri;
	}
	public void setNatif_bakteri(String natif_bakteri) {
		this.natif_bakteri = natif_bakteri;
	}
	public String getBdm() {
		return bdm;
	}
	public void setBdm(String bdm) {
		this.bdm = bdm;
	}
	public String getBdp() {
		return bdp;
	}
	public void setBdp(String bdp) {
		this.bdp = bdp;
	}
	public String getNetrofil() {
		return netrofil;
	}
	public void setNetrofil(String netrofil) {
		this.netrofil = netrofil;
	}
	public String getEosinofil() {
		return eosinofil;
	}
	public void setEosinofil(String eosinofil) {
		this.eosinofil = eosinofil;
	}
	public String getBasofil() {
		return basofil;
	}
	public void setBasofil(String basofil) {
		this.basofil = basofil;
	}
	public String getLimfosit() {
		return limfosit;
	}
	public void setLimfosit(String limfosit) {
		this.limfosit = limfosit;
	}
	public String getMonosit() {
		return monosit;
	}
	public void setMonosit(String monosit) {
		this.monosit = monosit;
	}
	public String getStab() {
		return stab;
	}
	public void setStab(String stab) {
		this.stab = stab;
	}
	public String getHb() {
		return hb;
	}
	public void setHb(String hb) {
		this.hb = hb;
	}
	public String getHt() {
		return ht;
	}
	public void setHt(String ht) {
		this.ht = ht;
	}
	public String getDiagnosa() {
		return diagnosa;
	}
	public void setDiagnosa(String diagnosa) {
		this.diagnosa = diagnosa;
	}
	public String getDif_diag() {
		return dif_diag;
	}
	public void setDif_diag(String dif_diag) {
		this.dif_diag = dif_diag;
	}
	public String getPrognosa() {
		return prognosa;
	}
	public void setPrognosa(String prognosa) {
		this.prognosa = prognosa;
	}
	public String getTerapi() {
		return terapi;
	}
	public void setTerapi(String terapi) {
		this.terapi = terapi;
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
	private HasilLab() {}
	
	public HasilLab(String k_parasit, String k_jamur, String keadaan_feses, String p_interna, String protozoa, String mikroba,
			String warna, String bau, String uji_gula, String uji_protein, String uji_sedimentasi, String warna_darah, String sifat_darah,
			String natif_protozoa, String natif_bakteri, String bdm, String bdp, String netrofil, String eosinofil, String basofil,
			String limfosit, String monosit, String stab, String hb, String ht, String diagnosa, String dif_diag, String prognosa,
			String terapi, String drh_jaga, Ref<Hewan> hewan, Ref<Resepsionis> resepsionis, Ref<Ambulator> ambulator, String tgl,
			String kode_hewan, Long idAmb){
		
		this.k_parasit = k_parasit;
		this.k_jamur = k_jamur;
		this.keadaan_feses = keadaan_feses;
		this.p_interna = p_interna;
		this.protozoa = protozoa;
		this.mikroba = mikroba;
		this.warna = warna;
		this.bau = bau;
		this.uji_gula = uji_gula;
		this.uji_protein = uji_protein;
		this.uji_sedimentasi = uji_sedimentasi;
		this.warna_darah = warna_darah;
		this.sifat_darah = sifat_darah;
		this.natif_protozoa = natif_protozoa;
		this.natif_bakteri = natif_bakteri;
		this.bdm = bdm;
		this.bdp = bdp;
		this.netrofil = netrofil;
		this.eosinofil = eosinofil;
		this.basofil = basofil;
		this.limfosit = limfosit; 
		this.monosit = monosit;
		this.stab = stab;
		this.hb = hb;
		this.ht = ht;
		this.diagnosa = diagnosa;
		this.dif_diag = dif_diag;
		this.prognosa = prognosa;
		this.terapi = terapi;
		this.hewan = hewan;
		this.resepsionis = resepsionis;
		this.drh_jaga = drh_jaga;
		this.ambulator = ambulator;
		this.tgl = tgl;
		this.kode_hewan=kode_hewan;
		this.idAmb = idAmb;
		
	}
}
