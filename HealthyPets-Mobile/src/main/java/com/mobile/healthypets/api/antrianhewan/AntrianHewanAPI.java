package com.mobile.healthypets.api.antrianhewan;

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
import com.mobile.healthypets.model.antrianhewan.AntrianHewan;
import com.mobile.healthypets.model.antrianhewan.AntrianHewanCtrl;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.hewan.HewanCtrl; 
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl;   
 
@Api(name = "antrian",
title = "Layanan Antrian Hewan",
version="v1",
description="API untuk resource antrian hewan")
public class AntrianHewanAPI {
	
	//List Semua antrian pada semua klinik
	@ApiMethod(name="list",		
			path="antrian",
			httpMethod=HttpMethod.GET)
	public List<JSONAntrian> list(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit) throws NotFoundException {
		
		List<JSONAntrian> response = new ArrayList<JSONAntrian>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List<AntrianHewan> daftarHewan = new AntrianHewanCtrl().daftar(offset, limit);
		
		if (daftarHewan.isEmpty()) {
			throw new NotFoundException("Data kosong");
		}
		
		JSONAntrian b = new JSONAntrian();
		for(AntrianHewan Antrianhewan: daftarHewan) {
			b = new JSONAntrian(Antrianhewan);
			response.add(b);
		}
		
		return response;
	}

	//list antrian per klinik sesuai dengan resepsionis yang login
	@ApiMethod(name="listAntrianbyResepsionis", 
			httpMethod=HttpMethod.GET)
	public List<JSONAntrian> daftar (@Nullable @Named("offset") Integer offset,@Nullable @Named("limit") Integer limit, 
			@Named("id") Long id) throws Exception {
		List<JSONAntrian> response = new ArrayList<JSONAntrian>();
		
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
		
		List<AntrianHewan> daftarAntri = 
				(List<AntrianHewan>) new AntrianHewanCtrl().daftarbyResepsionis(offset, limit, id);
		
		if (daftarAntri.isEmpty()) {
			throw new NotFoundException("Daftar barang kosong");
		}
		
		JSONAntrian b = new JSONAntrian();
		for(AntrianHewan antri: daftarAntri) {
			b = new JSONAntrian(antri);
			response.add(b);
		}
		
		return response;
	}
	
	
	@ApiMethod(name="create",
			httpMethod=HttpMethod.POST)
	public JSONAntrian Antrianbaru(JSONAntrianCreate jsonAntrianCreate) throws Exception {
		JSONAntrian response = null;
		
		Hewan hewan = new HewanCtrl().get(jsonAntrianCreate.id);
		

		if (hewan==null){
			throw new NotFoundException ("ID Hewan Tidak Tersedia");
		} 
		
		Resepsionis resepsionis = new ResepsionisCtrl().get(jsonAntrianCreate.idResepsionis);

		
		if (resepsionis==null){
			throw new NotFoundException ("ID Resepsionis Tidak Tersedia");
		}  
		
		AntrianHewan Antrianhewan = new AntrianHewanCtrl().create(jsonAntrianCreate.nama, jsonAntrianCreate.id, jsonAntrianCreate.idResepsionis,
				jsonAntrianCreate.jenis, jsonAntrianCreate.ras, jsonAntrianCreate.umur, jsonAntrianCreate.gender, jsonAntrianCreate.ttl, 
				jsonAntrianCreate.warna, jsonAntrianCreate.no_reg, jsonAntrianCreate.ktp, jsonAntrianCreate.kode_hewan);
		response = new JSONAntrian(Antrianhewan);
		return response;
	}
	
	@ApiMethod(name="delete", 
			path="antrian/{id}", 
			httpMethod=HttpMethod.DELETE)
	public JSONPesan hapus(@Named("id") Long id) throws Exception {
		JSONPesan response = new JSONPesan("");
		
		new AntrianHewanCtrl().deleteAntrian(id);
		
		// pastikan sudah dihapus
		AntrianHewan Antrianhewan = new AntrianHewanCtrl().get(id);
		
		if(Antrianhewan == null) {
			response.setMessage("Berhasil dihapus");
		} else {
			response.setMessage("Gagal dihapus");
		}
		
		return response;
	}
	
	@ApiMethod(name="get", 
			httpMethod=HttpMethod.GET)
	public JSONAntrian cari(
			@Named("id") Long id) throws Exception {
		JSONAntrian response;
		
		AntrianHewan Antrianhewan = new AntrianHewanCtrl().get(id);
		
		if (Antrianhewan==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		}
		response = new JSONAntrian(Antrianhewan);
		return response;
	}
	
	@ApiMethod(name="cariKode",  //pencarian di queary search (search by no_reg)
			path="carik/{kode_hewan}/{id}",
			httpMethod=HttpMethod.GET)  
	public List<JSONAntrian> getHewanAntri( 
			@Nullable @Named("offset") Integer offset, 
			@Nullable @Named("limit") Integer limit, 
			@Named("id") Long id,
			@Named("kode_hewan") String kode_hewan) throws NotFoundException {
		 
		List<JSONAntrian> response = new ArrayList<JSONAntrian>();
		 
		Hewan hewan = new HewanCtrl().get(id);
		if (hewan==null){
			throw new NotFoundException("ID Tidak Tersedia");
		}
		
		List<AntrianHewan> daftarAntri = new AntrianHewanCtrl().getAntrianHewan(offset, limit, id, kode_hewan);
		
		if (daftarAntri.isEmpty()) {
			throw new NotFoundException("Daftar barang kosong");
		}
		
		JSONAntrian b = new JSONAntrian();
		for(AntrianHewan antri: daftarAntri) {
			b = new JSONAntrian(antri);
			response.add(b);
		}
		
		return response;
	}
 
	
//	
//	@ApiMethod(name="deletesRe", 
//			path="queues/{kode_hewan}", 
//			httpMethod=HttpMethod.DELETE)
//	public JSONPesan hapusAntrian(@Named("kode_hewan") String kode_hewan) throws Exception {
//		JSONPesan response = new JSONPesan("");
//		
//		new AntrianHewanCtrl().deleteAntrianRes(kode_hewan);
//		
//		// pastikan sudah dihapus
//		AntrianHewan antri = new AntrianHewanCtrl().gets(kode_hewan);
//		
//		if(antri == null) {
//			response.setMessage("Berhasil dihapus");
//		} else {
//			response.setMessage("Gagal dihapus");
//		}
//		
//		return response;
//	 }
	
	
	 
	
}

