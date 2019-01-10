package com.mobile.healthypets.api.drh;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.model.drh.DokterHewan;
import com.mobile.healthypets.model.drh.DokterHewanCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 

@Api(name = "drh",
title = "Layanan Dokter Hewan",
version="v1",
description="API untuk resource Dokter Hewan")

public class DokterHewanAPI {

	@ApiMethod(name="cari", 
			httpMethod=HttpMethod.GET)
	public JSONDokterHewan cari(@Named("id") Long id) throws Exception {
		JSONDokterHewan response;
		
		DokterHewan drh = new DokterHewanCtrl().get(id);
		if (drh==null){
			throw new NotFoundException("ID Tidak Tersedia");
		}
		response = new JSONDokterHewan(drh);
		return response;
	}
	
	
//	@ApiMethod(name="login", 
//			httpMethod=HttpMethod.GET)
//	public JSONDokterHewan login(@Named("email") String email, @Named("password") String password) throws Exception {
//		JSONDokterHewan  response=null;
//		
//		boolean login = new LoginAuthenticatorDrh().berhasilLogin(email, password);
//		
//		if (login==true){
//			DokterHewan  drh = new DokterHewanCtrl().cariEmail(email);		
//		response = new JSONDokterHewan(drh);
//		}
//		else {
//			//do nothing
//			throw new UnauthorizedException("Login Gagal");
//		}
//		return response;
//	}
	
	@ApiMethod(name="logins",  //pencarian utk di drh (get ambulator)
			path="logins/{email}",    
			httpMethod=HttpMethod.GET)
	public JSONDokterHewan search(@Named("email") String email) throws Exception {
		JSONDokterHewan response;
		
		DokterHewan drh = new DokterHewanCtrl().cariEmail(email);
		
		if (drh==null){
			throw new NotFoundException("No Regitrasi Tidak Tersedia");
		} 
		response = new JSONDokterHewan(drh);
		 
		return response;
	}
	
//	@ApiMethod(name="ubah", 
//			httpMethod=HttpMethod.PUT)
//	public JSONDokterHewan ubah(JSONDokterHewanUbah jsonDokterHewanUbah) throws Exception {
//		JSONDokterHewan response = null;
//		
//		DokterHewan cariDokterHewan = new DokterHewanCtrl().get(jsonDokterHewanUbah.id);
//	
//		if (cariDokterHewan==null){
//			throw new NotFoundException("ID Tidak Tersedia");
//		}
//		DokterHewan drh = new DokterHewanCtrl().updateDokterHewan(jsonDokterHewanUbah.id, jsonDokterHewanUbah.nama, jsonDokterHewanUbah.alamat, 
//				jsonDokterHewanUbah.telp, jsonDokterHewanUbah.no_praktik);
//		
//		response = new JSONDokterHewan(drh);
//		
//		return response;
//	}

//	@ApiMethod(name="ubahPassword", 
//			httpMethod=HttpMethod.PUT)
//	public JSONUbahPassword ubahPassword(JSONUbahPassword jsonUbahPassword) throws Exception {
//		JSONUbahPassword response = null;
//		
//		Pegawai caridrh = new PegawaiCtrl().cari(jsonUbahPassword.id);
//		
//		if (caridrh==null){
//			throw new NotFoundException("ID Tidak Tersedia");
//		}
//		new PegawaiCtrl().ubah(jsonUbahPassword.id, jsonUbahPassword.email, jsonUbahPassword.password);
//		
//		response = new JSONUbahPassword();
//		
//		return response;
//	}	
	
	
	
	@ApiMethod(name="drhbyResepsionis", 
			httpMethod=HttpMethod.GET)
	public List<JSONDokterHewan> drhbyResepsionis (@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit, 
			@Named("id") Long id) throws Exception {
		List<JSONDokterHewan> response = new ArrayList<JSONDokterHewan>();
		
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
		
		List<DokterHewan> daftardrh = 
				(List<DokterHewan>) new DokterHewanCtrl().drhbyResepsionis(offset, limit, id);
		
		if (daftardrh.isEmpty()) {
			throw new NotFoundException("Dokter Hewan kosong");
		}
		
		JSONDokterHewan b = new JSONDokterHewan();
		for(DokterHewan drh : daftardrh) {
			b = new JSONDokterHewan(drh);
			response.add(b);
		}
		
		return response;
	}
	 
}
