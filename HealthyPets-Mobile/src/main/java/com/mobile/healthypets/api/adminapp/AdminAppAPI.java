package com.mobile.healthypets.api.adminapp;

import java.util.ArrayList;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.mobile.healthypets.admin.Admin;
import com.mobile.healthypets.admin.AdminCtrl;
import com.mobile.healthypets.api.adminapp.admin.JSONAdmin;
import com.mobile.healthypets.api.adminapp.admin.JSONAdminCreate;
import com.mobile.healthypets.api.adminapp.dokterhewan.JSONDokterHewanCreate;
import com.mobile.healthypets.api.adminapp.dokterhewan.JSONDokterHewanUbah;
import com.mobile.healthypets.api.adminapp.klinik.JSONKlinik;
import com.mobile.healthypets.api.adminapp.klinik.JSONKlinikCreate;
import com.mobile.healthypets.api.adminapp.klinik.JSONKlinikUbah;
import com.mobile.healthypets.api.adminapp.pemilik.JSONPemilik;
import com.mobile.healthypets.api.adminapp.pemilik.JSONPemilikCreate;
import com.mobile.healthypets.api.adminapp.pemilik.JSONPemilikUbah;
import com.mobile.healthypets.api.adminapp.resepsionis.JSONResepsionis;
import com.mobile.healthypets.api.adminapp.resepsionis.JSONResepsionisCreate;
import com.mobile.healthypets.api.adminapp.resepsionis.JSONResepsionisUbah;
import com.mobile.healthypets.api.drh.JSONDokterHewan;
import com.mobile.healthypets.konfigurasi.KonfigurasiDasar;
import com.mobile.healthypets.konfigurasi.LoginAuthenticator;
import com.mobile.healthypets.model.drh.DokterHewan;
import com.mobile.healthypets.model.drh.DokterHewanCtrl;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.klinik.KlinikCtrl;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pemilik.PemilikCtrl;
import com.mobile.healthypets.model.resepsionis.Resepsionis;
import com.mobile.healthypets.model.resepsionis.ResepsionisCtrl;
 
@Api(name = "adminapp",

title = "Layanan AdminApp",
version="v1",
description="API untuk resource adminapp")

public class AdminAppAPI {
	// base path for API  
	private static final String BASE_PATH = "/adminapp"; 

//LIST Semua Pemilik Klinik
	@ApiMethod(name="tampilPemilik",		
			path=BASE_PATH+"/tampil",
			httpMethod=HttpMethod.GET)
	public List<JSONPemilik> list(
			@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit) throws NotFoundException {
		
		List<JSONPemilik> response = new ArrayList<JSONPemilik>();
		 
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		List<Pemilik> daftarPemilik = new PemilikCtrl().listAll(offset, limit);
		
		if (daftarPemilik.isEmpty()) {
			throw new NotFoundException("Data kosong");
		}
		
		JSONPemilik b = new JSONPemilik();
		for(Pemilik pemilik: daftarPemilik) {
			b = new JSONPemilik(pemilik);
			response.add(b);
		}		
		return response;
	} 
		
//CREATE Pemilik Klinik
	@ApiMethod(name="buatPemilik",
			path=BASE_PATH+"/buat",
			httpMethod=HttpMethod.POST)
	public JSONPemilik baru(JSONPemilikCreate jsonHewanCreate) throws Exception {
		JSONPemilik response = null; 
		Pemilik pemilik = new PemilikCtrl().create(jsonHewanCreate.nama, jsonHewanCreate.alamat, jsonHewanCreate.email, 
				jsonHewanCreate.identitas, jsonHewanCreate.telp);
		response = new JSONPemilik(pemilik);
		return response;
	}
	
//GET Pemilik Klinik
	@ApiMethod(name="ambilPemilik",
			path=BASE_PATH+"/ambil",
			httpMethod=HttpMethod.GET)
	public JSONPemilik GetPemilik(
			@Named("id") Long id) throws Exception {
		JSONPemilik response;
		
		Pemilik pemilik = new PemilikCtrl().get(id);
		
		if (pemilik==null){
			throw new NotFoundException ("ID Tidak Tersedia");
		}
		response = new JSONPemilik(pemilik);
		return response;
	}
	
//UPDATE Pemilik
			@ApiMethod(name="ubahPemilik", 
					path=BASE_PATH+"/update",
					httpMethod=HttpMethod.PUT)
			public JSONPemilik ubahPemilik(JSONPemilikUbah jsonPemilikUbah) throws Exception {
				JSONPemilik response = null;

				Pemilik cariPemilik = new PemilikCtrl().get(jsonPemilikUbah.id);

				if (cariPemilik==null){
					throw new NotFoundException("ID Pemilik Tidak Tersedia");
				}
				@SuppressWarnings("unused")
				Pemakai pemilik = new PemilikCtrl().updatePemilik(jsonPemilikUbah.id, jsonPemilikUbah.namaBaru, 
						jsonPemilikUbah.alamatBaru, jsonPemilikUbah.identitasBaru, jsonPemilikUbah.telpBaru);			
				response = new JSONPemilik(cariPemilik);			
				return response;
			}
	
//LIST Klinik Berdasarkan id Pemilik Klinik
	@ApiMethod(name="listKlinik",
			path=BASE_PATH+"/daftar",
			httpMethod=HttpMethod.GET)
	public List<JSONKlinik> daftar (@Nullable @Named("offset") Integer offset,
			@Nullable @Named("limit") Integer limit, 
			@Named("id") Long id) throws Exception {
		List<JSONKlinik> response = new ArrayList<JSONKlinik>();
		
		if (offset == null || offset < 0) {
			offset = KonfigurasiDasar.OFFSET;
		}
		
		if (limit == null || limit < 0) {
			limit = KonfigurasiDasar.LIMIT;
		}
		
		Pemilik pemilik = new PemilikCtrl().get(id);   //  => daftar berdasarkan id
		if (pemilik==null){
			throw new NotFoundException("Data Tidak Tersedia");
		}
				
		List<Klinik> daftarKlinik = 
				(List<Klinik>) new KlinikCtrl().listByOwner(offset, limit, id);
		
		if (daftarKlinik.isEmpty()) {
			throw new NotFoundException("Tidak ada hewan");
		}
		
		JSONKlinik b = new JSONKlinik();
		for(Klinik klinik : daftarKlinik) {
			b = new JSONKlinik(klinik);
			response.add(b);
		}		
		return response;
	}
	
//CREATE Klinik
		@ApiMethod(name="Klinikbaru",
				path=BASE_PATH+"/buatklinik",
				httpMethod=HttpMethod.POST)
		public JSONKlinik buatKlinik(JSONKlinikCreate jsonKlinikCreate) throws Exception {
			JSONKlinik response = null; 
			
			Pemilik pemilik = new PemilikCtrl().get(jsonKlinikCreate.id);
			if (pemilik==null){
				throw new NotFoundException ("ID tidak ada");
			} 
			Klinik klinik = new KlinikCtrl().create(jsonKlinikCreate.nama, jsonKlinikCreate.id, jsonKlinikCreate.alamat, 
					jsonKlinikCreate.email, jsonKlinikCreate.telp, jsonKlinikCreate.identitas);
			response = new JSONKlinik(klinik);
			return response;
		}
		
//GET Klinik
		@ApiMethod(name="ambilKlinik",
				path=BASE_PATH+"/klinik",
				httpMethod=HttpMethod.GET)
		public JSONKlinik ambilKlinik(
				@Named("id") Long id) throws Exception {
			JSONKlinik response;
			
			Klinik klinik = new KlinikCtrl().get(id);
			
			if (klinik==null){
				throw new NotFoundException ("ID Klinik Tidak Terdaftar");
			}
			response = new JSONKlinik(klinik);
			return response;
		}
		
//UPDATE Klinik
		@ApiMethod(name="ubahKlinik", 
				path=BASE_PATH+"/ubahklinik",
				httpMethod=HttpMethod.PUT)
		public JSONKlinik ubah(JSONKlinikUbah jsonKlienUbah) throws Exception {
			JSONKlinik response = null;

			Klinik cariKlinik = new KlinikCtrl().get(jsonKlienUbah.id);

			if (cariKlinik==null){
				throw new NotFoundException("ID Klinik Tidak Tersedia");
			}
			Klinik klinik = new KlinikCtrl().updateKlinik(jsonKlienUbah.id, jsonKlienUbah.namaBaru, 
					jsonKlienUbah.alamatBaru, jsonKlienUbah.praktikBaru,
					jsonKlienUbah.emailBaru, jsonKlienUbah.telpBaru);			
			response = new JSONKlinik(klinik);			
			return response;
		}
		
//LIST Semua Klinik
		@ApiMethod(name="listSemuaKlinik",		
				path=BASE_PATH+"/all/klinik",
				httpMethod=HttpMethod.GET)
		public List<JSONKlinik> listKlinik(
				@Nullable @Named("offset") Integer offset,
				@Nullable @Named("limit") Integer limit) throws NotFoundException {
			
			List<JSONKlinik> response = new ArrayList<JSONKlinik>();
			 
			if (offset == null || offset < 0) {
				offset = KonfigurasiDasar.OFFSET;
			}
			
			if (limit == null || limit < 0) {
				limit = KonfigurasiDasar.LIMIT;
			}
			
			List<Klinik> daftarKlinik = new KlinikCtrl().list(offset, limit);
			
			if (daftarKlinik.isEmpty()) {
				throw new NotFoundException("Data kosong");
			}
			
			JSONKlinik b = new JSONKlinik();
			for(Klinik klinik: daftarKlinik) {
				b = new JSONKlinik(klinik);
				response.add(b);
			}		
			return response;
		}
		
//CREATE Resepsionis 
		@ApiMethod(name="createResepsionis",
				path=BASE_PATH+"/resepsionis/buat",
				httpMethod=HttpMethod.POST)
		public JSONResepsionis buat(JSONResepsionisCreate jsonResepsionisCreate) throws Exception {
			JSONResepsionis response = null; 
			
			Klinik klinik = new KlinikCtrl().get(jsonResepsionisCreate.id);
			if (klinik==null){
				throw new NotFoundException ("ID tidak ada");
			}  
			Resepsionis resepsionis = new ResepsionisCtrl().create(jsonResepsionisCreate.nama, jsonResepsionisCreate.id, 
					jsonResepsionisCreate.alamat,jsonResepsionisCreate.email, jsonResepsionisCreate.telp, 
					jsonResepsionisCreate.email_klinik, jsonResepsionisCreate.nama_klinik);
			response = new JSONResepsionis(resepsionis);
			return response;
		}		
		

//UPDATE Resepsionis 
				@ApiMethod(name="ubahResepsionis", 
						path=BASE_PATH+"/resepsionis/ubah",
						httpMethod=HttpMethod.PUT)
				public JSONResepsionis ubahResepsionis(JSONResepsionisUbah jsonResepsionisUbah) throws Exception {
					JSONResepsionis response = null;

					Resepsionis cariResepsionis = new ResepsionisCtrl().get(jsonResepsionisUbah.id);

					if (cariResepsionis==null){
						throw new NotFoundException("ID Resepsionis Tidak Tersedia");
					}  
					Resepsionis resepsionis = new ResepsionisCtrl().updateResepsionis(jsonResepsionisUbah.id, 
							jsonResepsionisUbah.namaBaru, jsonResepsionisUbah.alamatBaru, jsonResepsionisUbah.telpBaru);
//							jsonResepsionisUbah.email_klinik_baru, jsonResepsionisUbah.nama_klinik_baru);			
					response = new JSONResepsionis(resepsionis);			
					return response;
				}
				
		
//LIST Resepsionis Berdasarkan id Klinik
				@ApiMethod(name="listResepsionis",
						path=BASE_PATH+"/resepsionis/list",
						httpMethod=HttpMethod.GET)
				public List<JSONResepsionis> listResepsionis(@Nullable @Named("offset") Integer offset,
						@Nullable @Named("limit") Integer limit, 
						@Named("id") Long id) throws Exception {
					List<JSONResepsionis> response = new ArrayList<JSONResepsionis>();
					
					if (offset == null || offset < 0) {
						offset = KonfigurasiDasar.OFFSET;
					}
					
					if (limit == null || limit < 0) {
						limit = KonfigurasiDasar.LIMIT;
					}
					
					Klinik klinik = new KlinikCtrl().get(id);   //  => daftar berdasarkan id
					if (klinik==null){
						throw new NotFoundException("Data Klinik Tidak Tersedia");
					}
							
					List<Resepsionis> daftarResepsionis = 
							(List<Resepsionis>) new ResepsionisCtrl().listByOwner(offset, limit, id);
					
					if (daftarResepsionis.isEmpty()) {
						throw new NotFoundException("Tidak ada hewan");
					}
					
					JSONResepsionis b = new JSONResepsionis();
					for(Resepsionis resepsionis : daftarResepsionis) {
						b = new JSONResepsionis(resepsionis);
						response.add(b);
					}		
					return response;
				}
				
//GET Resepsionis
				@ApiMethod(name="GetResepsionis",
						path=BASE_PATH+"/get/resepsionis",
						httpMethod=HttpMethod.GET)
				public JSONResepsionis Getresepsionis(
						@Named("id") Long id) throws Exception {
					JSONResepsionis response;
					
					Resepsionis resepsionis = new ResepsionisCtrl().get(id);
					
					if (resepsionis==null){
						throw new NotFoundException ("ID Resepsionis Tersedia");
					}
					response = new JSONResepsionis(resepsionis);
					return response;
				}
				
//CREATE Drh 
				@ApiMethod(name="makeDrh",
						path=BASE_PATH+"/create/DokterHewan",
						httpMethod=HttpMethod.POST)
				public JSONDokterHewan createDokterHewan(JSONDokterHewanCreate jsonDokterHewanCreate) throws Exception {
					JSONDokterHewan response = null; 
					
					Resepsionis resepsionis= new ResepsionisCtrl().get(jsonDokterHewanCreate.id);
					if (resepsionis==null){
						throw new NotFoundException ("ID Resepsionis tidak ada");
					}   					
					DokterHewan drh = new DokterHewanCtrl().create(jsonDokterHewanCreate.nama, jsonDokterHewanCreate.id, 
							jsonDokterHewanCreate.alamat,jsonDokterHewanCreate.email, jsonDokterHewanCreate.telp, 
							jsonDokterHewanCreate.no_praktik, jsonDokterHewanCreate.email_klinik, jsonDokterHewanCreate.nama_klinik);
					response = new JSONDokterHewan(drh);
					return response;
				}
				
//LIST Dokter Hewan Berdasarkan id Klinik
				@ApiMethod(name="listDrh",
						path=BASE_PATH+"/list/drh",
						httpMethod=HttpMethod.GET)
				public List<JSONDokterHewan> listDokterHewan(@Nullable @Named("offset") Integer offset,
						@Nullable @Named("limit") Integer limit, 
						@Named("id") Long id) throws Exception {
					List<JSONDokterHewan> response = new ArrayList<JSONDokterHewan>();
					
					if (offset == null || offset < 0) {
						offset = KonfigurasiDasar.OFFSET;
					}
					
					if (limit == null || limit < 0) {
						limit = KonfigurasiDasar.LIMIT;
					}
					
					Resepsionis resepsionis = new ResepsionisCtrl().get(id);
					if (resepsionis==null){
						throw new NotFoundException("Data Resepsionis Tidak Tersedia");
					}
							
					List<DokterHewan> daftarDokterHewan = 
							(List<DokterHewan>) new DokterHewanCtrl().listByOwner(offset, limit, id);
					
					if (daftarDokterHewan.isEmpty()) {
						throw new NotFoundException("Tidak ada hewan");
					}
					
					JSONDokterHewan b = new JSONDokterHewan();
					for(DokterHewan drh : daftarDokterHewan) {
						b = new JSONDokterHewan(drh);
						response.add(b);
					}		
					return response;
				}				
				
//GET DokterHewan
				@ApiMethod(name="dataDrh",
						path=BASE_PATH+"/ambil/drh",
						httpMethod=HttpMethod.GET)
				public JSONDokterHewan Ambildrh(
						@Named("id") Long id) throws Exception {
					JSONDokterHewan response;
					
					DokterHewan drh = new DokterHewanCtrl().get(id);
					
					if (drh==null){
						throw new NotFoundException ("ID DokterHewan Tersedia");
					}
					response = new JSONDokterHewan(drh);
					return response;
				}
				
//UPDATE DokterHewan 
				@ApiMethod(name="ubahDrh", 
						path=BASE_PATH+"/drh/update",
						httpMethod=HttpMethod.PUT)
				public JSONDokterHewan ubahDokterHewan(JSONDokterHewanUbah jsonDokterHewanUbah) throws Exception {
					JSONDokterHewan response = null;

					DokterHewan cariDokterHewan = new DokterHewanCtrl().get(jsonDokterHewanUbah.id);

					if (cariDokterHewan==null){
						throw new NotFoundException("ID DokterHewan Tidak Tersedia");
					}  
					DokterHewan drh = new DokterHewanCtrl().updateDokterHewan(jsonDokterHewanUbah.id, 
							jsonDokterHewanUbah.namaBaru, jsonDokterHewanUbah.alamatBaru, jsonDokterHewanUbah.telpBaru, 
							jsonDokterHewanUbah.no_praktikBaru, jsonDokterHewanUbah.email_klinik_baru, 
							jsonDokterHewanUbah.nama_klinik_baru);			
					response = new JSONDokterHewan(drh);			
					return response;
				}
				
				
//CREATE Admin
				@ApiMethod(name="createAdmin",
						path=BASE_PATH+"/register/admin",
						httpMethod=HttpMethod.POST)
				public JSONAdmin createAdmin(JSONAdminCreate jsonAdminCreate) throws Exception {
					JSONAdmin response = null; 
					 					
					Admin admin = new AdminCtrl().create(jsonAdminCreate.nama, 
							jsonAdminCreate.username,jsonAdminCreate.email,	jsonAdminCreate.password);
					response = new JSONAdmin(admin);
					return response;
				}
				
//LOGIN Admin
				@ApiMethod(name="loginAdmin", 
						path=BASE_PATH+"/login/admin",
				httpMethod=HttpMethod.GET)
		public JSONAdmin login(@Named("email") String email, @Named("password") String password) throws Exception {
			JSONAdmin response=null;
			
			boolean login = new LoginAuthenticator().berhasilLogin(email, password);
			
			if (login==true){
				Admin admin = new AdminCtrl().getByEmail(email);		
			response = new JSONAdmin(admin);
			}
			else {
				//do nothing
				throw new UnauthorizedException("Login Gagal");
			}
			return response;
		}
				
				
				//CREATE Admin
				@ApiMethod(name="Adminbaru",
						path=BASE_PATH+"/admin",
						httpMethod=HttpMethod.POST)
				public JSONAdmin buatAdmin(JSONAdminCreate jsonAdminCreate) throws Exception {
					JSONAdmin response = null; 
					
					Admin admin = new AdminCtrl().create(jsonAdminCreate.nama, jsonAdminCreate.username, jsonAdminCreate.email, 
							jsonAdminCreate.password);
					response = new JSONAdmin(admin);
					return response;
				}
				
		//GET Admin
				@ApiMethod(name="ambilAdmin",
						path=BASE_PATH+"/adminHealthyPets",
						httpMethod=HttpMethod.GET)
				public JSONAdmin ambilAdmin(
						@Named("email") String email) throws Exception {
					JSONAdmin response;
					
					Admin admin = new AdminCtrl().getByEmail(email);
					
					if (email==null){
						throw new NotFoundException ("ID Klinik Tidak Terdaftar");
					}
					response = new JSONAdmin(admin);
					return response;
				}
}



