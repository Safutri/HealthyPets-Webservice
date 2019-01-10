package com.mobile.healthypets.api.antrianklien;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException; 
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.antriandrh.AntrianKlien;
import com.mobile.healthypets.model.antriandrh.AntrianKlienCtrl; 
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.hewan.HewanCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl;  

@Api(name = "klienantri",
title = "Layanan Antrian Klien",
version="v1",
description="API untuk resource klien antri")
public class AntrianKlienAPI {
	
	@ApiMethod(name="list",
			path="antri",
			httpMethod=HttpMethod.GET)
	public List<JSONAntrianKlien> daftar(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit,
			@Named("drh_jaga") Long drh_jaga) throws NotFoundException {
		
		List<JSONAntrianKlien> response = new ArrayList<JSONAntrianKlien>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List<AntrianKlien> daftarAntrianKlien = new AntrianKlienCtrl().daftar(offset, limit, drh_jaga);
		
		if (daftarAntrianKlien.isEmpty()) {
			throw new NotFoundException("Daftar klien kosong");
		}
		
		JSONAntrianKlien jsonAntrianKlien = new JSONAntrianKlien();
		for(AntrianKlien p: daftarAntrianKlien) {
			jsonAntrianKlien = new JSONAntrianKlien(p);
			response.add(jsonAntrianKlien);
		}
		
		return response;
	}
	
	@ApiMethod(name="create",
			httpMethod=HttpMethod.POST)
	public JSONAntrianKlien Antri(JSONAntrianKlienCreate jsonAntrianKlienCreate) throws Exception {
		JSONAntrianKlien response = null;
		
		Hewan hewan = new HewanCtrl().get(jsonAntrianKlienCreate.id);
		

		if (hewan==null){
			throw new NotFoundException ("ID Hewan Tidak Tersedia");
		} 
		
		Resepsionis resepsionis = new ResepsionisCtrl().get(jsonAntrianKlienCreate.idResepsionis);

		
		if (resepsionis==null){
			throw new NotFoundException ("ID Resepsionis Tidak Tersedia");
		} 
		
		AntrianKlien antri = new AntrianKlienCtrl().create(jsonAntrianKlienCreate.nama, jsonAntrianKlienCreate.id, jsonAntrianKlienCreate.idResepsionis,
				jsonAntrianKlienCreate.jenis, jsonAntrianKlienCreate.ras, jsonAntrianKlienCreate.umur, jsonAntrianKlienCreate.gender, jsonAntrianKlienCreate.ttl, 
				jsonAntrianKlienCreate.warna, jsonAntrianKlienCreate.no_reg, jsonAntrianKlienCreate.ktp, 
				jsonAntrianKlienCreate.drh_jaga, jsonAntrianKlienCreate.kode_hewan, jsonAntrianKlienCreate.ambs, jsonAntrianKlienCreate.drhPilih);
		response = new JSONAntrianKlien (antri);
		return response;
	}
	
	@ApiMethod(name="delete", 
			path="antri/{id}", 
			httpMethod=HttpMethod.DELETE)
	public JSONPesan hapus(@Named("id") Long id) throws Exception {
		JSONPesan response = new JSONPesan("");
		
		new AntrianKlienCtrl().deleteAntrianKlien(id);
		
		// pastikan sudah dihapus
		AntrianKlien antri = new AntrianKlienCtrl().get(id);
		
		if(antri == null) {
			response.setMessage("Berhasil dihapus");
		} else {
			response.setMessage("Gagal dihapus");
		}
		
		return response;
	} 
	
	@ApiMethod(name="get", 
			httpMethod=HttpMethod.GET)
	public JSONAntrianKlien cari(
			@Named("id") Long id) throws Exception {
		JSONAntrianKlien response;
		 
		AntrianKlien antri = new AntrianKlienCtrl().get(id);
		
		if (antri==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		}
		response = new JSONAntrianKlien(antri);
		return response; 
	}
	   
	
	//list antrian per klinik sesuai dengan resepsionis yang login
		@ApiMethod(name="listAntrianbyResepsionisKlien", 
				httpMethod=HttpMethod.GET)
		public List<JSONAntrianKlien> daftarAntrian (@Nullable @Named("offset") Integer offset,@Nullable @Named("limit") Integer limit, 
				@Named("id") Long id) throws Exception {
			List<JSONAntrianKlien> response = new ArrayList<JSONAntrianKlien>();
			
			// periksa nilai offset dan limit
			if (offset == null || offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			
			if (limit == null || limit < 0) {
				limit = KonfigurasiDasar.LIMIT;
			}
			
			Resepsionis resepsionis = new ResepsionisCtrl().get(id);
			if (resepsionis==null){
				throw new NotFoundException("ID Tidak Tersedia");
			}
			
			List<AntrianKlien> daftarAntri = 
					(List<AntrianKlien>) new AntrianKlienCtrl().daftarbyResepsionis(offset, limit, id);
			
			if (daftarAntri.isEmpty()) {
				throw new NotFoundException("Daftar barang kosong");
			}
			
			JSONAntrianKlien b = new JSONAntrianKlien();
			for(AntrianKlien antri: daftarAntri) {
				b = new JSONAntrianKlien(antri);
				response.add(b);
			}
			
			return response;
		}
		
}
