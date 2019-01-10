package com.mobile.healthypets.model.hewan;

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
import com.mobile.healthypets.model.klien.Klien;
import com.mobile.healthypets.model.klien.KlienCtrl;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.klinik.KlinikCtrl;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pemilik.PemilikCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 

public class TestHewanCtrl extends BaseTest {
//	private static final int offset = 0;
//	private static final int limit = 0;
//	private static final Long id = 1L;

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
	public void Hewanbaru() throws NoSuchAlgorithmException, InvalidKeySpecException {	
		Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		ofy().save().entity(pemilik).now();
		pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
		Pemakai pemakai = (Pemakai) pemilik;
		
			Klinik klinik = new Klinik("Klinik B", Ref.create(pemakai), "Lamteh", "klinikb@mail.com", "0812121212", "1922442");
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
					 
					 Hewan hewan = new HewanCtrl().create(nama, klien.getId(), email_klinik, jenis, ras,
							 umur, gender, ttl, warna, no_reg, ktp, kode_hewan);		
		
		assertNotNull(pemilik);
		assertNotNull(klinik);
		assertNotNull(resepsionis);
		assertNotNull(klien);
		assertEquals(hewan.getNama(),"caca");
		assertEquals(hewan.getJenis(),"kucing");			
	}
	
	 @Test
	  public void getHewan() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 	Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
				 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
						"klinikb@mail", "Klinik B");		 
							 Klien klien = new KlienCtrl().create(email_klinik, nama, "lampineung", "kaka@mail", no_reg, "08242352525",
								    	ktp, resepsionis.getId());
		 
							 Hewan hewan = new HewanCtrl().create(nama, klien.getId(), email_klinik, jenis, ras,
									 umur, gender, ttl, warna, no_reg, ktp, kode_hewan);		

		 Hewan hewans = new HewanCtrl().get(hewan.getId());	   
		 
		 assertNotNull(pemilik);
			assertNotNull(klinik);
			assertNotNull(resepsionis);
			assertNotNull(klien);
		assertEquals(hewans.getNama(),"caca");
	  }
	 
	 @Test
	  public void getKodeHewan() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 	Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
				 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
						"klinikb@mail", "Klinik B");		 
							 Klien klien = new KlienCtrl().create(email_klinik, nama, "lampineung", "kaka@mail", no_reg, "08242352525",
								    	ktp, resepsionis.getId());
		 
							 Hewan hewan = new HewanCtrl().create(nama, klien.getId(), email_klinik, jenis, ras,
									 umur, gender, ttl, warna, no_reg, ktp, kode_hewan);		

		 new HewanCtrl().getk_hewan(hewan.getKode_hewan());	  
		 
		 assertNotNull(pemilik);
			assertNotNull(klinik);
			assertNotNull(resepsionis);
			assertNotNull(klien);
	  }
	 
	 @Test
		public void listHewan() throws NoSuchAlgorithmException, InvalidKeySpecException {
			
			List<Hewan> daftarHewan = new HewanCtrl().daftar(0, 0);		
			
			 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
			 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123"); 
			 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
						"klinikb@mail", "Klinik B");
			 Klien klien = new KlienCtrl().create(email_klinik, nama, "lampineung", "kaka@mail", no_reg, "08242352525",
				    	ktp, resepsionis.getId());
			 
			 //Hewan 1
			 Hewan hewan1 = new HewanCtrl().create(nama, klien.getId(), email_klinik, jenis, ras,
					 umur, gender, ttl, warna, no_reg, ktp, kode_hewan);	
			 assertEquals(hewan1.getNama(),"caca");
			 daftarHewan = new HewanCtrl().daftar(0, 0);			
			assertEquals(daftarHewan.size(),1);
			
			//Hewan 2 
			Hewan hewan2 = new HewanCtrl().create("cici", klien.getId(), email_klinik, jenis, ras,
					 umur, gender, ttl, warna, no_reg, ktp, "cici_1001");	
			
			 assertEquals(hewan2.getNama(),"cici");
			 daftarHewan = new HewanCtrl().daftar(0, 0);
			assertEquals(daftarHewan .size(),2);

		}
	 
//	 @Test
//		public void listHewanbyOwner() throws Exception {
//		 
//			List<Hewan> daftarHewanbyOwner = new HewanCtrl().daftarbyKlien(offset, limit, id);			
//			
//			 Pemilik pemilik = new PemilikCtrl().create("diana", "darussalam", "diana@bna.com", "11993343", "0812122121");
//			 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
//			 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
//						"klinika@mail", "Klinik A");
//			 Klien klien = new KlienCtrl().create(email_klinik, nama, "lampineung", "kaka@mail", no_reg, "08242352525",
//				    	ktp, resepsionis.getId());
//			 
//			 Klien kliens = new KlienCtrl().cari(klien.getId());
//			 
//			//Hewan 1
//			 Hewan hewan1 = new HewanCtrl().create(nama, kliens.getId(), email_klinik, jenis, ras,
//					 umur, gender, ttl, warna, no_reg, ktp, kode_hewan);				 
//			 assertEquals(hewan1.getNama(),"caca");
//			 daftarHewanbyOwner = new HewanCtrl().daftarbyKlien(offset, limit, id);
//			 assertEquals(daftarHewanbyOwner.size(),0);
//
//			//Hewan2
//			 Hewan hewan2 = new HewanCtrl().create("cici", kliens.getId(), email_klinik, jenis, ras,
//					 umur, gender, ttl, warna, no_reg, ktp, "cici_1001");	
//						 
//			 assertEquals(hewan2.getNama(),"cici");
//			 daftarHewanbyOwner = new HewanCtrl().daftarbyKlien(offset, limit, id);	
//			 assertEquals(daftarHewanbyOwner.size(),0);
//		}
	 
}
