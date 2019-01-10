package com.mobile.healthypets.model.klien;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.assertEquals;
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

public class TestKlien extends BaseTest {
	
	private static final String nama = "Tata";
	private static final String alamat ="Lamnyong";
	private static final String email = "tata@mail";
	private static final String telp = "08121211122";
	private static final String email_klinik = "klinika@mail";
	private static final Long no_reg = (long) 1001;
	private static final String ktp = "19999434344545";

	@Test  
	public void createKlien() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "1323", "0812124552765");
			ofy().save().entity(pemilik).now();
			pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
			Pemakai pemakai = (Pemakai) pemilik;
		
			Klinik klinik = new Klinik("klinikA", Ref.create(pemakai), "lamteh",
				"klinika@mail.com", "091243423", "sads334ed");
				ofy().save().entity(klinik).now();
				klinik = ofy().load().key(Key.create(Klinik.class, klinik.getId())).safe(); 
			
				Resepsionis resepsionis = new Resepsionis("Nana", Ref.create(klinik), "keutapang",
						"nana@mail.com", "08544546765", "klinikA", "klinika@mail.com");
						ofy().save().entity(resepsionis).now();
						resepsionis = ofy().load().key(Key.create(Resepsionis.class, resepsionis.getId())).safe();

	    Klien klien = new Klien(email_klinik, nama, alamat, email, no_reg, telp,
	    		ktp, Ref.create(resepsionis));
	 
	    assertNotNull(pemilik);
	    assertNotNull(klinik);
	    assertNotNull(resepsionis);
		assertEquals(klien.getNama(),"Tata");
	} 
}
