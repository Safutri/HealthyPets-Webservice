package com.mobile.healthypets.model.drh;

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

public class TestDrhCtrl extends BaseTest {
	
	private static final int offset = 0;
	private static final int limit = 0;
	private static final Long id = 1L;
	
	private static final String nama = "Nini";
	private static final String alamat ="Ulee Kareng";
	private static final String email = "nini@mail";
	private static final String telp = "08121211123";
	private static final String nama_klinik = "Klinika";
	private static final String email_klinik = "klinika@mail";
	private static final String no_praktik = "23edr4rda"; 
	
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
 
		DokterHewan drh = new DokterHewanCtrl().create(nama, resepsionis.getId(), alamat, email, telp, 
				no_praktik, email_klinik, nama_klinik);				 
		
		assertNotNull(pemilik);
		assertNotNull(klinik);
		assertNotNull(resepsionis);
		assertEquals(drh.getEmail(),"nini@mail");			
	}
	
	 @Test
	  public void getDokterHewan() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
		 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
					"klinikb@mail", "Klinik B");
		 
		 DokterHewan drh = new DokterHewanCtrl().create(nama, resepsionis.getId(), alamat, email, telp, 
					no_praktik, email_klinik, nama_klinik);		

		 DokterHewan drh2 = new DokterHewanCtrl().get(drh.getId());	    
		assertEquals(drh2.getNama(),"Nini");
	  }
	 
	 @Test
	  public void ubahDrh() throws Exception {
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
		 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
					"klinikb@mail", "Klinik B");
		 
		 DokterHewan drh = new DokterHewanCtrl().create(nama, resepsionis.getId(), alamat, email, telp, 
					no_praktik, email_klinik, nama_klinik);	
		 
	    String namaBaru = "Mardiana";
	    String alamatBaru = "Lampriet"; 
	    String telpBaru = "08111";
	    String no_praktikBaru = "42ed45t";
	    String email_klinik_baru = "klinikb@mail.com";
	    String nama_klinik_baru = "klinik b";
	    
	    DokterHewan ubah = new DokterHewanCtrl().updateDokterHewan(drh.getId(), namaBaru, alamatBaru, telpBaru,
	    		no_praktikBaru, email_klinik_baru, nama_klinik_baru);
	    
	    assertEquals(namaBaru, ubah.getNama());
	  }
	 
	 @Test
		public void listDokterHewan() throws NoSuchAlgorithmException, InvalidKeySpecException {
			
			List<DokterHewan> daftarDokterHewan = new DokterHewanCtrl().list(0, 0);		
			
			 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
			 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123"); 
			 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
						"klinikb@mail", "Klinik B");
			 
			 //DokterHewan 1
			 DokterHewan drh1 = new DokterHewanCtrl().create(nama, resepsionis.getId(), alamat, email, telp, 
						no_praktik, email_klinik, nama_klinik);	
			 assertEquals(drh1.getNama(),"Nini");
			 daftarDokterHewan = new DokterHewanCtrl().list(0, 0);
			
			assertEquals(daftarDokterHewan.size(),1);
			
			//DokterHewan 2 
			DokterHewan drh2 = new DokterHewanCtrl().create("Nanang", resepsionis.getId(), alamat, "nanang@mail.com", "07745654654", 
					"654f45r54", email_klinik, nama_klinik);	
			
			 assertEquals(drh2.getNama(),"Nanang");
			 daftarDokterHewan = new DokterHewanCtrl().list(0, 0);
			assertEquals(daftarDokterHewan .size(),2);

		}
	 
	 @Test
		public void listDrhbyOwner() throws NoSuchAlgorithmException, InvalidKeySpecException {
		 
			List<DokterHewan> daftarDokterHewanbyOwner = new DokterHewanCtrl().listByOwner(offset, limit, id);			
			
			 Pemilik pemilik = new PemilikCtrl().create("diana", "darussalam", "diana@bna.com", "11993343", "0812122121");
			 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");
			 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail", "081212121", 
						"klinika@mail", "Klinik A");
			 
			//drh 1
			 DokterHewan drh1 = new DokterHewanCtrl().create(nama, resepsionis.getId(), alamat, email, telp, 
						no_praktik, email_klinik, nama_klinik);	
			 
			 assertEquals(drh1.getNama(),"Nini");
			 daftarDokterHewanbyOwner = new DokterHewanCtrl().listByOwner(offset, limit, id);
			 assertEquals(daftarDokterHewanbyOwner.size(),0);

			//drh 2
			 DokterHewan drh2 = new DokterHewanCtrl().create("Nanang", resepsionis.getId(), alamat, "nanang@mail.com", "07745654654", 
						"654f45r54", email_klinik, nama_klinik);	
			 
			 assertEquals(drh2.getNama(),"Nanang");
			 daftarDokterHewanbyOwner = new DokterHewanCtrl().listByOwner(offset, limit, id);	
			 assertEquals(daftarDokterHewanbyOwner.size(),0);
		}

	 @Test
	  public void cariDrhByEmail() throws Exception {
		 
		 Pemilik pemilik = new PemilikCtrl().create("diana", "bna", "diana@bna.com", "11993343", "0812122121");
		 Klinik klinik = new KlinikCtrl().create("Klinik A", pemilik.getId(), "Pango", "klinika@mail.com", "0808080", "123");		 
		 Resepsionis resepsionis = new ResepsionisCtrl().create("Nana", klinik.getId(), "Darussalam", "nana@mail.com", "081212121", 
					"klinikb@mail", "Klinik B");
		 
		 DokterHewan drh = new DokterHewanCtrl().create(nama, resepsionis.getId(), alamat, email, telp, 
					no_praktik, email_klinik, nama_klinik);	
		 
		 DokterHewan drhs = new DokterHewanCtrl().cariEmail(drh.getEmail());
	    
		assertEquals(drhs.getEmail(),"nini@mail");
	 }
	 
}
