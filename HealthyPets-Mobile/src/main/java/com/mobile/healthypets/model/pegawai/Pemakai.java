package com.mobile.healthypets.model.pegawai;

import java.io.Serializable;
 
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Pemakai implements Serializable {

	@Id private Long id;
	
	@Index
	private String nama;
	private String alamat;
	private String email;
	private String identitas;
	private String telp; 
	
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

	public Long getId() {
		return id;
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
	

	public String getEmail() {
		return email;
	}

	protected Pemakai () {}
	
	public Pemakai(String nama, String alamat, String email, String identitas, String telp) {
		this.nama = nama;
		this.alamat = alamat; 
		this.email = email;
		this.identitas = identitas;
		this.telp = telp;
	}
	
	private static final long serialVersionUID = 1L;
	
//	
//	public Key<Pemakai> getRef(long id){
//		return Key.create(Pemakai.class,id);
//	}
}
