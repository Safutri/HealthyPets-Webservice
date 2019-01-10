package com.mobile.healthypets.model.antrianhewan;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List; 
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.hewan.HewanCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 

public class AntrianHewanCtrl {
	public List <AntrianHewan> daftar (int offset, int limit){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List <AntrianHewan> daftarHewan = ofy().load().type(AntrianHewan.class).offset(offset).limit(limit).list();
		
		if(daftarHewan==null||daftarHewan.size()<1){
			daftarHewan = new ArrayList<>();
		}
		return daftarHewan;
	}
	
	public List<AntrianHewan> daftarbyResepsionis(int offset, int limit, Long id) throws Exception {
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		Resepsionis resepsionis = new ResepsionisCtrl().get(id);
		Ref<Resepsionis > refResepsionis  = Ref.create(resepsionis );
		List<AntrianHewan> daftarAntri = ofy().load().type(AntrianHewan.class).filter("resepsionis =", refResepsionis).list();
		
		return daftarAntri;
	}
	
	public AntrianHewan create(String nama, Long idHewan, Long idResepsionis, String jenis, String ras, 
			String umur, String gender, String ttl, String warna, Long no_reg, String ktp, String kode_hewan) {
		
		Key<Hewan> keyHewan = Key.create(Hewan.class, idHewan);
		Ref<Hewan> refHewan = Ref.create(keyHewan);
		
		Key<Resepsionis> keyResepsionis = Key.create(Resepsionis.class, idResepsionis);
		Ref<Resepsionis> refResepsionis = Ref.create(keyResepsionis);		
		
		// entitas hewan yang baru
		AntrianHewan Antrianhewan = new AntrianHewan(nama, refHewan, refResepsionis, jenis, ras, umur, gender, ttl, warna,
				no_reg, ktp, kode_hewan);
		
		// tambah cafe ini ke Pemilik.cafe
		// simpan semua perubahan
		ofy().save().entity(Antrianhewan).now();

		return Antrianhewan;
	}
	
	public AntrianHewan get(Long id){
		// buat key dari id
				Key<AntrianHewan> key = Key.create(AntrianHewan.class, id);
				AntrianHewan Antrianhewan = null;
					try {
						Antrianhewan = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return Antrianhewan;
	}
	
	public void deleteAntrian(Long id) {
		try {
			// ambil dari datastore
			AntrianHewan  Antrianhewan = ofy().load().key(Key.create(AntrianHewan.class, id)).safe();

			// hapus sekarang
			ofy().delete().entity(Antrianhewan).now();

		} catch (NotFoundException e) {
		}
	}
	
	public AntrianHewan gets(String kode_hewan){
		// buat key dari id
				Key<AntrianHewan> key = Key.create(AntrianHewan.class, kode_hewan);
				AntrianHewan Antrianhewan = null;
					try {
						Antrianhewan = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return Antrianhewan;
	}
	
	public void deleteAntrianRes(String kode_hewan) {
		try {
			// ambil dari datastore
			AntrianHewan  Antrianhewan = ofy().load().key(Key.create(AntrianHewan.class, kode_hewan)).safe();

			// hapus sekarang
			ofy().delete().entity(Antrianhewan).now();

		} catch (NotFoundException e) {
		}
	}
	
	public List <AntrianHewan> getAntrianHewan(int offset, int limit, Long id, String kode_hewan){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}

		Hewan hewan = new HewanCtrl().get(id);
		Ref<Hewan> refhewan  = Ref.create(hewan);
		List<AntrianHewan> d = ofy().load().type(AntrianHewan.class).filter("hewan", refhewan).
				filter("kode_hewan", kode_hewan).list(); 
		return d;
	}	
	
	 	
	

}
