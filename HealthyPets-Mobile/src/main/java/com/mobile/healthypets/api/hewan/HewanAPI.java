package com.mobile.healthypets.api.hewan;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod; 
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.NotFoundException;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.hewan.HewanCtrl;
import com.mobile.healthypets.model.klien.Klien;
import com.mobile.healthypets.model.klien.KlienCtrl; 
 
@Api(name = "hewan",
title = "Layanan Hewan",
version="v1",
description="API untuk resource hewan")


public class HewanAPI {

	@ApiMethod(name="daftar", 
			httpMethod=HttpMethod.GET)
	public List<JSONHewan> daftar (@Nullable @Named("offset") Integer offset,@Nullable @Named("limit") Integer limit, 
			@Named("id") Long id) throws Exception {
		List<JSONHewan> response = new ArrayList<JSONHewan>();
		
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		Klien klien = new KlienCtrl().cari(id);   //  => daftar berdasarkan id
		if (klien==null){
			throw new NotFoundException("Data Tidak Tersedia");
		}
				
		List<Hewan> daftarHewan = 
				(List<Hewan>) new HewanCtrl().daftarbyKlien(offset, limit, id);
		
		if (daftarHewan.isEmpty()) {
			throw new NotFoundException("Tidak ada hewan");
		}
		
		JSONHewan b = new JSONHewan();
		for(Hewan hewan: daftarHewan) {
			b = new JSONHewan(hewan);
			response.add(b);
		}
		
		return response;
	}
	
	
		@ApiMethod(name="list",		
				path="hewan",
				httpMethod=HttpMethod.GET)
		public List<JSONHewan> list(
				@Nullable @Named("offset") Integer offset,
				@Nullable @Named("limit") Integer limit) throws NotFoundException {
			
			List<JSONHewan> response = new ArrayList<JSONHewan>();
			 
			if (offset == null || offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			
			if (limit == null || limit < 0) {
				limit = KonfigurasiDasar.LIMIT;
			}
			
			List<Hewan> daftarHewan = new HewanCtrl().daftar(offset, limit);
			
			if (daftarHewan.isEmpty()) {
				throw new NotFoundException("Data kosong");
			}
			
			JSONHewan b = new JSONHewan();
			for(Hewan hewan: daftarHewan) {
				b = new JSONHewan(hewan);
				response.add(b);
			}
			
			return response;
		} 
	
	
	@ApiMethod(name="create",
			httpMethod=HttpMethod.POST)
	public JSONHewan baru(JSONHewanCreate jsonHewanCreate) throws Exception {
		JSONHewan response = null;
		
		Klien klien = new KlienCtrl().cari(jsonHewanCreate.id);
		if (klien==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		} 
		
		Hewan hewan = new HewanCtrl().create(jsonHewanCreate.nama, jsonHewanCreate.id, jsonHewanCreate.email_klinik, jsonHewanCreate.jenis, 
				jsonHewanCreate.ras, jsonHewanCreate.umur, jsonHewanCreate.gender, jsonHewanCreate.ttl, jsonHewanCreate.warna,
				jsonHewanCreate.no_reg, jsonHewanCreate.ktp, jsonHewanCreate.kode_hewan);
		response = new JSONHewan(hewan);
		return response;
	}
	

	
	
	@ApiMethod(name="cari", 
			httpMethod=HttpMethod.GET)
	public JSONHewan cari(
			@Named("id") Long id) throws Exception {
		JSONHewan response;
		
		Hewan hewan = new HewanCtrl().get(id);
		
		if (hewan==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		}
		response = new JSONHewan(hewan);
		return response;
	}
	
//	@ApiMethod(name="search", 
//			path="hewan/search/{no_reg}", 
//			httpMethod=HttpMethod.GET)
//	public JSONHewan search(@Named("no_reg") Long no_reg) throws Exception {
//		JSONHewan response;
//		
//		Hewan hewan = new HewanCtrl().cariNoreg(no_reg);
//		
//		if (hewan==null){
//			throw new NotFoundException("No Regitrasi Tidak Tersedia");
//		}
//		response = new JSONHewan(hewan);
//		
//		return response;
//	}
	


	
	@ApiMethod(name="DataNama", 
			path="data/{kode_hewan}", 
			httpMethod=HttpMethod.GET)
	public JSONHewan search(@Named("kode_hewan") String kode_hewan) throws Exception {
		JSONHewan response;
		
		Hewan hewan = new HewanCtrl().cariNama(kode_hewan);
		
		if (hewan==null){
			throw new NotFoundException("No Regitrasi Tidak Tersedia"); 
		}
		response = new JSONHewan(hewan);
		
		return response;
	}
	
	
//	@ApiMethod(name="data",
//			path="kode_hewan",
//			httpMethod=HttpMethod.GET)
//	public List<JSONHewan> daftarData(
//			@Nullable @Named("offset") Integer offset,
//			@Nullable @Named("limit") Integer limit,
//			@Named("kode_hewan") String kode_hewan) throws NotFoundException {
//		
//		List<JSONHewan> response = new ArrayList<JSONHewan>();
//		 
//		if (offset == null || offset < 0) {
//			offset = KonfigurasiDasar.OFFSET;
//		}
//		 
//		if (limit == null || limit < 0) {
//			limit = KonfigurasiDasar.LIMIT;
//		}
//		
//		List<Hewan> daftarHewan = new HewanCtrl().daftarHewan(offset, limit, kode_hewan);
//		
//		if (daftarHewan.isEmpty()) {
//			throw new NotFoundException("Daftar klien kosong");
//		}
//		
//		JSONHewan jsonHewan = new JSONHewan();
//		for(Hewan p: daftarHewan) {
//			jsonHewan = new JSONHewan(p);
//			response.add(jsonHewan);
//		}
//		
//		return response;
//	}
	
	
}
