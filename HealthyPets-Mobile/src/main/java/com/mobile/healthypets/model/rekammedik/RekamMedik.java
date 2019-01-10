package com.mobile.healthypets.model.rekammedik;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.mobile.healthypets.model.ambulator.Ambulator;
 
@Entity
public class RekamMedik {
@Id private Long id;
	
	@Index 	
	private String tgl;
	private String status_awal;
	private String terapi;
	private String diagnosa;
	private String obat;
	private String lain;
	@Index 
	private String kode_hewan;
	@Index 
	private Long no_reg;
	@Index 
	private String nama_drh;
	
	@Index 
	private Long idAmb;
	
	@Load
	@Index
	private Ref<Ambulator> ambulator;
		
	public Ref<Ambulator> getAmbulator() {
		return ambulator;
	}
	public Long getIdAmb() {
		return idAmb;
	}
	public void setIdAmb(Long idAmb) {
		this.idAmb = idAmb;
	}
	public String getNama_drh() {
		return nama_drh;
	}

	public void setNama_drh(String nama_drh) {
		this.nama_drh = nama_drh;
	}

	public void setAmbulator(Ref<Ambulator> ambulator) {
		this.ambulator = ambulator;
	}
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
	public String getLain() {
		return lain;
	}
	public void setLain(String lain) {
		this.lain = lain;
	}
	public String getTgl() {
		return tgl;
	}
	public void setTgl(String tgl) {
		this.tgl = tgl;
	}
	
	public String getStatus_awal() {
		return status_awal;
	}

	public void setStatus_awal(String status_awal) {
		this.status_awal = status_awal;
	}

	public String getTerapi() {
		return terapi;
	}

	public void setTerapi(String terapi) {
		this.terapi = terapi;
	}

	public String getDiagnosa() {
		return diagnosa;
	}

	public void setDiagnosa(String diagnosa) {
		this.diagnosa = diagnosa;
	}

	public String getObat() {
		return obat;
	}

	public void setObat(String obat) {
		this.obat = obat;
	}
  

	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private RekamMedik(){}
	
	public RekamMedik(String tgl, String nama_drh, String status_awal, String terapi, String diagnosa, String obat,
			String kode_hewan, Long no_reg, String lain, Ref<Ambulator> ambulator,
			Long idAmb){
		this.tgl=tgl;
		this.nama_drh=nama_drh;
		this.status_awal=status_awal;
		this.terapi=terapi;
		this.diagnosa=diagnosa;
		this.obat=obat;  
		this.kode_hewan=kode_hewan;
		this.no_reg =no_reg;
		this.lain=lain;
		this.ambulator=ambulator;
		this.idAmb=idAmb;
	}
}
