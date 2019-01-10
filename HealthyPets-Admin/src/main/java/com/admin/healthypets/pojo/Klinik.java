package com.admin.healthypets.pojo;

import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList;
//import java.util.List;

public class Klinik {
	private String id;
	private Pemakai pemakai;
	
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
	public String getalamat() {
		if (alamat == null) {
			return "";
		}
		return alamat;
	}

	public void setalamat(String alamat) {
		this.alamat = alamat;
	}
	
	private String email;
	public String getemail() {
		if (email == null) {
			return "";
		}
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}
	
	private String telp;
	public String gettelp() {
		if (telp == null) {
			return "";
		}
		return telp;
	}

	public void settelp(String telp) {
		this.telp = telp;
	}
	
	private String identitas;
	public String getidentitas() {
		if (identitas == null) {
			return "";
		}
		return identitas;
	}

	public void setidentitas(String identitas) {
		this.identitas = identitas;
	}
//	
//	private List<Resepsionis> daftarResepsionis;		
//	public List<Resepsionis> getDaftarResepsionis() {
//		if (daftarResepsionis == null) {
//			return new ArrayList<>();
//		}
//			return daftarResepsionis;
//		} 
	
	public Long getIdPemakai(){
		if(pemakai == null){
			return 0L;
		}
		return (Long) pemakai.getId();
	}
	

	private List<Klinik> daftarKlinik;		
	public Klinik() {
		this.daftarKlinik = new ArrayList<>();
	}
	
	public void addDaftarKlinik(Klinik klinik) {
		this.daftarKlinik.add(klinik);
	}
	
	public Klinik(String id, String nama, String alamat, String email, 
			String telp, Pemakai pemakai, String identitas) {
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.email = email;
		this.telp = telp; 
//		this.daftarResepsionis =daftarResepsionis;
		this.pemakai = pemakai;
		this.identitas=identitas;
	}
 
}
