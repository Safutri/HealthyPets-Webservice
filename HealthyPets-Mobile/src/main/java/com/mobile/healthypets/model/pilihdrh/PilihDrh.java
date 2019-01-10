package com.mobile.healthypets.model.pilihdrh;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.mobile.healthypets.model.resepsionis.Resepsionis; 

@Entity
public class PilihDrh {

	@Id private Long id;
	public Long drh_jaga; 
	 
	
	@Load
	@Index
	private Ref<Resepsionis> resepsionis;
	
	

	public Ref<Resepsionis> getResepsionis() {
		return resepsionis;
	}

	public Long getId() {
		return id;
	}

	public Long getDrh_jaga() {
		return drh_jaga;
	} 
	
	@SuppressWarnings("unused")
	private PilihDrh(){}
	
	public PilihDrh(Long drh_jaga, Ref<Resepsionis> resepsionis){
		this.drh_jaga = drh_jaga;  
		this.resepsionis = resepsionis;
	}
}
