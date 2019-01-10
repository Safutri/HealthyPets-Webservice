package com.mobile.healthypets.model.pilihdrh;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 
 
public class PilihDrhCtrl {
	
	public List<PilihDrh> daftarbyResepsionis(int offset, int limit, Long id) throws Exception {
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET; 
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		// ambil info pemilik cafe
		Resepsionis resepsionis = new ResepsionisCtrl().get(id);
		Ref<Resepsionis> refResepsionis = Ref.create(resepsionis);
		List<PilihDrh> daftarKlien= ofy().load().type(PilihDrh.class).filter("resepsionis =", refResepsionis).list();
		
		return daftarKlien;
	}

	public PilihDrh create(Long drh_jaga, Long idResepsionis) { 
		
		Key<Resepsionis> keyResepsionis = Key.create(Resepsionis.class, idResepsionis);
		Ref<Resepsionis> refResepsionis = Ref.create(keyResepsionis);
		
		PilihDrh pilih = new PilihDrh (drh_jaga, refResepsionis);
		ofy().save().entity(pilih).now();

		return pilih;
	} 
	
	public PilihDrh get(Long id){
		// buat key dari id
				Key<PilihDrh> key = Key.create(PilihDrh.class, id);
				PilihDrh pilih  = null;
					try {
					pilih = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return pilih;
	}
	
	
	public void DeletePilihDrh(Long id) {
		try {
			// ambil dari datastore
			PilihDrh drh= ofy().load().key(Key.create(PilihDrh.class, id)).safe();

			// hapus sekarang
			ofy().delete().entity(drh).now();
		 
		} catch (NotFoundException e) {}	
	}  
	
	
	
}
