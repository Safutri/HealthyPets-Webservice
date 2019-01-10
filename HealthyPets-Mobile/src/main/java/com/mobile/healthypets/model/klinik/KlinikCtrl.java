package com.mobile.healthypets.model.klinik;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pemilik.PemilikCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 

public class KlinikCtrl {
	
	// list klinik
	public List<Klinik> list(int offset, int limit){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
			List<Klinik> klinik = 
					ofy().load().type(Klinik.class).offset(offset).limit(limit).list();
				
			if (klinik == null || klinik.size() < 1) {
				klinik = new ArrayList<>();
			}
			//menampilkan data dengan offset & limit
		return klinik;	
	} 
	
	// list klinik by pemilik
	public List<Klinik> listByOwner(int offset, int limit, Long id) {
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		 
		// ambil info pemilik klinik
		Pemilik pemilik = new PemilikCtrl().get(id); //new
		List<Klinik> daftarKlinik = new ArrayList<>();
		daftarKlinik = ofy().load().type(Klinik.class).filter("pemakai =", pemilik).list();
		for (Klinik k: daftarKlinik) {
			k.getPemakai();
		}
		
		return daftarKlinik;
	}
	
	
//	//add resepsionis
	@SuppressWarnings("unchecked")
	public List<Key<Resepsionis>> addResepsionis(Long idKlinik, Long idResepsionis) {  
		// ambil info pemilik
  		Klinik klinik = new KlinikCtrl().get(idKlinik);
  		Resepsionis resepsionis = new ResepsionisCtrl().get(idResepsionis);
		
		if (klinik.getDaftarResepsionis() != null) {
			// tambah cafe baru ke dalam daftar
			klinik.getDaftarResepsionis().add(resepsionis.getKey());   
		}
		
		// simpan perubahan
		ofy().save().entity(klinik);
			
		return klinik.getDaftarResepsionis();
	}
	   
	public Klinik get(Long id) {		// get Klinik
		// buat key dari id
		Key<Klinik> key = Key.create(Klinik.class, id);
		Klinik klinik = null;
		// baca docs safe()
		try {
			klinik = ofy().load().key(key).safe();
		} catch (NotFoundException e) {
			// pastikan eksepsi yang ditangkap dari package objectify
			// tangani kesalahan
		}
 

		return klinik;
	}
	
	// create klinik
		// buat entitas klinik
		// dan simpan klinik ke daftarKlinik di class Pemilik
	
	public Klinik create(String nama, Long idPemilik, String alamat, String email, String telp, String identitas) {
		//create klinik by pemilik
		Pemilik pemilik = new PemilikCtrl().get(idPemilik);
		Ref<Pemakai> refPemakai = Ref.create((Pemakai) pemilik);	
		
		// entitas klinik yang baru
		Klinik klinik = new Klinik(nama, refPemakai, alamat, email, telp, identitas);  //here admin id
		
		// tambah klinik ini ke Pemilik.klinik
		// simpan semua perubahan
		ofy().save().entity(klinik).now();
		
		// ambil kembali info klinik yang baru disimpan
		klinik = get(klinik.getId());
		
		// simpan klinik yang sudah dibuat ke daftarKlinik di class pemakai
		new PemilikCtrl().addKlinik(idPemilik, klinik.getId());
		
		return klinik;
	}
	
	public Klinik updateKlinik(Long id, String namaBaru, String alamatBaru, String praktikBaru, String emailBaru, 
			String telpBaru) {
		
		Klinik klinik = null;
		
		try {
			// ambil dari datastore
			klinik  = ofy().load().key(Key.create(Klinik.class, id)).safe();
			// pastikan beda dan ubah
			if (!namaBaru.equals(klinik.getNama())) {
				klinik.setNama(namaBaru);
			}

			if (!alamatBaru.equals(klinik.getAlamat())) {
				klinik.setAlamat(alamatBaru);
			}
			
			if (!praktikBaru.equals(klinik.getIdentitas())) {
				klinik.setIdentitas(praktikBaru);
			}
			
			if (!emailBaru.equals(klinik.getEmail())) {
				klinik.setEmail(emailBaru);
			}
			
			if (!telpBaru.equals(klinik.getTelp())) {
				klinik.setTelp(telpBaru);
			}

			// simpan semua perubahan
			ofy().save().entity(klinik).now();
			
			// ambil cafe yang telah diubah
			klinik = get(id);

		} catch (NotFoundException e) { }
		
		return klinik;
	}

}
