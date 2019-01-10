package com.mobile.healthypets.api.pilihdrh;
 
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.mobile.healthypets.api.antrianklien.JSONPesan; 
import com.mobile.healthypets.model.pilihdrh.PilihDrh;
import com.mobile.healthypets.model.pilihdrh.PilihDrhCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl;  

@Api(name = "pilihdrh",
title = "Layanan Pilih Drh",
version="v1",
description="API untuk resource Pilih drh")

public class PilihDrhAPI {
	
	
	@ApiMethod(name="create",
			path="pilihDrh",
			httpMethod=HttpMethod.POST)
	public JSONPilihDrh baru(JSONPilihDrhCreate jsonPilihDrhCreate) throws Exception {
		JSONPilihDrh response = null;
		
		Resepsionis resepsionis = new ResepsionisCtrl().get(jsonPilihDrhCreate.idResepsionis);
		if (resepsionis==null){
			throw new NotFoundException ("ID Resepsionis Tidak Tersedia");
		} 
		PilihDrh pilih = new PilihDrhCtrl().create(jsonPilihDrhCreate.drh_jaga, jsonPilihDrhCreate.idResepsionis);
		
		response = new JSONPilihDrh(pilih);
		
		return response;
	}
	
	@ApiMethod(name="get",  
			httpMethod=HttpMethod.GET)
	public JSONPilihDrh get(
			@Named("id") Long id) throws Exception {
		JSONPilihDrh response;
		
		PilihDrh pilih = new PilihDrhCtrl().get(id);
		
		if (pilih==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		}
		response = new JSONPilihDrh(pilih);
		return response;
	}
	
	@ApiMethod(name="delete", 
			path="pilihDrh/{id}", 
			httpMethod=HttpMethod.DELETE)
	public JSONPesan hapus(@Named("id") Long id) throws Exception {
		JSONPesan response = new JSONPesan("");
		
		new PilihDrhCtrl().DeletePilihDrh(id);
		
		// pastikan sudah dihapus
		PilihDrh drh = new PilihDrhCtrl().get(id);
		
		if(drh == null) {
			response.setMessage("Berhasil dihapus");
		} else {
			response.setMessage("Gagal dihapus");
		}
		
		return response;
	} 
	
	 
}
