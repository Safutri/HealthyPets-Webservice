package com.mobile.healthypets.model.ambulator;

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
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.klien.Klien;
import com.mobile.healthypets.model.klinik.Klinik; 
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik; 
import com.mobile.healthypets.model.pilihdrh.PilihDrh;
import com.mobile.healthypets.model.resepsionis.Resepsionis; 

public class TestAmbulatorCtrl extends BaseTest {
	private static final String tgl = "2/2/2018";
	private static final String sinyalmen = "a";
	private static final String anamnesa = "a";
	private static final String gizi = "a";
	private static final String tempramen = "a";
	private static final String habitat = "a";
	private static final String frek_nafas = "a";
	private static final String frek_pulsus= "a";
	private static final String suhu_tubuh= "a";
	private static final String kulit_bulu= "a";
	private static final String sedir_mata= "a";
	private static final String sedir_hidung= "a";
	private static final String sedir_mulut= "a";
	private static final String sedir_anus= "a";
	private static final String k_limfase= "a";
	private static final String a_nafas= "a";
	private static final String a_darah= "a";
	private static final String a_kelamin= "a";
	private static final String a_cerna= "a";
	private static final String u_saraf= "a";
	private static final String ang_gerak= "a";
	private static final String lain= "a";	
	private static final Long drh_jaga= (long) 1;
	private static final Long no_reg= (long) 1001;
	private static final String ktp= "a";
	private static final String kode_hewan= "a";	
	private static final String nama = "caca";
	private static final String jenis = "kucing";
	private static final String ras = "persia";
	private static final String umur = "3";
	private static final String ttl = "3/10/2016";
	private static final String gender = "female";
	private static final String email_klinik = "klinika@mail";
	private static final String warna = "hitam";  
	
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
 
			 Klien klien = new Klien(email_klinik, nama, "lampineung", "kaka@mail", no_reg, "08242352525",
			    		ktp, Ref.create(resepsionis));
			 ofy().save().entity(klien).now();
			 klien = ofy().load().key(Key.create(Klien.class, klien.getId())).safe();
			 
			 Hewan hewan = new Hewan(nama, Ref.create(klien), 
		 				email_klinik, jenis, ras, umur, gender, ttl, warna, no_reg, ktp, kode_hewan);
						ofy().save().entity(hewan).now();
						hewan = ofy().load().key(Key.create(Hewan.class, hewan.getId())).safe();
		  
						PilihDrh pilih = new PilihDrh(drh_jaga, Ref.create(resepsionis));
								ofy().save().entity(pilih).now();
								pilih = ofy().load().key(Key.create(PilihDrh.class, pilih.getId())).safe();
										
		 Ambulator amb = new AmbulatorCtrl().create(tgl,sinyalmen, anamnesa, gizi, tempramen,
					habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
					sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
					hewan.getId(), pilih.getId(), no_reg, ktp, kode_hewan);
		   
		assertNotNull(pemilik);
		assertNotNull(klinik);
		assertNotNull(resepsionis);
		assertEquals(amb.getTgl(),"2/2/2018");			
	}
	
	 @Test
	  public void getAmb() throws Exception {
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
	 
				 Klien klien = new Klien(email_klinik, nama, "lampineung", "kaka@mail", no_reg, "08242352525",
				    		ktp, Ref.create(resepsionis));
				 ofy().save().entity(klien).now();
				 klien = ofy().load().key(Key.create(Klien.class, klien.getId())).safe();
				 
				 Hewan hewan = new Hewan(nama, Ref.create(klien), 
			 				email_klinik, jenis, ras, umur, gender, ttl, warna, no_reg, ktp, kode_hewan);
							ofy().save().entity(hewan).now();
							hewan = ofy().load().key(Key.create(Hewan.class, hewan.getId())).safe();
			  
							PilihDrh pilih = new PilihDrh(drh_jaga, Ref.create(resepsionis));
									ofy().save().entity(pilih).now();
									pilih = ofy().load().key(Key.create(PilihDrh.class, pilih.getId())).safe();
											
			 Ambulator amb = new AmbulatorCtrl().create(tgl,sinyalmen, anamnesa, gizi, tempramen,
						habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
						sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
						hewan.getId(), pilih.getId(), no_reg, ktp, kode_hewan);	
			 
			   Ambulator ambs= new AmbulatorCtrl().get(amb.getId());	;

			assertNotNull(pemilik);
			assertNotNull(klinik);
			assertNotNull(resepsionis);
			assertEquals(ambs.getTgl(),"2/2/2018");			
	  }	
	 
	 @Test
		public void listAmb() throws NoSuchAlgorithmException, InvalidKeySpecException {
			
			List<Ambulator> daftarAmbulator = new AmbulatorCtrl().daftar(0, 0);		
			
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
		 
					 Klien klien = new Klien(email_klinik, nama, "lampineung", "kaka@mail", no_reg, "08242352525",
					    		ktp, Ref.create(resepsionis));
					 ofy().save().entity(klien).now();
					 klien = ofy().load().key(Key.create(Klien.class, klien.getId())).safe();
					 
					 Hewan hewan = new Hewan(nama, Ref.create(klien), 
				 				email_klinik, jenis, ras, umur, gender, ttl, warna, no_reg, ktp, kode_hewan);
								ofy().save().entity(hewan).now();
								hewan = ofy().load().key(Key.create(Hewan.class, hewan.getId())).safe();
				  
								PilihDrh pilih = new PilihDrh(drh_jaga, Ref.create(resepsionis));
										ofy().save().entity(pilih).now();
										pilih = ofy().load().key(Key.create(PilihDrh.class, pilih.getId())).safe();
										
			 //DokterHewan 1
			 Ambulator amb1 = new AmbulatorCtrl().create(tgl,sinyalmen, anamnesa, gizi, tempramen,
						habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
						sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
						hewan.getId(), pilih.getId(), no_reg, ktp, kode_hewan);	
			 
			 assertEquals(amb1.getKode_hewan(),"a");
 			 daftarAmbulator = new AmbulatorCtrl().daftar(0, 0);			
			assertEquals(daftarAmbulator.size(),1);
			
			//DokterHewan 2 
			 Ambulator amb2 = new AmbulatorCtrl().create(tgl,sinyalmen, anamnesa, gizi, tempramen,
						habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
						sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
						hewan.getId(), pilih.getId(), no_reg, ktp, "1001_cici");		
			
			 assertEquals(amb2.getKode_hewan(),"1001_cici");
			 daftarAmbulator = new AmbulatorCtrl().daftar(0, 0);
			 
			assertEquals(daftarAmbulator .size(),2);

		}
	 
	 @Test
	  public void getCariNoreg() throws Exception {
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
	 
				 Klien klien = new Klien(email_klinik, nama, "lampineung", "kaka@mail", no_reg, "08242352525",
				    		ktp, Ref.create(resepsionis));
				 ofy().save().entity(klien).now();
				 klien = ofy().load().key(Key.create(Klien.class, klien.getId())).safe();
				 
				 Hewan hewan = new Hewan(nama, Ref.create(klien), 
			 				email_klinik, jenis, ras, umur, gender, ttl, warna, no_reg, ktp, kode_hewan);
							ofy().save().entity(hewan).now();
							hewan = ofy().load().key(Key.create(Hewan.class, hewan.getId())).safe();
			  
							PilihDrh pilih = new PilihDrh(drh_jaga, Ref.create(resepsionis));
									ofy().save().entity(pilih).now();
									pilih = ofy().load().key(Key.create(PilihDrh.class, pilih.getId())).safe();
											
			 Ambulator amb = new AmbulatorCtrl().create(tgl,sinyalmen, anamnesa, gizi, tempramen,
						habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
						sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
						hewan.getId(), pilih.getId(), no_reg, ktp, kode_hewan);	
			 
			   new AmbulatorCtrl().get(amb.getNo_reg());	

			assertNotNull(pemilik);
			assertNotNull(klinik);
			assertNotNull(resepsionis);
//			assertEquals(ambs.getTgl(),"2/2/2018");			
	  }	
}
