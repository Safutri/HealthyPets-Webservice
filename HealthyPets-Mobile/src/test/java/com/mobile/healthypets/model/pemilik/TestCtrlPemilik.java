package com.mobile.healthypets.model.pemilik;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull; 
import org.junit.Test; 
import com.mobile.healthypets.BaseTest;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pemilik.PemilikCtrl;

public class TestCtrlPemilik extends BaseTest {
	
	private static final String nama = "Diana";
	private static final String alamat = "Lampineung";
	private static final String email = "diana@mail.com";
	private static final String identitas = "1111";
	private static final String telp = "00000";
	
	@Test
	public void tambahPemilik() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		Pemilik pemiliks = new PemilikCtrl().create(nama, "bandaaceh", "email", "identitas", "telp");				
		assertNotNull(pemiliks);
		assertEquals(pemiliks.getAlamat(),"bandaaceh");
		assertEquals(pemiliks.getEmail(),"email");			
	}
	
	 @Test
	  public void ubahPemilik() throws Exception {
		 Pemilik pemiliks = new PemilikCtrl().create(nama, alamat, email, identitas, telp);
		 assertEquals(pemiliks.getNama(), nama);
	    
	    String namaBaru = "Mardiana";
	    String alamatBaru = "Lampriet";
	    String identitasBaru = "22";
	    String telpBaru = "08111";
	    
	    Pemakai bUbah = new PemilikCtrl().updatePemilik(pemiliks.getId(), namaBaru, alamatBaru, identitasBaru, telpBaru);
	    assertEquals(namaBaru, bUbah.getNama());
	  }
	  
	 @Test
	  public void getPemilik() throws Exception {

		 Pemilik pemiliks = new PemilikCtrl().create(nama, alamat, email, identitas, telp);
		 assertEquals(pemiliks.getNama(), nama);
	    
	    Pemilik pemilik = new PemilikCtrl().get(pemiliks.getId());
	    
	    assertEquals(pemiliks.getNama(), pemilik.getNama());
	  }
	 
	 
}
