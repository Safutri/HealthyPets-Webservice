package com.mobile.healthypets.model.antrian.antrianhewan;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.BaseTest;
import com.mobile.healthypets.model.antrianhewan.AntrianHewan;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.klien.Klien;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.resepsionis.Resepsionis;

public class TestAntrianHewan extends BaseTest {
	
	private static final String nama = "caca";
	private static final String jenis = "kucing";
	private static final String ras = "persia";
	private static final String umur = "3";
	private static final String ttl = "3/10/2016";
	private static final String gender = "female";
	private static final String email_klinik = "klinika@mail";
	private static final String warna = "hitam"; 
	private static final Long no_reg = (long) 1001;
	private static final String ktp = "132435464";
	private static final String kode_hewan = "1001_caca";

	@Test  
	public void createAntrianHewan() throws NoSuchAlgorithmException, InvalidKeySpecException {
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

						 Klien klien = new Klien(email_klinik, nama, "lampineung", "kaka@mail", no_reg, "08242352525",
						    		ktp, Ref.create(resepsionis));
						 ofy().save().entity(klien).now();
						 klien = ofy().load().key(Key.create(Klien.class, klien.getId())).safe();
						 
							 Hewan hewan = new Hewan(nama, Ref.create(klien), email_klinik, 
									 jenis, ras, umur, gender, ttl, warna, no_reg, ktp, kode_hewan);
							 ofy().save().entity(hewan).now();
							 hewan = ofy().load().key(Key.create(Hewan.class, hewan.getId())).safe();
						 
							 AntrianHewan antri = new AntrianHewan(nama, Ref.create(hewan), Ref.create(resepsionis),
									 jenis, ras, umur, gender, ttl, warna, no_reg, ktp, kode_hewan);
	 
	    assertNotNull(pemilik);
	    assertNotNull(klinik);
	    assertNotNull(resepsionis);
	    assertNotNull(klien);
	    assertNotNull(hewan);
		assertEquals(antri.getNama(),"caca");
	} 	
}
