package com.mobile.healthypets.model.pemilik;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.Subclass;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.pegawai.Pemakai; 

/**
 * Pemilik menyimpan info pemilik klinik
 * @author Lenovo
 *
 */
@SuppressWarnings("serial")
@Subclass (index = true)
public class Pemilik extends Pemakai {

//	@Index
//	private String nama;
//	@Index
//	private String alamat;
//	@Index
//	private String email;
//	@Index
//	private String identitas;
//	@Index
//	private String telp; 
	
	@Load
	private List<Key<Klinik>> daftarKlinik;	
	public List<Key<Klinik>> getDaftarKlinik() {
		return daftarKlinik;
	}

	private List<Pemakai> pemakai;	
	public List<Pemakai> getPemakai() {
		return pemakai;
	}

	@SuppressWarnings("unused")
	private Pemilik() {}
	
	public Pemilik(String nama, String alamat, String email, String identitas, String telp) {
		// panggil super untuk buat pemakai
		super(nama, alamat, email, identitas, telp);
		this.daftarKlinik = new ArrayList<>();
		
		this.pemakai = new ArrayList<>();

	}

}
