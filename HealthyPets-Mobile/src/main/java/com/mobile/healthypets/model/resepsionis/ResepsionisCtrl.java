package com.mobile.healthypets.model.resepsionis;

import static com.googlecode.objectify.ObjectifyService.ofy;  
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref; 
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.drh.DokterHewan;
import com.mobile.healthypets.model.drh.DokterHewanCtrl;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.klinik.KlinikCtrl; 

public class ResepsionisCtrl {
	
	// list klinik
		public List<Resepsionis> list(int offset, int limit){
			if (offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			if (limit < 1) {
				limit = KonfigurasiDasar.LIMIT;
			}
				List<Resepsionis> daftaraResepsionis = 
						ofy().load().type(Resepsionis.class).offset(offset).limit(limit).list();
					
				if (daftaraResepsionis == null || daftaraResepsionis.size() < 1) {
					daftaraResepsionis= new ArrayList<>();
				}
				//menampilkan data dengan offset & limit
			return daftaraResepsionis;	
		} 
		 		
		
		// list klinik by pemilik
		public List<Resepsionis> listByOwner(int offset, int limit, Long id) {
			if (offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			if (limit < 1) {
				limit = KonfigurasiDasar.LIMIT;
			}
			 
			// ambil info pemilik klinik
			Klinik klinik = new KlinikCtrl().get(id); //new
			List<Resepsionis> daftarResepsionis = new ArrayList<>();
			daftarResepsionis = ofy().load().type(Resepsionis.class).filter("klinik =", klinik).list();
			for (Resepsionis k: daftarResepsionis) {
				k.getKlinik();
			}
			
			return daftarResepsionis;
		}
		
		// get Resepsionis
		public Resepsionis get(Long id) {	
			// buat key dari id
			Key<Resepsionis> key = Key.create(Resepsionis.class, id);
			Resepsionis resepsionis = null; 
			try {
				resepsionis = ofy().load().key(key).safe();
			} catch (NotFoundException e) {
				// pastikan eksepsi yang ditangkap dari package objectify
				// tangani kesalahan
			}
			return resepsionis;
		}

		public Resepsionis cariEmail(String email) {
			
			List<Resepsionis> daftarResepsionis = null;
			Resepsionis resepsionis = null;
			// baca docs safe()
			try {
				daftarResepsionis = ofy().load().type(Resepsionis.class).filter("email", email).list();
				// kalau email > lebih dari satu 
			} catch (NotFoundException e) {}
			if (daftarResepsionis.size() < 1) {
				
			} else {

				resepsionis = daftarResepsionis.get(0);	
			}
			return resepsionis;
		}
		  
			
		public Resepsionis create(String nama, Long idKlinik, String alamat, String email, String telp, 
			 String email_klinik, String nama_klinik) {			 
			Klinik klinik = new KlinikCtrl().get(idKlinik);
			Ref<Klinik> refKlinik = Ref.create((Klinik) klinik); 
			
			// entitas klinik yang baru
			Resepsionis resepsionis = new Resepsionis(nama, refKlinik, alamat, email, telp,
					email_klinik, nama_klinik);  //here admin id
			
		
			// tambah klinik ini ke Pemilik.klinik
			// simpan semua perubahan
			ofy().save().entity(resepsionis).now();
			
			// ambil kembali info klinik yang baru disimpan
			resepsionis = get(resepsionis.getId());
			
			// simpan klinik yang sudah dibuat ke daftarKlinik di class pemakai
			new KlinikCtrl().addResepsionis(idKlinik, resepsionis.getId());
			
			return resepsionis;
		}
			
			public Resepsionis updateResepsionis(Long id, String namaBaru, String alamatBaru, String telpBaru) {
				
				Resepsionis resepsionis = null;
				
				try {
					// ambil dari datastore
					resepsionis = ofy().load().key(Key.create(Resepsionis.class, id)).safe();
					// pastikan beda dan ubah
					if (!namaBaru.equals(resepsionis.getNama())) {
						resepsionis.setNama(namaBaru);
					}

					if (!alamatBaru.equals(resepsionis.getAlamat())) {
						resepsionis.setAlamat(alamatBaru);
					}
					  
					if (!telpBaru.equals(resepsionis.getTelp())) {
						resepsionis.setTelp(telpBaru);
					}
					
//					if (!email_klinik_baru.equals(resepsionis.getEmail_klinik())) {
//						resepsionis.setEmail_klinik(email_klinik_baru);
//					}
//					
//					if (!nama_klinik_baru.equals(resepsionis.getNama_klinik())) {
//						resepsionis.setNama_klinik(nama_klinik_baru);
//					}

					// simpan semua perubahan
					ofy().save().entity(resepsionis).now();
					
					// ambil cafe yang telah diubah
					resepsionis = get(id);

				} catch (NotFoundException e) { }
				
				return resepsionis;
			} 
			
			// list klinik by pemilik
			public List<DokterHewan> listDrh(Long id) { 
			// ambil info pemilik klinik
				DokterHewan drh = new DokterHewanCtrl().get(id); //new
//				Ref<DokterHewan> refDokterHewan= Ref.create(drh); 
				List<DokterHewan> daftar= ofy().load().type(DokterHewan.class).filter("DokterHewan =", drh).list();
			return daftar;
			}

}
