package com.admin.healthypets.pojo;


public class Resepsionis {
private Long id;
	
	public Long getId() {
		if (id == null) {
			return 0L;
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
	
	private String email;
	public String getemail() {
		if (email == null) {
			return "";
		}
		return email;
	}

	public void settelp(String telp) {
		this.telp = telp;
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
	
	public Resepsionis(String nama, Long id, String alamat, String email, String telp, 
			String nama_klinik, String email_klinik) {
		this.id = id;
		this.nama = nama;
		this.email=email;
		this.alamat = alamat;
		this.telp = telp;
		this.nama_klinik = nama_klinik;
		this.email_klinik = email_klinik;
	}
}
