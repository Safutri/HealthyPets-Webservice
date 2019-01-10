package com.admin.healthypets.pojo;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Load;
 
public class Pemilik {	
		
	private String id;	
	public String getId() {
		if (id == null) {
			return "";
		}
		return id;
	}
	
	private String nama;
	public String getNama() {
		if (nama == null) {
			return "";
		}
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	private String alamat;
	public String getAlamat() {
		if (alamat == null) {
			return "";
		}
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	private String email;
	public String getEmail() {
		if (email == null) {
			return "";
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	private String telp;
	public String getTelp() {
		if (telp == null) {
			return "";
		}
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}
	
	private String identitas;
	public String getIdentitas() {
		if (identitas == null) {
			return "";
		}
		return identitas;
	}

	public void setIdentitas(String identitas) {
		this.identitas = identitas;
	}
 
	@Load
	private List<Key<Klinik>> daftarKlinik;
	
	
	public List<Key<Klinik>> getDaftarKlinik() {
		return daftarKlinik;
	}
	
	public Pemilik(String id, String nama, String alamat, String email, String identitas, String telp) {
		this.id =id;
		this.nama =nama;
		this.email=email;
		this.identitas=identitas;
		this.telp=telp;
		this.alamat=alamat; 
		this.daftarKlinik = new ArrayList<>();	
		

	} 
}
