package com.mobile.healthypets.model.pemilik;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException; 
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.klinik.KlinikCtrl;
import com.mobile.healthypets.model.pegawai.Pemakai; 

public class PemilikCtrl {

	
//	//add pemilik
	@SuppressWarnings("unchecked")
	public List<Key<Klinik>> addKlinik(Long idPemilik, Long idKlinik) {  
		// ambil info pemilik
  		Pemilik pemilik = new PemilikCtrl().get(idPemilik);
  		Klinik klinik = new KlinikCtrl().get(idKlinik);
		
		if (pemilik.getDaftarKlinik() != null) {
			// tambah klinik baru ke dalam daftar
			pemilik.getDaftarKlinik().add(klinik.getKey());   
		}
		
		// simpan perubahan
		ofy().save().entity(pemilik);
			
		return pemilik.getDaftarKlinik();
	}
			
	public List<Pemilik> listAll(int offset, int limit){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
			List<Pemilik> pemilik = 
					ofy().load().type(Pemilik.class).offset(offset).limit(limit).list();
				
			if (pemilik == null || pemilik.size() < 1) {
				pemilik = new ArrayList<>();
			} 
		return pemilik;  
	}
	
		
	public Pemilik get(Long id) {		// dapat info dari 1 klinik		
		Key<Pemilik> key = Key.create(Pemilik.class, id);
		Pemilik pemilik = null; 
		try {
			pemilik = ofy().load().key(key).safe();
		} catch (NotFoundException e) { 
		}
		return pemilik;
	}
	
	
	public Pemilik create(String nama, String alamat, String email, String identitas, String telp){   //create klinik by admin
		  
		Pemilik pemilik = new Pemilik(nama, alamat, email, identitas, telp);		
		// tambah klinik ini ke Pemilik.klinik 
		ofy().save().entity(pemilik).now();

		return pemilik;
	}
	
	
	public Pemakai updatePemilik(Long id, String namaBaru, String alamatBaru, String identitasBaru, String telpBaru) {
		Pemakai pemilik = null;
		
		try { 
			pemilik = ofy().load().key(Key.create(Pemakai.class, id)).safe();
			if (!namaBaru.equals(pemilik.getNama())) {
				pemilik.setNama(namaBaru);
			}

			if (!alamatBaru.equals(pemilik.getAlamat())) {
				pemilik.setAlamat(alamatBaru);
			}
			
			if (!identitasBaru.equals(pemilik.getIdentitas())) {
				pemilik.setIdentitas(identitasBaru);
			}
			
			if (!telpBaru.equals(pemilik.getTelp())) {
				pemilik.setTelp(telpBaru);
			}

			// simpan semua perubahan
			ofy().save().entity(pemilik).now();
			 
			pemilik = get(id);

		} catch (NotFoundException e) { }
		
		return pemilik;
	}
}
