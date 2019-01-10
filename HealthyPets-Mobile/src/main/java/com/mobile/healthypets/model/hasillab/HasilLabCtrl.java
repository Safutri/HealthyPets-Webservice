package com.mobile.healthypets.model.hasillab;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List; 
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.Result;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.ambulator.Ambulator;
import com.mobile.healthypets.model.ambulator.AmbulatorCtrl;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.hewan.HewanCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;

public class HasilLabCtrl {

	public List <HasilLab> daftar (int offset, int limit){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List <HasilLab> daftarHasilLab = ofy().load().type(HasilLab.class).offset(offset).limit(limit).list();
		
		if(daftarHasilLab==null||daftarHasilLab.size()<1){
			daftarHasilLab = new ArrayList<>();
		}
		return daftarHasilLab;
	}
 
	
	public List<HasilLab> daftarbyAmbulator(int offset, int limit, Long id) throws Exception {
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		// ambil info pemilik klien
		Ambulator amb = new AmbulatorCtrl().get(id);
		Ref<Ambulator> refAmbulator = Ref.create(amb);
		List<HasilLab> daftarHasilLab= ofy().load().type(HasilLab.class).filter("ambulator =", refAmbulator).list();
		 
		return daftarHasilLab;
	}
	
	public List<HasilLab> daftarHasilLabbyHewan(int offset, int limit, Long id) throws Exception {
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		Hewan hewan = new HewanCtrl().get(id);
		Ref<Hewan> refHewan  = Ref.create(hewan);
		List<HasilLab> daftarHasilLab = ofy().load().type(HasilLab.class).filter("hewan =", refHewan).list();
		
		return daftarHasilLab;
	}
	
	public HasilLab create(String k_parasit, String k_jamur, String keadaan_feses, String p_interna, String protozoa, String mikroba,
			String warna, String bau, String uji_gula, String uji_protein, String uji_sedimentasi, String warna_darah, String sifat_darah,
			String natif_protozoa, String natif_bakteri, String bdm, String bdp, String netrofil, String eosinofil, String basofil,
			String limfosit, String monosit, String stab, String hb, String ht, String diagnosa, String dif_diag, String prognosa,
			String terapi, String drh_jaga, Long idHewan, Long idResepsionis, Long idAmbulator, String tgl, String kode_hewan,
			Long idAmb) {
		// buat entitas  
		Key<Hewan> keyHewan = Key.create(Hewan.class, idHewan);
		Ref<Hewan> refHewan = Ref.create(keyHewan);
		
		Key<Resepsionis> keyResepsionis = Key.create(Resepsionis.class, idResepsionis);
		Ref<Resepsionis> refResepsionis = Ref.create(keyResepsionis);
		
		Key<Ambulator> keyAmbulator = Key.create(Ambulator.class, idAmbulator);
		Ref<Ambulator> refAmbulator = Ref.create(keyAmbulator);
		
		// entitas hewan yang baru
		HasilLab lab = new HasilLab(k_parasit, k_jamur, keadaan_feses, p_interna, protozoa, mikroba, warna, bau, uji_gula,
				uji_protein, uji_sedimentasi, warna_darah, sifat_darah, natif_protozoa, natif_bakteri, bdm, bdp, netrofil,
				eosinofil, basofil, limfosit, monosit, stab, hb, ht, diagnosa, dif_diag, prognosa, terapi, drh_jaga,
				refHewan, refResepsionis, refAmbulator, tgl, kode_hewan, idAmb);
		
		// tambah cafe ini ke Pemilik.cafe
		// simpan semua perubahan
		ofy().save().entity(lab).now();

		return lab;
	}
	
	public HasilLab get(Long id){
		// buat key dari id
				Key<HasilLab> key = Key.create(HasilLab.class, id);
				HasilLab lab = null;
					try {
					lab = ofy().load().key(key).safe();
				} catch (NotFoundException e) {
					// pastikan eksepsi yang ditangkap dari package objectify
					// tangani kesalahan
				}
				return lab;
	}
	
//	public HasilLab cariLab(String kode_hewan){
//		List<HasilLab> daftarLab = null;
//		HasilLab lab = null;
//			try {
//				daftarLab = ofy().load().type(HasilLab.class).filter("kode_hewan", kode_hewan).list();
//		} catch (NotFoundException e) {}
//			if (daftarLab.size() < 1) {
//				
//			} else {
//
//				lab = daftarLab.get(0);	
//			}
//		return lab;
//	}
	
	public HasilLab UpdateLab(Long id, String k_parasit, String k_jamur, String keadaan_feses, String p_interna, String protozoa, String mikroba,
			String warna, String bau, String uji_gula, String uji_protein, String uji_sedimentasi, String warna_darah, String sifat_darah,
			String natif_protozoa, String natif_bakteri, String bdm, String bdp, String netrofil, String eosinofil, String basofil,
			String limfosit, String monosit, String stab, String hb, String ht, String diagnosa, String dif_diag, String prognosa,
			String terapi) throws Exception{
		
		HasilLab lab  = null;
		Result<HasilLab> result = ofy().load().key(Key.create(HasilLab.class, id));
		lab = result.now();
		
		lab.setK_parasit(k_parasit);
		lab.setK_jamur(k_jamur);
		lab.setKeadaan_feses(keadaan_feses);
		lab.setP_interna(p_interna);
		lab.setProtozoa(protozoa);
		lab.setWarna(warna);
		lab.setBau(bau);
		lab.setUji_gula(uji_gula);
		lab.setUji_protein(uji_protein);
		lab.setUji_sedimentasi(uji_sedimentasi);
		lab.setWarna(warna_darah);
		lab.setSifat_darah(sifat_darah);
		lab.setNatif_protozoa(natif_protozoa);
		lab.setNatif_bakteri(natif_bakteri);
		lab.setBdm(bdm);
		lab.setBdp(bdp);
		lab.setNetrofil(netrofil);
		lab.setEosinofil(eosinofil);
		lab.setBasofil(basofil);
		lab.setLimfosit(limfosit);
		lab.setMonosit(monosit);
		lab.setStab(stab);
		lab.setHb(hb);
		lab.setHt(ht);
		lab.setDiagnosa(diagnosa);
		lab.setDif_diag(dif_diag);
		lab.setPrognosa(prognosa);
		lab.setTerapi(terapi);
		
		ofy().save().entities(lab).now();
		
		result = ofy().load().key(Key.create(HasilLab.class, id));
		lab = result.now();
	
	return lab;
	}
	
	
	public List<HasilLab> labs(int offset, int limit, Long idAmbulator, Long idAmb){
		if (offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		if (limit < 1) {
			limit = KonfigurasiDasar.LIMIT;
		}
		Ambulator ambs = new AmbulatorCtrl().get(idAmbulator);
		Ref<Ambulator> refAmbulator = Ref.create(ambs);
		List<HasilLab> amb = ofy().load().type(HasilLab.class).offset(offset).limit(limit).filter("ambulator", refAmbulator).
				filter("idAmb", idAmb).list(); 
		return amb;
	}	
	 
}
