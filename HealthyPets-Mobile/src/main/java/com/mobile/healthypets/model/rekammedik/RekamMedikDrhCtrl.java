package com.mobile.healthypets.model.rekammedik;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar; 

public class RekamMedikDrhCtrl {
	
	public List <RekamMedikDrh> daftar(int offset, int limit, String kode_hewan){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		
		List <RekamMedikDrh> daftarRekamMedik = ofy().load().type(RekamMedikDrh.class).offset(offset).limit(limit).
				filter("kode_hewan", kode_hewan).list();
		
		if(daftarRekamMedik==null||daftarRekamMedik.size()<1){
			daftarRekamMedik = new ArrayList<>();
		}
		return daftarRekamMedik;
	}
	
//	public List <RekamMedikDrh> daftarRM(int offset, int limit, String kode_hewan){
//		if (offset < 0) {
//			offset = KonfigurasiDasar.OFFSET;
//		}
//		if (limit < 1) {
//			limit = KonfigurasiDasar.LIMIT;
//		}
//		
//		List<RekamMedikDrh> daftarRM = ofy().load().type(RekamMedikDrh.class).offset(offset).limit(limit).filter("kode_hewan", kode_hewan).list();
//		
//		if(daftarRM==null||daftarRM.size()<1){
//			daftarRM = new ArrayList<>();
//		}
//		return daftarRM;
//	}

	
	public RekamMedikDrh cariRMDrh(String kode_hewan){
		List<RekamMedikDrh> daftarRM = null;
		RekamMedikDrh rm = null;
			try {
				daftarRM = ofy().load().type(RekamMedikDrh.class).filter("kode_hewan", kode_hewan).list();
		} catch (NotFoundException e) {}
			if (daftarRM.size() < 1) {
				
			} else {

			 rm = daftarRM.get(0);	
			}
		return rm;
	}
	
	
	
	public RekamMedikDrh get(Long id){
		// buat key dari id
				Key<RekamMedikDrh> key = Key.create(RekamMedikDrh.class, id);
				RekamMedikDrh rm = null;
					try {
						rm = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return rm;
	}
	
	public RekamMedikDrh create(String tgl, String nama_drh, String status_awal, String terapi, String diagnosa, String obat,
			String kode_hewan, Long no_reg, String lain) {
   
		// entitas hewan yang baru
		 RekamMedikDrh rm = new  RekamMedikDrh(tgl, nama_drh, status_awal, terapi, diagnosa, obat, kode_hewan, no_reg, lain);
		
		// tambah cafe ini ke Pemilik.cafe
		// simpan semua perubahan
		ofy().save().entity(rm).now();

		return rm;
	}
	
}
