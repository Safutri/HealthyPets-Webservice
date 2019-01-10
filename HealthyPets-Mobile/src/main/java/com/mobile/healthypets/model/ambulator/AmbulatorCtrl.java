package com.mobile.healthypets.model.ambulator;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.Result;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.hewan.HewanCtrl;
import com.mobile.healthypets.model.pilihdrh.PilihDrh; 
 
public class AmbulatorCtrl {
 
	public List <Ambulator> daftar(int offset, int limit){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List <Ambulator> daftarAmbulator = ofy().load().type(Ambulator.class).offset(offset).limit(limit).list();
		
		if(daftarAmbulator==null||daftarAmbulator.size()<1){
			daftarAmbulator = new ArrayList<>();
		}
		return daftarAmbulator;
	}
	
	public List <Ambulator> daftarAmb(int offset, int limit, String kode_hewan){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List<Ambulator> daftarAmb = ofy().load().type(Ambulator.class).offset(offset).limit(limit).
				filter("kode_hewan", kode_hewan).list();
		
		if(daftarAmb==null||daftarAmb.size()<1){
			daftarAmb = new ArrayList<>();
		}
		return daftarAmb;
	}
	
	 
	public List<Ambulator> daftarbyHewan(int offset, int limit, Long id) throws Exception {
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		// ambil info pemilik klien
		Hewan hewan = new HewanCtrl().get(id);
		Ref<Hewan> refHewan = Ref.create(hewan);
		List<Ambulator> daftarAmbulator= ofy().load().type(Ambulator.class).filter("hewan =", refHewan).list();
		
		return daftarAmbulator;
	}
	
	public Ambulator create(String tgl, String sinyalmen, String anamnesa, String gizi, String tempramen,
			String habitat, String frek_nafas, String frek_pulsus, String suhu_tubuh, 
			String kulit_bulu, String sedir_mata, String sedir_hidung, String sedir_mulut,
			String sedir_anus, String k_limfase, String a_nafas, String a_darah, String a_cerna,
			String a_kelamin, String u_saraf, String ang_gerak, String lain, Long drh_jaga, Long idHewan, Long idPilih,
			Long no_reg, String ktp, String kode_hewan) {
		// buat entitas ambulator

		Key<Hewan> keyHewan = Key.create(Hewan.class, idHewan);
		Ref<Hewan> refHewan = Ref.create(keyHewan);
		
		Key<PilihDrh> keyPilih= Key.create(PilihDrh.class, idPilih);
		Ref<PilihDrh> refPilih = Ref.create(keyPilih);
		
		// entitas hewan yang baru
		Ambulator ambulator = new Ambulator(tgl, sinyalmen, anamnesa, gizi, tempramen, habitat, frek_nafas, 
				frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut, sedir_anus,
				k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga, refHewan, refPilih,
				no_reg, ktp, kode_hewan);
		
		// tambah cafe ini ke Pemilik.cafe
		// simpan semua perubahan
		ofy().save().entity(ambulator).now();

		return ambulator;
	}
	
	public Ambulator get(Long id){
		// buat key dari id
				Key<Ambulator> key = Key.create(Ambulator.class, id);
				Ambulator ambulator = null;
					try {
						ambulator = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return ambulator;
	}
	
	public Ambulator cariNoreg(Long no_reg){
		List<Ambulator> daftarAmbulator = null;
		Ambulator ambulator = null;
			try {
				daftarAmbulator = ofy().load().type(Ambulator.class).filter("no_reg", no_reg).list();
		} catch (NotFoundException e) {}
			if (daftarAmbulator.size() < 1) {
				
			} else {

				ambulator = daftarAmbulator.get(0);	
			}
		return ambulator;
	}
	
	public Ambulator get_drh(Long drh_jaga){
		List<Ambulator> daftarAmbulator = null;
		Ambulator ambulator = null;
			try {
				daftarAmbulator = ofy().load().type(Ambulator.class).filter("drh_jaga", drh_jaga).list();
		} catch (NotFoundException e) {}
			if (daftarAmbulator.size() < 1) {
				
			} else {

				ambulator = daftarAmbulator.get(0);	
			}
		return ambulator;
	}

	public Ambulator updateAmb(Long id, String sinyalmen, String anamnesa, String gizi, String tempramen,
			String habitat, String frek_nafas, String frek_pulsus, String suhu_tubuh, 
			String kulit_bulu, String sedir_mata, String sedir_hidung, String sedir_mulut,
			String sedir_anus, String k_limfase, String a_nafas, String a_darah, String a_cerna,
			String a_kelamin, String u_saraf, String ang_gerak, String lain) throws Exception{
		Ambulator ambulator = null;
		
		Result<Ambulator> result = ofy().load().key(Key.create(Ambulator.class, id));
		ambulator = result.now();
		 
		ambulator.setSinyalmen(sinyalmen);
		ambulator.setAnamnesa(anamnesa);
		ambulator.setGizi(gizi);
		ambulator.setTempramen(tempramen);
		ambulator.setHabitat(habitat); 
		ambulator.setFrek_nafas(frek_nafas); 
		ambulator.setFrek_pulsus(frek_pulsus); 
		ambulator.setSuhu_tubuh(suhu_tubuh); 
		ambulator.setKulit_bulu(kulit_bulu); 
		ambulator.setSedir_mata(sedir_mata); 
		ambulator.setSedir_mulut(sedir_mulut); 
		ambulator.setSedir_anus(sedir_anus); 
		ambulator.setK_limfase(k_limfase); 
		ambulator.setA_nafas(a_nafas); 
		ambulator.setA_darah(a_darah); 
		ambulator.setA_cerna(a_cerna); 
		ambulator.setA_kelamin(a_kelamin); 
		ambulator.setU_saraf(u_saraf); 
		ambulator.setAng_gerak(ang_gerak); 
		ambulator.setLain(lain); 
		
			ofy().save().entities(ambulator).now();
			
			result = ofy().load().key(Key.create(Ambulator.class, id));
			ambulator = result.now();
		
		return ambulator;
		
	} 
	
 	
	public void hapus(Long id) throws Exception{
		Ambulator amb = get(id);
		ofy().delete().entity(amb).now();
	}
	
   
	
	
	public Ambulator cariAmbulator(String kode_hewan){
	List<Ambulator> daftaramb = null;
	Ambulator amb = null;
		try {
			daftaramb = ofy().load().type(Ambulator.class).filter("kode_hewan", kode_hewan).list();
	} catch (NotFoundException e) {}
		if (daftaramb.size() < 1) {
			
		} else {

			amb = daftaramb.get(0);	
		}
	return amb;
}
	 
	
}
