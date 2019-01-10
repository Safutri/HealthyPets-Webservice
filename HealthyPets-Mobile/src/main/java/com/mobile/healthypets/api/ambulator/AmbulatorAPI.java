package com.mobile.healthypets.api.ambulator;

import java.util.ArrayList;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.mobile.healthypets.api.antrianklien.JSONPesan;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.ambulator.Ambulator;
import com.mobile.healthypets.model.ambulator.AmbulatorCtrl; 
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.hewan.HewanCtrl;  

@Api(name = "ambulator",

title = "Layanan Ambulator",
version="v1",
description="API untuk resource ambulator")

public class AmbulatorAPI {
	@ApiMethod(name="list",
			httpMethod=HttpMethod.GET)
	public List<JSONAmbulator> list(@Nullable @Named("offset") Integer offset,@Nullable @Named("limit") Integer limit, 
			@Named("id") Long id) throws Exception {
		List<JSONAmbulator> response = new ArrayList<JSONAmbulator>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		Hewan hewan = new HewanCtrl().get(id);
		 
		if (hewan==null){
			throw new NotFoundException("ID Tidak Tersedia");
		}
				
		List<Ambulator> daftarAmbulator = 
				(List<Ambulator>) new AmbulatorCtrl().daftarbyHewan(offset, limit, id);
		
		if (daftarAmbulator.isEmpty()) {
			throw new NotFoundException("Tidak ada hewan");
		}
		
		JSONAmbulator b = new JSONAmbulator();
		for(Ambulator ambulator: daftarAmbulator) {
			b = new JSONAmbulator(ambulator);
			response.add(b);
		}
		
		return response;
	}


	
	
	@ApiMethod(name="Data",		
			path="dataAmb",
			httpMethod=HttpMethod.GET)  // list data ambulator berdasarkan kode_hewan di drh app
	public List<JSONAmbulator> Data(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit, 
			@Nullable @Named("kode_hewan") String kode_hewan) throws NotFoundException {
		
		List<JSONAmbulator> response = new ArrayList<JSONAmbulator>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		 
		List<Ambulator> amb = new AmbulatorCtrl().daftarAmb(offset, limit, kode_hewan);
		
		if (amb.isEmpty()) {  
			throw new NotFoundException("Data kosong");
		}
		
		JSONAmbulator b = new JSONAmbulator();
		for(Ambulator Ambulator: amb) {
			b = new JSONAmbulator(Ambulator);
			response.add(b);
		}
		
		return response;
	}
	
//	@ApiMethod(name="listForDrh",
//			httpMethod=HttpMethod.GET)
//	public List<JSONAmbulator> AmbForDrh(@Nullable @Named("offset") Integer offset,@Nullable @Named("limit") Integer limit, 
//			@Named("kode_hewan") String kode_hewan) throws Exception {
//		List<JSONAmbulator> response = new ArrayList<JSONAmbulator>();
//		 
//		if (offset == null || offset < 0) {
//			offset = KonfigurasiDasar.OFFSET;
//		}
//		
//		if (limit == null || limit < 0) {
//			limit = KonfigurasiDasar.LIMIT;
//		}
//		
//		Hewan hewan = new HewanCtrl().getk_hewan(kode_hewan);
//		 
//		if (hewan==null){
//			throw new NotFoundException("ID Tidak Tersedia");
//		}
//				 
//		List<Ambulator> daftarAmbulator =    
//				(List<Ambulator>) new AmbulatorCtrl().daftarAmb(offset, limit, kode_hewan);
//		
//		if (daftarAmbulator.isEmpty()) {
//			throw new NotFoundException("Tidak ada hewan");
//		}
//		
//		JSONAmbulator b = new JSONAmbulator();
//		for(Ambulator ambulator: daftarAmbulator) {
//			b = new JSONAmbulator(ambulator);
//			response.add(b);
//		}
//		
//		return response;
//	}

	@ApiMethod(name="create",
			httpMethod=HttpMethod.POST)
	public JSONAmbulator baru(JSONAmbulatorCreate jsonAmbulatorCreate) throws Exception {
		JSONAmbulator response = null;
		 		
		Hewan hewan = new HewanCtrl().get(jsonAmbulatorCreate.id);
		if (hewan==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		} 
		
		Ambulator ambulator = new AmbulatorCtrl().create(jsonAmbulatorCreate.tgl, jsonAmbulatorCreate.sinyalmen, 
				jsonAmbulatorCreate.anamnesa, jsonAmbulatorCreate.gizi, jsonAmbulatorCreate.tempramen, jsonAmbulatorCreate.habitat,
				 jsonAmbulatorCreate.frek_nafas,  jsonAmbulatorCreate.frek_pulsus, jsonAmbulatorCreate.suhu_tubuh, jsonAmbulatorCreate.kulit_bulu,
				 jsonAmbulatorCreate.sedir_mata, jsonAmbulatorCreate.sedir_hidung, jsonAmbulatorCreate.sedir_mulut, jsonAmbulatorCreate.sedir_anus,
				 jsonAmbulatorCreate.k_limfase, jsonAmbulatorCreate.a_nafas, jsonAmbulatorCreate.a_darah, jsonAmbulatorCreate.a_cerna, 
				 jsonAmbulatorCreate.a_kelamin, jsonAmbulatorCreate.u_saraf, jsonAmbulatorCreate.ang_gerak, jsonAmbulatorCreate.lain, 
				 jsonAmbulatorCreate.drh_jaga, jsonAmbulatorCreate.id, jsonAmbulatorCreate.idPilih, jsonAmbulatorCreate.no_reg, 
				 jsonAmbulatorCreate. ktp, jsonAmbulatorCreate.kode_hewan);
		response = new JSONAmbulator(ambulator);
		return response;  
	}
	 
 		@ApiMethod(name="get", 
				path="ambulator/{id}", 
				httpMethod=HttpMethod.GET)
		public JSONAmbulator get(@Named("id") Long id) throws Exception {
			JSONAmbulator response;
			
			Ambulator amb = new AmbulatorCtrl().get(id);
			 
			response = new JSONAmbulator(amb);
			
			return response;
		}
	
  		@ApiMethod(name="searchAmb", 
 				path="ambulator/search/{no_reg}", 
 				httpMethod=HttpMethod.GET)
 		public JSONAmbulator search(@Named("no_reg") Long no_reg) throws Exception {
 			JSONAmbulator response;
 			
 			Ambulator ambulator = new AmbulatorCtrl().cariNoreg(no_reg);
 			
 			if (ambulator==null){
 				throw new NotFoundException("drh Tidak Tersedia");
 			}
 			response = new JSONAmbulator(ambulator);
 			
 			return response; 
 		} 
  		
  		
  		@ApiMethod(name="DataHewan", 
  				path="data/kode_hewan/{kode_hewan}", //digunakan utk get amb
  				httpMethod=HttpMethod.GET)
  		public JSONAmbulator data(@Named("kode_hewan") String kode_hewan) throws Exception {
  			JSONAmbulator response;
  			
  			Ambulator amb = new AmbulatorCtrl().cariAmbulator(kode_hewan);
  			
  			if (amb==null){
  				throw new NotFoundException("Data Tidak Tersedia"); 
  			}
  			response = new JSONAmbulator(amb);
  			
  			return response;
  		}
  		
  		
  		@ApiMethod(name="delete", 
  				path="ambs/{id}", 
  				httpMethod=HttpMethod.DELETE)
  		public JSONPesan hapusAmb(@Named("id") Long id) throws Exception {
  			JSONPesan response = new JSONPesan("");
  			
  			new AmbulatorCtrl().hapus(id);
  			
  			Ambulator amb = new AmbulatorCtrl().get(id);
  			
  			if(amb == null) {
  				response.setMessage("Berhasil dihapus");
  			} else {
  				response.setMessage("Gagal dihapus");
  			}
  			
  			return response;
  		} 
  		
}
