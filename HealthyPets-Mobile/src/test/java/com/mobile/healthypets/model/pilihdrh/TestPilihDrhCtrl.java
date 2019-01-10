package com.mobile.healthypets.model.pilihdrh;

import static com.googlecode.objectify.ObjectifyService.ofy; 
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.BaseTest; 
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.resepsionis.Resepsionis;

public class TestPilihDrhCtrl extends BaseTest {
	private static final Long drh_jaga = (long) 1;
	
	@Test
	public void PilihDrhbaru() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		ofy().save().entity(pemilik).now();
		pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
		Pemakai pemakai = (Pemakai) pemilik;
		
		Klinik klinik = new Klinik("Klinik B", Ref.create(pemakai), "Lamteh", "klinikb@mail.com", "0812121212", "1922442");
		ofy().save().entity(klinik).now();
		klinik = ofy().load().key(Key.create(Klinik.class, klinik.getId())).safe();
				
		Resepsionis resepsionis = new Resepsionis("Nana", Ref.create(klinik), "Darussalam", "nana@mail", "081212121", 
				"klinikb@mail", "Klinik B");				
		ofy().save().entity(resepsionis).now();
		resepsionis = ofy().load().key(Key.create(Resepsionis.class, resepsionis.getId())).safe();
 
		 new PilihDrhCtrl().create(drh_jaga, resepsionis.getId());				 
		
		assertNotNull(pemilik);
		assertNotNull(klinik);
		assertNotNull(resepsionis);
	}
	
	 @Test
	  public void getPilihDrh() throws Exception {
		 Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "11993343", "0812122121");
			ofy().save().entity(pemilik).now();
			pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
			Pemakai pemakai = (Pemakai) pemilik;
			
			Klinik klinik = new Klinik("Klinik B", Ref.create(pemakai), "Lamteh", "klinikb@mail.com", "0812121212", "1922442");
			ofy().save().entity(klinik).now();
			klinik = ofy().load().key(Key.create(Klinik.class, klinik.getId())).safe();
					
			Resepsionis resepsionis = new Resepsionis("Nana", Ref.create(klinik), "Darussalam", "nana@mail", "081212121", 
					"klinikb@mail", "Klinik B");				
			ofy().save().entity(resepsionis).now();
			resepsionis = ofy().load().key(Key.create(Resepsionis.class, resepsionis.getId())).safe();
	 
			 PilihDrh pilih = new PilihDrhCtrl().create(drh_jaga, resepsionis.getId());		
	
		    @SuppressWarnings("unused")
			PilihDrh pilih2 = new PilihDrhCtrl().get(pilih.getId());	
		 
		 assertNotNull(pemilik);
		 assertNotNull(klinik);
		 assertNotNull(resepsionis);
	  }

	
}
