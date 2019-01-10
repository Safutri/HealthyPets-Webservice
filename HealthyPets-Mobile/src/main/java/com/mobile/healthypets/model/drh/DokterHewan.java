package com.mobile.healthypets.model.drh;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Subclass;
import com.mobile.healthypets.model.pegawai.Pegawai;
import com.mobile.healthypets.model.resepsionis.Resepsionis; 

@SuppressWarnings("serial")
@Subclass (index = true)
public class DokterHewan extends Pegawai{
	
	@Index
	public String nama;
	@Index
	public String alamat;
	@Index
	public String telp;
	@Index
	public String no_praktik; 
	@Index
	private String nama_klinik;
	@Index
	private String email_klinik;


	@Load
	@Index
	private Ref<Resepsionis> resepsionis;
	 
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
	
	public Ref<Resepsionis> getResepsionis() {
		return resepsionis;
	}

	public void setResepsionis(Ref<Resepsionis> resepsionis) {
		this.resepsionis = resepsionis;
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

	public String getNo_praktik() {
		return no_praktik;
	}

	public void setNo_praktik(String no_praktik) {
		this.no_praktik = no_praktik;
	}  
	
	@SuppressWarnings("unused")
	private DokterHewan () {}
	
	public DokterHewan(String nama, Ref<Resepsionis> resepsionis, String alamat, String email, String no_praktik,
			String telp, String email_klinik, String nama_klinik) {
		super(email);
		this.nama = nama; 
		this.alamat = alamat; 
		this.no_praktik = no_praktik;
		this.telp = telp;  
		this.resepsionis = resepsionis;
		this.email_klinik = email_klinik;
		this.nama_klinik = nama_klinik;
	}

}
