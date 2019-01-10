package com.mobile.healthypets.model.klinik;

import java.beans.Transient;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.mobile.healthypets.model.drh.DokterHewan;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.resepsionis.Resepsionis; 

@Entity
public class Klinik {
	@Id private Long id;
	
	@Index 
	private String nama;
	private String alamat;
	private String email;
	
	@Index
	private String telp;
	private String identitas;
	
	@Load
	@Index
	private Ref<Pemakai> pemakai;

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}

	public String getIdentitas() {
		return identitas;
	}

	public void setIdentitas(String identitas) {
		this.identitas = identitas;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Ref<Pemakai> getPemakai() {
		return pemakai;
	}

	public void setPemakai(Ref<Pemakai> pemakai) {
		this.pemakai= pemakai;
	}

	public Long getId() {
		return id;
	}
	

	@SuppressWarnings("rawtypes")
	@Transient
	public Key getKey() {
		return Key.create(Klinik.class, id);
	}
	
	@SuppressWarnings("unused")
	private Klinik(){}

	public Klinik(String nama, Ref<Pemakai> pemakai, String alamat, String email, String telp, String identitas) {
		super();
		this.nama = nama;
		this.alamat = alamat;
		this.email = email;
		this.telp = telp;
		this.identitas = identitas;
		this.pemakai = pemakai;
	}

	@Load
	private List<Key<Resepsionis>> daftarResepsionis;
	public List<Key<Resepsionis>> getDaftarResepsionis() {
		//  Auto-generated method stub
		return daftarResepsionis;
	}
	
	@Load
	private List<Key<DokterHewan>> daftarDokterHewan;
	public List<Key<DokterHewan>> getDaftarDokterHewan() {
		//Auto-generated method stub
		return daftarDokterHewan;
	}

	
	//untuk hapus data di unit tes 
//		@Transient
//		public Key<Klinik> getKey(){
//			return Key.create(Klinik.class,id);
//		}
		
}
