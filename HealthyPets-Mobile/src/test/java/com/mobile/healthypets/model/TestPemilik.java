package com.mobile.healthypets.model;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
 
import org.junit.Test; 
import static org.junit.Assert.assertNotNull;  
import com.mobile.healthypets.BaseTest;
import com.mobile.healthypets.model.pemilik.Pemilik; 

public class TestPemilik extends BaseTest {


	@Test
	public void createPemilik() throws NoSuchAlgorithmException, InvalidKeySpecException {
		 
		Pemilik pemilik = new Pemilik("nama", "bandaaceh", "email",
				"identitas", "telp");
		
		assertNotNull(pemilik);
		assertEquals(pemilik.getAlamat(),"bandaaceh");
		
	}
	
	@Test
	public void tambahPemilikStringKosong() {
		
		Pemilik pemiliks = new Pemilik("", "bandaaceh", "email", "identitas", "telp");
		
		assertNotNull(pemiliks);
		assertEquals(pemiliks.getAlamat(),"bandaaceh");
		
	}
}
