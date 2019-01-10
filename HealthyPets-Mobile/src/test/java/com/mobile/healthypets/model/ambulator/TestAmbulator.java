package com.mobile.healthypets.model.ambulator;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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

public class TestAmbulator extends BaseTest  {
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
	
	@Test  
	public void createHewan() throws NoSuchAlgorithmException, InvalidKeySpecException {
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
	    assertNotNull(pemilik);
	    assertNotNull(klinik);
	    assertNotNull(resepsionis);
	    assertNotNull(klien);
		assertEquals(amb.getAnamnesa(),"a");
	} 	
	
}
