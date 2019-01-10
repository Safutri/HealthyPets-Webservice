package com.mobile.healthypets.model.antriandrh;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar; 
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 

public class AntrianKlienCtrl {
	
	public List <AntrianKlien> daftar(int offset, int limit, Long drh_jaga){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List <AntrianKlien> daftarAntrian = ofy().load().type(AntrianKlien.class).offset(offset).limit(limit).filter("drh_jaga", drh_jaga).list();
		
		if(daftarAntrian==null||daftarAntrian.size()<1){
			daftarAntrian = new ArrayList<>();
		}
		
		List<AntrianKlien> daftarAntrianBaru = UrutkanAntrian(daftarAntrian);
		
		return daftarAntrianBaru;
	}
	 
	public List<AntrianKlien> daftarbyResepsionis(int offset, int limit, Long id) throws Exception {
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		Resepsionis resepsionis = new ResepsionisCtrl().get(id);
		Ref<Resepsionis > refResepsionis  = Ref.create(resepsionis );
		List<AntrianKlien> daftarAntrian = ofy().load().type(AntrianKlien.class).filter("resepsionis =", refResepsionis).offset(offset).limit(limit).list();
		Integer.toString(daftarAntrian.size());
		List<AntrianKlien> daftarAntrianBaru = UrutkanAntrian(daftarAntrian);
		
		return daftarAntrianBaru;
	}
	
	 public List<AntrianKlien> UrutkanAntrian(List<AntrianKlien> daftarAntrian){
		List<AntrianKlien> daftarAntrianBaru = daftarAntrian;
		AntrianKlien temp;
		 for (int i = 0; i < daftarAntrianBaru.size(); i++) {

	            for (int j = 1; j < (daftarAntrianBaru.size() - i); j++) {

	                if (Integer.parseInt(daftarAntrianBaru.get(j - 1).getAntri()) > Integer.parseInt(daftarAntrianBaru.get(j).getAntri())) {
	                    temp = daftarAntrianBaru.get(j - 1);
//	                    daftarAntrianBaru.set(j-1) = daftarAntrianBaru.get(j);
	                  daftarAntrianBaru.set(j-1, daftarAntrianBaru.get(j));
	                  daftarAntrianBaru.set(j, temp);
	                }
	            }			
		
		 }
		return daftarAntrianBaru;	
		
	}
		
	public AntrianKlien create(String nama, Long idHewan, Long idResepsionis, String jenis, String ras, 
			String umur, String gender, String ttl, String warna, Long no_reg, String ktp, Long drh_jaga, String kode_hewan,
			Long ambs, Long drhPilih) {
		
		Key<Hewan> keyHewan = Key.create(Hewan.class, idHewan);
		Ref<Hewan> refHewan = Ref.create(keyHewan);
		
		Key<Resepsionis> keyResepsionis = Key.create(Resepsionis.class, idResepsionis);
		Ref<Resepsionis> refResepsionis = Ref.create(keyResepsionis);	 
		
		List<AntrianKlien> antri = ofy().load().type(AntrianKlien.class).filter("resepsionis =", refResepsionis).list();
		
		// entitas hewan yang baru
		AntrianKlien Antrianklien = new AntrianKlien(nama, refHewan, refResepsionis, jenis, ras, umur, gender, ttl, warna,
				no_reg, ktp, drh_jaga, kode_hewan, ambs, Integer.toString(antri.size()), drhPilih);
		
		// tambah cafe ini ke Pemilik.cafe
		// simpan semua perubahan
		ofy().save().entity(Antrianklien).now();

		return Antrianklien;
	}
	
	public AntrianKlien get(Long id){
		// buat key dari id
				Key<AntrianKlien> key = Key.create(AntrianKlien.class, id);
				AntrianKlien Antrianklien = null;
					try {
						Antrianklien = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return Antrianklien;
	} 
	 
	public void deleteAntrianKlien(Long id) {
		try {
			// ambil dari datastore
			AntrianKlien  Antrian = ofy().load().key(Key.create(AntrianKlien.class, id)).safe();

			// hapus sekarang
			ofy().delete().entity(Antrian).now();
		 
		} catch (NotFoundException e) {}	
	}   
	
	
//	public void deleteAntrian(String nama) {
//		try {
//			// ambil dari datastore
//			AntrianKlien  Antrian = ofy().load().key(Key.create(AntrianKlien.class, nama)).safe();
//
//			// hapus sekarang
//			ofy().delete().entity(Antrian).now();
//			
//
//		} catch (NotFoundException e) {}	
//	} 
}
