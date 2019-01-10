package com.mobile.healthypets.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull; 
import org.junit.Test; 
import com.mobile.healthypets.BaseTest; 
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pemilik.PemilikCtrl;

public class TestCtrlPemilik extends BaseTest {
	
	private static final String nama = "Diana";
	
	@Test
	public void tambahPemilik() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		Pemilik pemiliks = new PemilikCtrl().create(nama, "bandaaceh", "email",
				"identitas", "telp");
				
		assertNotNull(pemiliks);
		assertEquals(pemiliks.getAlamat(),"bandaaceh");
		assertEquals(pemiliks.getEmail(),"email");	
		
	}
	
}
