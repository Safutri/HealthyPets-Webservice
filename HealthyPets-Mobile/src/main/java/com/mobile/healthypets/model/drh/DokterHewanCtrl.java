package com.mobile.healthypets.model.drh;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 

public class DokterHewanCtrl {
	
	// list klinik
			public List<DokterHewan> list(int offset, int limit){
				if (offset < 0) {
					offset = KonfigurasiDasar.OFFSET;
				}
				if (limit < 1) {
					limit = KonfigurasiDasar.LIMIT;
				}
					List<DokterHewan> drh = 
							ofy().load().type(DokterHewan.class).offset(offset).limit(limit).list();
						
					if (drh == null || drh.size() < 1) {
						drh = new ArrayList<>();
					}
					//menampilkan data dengan offset & limit
				return drh;	
			} 

			// list klinik by pemilik
			public List<DokterHewan> listByOwner(int offset, int limit, Long id) {
				if (offset < 0) {
					offset = KonfigurasiDasar.OFFSET;
				}
				if (limit < 1) {
					limit = KonfigurasiDasar.LIMIT;
				}
				 
				// ambil info pemilik klinik
				Resepsionis resepsionis = new ResepsionisCtrl().get(id); //new
				List<DokterHewan> daftarDokterHewan = new ArrayList<>();
				daftarDokterHewan = ofy().load().type(DokterHewan.class).filter("resepsionis =", resepsionis).list();
				for (DokterHewan k: daftarDokterHewan) {
					k.getResepsionis();
				}
				
				return daftarDokterHewan;
			}			
			
			public List<DokterHewan> drhbyResepsionis(int offset, int limit, Long id) throws Exception {
				if (offset < 0) {
					offset = KonfigurasiDasar.OFFSET;
				}
				if (limit < 1) {
					limit = KonfigurasiDasar.LIMIT;
				}
				// ambil info pemilik cafe
				Resepsionis resepsionis = new ResepsionisCtrl().get(id);
				Ref<Resepsionis> refResepsionis = Ref.create(resepsionis);
				List<DokterHewan> daftarDokterHewan= ofy().load().type(DokterHewan.class).filter("resepsionis =", refResepsionis).list();
				
				return daftarDokterHewan;
			}
			
			// get drh
			public DokterHewan get(Long id) {	
				// buat key dari id
				Key<DokterHewan> key = Key.create(DokterHewan.class, id);
				DokterHewan drh = null; 
				try {
					drh = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
				}
				return drh;
			}
			
			public DokterHewan cariEmail(String email) {
				
				List<DokterHewan> daftarDokterHewan = null;
				DokterHewan drh = null;
				// baca docs safe()
				try {
					daftarDokterHewan = ofy().load().type(DokterHewan.class).filter("email", email).list();
					// kalau email > lebih dari satu 
				} catch (NotFoundException e) {}
				if (daftarDokterHewan.size() < 1) {
					
				} else {

					drh = daftarDokterHewan.get(0);	
				}
				return drh;
			} 
					
		public DokterHewan create(String nama, Long idResepsionis, String alamat, String email, String telp, 
				String no_praktik, String email_klinik, String nama_klinik) {
		 
			//create DokterHewan by klinik
			Resepsionis resepsionis= new ResepsionisCtrl().get(idResepsionis);
			Ref<Resepsionis> refResepsionis = Ref.create((Resepsionis) resepsionis);
			
			// entitas klinik yang baru
			DokterHewan drh = new DokterHewan(nama, refResepsionis, alamat, email, no_praktik, telp,
					email_klinik, nama_klinik);
					
			// tambah klinik ini ke Pemilik.klinik
			// simpan semua perubahan
			ofy().save().entity(drh).now();
			
			// ambil kembali info klinik yang baru disimpan
			drh = get(drh.getId());
			
			// simpan klinik yang sudah dibuat ke daftarKlinik di class pemakai
//			new KlinikCtrl().addDokterHewan(idResepsionis, drh.getId());
			
			return drh;
		}
		
		public DokterHewan updateDokterHewan(Long id, String namaBaru, String alamatBaru, String telpBaru, String no_praktikBaru,
				String email_klinik_baru, String nama_klinik_baru) {
			
			DokterHewan drh = null;
			
			try {
				// ambil dari datastore
				drh = ofy().load().key(Key.create(DokterHewan.class, id)).safe();
				// pastikan beda dan ubah
				if (!namaBaru.equals(drh.getNama())) {
					drh.setNama(namaBaru);
				}

				if (!alamatBaru.equals(drh.getAlamat())) {
					drh.setAlamat(alamatBaru);
				}
				  
				if (!telpBaru.equals(drh.getTelp())) {
					drh.setTelp(telpBaru);
				}
				
				if (!no_praktikBaru.equals(drh.getNo_praktik())) {
					drh.setNo_praktik(no_praktikBaru);
				}
				
				if (!email_klinik_baru.equals(drh.getEmail_klinik())) {
					drh.setEmail_klinik(email_klinik_baru);
				}
				
				if (!nama_klinik_baru.equals(drh.getNama_klinik())) {
					drh.setNama_klinik(nama_klinik_baru);
				}

				// simpan semua perubahan
				ofy().save().entity(drh).now();
				
				// ambil cafe yang telah diubah
				drh = get(id);

			} catch (NotFoundException e) { }
			
			return drh;
		}
		 
		
}
