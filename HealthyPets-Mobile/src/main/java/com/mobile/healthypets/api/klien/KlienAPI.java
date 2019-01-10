package com.mobile.healthypets.api.klien;

import java.util.ArrayList;
import java.util.List;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.NotFoundException;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.klien.Klien;
import com.mobile.healthypets.model.klien.KlienCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 

@Api (name= "klien",
title = "Layanan Klien",
version="v1",
description="API untuk resource klien")


public class KlienAPI {  
		@ApiMethod(name="daftar",
				path="klien",
				httpMethod=HttpMethod.GET)
		public List<JSONKlien> daftar(
				@Nullable @Named("offset") Integer offset,
				@Nullable @Named("limit") Integer limit) throws NotFoundException {
			
			List<JSONKlien> response = new ArrayList<JSONKlien>();
			 
			if (offset == null || offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			
			if (limit == null || limit < 0) {
				limit = KonfigurasiDasar.LIMIT;
			}
			
			List<Klien> daftarKlien = new KlienCtrl().daftar(offset, limit);
			
			if (daftarKlien.isEmpty()) {
				throw new NotFoundException("Daftar klien kosong");
			}
			
			JSONKlien jsonKlien = new JSONKlien();
			for(Klien p: daftarKlien) {
				jsonKlien = new JSONKlien(p);
				response.add(jsonKlien);
			}
			
			return response;
		}
	
		
		@ApiMethod(name="daftarbyResepsionis", 
				httpMethod=HttpMethod.GET)
		public List<JSONKlien> daftarbyResepsionis (@Nullable @Named("offset") Integer offset,
				@Nullable @Named("limit") Integer limit, 
				@Named("id") Long id) throws Exception {
			List<JSONKlien> response = new ArrayList<JSONKlien>();
			
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
			
			List<Klien> daftarklien = 
					(List<Klien>) new KlienCtrl().daftarbyResepsionis(offset, limit, id);
			
			if (daftarklien.isEmpty()) {
				throw new NotFoundException("Daftar barang kosong");
			}
			
			JSONKlien b = new JSONKlien();
			for(Klien klien: daftarklien) {
				b = new JSONKlien(klien);
				response.add(b);
			}
			
			return response;
		}		
		
		@ApiMethod(name="daftarbyKlinikHewan", 
				httpMethod=HttpMethod.GET)
		public List<JSONKlien> daftarbyKlinik (@Nullable @Named("offset") Integer offset,
				@Nullable @Named("limit") Integer limit, 
				@Named("email_klinik") String email_klinik) throws Exception {
			List<JSONKlien> response = new ArrayList<JSONKlien>();
			
			// periksa nilai offset dan limit
			if (offset == null || offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			
			if (limit == null || limit < 0) {
				limit = KonfigurasiDasar.LIMIT;
			}
			 			
			List<Klien> daftarklien = 
					(List<Klien>) new KlienCtrl().daftarbyKlinik(offset, limit, email_klinik);
			
			if (daftarklien.isEmpty()) {
				throw new NotFoundException("Daftar barang kosong");
			}
			
			JSONKlien b = new JSONKlien();
			for(Klien klien: daftarklien) {
				b = new JSONKlien(klien);
				response.add(b);
			}
			
			return response;
		}
		
				
 		@ApiMethod(name="create",
				path="klien",
				httpMethod=HttpMethod.POST)
		public JSONKlien baru(JSONKlienCreate jsonKlienCreate) throws Exception {
			JSONKlien response = null;
			
			Resepsionis resepsionis = new ResepsionisCtrl().get(jsonKlienCreate.id);
			if (resepsionis==null){
				throw new NotFoundException ("ID Tidak Tersedia");
			}  
			
			Klien klien = new KlienCtrl().create(jsonKlienCreate.email_klinik, jsonKlienCreate.nama, jsonKlienCreate.alamat, jsonKlienCreate.email, 
					jsonKlienCreate.no_reg, jsonKlienCreate.telp, jsonKlienCreate.ktp, jsonKlienCreate.id);
			
			response = new JSONKlien(klien); 
			
			return response;
		}
		
//		GET http://localhost:8080/_ah/api/klien/v1/klien/
		@ApiMethod(name="get", 
				path="klien/{id}", 
				httpMethod=HttpMethod.GET)
		public JSONKlien cari(@Named("id") Long id) throws Exception {
			JSONKlien response;
			
			Klien klien = new KlienCtrl().cari(id);
			
			response = new JSONKlien(klien);
			
			return response;
		} 		  
 
		@ApiMethod(name="searchs",  //pencarian utk di drh (get ambulator)
				path="klien/search/{no_reg}",    
				httpMethod=HttpMethod.GET)
		public JSONKlien search(@Named("no_reg") Long no_reg) throws Exception {
			JSONKlien response;
			
			Klien klien = new KlienCtrl().cariNoreg(no_reg);
			
			if (klien==null){
				throw new NotFoundException("No Regitrasi Tidak Tersedia");
			} 
			response = new JSONKlien(klien);
			 
			return response;
		}
		
		@ApiMethod(name="search",  //pencarian di queary search (search by no_reg)
				httpMethod=HttpMethod.GET)  
		public List<JSONKlien> data( 
				@Nullable @Named("offset") Integer offset, 
				@Nullable @Named("limit") Integer limit, 
				@Named("idResepsionis") Long idResepsionis,
				@Named("no_reg") Long no_reg) throws NotFoundException {
			 
			List<JSONKlien> response = new ArrayList<JSONKlien>();
			 
			if (offset == null || offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			} 
			
			if (limit == null || limit < 0) {
				limit = KonfigurasiDasar.LIMIT;
			}
			
			Resepsionis resepsionis = new ResepsionisCtrl().get(idResepsionis);
			if (resepsionis==null){
				throw new NotFoundException("ID Tidak Tersedia");
			}
			
			List<Klien> daftarklien = new KlienCtrl().daftarKlien(offset, limit, idResepsionis, no_reg);
			
			if (daftarklien.isEmpty()) { 
				throw new NotFoundException("Data Kosong");
			}
			 
			JSONKlien b = new JSONKlien();
			for(Klien klien: daftarklien) {
				b = new JSONKlien(klien);
				response.add(b);
			}
			
			return response;
		}
 
		@ApiMethod(name="ubah", 
		httpMethod=HttpMethod.PUT)
public JSONKlien ubah(JSONKlienUbah jsonKlienUbah) throws Exception {
	JSONKlien response = null;
	
	Klien cariKlien = new KlienCtrl().cari(jsonKlienUbah.id);

	if (cariKlien==null){
		throw new NotFoundException("ID Tidak Tersedia");
	}
	Klien klien = new KlienCtrl().updateKlien(jsonKlienUbah.id, jsonKlienUbah.nama, 
			jsonKlienUbah.alamat, jsonKlienUbah.email,
			jsonKlienUbah.telp, jsonKlienUbah.ktp);
	
	response = new JSONKlien(klien);
	
	return response;
}
		
		@ApiMethod(name="cariKTP",  //pencarian di queary search (search by no_reg)
				httpMethod=HttpMethod.GET)  
		public List<JSONKlien> dataKlienKTP( 
				@Nullable @Named("offset") Integer offset, 
				@Nullable @Named("limit") Integer limit, 
				@Named("idResepsionis") Long idResepsionis,
				@Named("ktp") String ktp) throws NotFoundException {
			 
			List<JSONKlien> response = new ArrayList<JSONKlien>();
			 
			if (offset == null || offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			} 
			
			if (limit == null || limit < 0) {
				limit = KonfigurasiDasar.LIMIT;
			}
			
			Resepsionis resepsionis = new ResepsionisCtrl().get(idResepsionis);
			if (resepsionis==null){
				throw new NotFoundException("ID Tidak Tersedia");
			}
			
			List<Klien> daftarklien = new KlienCtrl().daftarKlienKTP(offset, limit, idResepsionis, ktp);
			
			if (daftarklien.isEmpty()) { 
				throw new NotFoundException("Data tidak ditemukan");
			}
			 
			JSONKlien b = new JSONKlien();
			for(Klien klien: daftarklien) {
				b = new JSONKlien(klien);
				response.add(b);
			}
			
			return response;
		}
}
