package com.mobile.healthypets.model.resepsionis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import org.junit.Test;
import com.mobile.healthypets.BaseTest;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.klinik.KlinikCtrl;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pemilik.PemilikCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl;

public class TestResepsionisCtrl extends BaseTest { 
	
	private static final int offset = 0;
	private static final int limit = 0;
	private static final Long id = 1L;
	
	@Test
	public void Resepsionisbaru() throws NoSuchAlgorithmException, InvalidKeySpecException {	
		Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		ofy().save().entity(pemilik).now();
		pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
		Pemakai pemakai = (Pemakai) pemilik;
		
		Klinik klinik = new Klinik("Klinik B", Ref.create(pemakai), "Lamteh", "klinikb@mail.com", "0812121212", "1922442");
		ofy().save().entity(klinik).now();
		klinik = ofy().load().key(Key.create(Klinik.class, klinik.getId())).safe();
				
		Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
				"klinikb@mail", "Klinik B");				 
		
		assertNotNull(pemilik);
		assertNotNull(klinik);
		assertNotNull(resepsionis);
		assertEquals(resepsionis.getNama(),"Nana");
		assertEquals(resepsionis.getEmail(),"nana@mail");			
	}
	 
	 @Test
	  public void getResepsionis() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
		 
		 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
					"klinikb@mail", "Klinik B");

		 Resepsionis resepsioniss = new ResepsionisCtrl().get(resepsionis.getId());	    
		assertEquals(resepsioniss.getNama(),"Nana");
	  }
	 
	 @Test
	  public void ubahResepsionis() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
		 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
					"klinikb@mail", "Klinik B");
		 
	    String namaBaru = "Mardiana";
	    String alamatBaru = "Lampriet"; 
	    String telpBaru = "08111";
	    
	    Resepsionis ubah = new ResepsionisCtrl().updateResepsionis(resepsionis.getId(), namaBaru, alamatBaru, telpBaru);
	    assertEquals(namaBaru, ubah.getNama());
	  }

	 @Test
		public void listResepsionis() throws NoSuchAlgorithmException, InvalidKeySpecException {
			
			List<Resepsionis> daftarResepsionis = new ResepsionisCtrl().list(0, 0);			
			 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
			 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");

			//klinik 1
			 Resepsionis resepsionis1 = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
						"klinikb@mail", "Klinik B");
			 
			 assertEquals(resepsionis1.getNama(),"Nana");
			 daftarResepsionis = new ResepsionisCtrl().list(0, 0);
			
			assertEquals(daftarResepsionis.size(),1);
			
			//klinik 2
			Resepsionis resepsionis2 = new ResepsionisCtrl().create("Nani", klinik.getId(), "Lampineung", "nani@mail", "081212111", 
					"klinikb@mail", "Klinik B");
			
			 assertEquals(resepsionis2.getNama(),"Nani");
			 daftarResepsionis = new ResepsionisCtrl().list(0, 0);
			assertEquals(daftarResepsionis.size(),2);

		}
	 
	 @Test
		public void listResepsionisbyOwner() throws NoSuchAlgorithmException, InvalidKeySpecException {
		 
			List<Resepsionis> daftarResepsionisbyOwner = new ResepsionisCtrl().listByOwner(offset, limit, id);	

			
			//resepsionis 1
			 Pemilik pemilik = new PemilikCtrl().create("diana", "darussalam", "diana@bna.com", "11993343", "0812122121");
			 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");

			 Resepsionis resepsionis1 = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
						"klinika@mail", "Klinik A");
			 assertEquals(resepsionis1.getNama(),"Nana");
			 daftarResepsionisbyOwner = new ResepsionisCtrl().listByOwner(offset, limit, id);
			 assertEquals(daftarResepsionisbyOwner.size(),0);

			//resepsionis 2
			 Resepsionis resepsionis2 = new ResepsionisCtrl().create("Nani", klinik.getId(), "Lampineung", "nani@mail", "081212111", 
					 "klinika@mail", "Klinik A");
			 assertEquals(resepsionis2.getNama(),"Nani");
			 daftarResepsionisbyOwner  = new ResepsionisCtrl().listByOwner(offset, limit, id);	
			 assertEquals(daftarResepsionisbyOwner.size(),0);
		}
	 
	 @Test
	  public void cariResepsionisByEmail() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
		 
		 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail.com", "081212121", 
					"klinikb@mail", "Klinik B");

		 Resepsionis resepsioniss = new ResepsionisCtrl().cariEmail(resepsionis.getEmail());
	    
		assertEquals(resepsioniss.getEmail(),"nana@mail.com");
	 }


}
