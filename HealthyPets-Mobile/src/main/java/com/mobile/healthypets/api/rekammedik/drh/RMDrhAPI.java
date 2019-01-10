package com.mobile.healthypets.api.rekammedik.drh;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.rekammedik.RekamMedikDrh;
import com.mobile.healthypets.model.rekammedik.RekamMedikDrhCtrl;  

@Api (name= "rekammedikDrh",
title = "Layanan Rekam Medik Drh",
version="v1",
description="API untuk resource rekam medik drh")

public class RMDrhAPI {

	@ApiMethod(name="create",
			path="rekamedikDrh",
			httpMethod=HttpMethod.POST)
	public JSONRM create(JSONRMDrhCreate jsonRMDrh) throws Exception {
		JSONRM response = null;
		 		
		RekamMedikDrh rm= new RekamMedikDrhCtrl().create(jsonRMDrh.tgl, jsonRMDrh.nama_drh, jsonRMDrh.status_awal, jsonRMDrh.terapi, 
				jsonRMDrh.diagnosa, jsonRMDrh.obat, jsonRMDrh.kode_hewan, jsonRMDrh.no_reg, 
				jsonRMDrh.lain);
		
		response = new JSONRM(rm);
		
		return response;
	}
	
	@ApiMethod(name="daftar",
			path="rekamedikdrh",
			httpMethod=HttpMethod.GET)
	public List<JSONRM> daftar(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit,
			@Named("kode_hewan") String kode_hewan) throws NotFoundException {  
		 
		List<JSONRM> response = new ArrayList<JSONRM>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		} 
			
		List<RekamMedikDrh> daftarRekamMedik = new RekamMedikDrhCtrl().daftar(offset, limit, kode_hewan);
		
		if (daftarRekamMedik.isEmpty()) {
			throw new NotFoundException("Daftar Rekam Medik kosong");
		}
		
		JSONRM jsonRekamMedik = new JSONRM();
		for(RekamMedikDrh rm: daftarRekamMedik) {
			jsonRekamMedik = new JSONRM(rm);
			response.add(jsonRekamMedik);
		}
		
		return response;
	}	
	
	@ApiMethod(name="get", 
			httpMethod=HttpMethod.GET)
	public JSONRM ambilData(
			@Named("id") Long id) throws Exception {
		JSONRM response;
		
		RekamMedikDrh rm = new RekamMedikDrhCtrl().get(id);
		
		if (rm==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		}
		response = new JSONRM(rm);
		return response;
	}
	
}
