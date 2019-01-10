package com.admin.healthypets.pojo;

public class Pemakai {  
	
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
	
	public Pemakai(String nama, String alamat, String email, String identitas, String telp) {
		this.nama = nama;
		this.alamat = alamat; 
		this.email = email;
		this.identitas = identitas;
		this.telp = telp;
	}

}
