package com.mobile.healthypets.model.klien;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.junit.Test;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.BaseTest; 
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.klinik.KlinikCtrl;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pemilik.PemilikCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl;

public class TestKlienCtrl extends BaseTest { 

	private static final String nama = "Tata";
	private static final String alamat ="Lamnyong";
	private static final String email = "tata@mail";
	private static final String telp = "08121211122";
	private static final String email_klinik = "klinika@mail";
	private static final Long no_reg = (long) 1001;
	private static final String ktp = "19999434344545"; 
	
	@Test
	public void DokterHewanbaru() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
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
  
		  Klien klien = new KlienCtrl().create(email_klinik, nama, alamat, email, no_reg, telp,
		    		ktp, resepsionis.getId());
		
		assertNotNull(pemilik);
		assertNotNull(klinik); 
		assertNotNull(resepsionis);
		assertEquals(klien.getEmail(),"tata@mail");			
	}
	
	 @Test
	  public void getKlien() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
		 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
					"klinikb@mail", "Klinik B");
		 
		 Klien klien = new KlienCtrl().create(email_klinik, nama, alamat, email, no_reg, telp,
		    		ktp, resepsionis.getId());

		 Klien kliencari = new KlienCtrl().cari(klien.getId());	    
		assertEquals(kliencari.getNama(),"Tata");
	  }
	
	 @Test
	  public void getKlienI() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
		 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
					"klinikb@mail", "Klinik B");
		 
		 Klien klien = new KlienCtrl().create(email_klinik, nama, alamat, email, no_reg, telp,
		    		ktp, resepsionis.getId());

		 Klien kliencari = new KlienCtrl().cariNoreg(klien.getNo_reg());	    
		assertEquals(kliencari.getNama(),"Tata");
	  }
	 
	 @Test
	  public void ubahKlien() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
		 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
					"klinikb@mail", "Klinik B");
		 
		Klien klien = new KlienCtrl().create(email_klinik, nama, alamat, email, no_reg, telp,
	    		ktp, resepsionis.getId());
 
	    String namaBaru = "Mardiana";
	    String alamatBaru = "Lampriet"; 
	    String telpBaru = "08111";
	    String emailBaru = "ninis@mail.com";
	    String ktpBaru = "1213346546747";
	    
	    Klien ubah = new KlienCtrl().updateKlien(klien.getId(), namaBaru, alamatBaru, emailBaru, telpBaru, ktpBaru);
	    
	    assertEquals(namaBaru, ubah.getNama());
	  }
	 
	 @Test
		public void listKlien() throws NoSuchAlgorithmException, InvalidKeySpecException {
			
			List<Klien> daftarKlien = new KlienCtrl().daftar(0, 0);		
			
			 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
			 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123"); 
			 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
						"klinikb@mail", "Klinik B");
			 
			 //DokterHewan 1
			 Klien klien1 = new KlienCtrl().create(email_klinik, nama, alamat, email, no_reg, telp,
			    		ktp, resepsionis.getId());
			 assertEquals(klien1.getNama(),"Tata");
			 daftarKlien = new KlienCtrl().daftar(0, 0);
			
			assertEquals(daftarKlien.size(),1);
			
			//DokterHewan 2 
			Klien klien2 = new KlienCtrl().create(email_klinik, "Titi", alamat, "titi@mail", (long) 1002, telp,
		    		"3547568242", resepsionis.getId());
			
			 assertEquals(klien2.getNama(),"Titi");
			 daftarKlien = new KlienCtrl().daftar(0, 0);
			assertEquals(daftarKlien .size(),2);

		}
}
