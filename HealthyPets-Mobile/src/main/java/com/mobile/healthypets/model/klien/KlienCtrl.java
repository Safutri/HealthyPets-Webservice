package com.mobile.healthypets.model.klien;

import static com.googlecode.objectify.ObjectifyService.ofy;
 
import java.util.ArrayList;
import java.util.List;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref; 
import com.googlecode.objectify.Result;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl;

public class KlienCtrl {
 
		public List <Klien> daftar(int offset, int limit){
			if (offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			if (limit < 1) {
				limit = KonfigurasiDasar.LIMIT;
			}
			
			List <Klien> daftarKlien = ofy().load().type(Klien.class).offset(offset).limit(limit).list();
			
			if(daftarKlien==null||daftarKlien.size()<1){
				daftarKlien = new ArrayList<>();
			}
			return daftarKlien;
		}
		
		public List <Klien> daftarKlien(int offset, int limit, Long idResepsionis, Long no_reg){
			if (offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			if (limit < 1) {
				limit = KonfigurasiDasar.LIMIT;
			}
			Resepsionis resepsionis = new ResepsionisCtrl().get(idResepsionis);
			Ref<Resepsionis> refResepsionis = Ref.create(resepsionis);
			List<Klien> daftarKlien = ofy().load().type(Klien.class).offset(offset).limit(limit).filter("resepsionis", refResepsionis).
					filter("no_reg", no_reg).list();
			
//			if(daftarKlien==null||daftarKlien.size()<1){
//				daftarKlien = new ArrayList<>();
//			}
			return daftarKlien;
		}		
		
		public List<Klien> daftarbyResepsionis(int offset, int limit, Long id) throws Exception {
			if (offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			if (limit < 1) {
				limit = KonfigurasiDasar.LIMIT;
			}
			// ambil info pemilik cafe
			
			Resepsionis resepsionis = new ResepsionisCtrl().get(id);
			Ref<Resepsionis> refResepsionis = Ref.create(resepsionis);
			List<Klien> daftarKlien= ofy().load().type(Klien.class).filter("resepsionis =", refResepsionis).list();
			
			return daftarKlien;
		}
		
		public List<Klien> daftarbyKlinik(int offset, int limit, String email_klinik) throws Exception {
			if (offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			if (limit < 1) {
				limit = KonfigurasiDasar.LIMIT;
			}
			// ambil info pemilik cafe
			List<Klien> daftarKlien= ofy().load().type(Klien.class).filter("email_klinik", email_klinik).list();
			
			return daftarKlien;
		}
		
	public Klien create(String email_klinik, String nama, String alamat, 
			String email, Long no_reg, String telp, String ktp, Long idResepsionis) {
 		 
		Key<Resepsionis> keyResepsionis = Key.create(Resepsionis.class, idResepsionis);
		Ref<Resepsionis> refResepsionis = Ref.create(keyResepsionis);
			
			Klien klien = new Klien(email_klinik, nama, alamat, email, no_reg, telp, ktp, refResepsionis); 
		 
 			ofy().save().entity(klien).now();
			
			return klien;
		} 
	
 	
	public Klien cari(Long id) throws Exception{
		// buat key dari id
				Key<Klien> key = Key.create(Klien.class, id);
				Klien klien = null;
					try {
						klien = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return klien;
	}
 	
	public Klien cariNoreg(Long no_reg){
		List<Klien> daftarKlien = null;
		Klien klien = null;
			try {
				daftarKlien = ofy().load().type(Klien.class).filter("no_reg", no_reg).list();
		} catch (NotFoundException e) {}
			if (daftarKlien.size() < 1) {
				
			} else {

				klien = daftarKlien.get(0);	
			}
		return klien;
	}
	
	
	
	public Klien cariNoreg(Long no_reg, Long id){
		Resepsionis resepsionis = new ResepsionisCtrl().get(id);
		Ref<Resepsionis> refResepsionis = Ref.create(resepsionis);
		List<Klien> daftarKlien = null;
		Klien klien = null;
			try {
				daftarKlien = ofy().load().type(Klien.class).filter("resepsionis =", refResepsionis).list();
		} catch (NotFoundException e) {}
			if (daftarKlien.size() < 1) {
				
			} else {

				klien = daftarKlien.get(0);	
			}
		return klien;
	}
	
	public List <Klien> daftarKlienKTP(int offset, int limit, Long idResepsionis, String ktp){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		Resepsionis resepsionis = new ResepsionisCtrl().get(idResepsionis);
		Ref<Resepsionis> refResepsionis = Ref.create(resepsionis);
		List<Klien> daftarKlien = ofy().load().type(Klien.class).offset(offset).limit(limit).filter("resepsionis", refResepsionis).
				filter("ktp", ktp).list();
		
//		if(daftarKlien==null||daftarKlien.size()<1){
//			daftarKlien = new ArrayList<>();
//		}
		return daftarKlien;
	}	
		
	public Klien updateKlien(Long id, String nama, String alamat, String email,
			String telp, String ktp) throws Exception{
		Klien klien = null;
		
		Result<Klien> result = ofy().load().key(Key.create(Klien.class, id));
		klien = result.now();
		
		klien.setNama(nama);
		klien.setAlamat(alamat);
		klien.setEmail(email);
		klien.setTelp(telp);
		klien.setKtp(ktp); 
			ofy().save().entities(klien).now();
			
			result = ofy().load().key(Key.create(Klien.class, id));
			klien = result.now();
		
		return klien;
		
	}
}
