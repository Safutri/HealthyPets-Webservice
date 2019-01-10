package com.admin.healthypets.pojo;

public class DokterHewan { 
	
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
	public String getalamat() {
		if (alamat == null) {
			return "";
		}
		return alamat;
	}

	public void setalamat(String alamat) {
		this.alamat = alamat;
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
	
	private String no_praktik;
	public String getno_praktik() {
		if (no_praktik == null) {
			return "";
		}
		return no_praktik;
	}

	public void setno_praktik(String no_praktik) {
		this.no_praktik = no_praktik;
	}
	
	private String nama_klinik;
	public String getnama_klinik() {
		if (nama_klinik == null) {
			return "";
		}
		return nama_klinik;
	}

	public void setnama_klinik(String nama_klinik) {
		this.nama_klinik = nama_klinik;
	}
	
	private String email_klinik;
	public String getemail_klinik() {
		if (email_klinik == null) {
			return "";
		}
		return email_klinik;
	}

	public void setemail_klinik(String email_klinik) {
		this.email_klinik = email_klinik;
	}
	
	public DokterHewan(String nama, String id, String alamat, String email, String no_praktik,
			String telp, String email_klinik, String nama_klinik){
		this.nama = nama; 
		this.id=id;
		this.alamat = alamat; 
		this.email = email;
		this.no_praktik = no_praktik;
		this.telp = telp;   
		this.email_klinik = email_klinik;
		this.nama_klinik = nama_klinik;
	}
}
