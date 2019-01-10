package com.mobile.healthypets.model.hewan;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.klien.Klien;
import com.mobile.healthypets.model.klien.KlienCtrl;

public class HewanCtrl {
	
	
	public List <Hewan> daftar(int offset, int limit){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List <Hewan> daftarHewan = ofy().load().type(Hewan.class).offset(offset).limit(limit).list();
		
		if(daftarHewan==null||daftarHewan.size()<1){
			daftarHewan = new ArrayList<>();
		}
		return daftarHewan;
	}
	
	public List<Hewan> daftarbyKlien(int offset, int limit, Long id) throws Exception {
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		// ambil info pemilik klien
		Klien klien = new KlienCtrl().cari(id);
		Ref<Klien> refKlien = Ref.create(klien);
		List<Hewan> daftarHewan= ofy().load().type(Hewan.class).filter("klien =", refKlien).list();
		
		return daftarHewan;
	}
	
	public Hewan create(String nama, Long idKlien, String email_klinik, 
			String jenis, String ras, String umur, String gender, String ttl, String warna,
			Long no_reg, String ktp, String kode_hewan) {
		// buat entitas cafe
		Key<Klien> keyKlien = Key.create(Klien.class, idKlien);
		Ref<Klien> refKlien = Ref.create(keyKlien);
		// entitas hewan yang baru
		Hewan hewan = new Hewan(nama, refKlien, email_klinik, jenis, ras, umur, gender, ttl, warna, no_reg, ktp, kode_hewan);
		
		// tambah cafe ini ke Pemilik.cafe
		// simpan semua perubahan
		ofy().save().entity(hewan).now();

		return hewan;
	}
	
	public Hewan get(Long id){
		// buat key dari id
				Key<Hewan> key = Key.create(Hewan.class, id);
				Hewan hewan = null;
					try {
					hewan = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return hewan;
	}
	
	public Hewan getk_hewan(String kode_hewan){
		// buat key dari id
				Key<Hewan> key = Key.create(Hewan.class, kode_hewan);
				Hewan hewan = null;
					try {
					hewan = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return hewan;
	}
	
	public Hewan cariNoreg(Long no_reg){
		List<Hewan> daftarHewan = null;
		Hewan hewan = null;
			try {
				daftarHewan = ofy().load().type(Hewan.class).filter("no_reg", no_reg).list();
		} catch (NotFoundException e) {}
			if (daftarHewan.size() < 1) {
				
			} else {

				hewan = daftarHewan.get(0);	
			}
		return hewan;
	}
	
	public Hewan cariNama(String kode_hewan){
		List<Hewan> daftarHewan = null;
		Hewan hewan = null;
			try {
				daftarHewan = ofy().load().type(Hewan.class).filter("kode_hewan", kode_hewan).list();
		} catch (NotFoundException e) {}
			if (daftarHewan.size() < 1) {
				
			} else {

				hewan = daftarHewan.get(0);	
			}
		return hewan;
	}
	 


}
