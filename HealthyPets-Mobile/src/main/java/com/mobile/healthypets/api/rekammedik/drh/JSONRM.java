package com.mobile.healthypets.api.rekammedik.drh;

import com.mobile.healthypets.model.rekammedik.RekamMedikDrh;

public class JSONRM {
	private RekamMedikDrh rm;

		public JSONRM(){};
		
		public String getTgl() {
			if(rm == null) {
		}
			return rm.getTgl();
		}
		
		public String getNama_drh() {
			if(rm == null) {
		}
			return rm.getNama_drh();
		}
		
		public String getStatus_awal() {
			if(rm == null) {
				return "";
			}
			return rm.getStatus_awal();
		}
		
		public String getTerapi() {
			if(rm == null) {
				return "";
			}
			return rm.getTerapi();
		}
		
		public String getDiagnosa() {
			if(rm == null) {
				return "";
			}
			return rm.getDiagnosa();
		}
		
		public String getObat() {
			if(rm == null) {
				return "";
			}
			return rm.getObat();
		}
		
		public String getKode_hewan() {
			if(rm == null) {
				return "";
			}
			return rm.getKode_hewan();
		}
		public String getLain() {
			if(rm == null) {
				return "";
			}
			return rm.getLain();
		}
		
		public Long getNo_reg() {
			if (rm == null) {
				return 0L;	
			}
			return (Long) rm.getNo_reg();
		}
		
		public Long getId() {
			if (rm == null) {
				return 0L;	
			}
			return (Long) rm.getId();
		}
		
		public JSONRM(RekamMedikDrh rm) {
			this.rm = rm;
		}
		
		public void setRekamMedikDrh(RekamMedikDrh rm) {
			this.rm = rm;
		}
}
