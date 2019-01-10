package com.mobile.healthypets.model.hasillab;

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

public class TestHasilLabCtrl extends BaseTest {
	
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
	
	private static final String tgl = "2/2/2018";
	private static final String k_parasit = "abc";
	private static final String k_jamur= "abc";
	private static final String keadaan_feses= "abc";
	private static final String p_interna= "abc";
	private static final String protozoa= "abc";
	private static final String mikroba= "abc";
	private static final String warna= "abc";
	private static final String bau= "abc";
	private static final String uji_gula= "abc";
	private static final String uji_protein= "abc";
	private static final String uji_sedimentasi= "abc";
	private static final String warna_darah= "abc";
	private static final String sifat_darah= "abc";
	private static final String natif_protozoa= "abc";
	private static final String natif_bakteri= "abc";
	private static final String bdm= "abc";
	private static final String bdp= "abc";
	private static final String netrofil= "abc";
	private static final String eosinofil= "abc";
	private static final String basofil= "abc";
	private static final String limfosit= "abc";
	private static final String monosit= "abc";
	private static final String stab= "abc";
	private static final String hb= "abc";
	private static final String ht= "abc";
	private static final String diagnosa= "abc";
	private static final String dif_diag= "abc";
	private static final String prognosa= "abc";
	private static final String terapi= "abc";
	private static final Long drh_jaga= (long) 1;
	private static final Long no_reg= (long) 1001;
	private static final String ktp= "a";
	private static final String kode_hewan= "a";
	private static final Long idAmb = (long) 111;

	
	@Test  
	public void createHLab() throws NoSuchAlgorithmException, InvalidKeySpecException {
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
								 
								   Ambulator ambulator = new Ambulator(tgl,sinyalmen, anamnesa, gizi, tempramen,
											habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
											sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
											Ref.create(hewan), Ref.create(pilih), no_reg, ktp, kode_hewan);
								   ofy().save().entity(ambulator).now();
								   ambulator = ofy().load().key(Key.create(Ambulator.class, ambulator.getId())).safe();
						 
           HasilLab hlab = new HasilLabCtrl().create(k_parasit, k_jamur, keadaan_feses, p_interna, protozoa, mikroba,
       			warna, bau, uji_gula, uji_protein, uji_sedimentasi, warna_darah, sifat_darah,
    			natif_protozoa, natif_bakteri, bdm, bdp, netrofil, eosinofil, basofil,
    			limfosit, monosit, stab, hb, ht, diagnosa, dif_diag, prognosa,
    			terapi,"1", hewan.getId(), resepsionis.getId(), ambulator.getId(), tgl, kode_hewan, idAmb);
           
		 
	    assertNotNull(pemilik);
	    assertNotNull(klinik);
	    assertNotNull(resepsionis);
	    assertNotNull(klien);
		assertEquals(hlab.getPrognosa(),"abc");
	} 	
	
	@Test  
	public void getHLab() throws NoSuchAlgorithmException, InvalidKeySpecException {
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
								 
								   Ambulator ambulator = new Ambulator(tgl,sinyalmen, anamnesa, gizi, tempramen,
											habitat, frek_nafas, frek_pulsus, suhu_tubuh, kulit_bulu, sedir_mata, sedir_hidung, sedir_mulut,
											sedir_anus, k_limfase, a_nafas, a_darah, a_cerna, a_kelamin, u_saraf, ang_gerak, lain, drh_jaga,
											Ref.create(hewan), Ref.create(pilih), no_reg, ktp, kode_hewan);
								   ofy().save().entity(ambulator).now();
								   ambulator = ofy().load().key(Key.create(Ambulator.class, ambulator.getId())).safe();
						 
           HasilLab hlab = new HasilLabCtrl().create(k_parasit, k_jamur, keadaan_feses, p_interna, protozoa, mikroba,
       			warna, bau, uji_gula, uji_protein, uji_sedimentasi, warna_darah, sifat_darah,
    			natif_protozoa, natif_bakteri, bdm, bdp, netrofil, eosinofil, basofil,
    			limfosit, monosit, stab, hb, ht, diagnosa, dif_diag, prognosa,
    			terapi,"1", hewan.getId(), resepsionis.getId(), ambulator.getId(), tgl, kode_hewan, idAmb);
           
		 HasilLab hlab2 = new HasilLabCtrl().get(hlab.getId());
           
	    assertNotNull(pemilik);
	    assertNotNull(klinik);
	    assertNotNull(resepsionis);
	    assertNotNull(klien);
		assertEquals(hlab2.getPrognosa(),"abc");
	} 	

}
