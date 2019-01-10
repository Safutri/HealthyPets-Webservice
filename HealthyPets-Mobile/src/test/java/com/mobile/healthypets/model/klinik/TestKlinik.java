package com.mobile.healthypets.model.klinik;

import static org.junit.Assert.assertEquals;

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
 

public class TestKlinik extends BaseTest { 

	@Test
	public void createKlinik() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "", "sf");
		// Ref hanya bisa diambil, kalau entitas sudah di simpan ke datastore
		// simpan pemilik ke datastore
		ofy().save().entity(pemilik).now();
		pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
		Pemakai pemakai = (Pemakai) pemilik;
		Klinik klinik = new Klinik("nama", Ref.create(pemakai), "bandaaceh",
			"email", "telp", "identitas");

		assertNotNull(pemilik);
		assertNotNull(klinik);
		assertEquals(klinik.getAlamat(),"bandaaceh");		
	} 

}
