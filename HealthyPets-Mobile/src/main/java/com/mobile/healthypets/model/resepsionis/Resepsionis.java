package com.mobile.healthypets.model.resepsionis;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Subclass;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.pegawai.Pegawai; 
 
@SuppressWarnings("serial")
@Subclass (index = true)
public class Resepsionis extends Pegawai{
 
	@Index
	public String nama;
	@Index
	public String alamat;
	@Index
	public String telp;  
	@Index
	private String nama_klinik;
	@Index
	private String email_klinik;
	
	@Load
	@Index
	private Ref<Klinik> klinik;	
	 
	public String getNama_klinik() {
		return nama_klinik;
	}

	public void setNama_klinik(String nama_klinik) {
		this.nama_klinik = nama_klinik;
	}

	public String getEmail_klinik() {
		return email_klinik;
	}

	public void setEmail_klinik(String email_klinik) {
		this.email_klinik = email_klinik;
	}
	 
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
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
	  
	public Ref<Klinik> getKlinik() {
		return klinik;
	}

	public void setKlinik(Ref<Klinik> klinik) {
		this.klinik= klinik;
	}  
	
	@SuppressWarnings("unused")
	private Resepsionis () {}
	
	public Resepsionis(String nama, Ref<Klinik> klinik, String alamat, String email, 
			String telp, String email_klinik, String nama_klinik) {	 
		super(email);
		this.nama = nama;
		this.klinik = klinik;
		this.alamat = alamat;  
		this.telp = telp;  
		this.email_klinik = email_klinik;
		this.nama_klinik = nama_klinik;
	}
 
}
