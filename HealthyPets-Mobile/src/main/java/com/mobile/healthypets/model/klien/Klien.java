package com.mobile.healthypets.model.klien;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.mobile.healthypets.model.resepsionis.Resepsionis;

@Entity
public class Klien {
@Id private Long id;
	
	@Index 
	private String nama;
	private String email;
	private String alamat;
	private String telp;
	
	@Index 
	private Long no_reg;
	@Index
	private String ktp;
	@Index 
	private String email_klinik;
	
	@Load
	@Index
	private Ref<Resepsionis> resepsionis;

	public String getEmail_klinik() {
		return email_klinik;
	}
	public void setEmail_klinik(String email_klinik) {
		this.email_klinik= email_klinik;
	}	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}
	
	public String getKtp() {
		return ktp;
	}

	public void setKtp(String ktp) {
		this.ktp = ktp;
	}

	public Long getNo_reg() {
		return no_reg;
	}

	public void setNo_reg(Long no_reg) {
		this.no_reg = no_reg;
	}
	

	public Ref<Resepsionis> getResepsionis() {
		return resepsionis;
	}

	public void setResepsionis(Ref<Resepsionis> resepsionis) {
		this.resepsionis = resepsionis;
	}

	@SuppressWarnings("rawtypes")
	public Key getKey() {
		return Key.create(Resepsionis.class, id);
	}
	
	public Long getId() {
		return id;
	}
	
 	@SuppressWarnings("unused")
	private Klien() {}
 	
	public Klien(String email_klinik,String nama, String alamat, String email, 
			Long no_reg, String telp, String ktp, Ref<Resepsionis> resepsionis) {
		
		this.email_klinik = email_klinik;
		this.nama = nama; 
		this.alamat = alamat; 
		this.email = email;
		this.no_reg = no_reg;
		this.telp = telp; 
		this.ktp = ktp;
		this.resepsionis = resepsionis; 
	}
	
}
