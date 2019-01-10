package com.mobile.healthypets.api.lab;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.ambulator.Ambulator;
import com.mobile.healthypets.model.ambulator.AmbulatorCtrl;
import com.mobile.healthypets.model.hasillab.HasilLab;
import com.mobile.healthypets.model.hasillab.HasilLabCtrl;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.hewan.HewanCtrl; 
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 
  
@Api(name = "lab",
title = "Layanan Hasil Lab",
version="v1",
description="API untuk resource hasil lab")
public class LabAPI {
	
	@ApiMethod(name="list",		
			path="hasillab",
			httpMethod=HttpMethod.GET)
	public List<JSONLab> list(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit) throws NotFoundException {
		
		List<JSONLab> response = new ArrayList<JSONLab>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List<HasilLab> lab = new HasilLabCtrl().daftar(offset, limit);
		
		if (lab.isEmpty()) {
			throw new NotFoundException("Data kosong");
		}
		
		JSONLab b = new JSONLab();
		for(HasilLab Hasillab: lab) {
			b = new JSONLab(Hasillab);
			response.add(b);
		}
		
		return response;
	}
	
	
	//list antrian per klinik sesuai dengan resepsionis yang login
		@ApiMethod(name="listHasilLabbyHewan", 
				httpMethod=HttpMethod.GET)
		public List<JSONLab> daftar (@Nullable @Named("offset") Integer offset,@Nullable @Named("limit") Integer limit, 
				@Named("id") Long id) throws Exception {
			List<JSONLab> response = new ArrayList<JSONLab>();
			
			// periksa nilai offset dan limit
			if (offset == null || offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			
			if (limit == null || limit < 0) {
				limit = KonfigurasiDasar.LIMIT;
			}
			
			Hewan hewan = new HewanCtrl().get(id);
			if (hewan==null){ 
				throw new NotFoundException("ID Hewan Tidak Tersedia");
			}
			
			List<HasilLab> daftarHasilLab = 
					(List<HasilLab>) new HasilLabCtrl().daftarHasilLabbyHewan(offset, limit, id);
			
			if (daftarHasilLab.isEmpty()) {
				throw new NotFoundException("Hasil Lab tidak ada");
			}
			
			JSONLab b = new JSONLab();
			for(HasilLab lab: daftarHasilLab) {
				b = new JSONLab(lab);
				response.add(b);
			}
			
			return response;
		}
	
		
		//list antrian per klinik sesuai dengan resepsionis yang login
				@ApiMethod(name="listHasilLabbyAmbulator", 
						httpMethod=HttpMethod.GET)
				public List<JSONLab> hasil (@Nullable @Named("offset") Integer offset,@Nullable @Named("limit") Integer limit, 
						@Named("id") Long id) throws Exception {
					List<JSONLab> response = new ArrayList<JSONLab>();
					
					// periksa nilai offset dan limit
					if (offset == null || offset < 0) {
						offset = KonfigurasiDasar.OFFSET;
					}
					
					if (limit == null || limit < 0) {
						limit = KonfigurasiDasar.LIMIT;
					}
					
					Ambulator amb = new AmbulatorCtrl().get(id);
					if (amb==null){ 
						throw new NotFoundException("ID Amb Tidak Tersedia");
					} 
					
					List<HasilLab> daftarHasilLab = 
							(List<HasilLab>) new HasilLabCtrl().daftarbyAmbulator(offset, limit, id);
					
					if (daftarHasilLab.isEmpty()) {
						throw new NotFoundException("Hasil Lab tidak ada");
					}
					  
					JSONLab b = new JSONLab();
					for(HasilLab lab: daftarHasilLab) {
						b = new JSONLab(lab);
						response.add(b);
					}
					
					return response;
				}
	
	@ApiMethod(name="create",
			httpMethod=HttpMethod.POST)
	public JSONLab HLabbaru(JSONLabCreate jsonLabCreate) throws Exception {
		JSONLab response = null;
		
		Hewan hewan = new HewanCtrl().get(jsonLabCreate.idHewan);
		if (hewan==null){
			throw new NotFoundException ("ID Hewan Tidak Tersedia");
		} 
		
		Resepsionis resepsionis = new ResepsionisCtrl().get(jsonLabCreate.idResepsionis);
		if (resepsionis==null){
			throw new NotFoundException ("ID Resepsionis Tidak Tersedia");
		}  
		
		Ambulator amb= new AmbulatorCtrl().get(jsonLabCreate.idAmbulator);
		if (amb==null){
			throw new NotFoundException ("ID Ambulator Tidak Tersedia");
		} 
		
		HasilLab lab = new HasilLabCtrl().create(jsonLabCreate.k_parasit, jsonLabCreate.k_jamur, jsonLabCreate.keadaan_feses, jsonLabCreate.p_interna, 
				jsonLabCreate.protozoa, jsonLabCreate.mikroba, jsonLabCreate.warna, jsonLabCreate.bau, jsonLabCreate.uji_gula,
				jsonLabCreate.uji_protein, jsonLabCreate.uji_sedimentasi, jsonLabCreate.warna_darah, jsonLabCreate.sifat_darah, 
				jsonLabCreate.natif_protozoa, jsonLabCreate.natif_bakteri, jsonLabCreate.bdm, jsonLabCreate.bdp, jsonLabCreate.netrofil,
				jsonLabCreate.eosinofil, jsonLabCreate.basofil, jsonLabCreate.limfosit, jsonLabCreate.monosit, jsonLabCreate.stab, 
				jsonLabCreate.hb, jsonLabCreate.ht, jsonLabCreate.diagnosa, jsonLabCreate.dif_diag, jsonLabCreate.prognosa, 
				jsonLabCreate.terapi, jsonLabCreate.drh_jaga, jsonLabCreate.idHewan, jsonLabCreate.idResepsionis, jsonLabCreate.idAmbulator,
				jsonLabCreate.tgl, jsonLabCreate.kode_hewan, jsonLabCreate.idAmb);
		response = new JSONLab(lab);
		return response;
	}
	
	@ApiMethod(name="get", 
			httpMethod=HttpMethod.GET)
	public JSONLab get(
			@Named("id") Long id) throws Exception {
		JSONLab response;
		
		HasilLab lab = new HasilLabCtrl().get(id);
		
		if (lab==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		}
		response = new JSONLab(lab);
		return response;
	} 
	
//	@ApiMethod(name="CariHasil",
//			path="lab",
//			httpMethod=HttpMethod.GET)
//	public JSONLab getLab(
//			@Named("kode_hewan") String kode_hewan) throws Exception {
//		JSONLab response;
//		
//		HasilLab lab = new HasilLabCtrl().cariLab(kode_hewan);
//		
//		if (lab==null){
//			throw new NotFoundException ("ID Tidak Tersedia");
//		}
//		response = new JSONLab(lab); 
//		return response;
//	}
	

	@ApiMethod(name="cariLab",  //pencarian di queary search (search by no_reg)
			httpMethod=HttpMethod.GET)  
	public List<JSONLab> cariLabs( 
			@Nullable @Named("offset") Integer offset, 
			@Nullable @Named("limit") Integer limit, 
			@Named("idAmbulator") Long idAmbulator,
			@Named("idAmb") Long idAmb) throws NotFoundException {
		 
		List<JSONLab> response = new ArrayList<JSONLab>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		} 
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		Ambulator ambs = new AmbulatorCtrl().get(idAmbulator);
		if (ambs==null){
			throw new NotFoundException("ID Tidak Tersedia");
		}
		
//		List<HasilLab> daftarHasilLab = 
//				(List<HasilLab>) new HasilLabCtrl().daftarbyAmbulator(offset, limit, id);
		
		
		List<HasilLab> daftarHasilLab = new HasilLabCtrl().labs(offset, limit, idAmbulator, idAmb);
		
		if (daftarHasilLab.isEmpty()) { 
			throw new NotFoundException("Data tidak ditemukan");
		}
		 
			JSONLab b = new JSONLab();
			for(HasilLab lab: daftarHasilLab) {
				b = new JSONLab(lab);
				response.add(b); 
			
		}
			return response;
		
	}
	
}
