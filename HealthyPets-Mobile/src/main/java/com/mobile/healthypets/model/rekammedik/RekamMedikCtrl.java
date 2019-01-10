package com.mobile.healthypets.model.rekammedik;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.Result;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.ambulator.Ambulator; 
 
public class RekamMedikCtrl {
	public List <RekamMedik> daftar(int offset, int limit, String kode_hewan){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		
		List <RekamMedik> daftarRekamMedik = ofy().load().type(RekamMedik.class).offset(offset).limit(limit).
				filter("kode_hewan", kode_hewan).list();
		
		if(daftarRekamMedik==null||daftarRekamMedik.size()<1){
			daftarRekamMedik = new ArrayList<>();
		}
		return daftarRekamMedik;
	}
	
	public RekamMedik cariRM(Long idAmb){
		List<RekamMedik> daftarRM = null;
		RekamMedik rm = null;
			try {
				daftarRM = ofy().load().type(RekamMedik.class).filter("idAmb", idAmb).list();
		} catch (NotFoundException e) {}
			if (daftarRM.size() < 1) {
				
			} else {

			 rm = daftarRM.get(0);	
			}
		return rm;
	}
	
	public List <RekamMedik> daftarRM(int offset, int limit, String kode_hewan){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List<RekamMedik> daftarRM = ofy().load().type(RekamMedik.class).offset(offset).limit(limit).filter("kode_hewan", kode_hewan).list();
		
		if(daftarRM==null||daftarRM.size()<1){
			daftarRM = new ArrayList<>();
		}
		return daftarRM;
	}
	
//	public List<RekamMedik> daftarbyAmbulator(int offset, int limit, Long id) throws Exception {
//		if (offset < 0) {
//			offset = KonfigurasiDasar.OFFSET;
//		}
//		if (limit < 1) {
//			limit = KonfigurasiDasar.LIMIT;
//		}
//		// ambil info pemilik klien
//		Ambulator ambulator = new AmbulatorCtrl().get(id);
//		Ref<Ambulator> refAmbulator= Ref.create(ambulator);
//		List<RekamMedik> daftarRekamMedik= ofy().load().type(RekamMedik.class).filter("rekammaedik =", refAmbulator).list();
//		
//		return daftarRekamMedik;
//	}
	
	public RekamMedik create(String tgl, String nama_drh, String status_awal, String terapi, 
			String diagnosa, String obat, String kode_hewan, Long no_reg, String lain, Long idAmbulator, Long idAmb) {
  
		Key<Ambulator> keyAmbulator = Key.create(Ambulator.class, idAmbulator);
		Ref<Ambulator> refAmbulator = Ref.create(keyAmbulator);
		
		// entitas hewan yang baru
		 RekamMedik rm = new  RekamMedik(tgl, nama_drh, status_awal, terapi, diagnosa, obat, kode_hewan, no_reg, 
				 lain, refAmbulator, idAmb);
		
		// tambah cafe ini ke Pemilik.cafe
		// simpan semua perubahan
		ofy().save().entity(rm).now();

		return rm;
	}
	
	public RekamMedik get(Long id){
		// buat key dari id
				Key<RekamMedik> key = Key.create(RekamMedik.class, id);
				RekamMedik rm = null;
					try {
						rm = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return rm;
	}
	
	public RekamMedik updateRM(Long id, String status_awal, String terapi, String diagnosa, String obat) throws Exception{
		RekamMedik rm = null;
		
		Result<RekamMedik> result = ofy().load().key(Key.create(RekamMedik.class, id));
		rm = result.now();
		
		rm.setStatus_awal(status_awal);
		rm.setTerapi(terapi);
		rm.setDiagnosa(diagnosa);
		rm.setObat(obat);
		
			ofy().save().entities(rm).now();
			
			result = ofy().load().key(Key.create(RekamMedik.class, id));
			rm = result.now();
		
		return rm;
		
	}

}
