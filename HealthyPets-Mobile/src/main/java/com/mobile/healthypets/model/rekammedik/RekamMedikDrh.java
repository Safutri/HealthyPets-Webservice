package com.mobile.healthypets.model.rekammedik;
 
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index; 

@Entity
public class RekamMedikDrh {
@Id private Long id;
	
	@Index 	
	private String tgl;
	@Index 	
	private String status_awal;
	@Index 	
	private String terapi;
	@Index 	
	private String diagnosa;
	@Index 	
	private String obat;
	@Index 	
	private String lain;
	@Index 
	private String kode_hewan;
	@Index 
	private Long no_reg;
	@Index 
	private String nama_drh;
 
	 
	public String getKode_hewan() {
		return kode_hewan;
	}
	public String getNama_drh() {
		return nama_drh;
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
	private RekamMedikDrh(){}
	
	public RekamMedikDrh(String tgl, String nama_drh, String status_awal, String terapi, String diagnosa, String obat,
			String kode_hewan, Long no_reg, String lain){
		this.tgl=tgl;
		this.nama_drh=nama_drh;
		this.status_awal=status_awal;
		this.terapi=terapi;
		this.diagnosa=diagnosa;
		this.obat=obat;  
		this.kode_hewan=kode_hewan;
		this.no_reg =no_reg;
		this.lain=lain; 
	}
}

