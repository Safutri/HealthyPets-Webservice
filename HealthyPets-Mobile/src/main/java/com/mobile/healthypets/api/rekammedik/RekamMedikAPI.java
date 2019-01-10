package com.mobile.healthypets.api.rekammedik;

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
import com.mobile.healthypets.model.rekammedik.RekamMedik;
import com.mobile.healthypets.model.rekammedik.RekamMedikCtrl;  

@Api (name= "rekammedik",
title = "Layanan Rekam Medik",
version="v1",
description="API untuk resource rekam medik")

public class RekamMedikAPI {
	
	@ApiMethod(name="daftar",
			path="rekamedik",
			httpMethod=HttpMethod.GET)
	public List<JSONRekamMedik> daftar(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit,
			@Named("kode_hewan") String kode_hewan) throws NotFoundException {  
		 
		List<JSONRekamMedik> response = new ArrayList<JSONRekamMedik>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		} 
			
		List<RekamMedik> daftarRekamMedik = new RekamMedikCtrl().daftar(offset, limit, kode_hewan);
		
		if (daftarRekamMedik.isEmpty()) {
			throw new NotFoundException("Daftar Rekam Medik kosong");
		}
		
		JSONRekamMedik jsonRekamMedik = new JSONRekamMedik();
		for(RekamMedik rm: daftarRekamMedik) {
			jsonRekamMedik = new JSONRekamMedik(rm);
			response.add(jsonRekamMedik);
		}
		
		return response;
	}
	
	@ApiMethod(name="Data",		
			path="dataRM",
			httpMethod=HttpMethod.GET)  // list data ambulator berdasarkan kode_hewan di drh app
	public List<JSONRekamMedik> Data(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit, 
			@Nullable @Named("kode_hewan") String kode_hewan) throws NotFoundException {
		
		List<JSONRekamMedik> response = new ArrayList<JSONRekamMedik>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List<RekamMedik> rm = new RekamMedikCtrl().daftarRM(offset, limit, kode_hewan);
		
		if (rm.isEmpty()) {  
			throw new NotFoundException("Data kosong");
		}
		
		JSONRekamMedik b = new JSONRekamMedik();
		for(RekamMedik RekamMedik: rm) {
			b = new JSONRekamMedik(RekamMedik);
			response.add(b);
		}
		
		return response;
	}

	@ApiMethod(name="create",
			path="rekamedik",
			httpMethod=HttpMethod.POST)
	public JSONRekamMedik baru(JSONRekamMedikCreate jsonRekamMedikCreate) throws Exception {
		JSONRekamMedik response = null;
		
		Ambulator ambulator = new AmbulatorCtrl().get(jsonRekamMedikCreate.id);
		if (ambulator==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		} 
		
		RekamMedik rm= new RekamMedikCtrl().create(jsonRekamMedikCreate.tgl, jsonRekamMedikCreate.nama_drh, jsonRekamMedikCreate.status_awal, 
				jsonRekamMedikCreate.terapi, jsonRekamMedikCreate.diagnosa,
				jsonRekamMedikCreate.obat, jsonRekamMedikCreate.kode_hewan, jsonRekamMedikCreate.no_reg, jsonRekamMedikCreate.lain,
				jsonRekamMedikCreate.id, jsonRekamMedikCreate.idAmb);
		
		response = new JSONRekamMedik(rm);
		
		return response;
	}
	
		@ApiMethod(name="ubah", 
		httpMethod=HttpMethod.PUT)
	public JSONRekamMedik ubah(JSONRekamMedikUbah jsonRekamMedikUbah) throws Exception {
			JSONRekamMedik response = null;
			
			RekamMedik cariRekamMedik = new RekamMedikCtrl().get(jsonRekamMedikUbah.id);
			
			if (cariRekamMedik==null){
				throw new NotFoundException("ID Tidak Tersedia");
			}
			RekamMedik rm = new RekamMedikCtrl().updateRM(jsonRekamMedikUbah.id, jsonRekamMedikUbah.status_awal, 
					jsonRekamMedikUbah.terapi, jsonRekamMedikUbah.diagnosa, jsonRekamMedikUbah.obat);
			
			response = new JSONRekamMedik(rm);
			
			return response;
	}
	
		@ApiMethod(name="get", 
				httpMethod=HttpMethod.GET)
		public JSONRekamMedik ambilData(
				@Named("id") Long id) throws Exception {
			JSONRekamMedik response;
			
			RekamMedik rm = new RekamMedikCtrl().get(id);
			
			if (rm==null){
				throw new NotFoundException ("ID Tidak Tersedia");
			}
			response = new JSONRekamMedik(rm);
			return response;
		}
		 
		
		@ApiMethod(name="DataRM", 
  				path="data/rekam_medik/{idAmb}", //digunakan utk get amb
  				httpMethod=HttpMethod.GET)
  		public JSONRekamMedik datarm(@Named("idAmb") Long idAmb) throws Exception {
  			JSONRekamMedik response;
  			
  			RekamMedik rm = new RekamMedikCtrl().cariRM(idAmb);
  			
  			if (rm==null){
  				throw new NotFoundException("Data Tidak Tersedia"); 
  			}
  			response = new JSONRekamMedik(rm);
  			
  			return response;
  		}
  		
}
