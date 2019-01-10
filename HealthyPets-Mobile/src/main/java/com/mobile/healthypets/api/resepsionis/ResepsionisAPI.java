package com.mobile.healthypets.api.resepsionis;
 
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl; 

@Api(name = "resepsionis",
title = "Layanan Resepsionis",
version="v1",
description="API untuk resource Resepsionis")

public class ResepsionisAPI {
	
	@ApiMethod(name="cari", 
			httpMethod=HttpMethod.GET)
	public JSONResepsionis cari(@Named("id") Long id) throws Exception {
		JSONResepsionis response;
		
		Resepsionis resepsionis = new ResepsionisCtrl().get(id);
		if (resepsionis==null){
			throw new NotFoundException("ID Tidak Tersedia");
		}
		response = new JSONResepsionis(resepsionis);
		return response;
	} 
	
	@ApiMethod(name="logins",  //pencarian utk di drh (get ambulator)
			path="login/{email}",    
			httpMethod=HttpMethod.GET)
	public JSONResepsionis search(@Named("email") String email) throws Exception {
		JSONResepsionis response;
		
		Resepsionis resepsionis = new ResepsionisCtrl().cariEmail(email);
		
		if (resepsionis==null){
			throw new NotFoundException("No Regitrasi Tidak Tersedia");
		} 
		response = new JSONResepsionis(resepsionis);
		 
		return response;
	}
}
