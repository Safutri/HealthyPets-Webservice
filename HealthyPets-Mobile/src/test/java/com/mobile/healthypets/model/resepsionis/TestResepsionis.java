package com.mobile.healthypets.model.resepsionis;

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

public class TestResepsionis extends BaseTest {
	
		private static final String nama = "Nana";
		private static final String alamat ="Darussalam";
		private static final String email = "nana@mail";
		private static final String telp = "081212121";
		private static final String nama_klinik = "Klinik";
		private static final String email_klinik = "klinik@mail";
		
				
		@Test  
		public void createResepionis() throws NoSuchAlgorithmException, InvalidKeySpecException {
			Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "1323", "0812124552765");
				ofy().save().entity(pemilik).now();
				pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
				Pemakai pemakai = (Pemakai) pemilik;
			
			Klinik klinik = new Klinik("nama", Ref.create(pemakai), "bandaaceh",
				"email", "telp", "identitas");
				ofy().save().entity(klinik).now();
				klinik = ofy().load().key(Key.create(Klinik.class, klinik.getId())).safe();

		    Resepsionis resepsionis = new Resepsionis(nama, Ref.create(klinik), alamat, email, telp, nama_klinik, email_klinik);
		 
		    assertNotNull(pemilik);
		    assertNotNull(klinik);
		    assertNotNull(resepsionis);
			assertEquals(resepsionis.getAlamat(),"Darussalam");
	} 		 
}