package com.mobile.healthypets.model.antrianhewan;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.resepsionis.Resepsionis;

@Entity
public class AntrianHewan {
@Id private Long id;
	
	@Index 
	private String nama;
	@Index 
	private String kode_hewan;
	private String jenis;
	private String ras;
	private String umur;
	private String gender;
	private String ttl;
	private String warna;
	@Index 
	private Long no_reg;
	@Index 
	private String ktp;
		
	@Load
	@Index
	private Ref<Resepsionis> resepsionis;
	
	@Load
	@Index
	private Ref<Hewan> hewan;
	
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
	public String getNama() {
		return nama;
	} 
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getJenis() {
		return jenis;
	}
	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
	public String getRas() {
		return ras;
	}
	public void setRas(String ras) {
		this.ras = ras;
	}
	public String getUmur() {
		return umur;
	}
	public void setUmur(String umur) {
		this.umur = umur;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
	public String getWarna() {
		return warna;
	}
	public void setWarna(String warna) {
		this.warna = warna;
	}
	public Long getId() {
		return id;
	}
	
	
	public Ref<Resepsionis> getResepsionis() {
		return resepsionis;
	}
	public void setResepsionis(Ref<Resepsionis> resepsionis) {
		this.resepsionis = resepsionis;
	}
	
	
	public Ref<Hewan> getHewan() {
		return hewan;
	}
	public void setKlien(Ref<Hewan> hewan) {
		this.hewan = hewan;
	}
		
 	@SuppressWarnings("unused")
	private AntrianHewan() {}
	
 	public AntrianHewan(String nama, Ref<Hewan> hewan, Ref<Resepsionis> resepsionis,
 			String jenis, String ras, String umur, String gender, String ttl, String warna, Long no_reg, String ktp, String kode_hewan) {
 		 
		this.nama = nama;
		this.hewan = hewan;							
		this.jenis = jenis; 
		this.ras = ras;
		this.umur = umur;
		this.gender = gender; 
		this.ttl = ttl;
		this.warna = warna;
		this.resepsionis = resepsionis;
		this.no_reg = no_reg;
		this.ktp =ktp;
		this.kode_hewan = kode_hewan;
	}

	
	
}
