package com.mobile.healthypets.model.rekammedik;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.mobile.healthypets.BaseTest;
import com.mobile.healthypets.model.ambulator.Ambulator;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.klien.Klien;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pilihdrh.PilihDrh;
import com.mobile.healthypets.model.resepsionis.Resepsionis;

public class TestRekamMedikCtrl extends BaseTest{ 
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
	private static final String ktp= "a";
	private static final String tgl = "2/2/2018";
	private static final String nama_drh = "anita";
	private static final String status_awal = "aa";
	private static final String terapi = "aa";
	private static final String diagnosa = "aa";
	private static final String obat = "aa";
	private static final String kode_hewan = "aa";
	private static final Long no_reg = (long) 1001;
	private static final Long drh_jaga = (long) 1;
	private static final String lain = "aa";
	private static final Long idAmb = (long) 11;
	
	@Test  
	public void createRM() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "1323", "0812124552765");
			ofy().save().entity(pemilik).now();
			pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
			Pemakai pemakai = (Pemakai) pemilik;
		
			Klinik klinik = new Klinik("klinikA", Ref.create(pemakai), "lamteh",
				"klinika@mail.com", "091243423", "sads334ed");
				ofy().save().entity(klinik).now();
				klinik = ofy().load().key(Key.create(Klinik.class, klinik.getId())).safe(); 
			
				Resepsionis resepsionis = new Resepsionis("Nana", Ref.create(klinik), "keutapang",
						"nana@mail.com", "08544546765", "klinikA", "klinika@mail.com");
						ofy().save().entity(resepsionis).now();
						resepsionis = ofy().load().key(Key.create(Resepsionis.class, resepsionis.getId())).safe();

						 Klien klien = new Klien("klinika@mail.com", "Sara", "lampineung", "kaka@mail", no_reg, "08242352525",
						    		"199004353525", Ref.create(resepsionis));
						 ofy().save().entity(klien).now();
						 klien = ofy().load().key(Key.create(Klien.class, klien.getId())).safe();
						 	
							 Hewan hewan = new Hewan("caca", Ref.create(klien), "klinika@mail.com", "kucing", "persia", "3", 
									 "female", "12/12/2012", "hitam", no_reg, ktp, kode_hewan);
							 ofy().save().entity(hewan).now();
							 hewan = ofy().load().key(Key.create(Hewan.class, hewan.getId())).safe();
							 
							    PilihDrh pilih = new PilihDrh(drh_jaga, Ref.create(resepsionis));
							    ofy().save().entity(pilih).now();
								 pilih = ofy().load().key(Key.create(PilihDrh.class, pilih.getId())).safe();
								 
								 Ambulator amb = new Ambulator(tgl,sinyalmen, anamnesa, gizi, tempramen,
											habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
											sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
											Ref.create(hewan), Ref.create(pilih), no_reg, ktp, kode_hewan);
								 ofy().save().entity(amb).now();
								 amb = ofy().load().key(Key.create(Ambulator.class, amb.getId())).safe();
						 
           RekamMedik rm = new RekamMedikCtrl().create(tgl, nama_drh, status_awal, terapi, diagnosa, obat,
              kode_hewan, no_reg, lain, amb.getId(), idAmb);
            
	    assertNotNull(pemilik);
	    assertNotNull(klinik);
	    assertNotNull(resepsionis);
	    assertNotNull(klien);
		assertEquals(rm.getTgl(),"2/2/2018");
	} 	
	
	@Test  
	public void RMCrari1() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "1323", "0812124552765");
			ofy().save().entity(pemilik).now();
			pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
			Pemakai pemakai = (Pemakai) pemilik;
		
			Klinik klinik = new Klinik("klinikA", Ref.create(pemakai), "lamteh",
				"klinika@mail.com", "091243423", "sads334ed");
				ofy().save().entity(klinik).now();
				klinik = ofy().load().key(Key.create(Klinik.class, klinik.getId())).safe(); 
			
				Resepsionis resepsionis = new Resepsionis("Nana", Ref.create(klinik), "keutapang",
						"nana@mail.com", "08544546765", "klinikA", "klinika@mail.com");
						ofy().save().entity(resepsionis).now();
						resepsionis = ofy().load().key(Key.create(Resepsionis.class, resepsionis.getId())).safe();

						 Klien klien = new Klien("klinika@mail.com", "Sara", "lampineung", "kaka@mail", no_reg, "08242352525",
						    		"199004353525", Ref.create(resepsionis));
						 ofy().save().entity(klien).now();
						 klien = ofy().load().key(Key.create(Klien.class, klien.getId())).safe();
						 	
							 Hewan hewan = new Hewan("caca", Ref.create(klien), "klinika@mail.com", "kucing", "persia", "3", 
									 "female", "12/12/2012", "hitam", no_reg, ktp, kode_hewan);
							 ofy().save().entity(hewan).now();
							 hewan = ofy().load().key(Key.create(Hewan.class, hewan.getId())).safe();
							 
							    PilihDrh pilih = new PilihDrh(drh_jaga, Ref.create(resepsionis));
							    ofy().save().entity(pilih).now();
								 pilih = ofy().load().key(Key.create(PilihDrh.class, pilih.getId())).safe();
								 
								 Ambulator amb = new Ambulator(tgl,sinyalmen, anamnesa, gizi, tempramen,
											habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
											sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
											Ref.create(hewan), Ref.create(pilih), no_reg, ktp, kode_hewan);
								 ofy().save().entity(amb).now();
								 amb = ofy().load().key(Key.create(Ambulator.class, amb.getId())).safe();
						 
           RekamMedik rm = new RekamMedikCtrl().create(tgl, nama_drh, status_awal, terapi, diagnosa, obat,
              kode_hewan, no_reg, lain, amb.getId(), idAmb);
           
           RekamMedik rm2 = new RekamMedikCtrl().get(rm.getId());
            
	    assertNotNull(pemilik);
	    assertNotNull(klinik);
	    assertNotNull(resepsionis);
	    assertNotNull(klien);
		assertEquals(rm2.getTgl(),"2/2/2018");
	} 	
	
	@Test  
	public void RMCari2() throws NoSuchAlgorithmException, InvalidKeySpecException {
		Pemilik pemilik = new Pemilik("diana", "bna", "diana@bna.com", "1323", "0812124552765");
			ofy().save().entity(pemilik).now();
			pemilik = ofy().load().key(Key.create(Pemilik.class, pemilik.getId())).safe();
			Pemakai pemakai = (Pemakai) pemilik;
		
			Klinik klinik = new Klinik("klinikA", Ref.create(pemakai), "lamteh",
				"klinika@mail.com", "091243423", "sads334ed");
				ofy().save().entity(klinik).now();
				klinik = ofy().load().key(Key.create(Klinik.class, klinik.getId())).safe(); 
			
				Resepsionis resepsionis = new Resepsionis("Nana", Ref.create(klinik), "keutapang",
						"nana@mail.com", "08544546765", "klinikA", "klinika@mail.com");
						ofy().save().entity(resepsionis).now();
						resepsionis = ofy().load().key(Key.create(Resepsionis.class, resepsionis.getId())).safe();

						 Klien klien = new Klien("klinika@mail.com", "Sara", "lampineung", "kaka@mail", no_reg, "08242352525",
						    		"199004353525", Ref.create(resepsionis));
						 ofy().save().entity(klien).now();
						 klien = ofy().load().key(Key.create(Klien.class, klien.getId())).safe();
						 	
							 Hewan hewan = new Hewan("caca", Ref.create(klien), "klinika@mail.com", "kucing", "persia", "3", 
									 "female", "12/12/2012", "hitam", no_reg, ktp, kode_hewan);
							 ofy().save().entity(hewan).now();
							 hewan = ofy().load().key(Key.create(Hewan.class, hewan.getId())).safe();
							 
							    PilihDrh pilih = new PilihDrh(drh_jaga, Ref.create(resepsionis));
							    ofy().save().entity(pilih).now();
								 pilih = ofy().load().key(Key.create(PilihDrh.class, pilih.getId())).safe();
								 
								 Ambulator amb = new Ambulator(tgl,sinyalmen, anamnesa, gizi, tempramen,
											habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
											sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
											Ref.create(hewan), Ref.create(pilih), no_reg, ktp, kode_hewan);
								 ofy().save().entity(amb).now();
								 amb = ofy().load().key(Key.create(Ambulator.class, amb.getId())).safe();
						 
           RekamMedik rm = new RekamMedikCtrl().create(tgl, nama_drh, status_awal, terapi, diagnosa, obat,
              kode_hewan, no_reg, lain, amb.getId(), idAmb);
           
           RekamMedik rm2 = new RekamMedikCtrl().cariRM(rm.getIdAmb());
            
	    assertNotNull(pemilik);
	    assertNotNull(klinik);
	    assertNotNull(resepsionis);
	    assertNotNull(klien);
		assertEquals(rm2.getTgl(),"2/2/2018");
	} 	

}
