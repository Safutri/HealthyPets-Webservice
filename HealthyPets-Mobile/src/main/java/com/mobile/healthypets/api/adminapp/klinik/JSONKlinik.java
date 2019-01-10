package com.mobile.healthypets.api.adminapp.klinik;

import com.mobile.healthypets.model.klinik.Klinik;

public class JSONKlinik { 
		
		public JSONKlinik(){}; 
		
		private Klinik klinik;
			
		public String getNama() {
			if(klinik == null) {
				return "";
		}
			return klinik.getNama();
		}
		 
		public String getEmail() {
			if(klinik == null) {
				return "";   
		}
			return klinik.getEmail();
		}
		 
		public String getAlamat() {
			if(klinik == null) {
				return "";
		}
			return klinik.getAlamat();
		}
		
		public String getIdentitas() {
			if(klinik == null) {
				return "";
		}
			return klinik.getIdentitas();
		}
		
		public String getTelp() {
			if(klinik == null) {
				return "";
		}
			return klinik.getTelp();
		}
			
		public Long getId() {
			if (klinik == null) {
				return 0L;	
			}
			return klinik.getId();
		}
			
		public JSONKlinik(Klinik klinik) {
			this.klinik = klinik;
		}
		
		public void setKlinik(Klinik klinik) {
			this.klinik = klinik;
		}
	}
