package com.mobile.healthypets.model.klinik;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.junit.Test; 
import com.mobile.healthypets.BaseTest;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.klinik.KlinikCtrl; 
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pemilik.PemilikCtrl;  

public class TestKlinikCtrl extends BaseTest {

	private static final String nama = "Klinik A";
	private static final String alamat = "Darussalam";
	private static final String email = "klinika@mail.com";
	private static final String telp = "0808080";
	private static final String identitas = "12335";
	private static final int offset = 0;
	private static final int limit = 0;
	private static final Long id = 1L;

	
	 @Test
	  public void KlinikBaru() throws Exception {  
		 
		Pemilik pemiliks = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		    
	    Klinik klinik = new KlinikCtrl().create(nama, pemiliks.getId(), alamat, email, telp, identitas);

		assertNotNull(klinik);
		assertEquals(klinik.getAlamat(),"Darussalam");
		assertEquals(klinik.getEmail(),"klinika@mail.com");	
	  }	
	 
	 @Test
	  public void getPemilik() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create(nama, pemilik.getId(), alamat, email, telp, identitas);
	    
	    Klinik kliniks = new KlinikCtrl().get(klinik.getId());
	    
		assertEquals(kliniks.getNama(),"Klinik A");
	  }
	 
	 @Test
	  public void ubahKlinik() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create(nama, pemilik.getId(), alamat, email, telp, identitas);
	    
	    String namaBaru = "Mardiana";
	    String alamatBaru = "Lampriet";
	    String praktikBaru = "22xx";
	    String emailBaru = "klinikb@mail.com";
	    String telpBaru = "08111";
	    
	    Klinik update = new KlinikCtrl().updateKlinik(klinik.getId(), namaBaru, alamatBaru, praktikBaru, emailBaru, telpBaru);
	    assertEquals(namaBaru, update.getNama());
	  }

	 
		@Test
		public void listKlinik() throws NoSuchAlgorithmException, InvalidKeySpecException {
			
			List<Klinik> daftarKlinik = new KlinikCtrl().list(0, 0);			
			 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");

			//klinik 1
			 Klinik klinik1 = new KlinikCtrl().create(nama, pemilik.getId(), alamat, email, telp, identitas);
			 assertEquals(klinik1.getNama(),"Klinik A");
			 daftarKlinik = new KlinikCtrl().list(0, 0);			
			assertEquals(daftarKlinik.size(),1);
			
			//klinik 2
			 Klinik klinik2 = new KlinikCtrl().create("Klinik B", pemilik.getId(), "Lamteh", "klinikb@mail.com", "0812121212", "1922442");
			
			 daftarKlinik = new KlinikCtrl().list(0, 0);
			 assertEquals(klinik2.getNama(),"Klinik B");
			assertEquals(daftarKlinik.size(),2);

		}
		
		@Test
		public void listKlinikbyOwner() throws NoSuchAlgorithmException, InvalidKeySpecException {
			
			List<Klinik> daftarKlinik = new KlinikCtrl().listByOwner(offset, limit, id);	
			
			//klinik 1
			 Pemilik pemilik1 = new PemilikCtrl().create("diana", "darussalam", "diana@bna.com", "11993343", "0812122121"); 
			 
			 Klinik klinik1 = new KlinikCtrl().create(nama, pemilik1.getId(), alamat, email, telp, identitas);
			 assertEquals(klinik1.getNama(),"Klinik A");
			 daftarKlinik =  new KlinikCtrl().listByOwner(offset, limit, id);
				assertEquals(daftarKlinik.size(),1);
						
			//klinik 2
			 Klinik klinik2 = new KlinikCtrl().create("Klinik B", pemilik1.getId(), "Lamteh", "klinikb@mail.com", "0812121212", "1922442");
			 assertEquals(klinik2.getNama(),"Klinik B");
			 daftarKlinik = new KlinikCtrl().listByOwner(offset, limit, id);	
				assertEquals(daftarKlinik.size(),2);


		}

}
