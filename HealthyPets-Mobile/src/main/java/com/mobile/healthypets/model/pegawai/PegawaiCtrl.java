package com.mobile.healthypets.model.pegawai;

import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;

public class PegawaiCtrl {
	public Pegawai cari(Long id) throws Exception{ 
				Key<Pegawai> key = Key.create(Pegawai.class, id);
				Pegawai pegawai = null; 
				try {
					pegawai = ofy().load().key(key).safe();
				} catch (NotFoundException e) { 
				}
				return pegawai;
	}
	
	public Pegawai ubah(Long id, String email){
		Pegawai pegawai = null;
		
		Result<Pegawai> result = ofy().load().key(Key.create(Pegawai.class, id));
		pegawai = result.now();
		
		
		pegawai.setEmail(email); 
		
		ofy().save().entities(pegawai).now();
		
		result = ofy().load().key(Key.create(Pegawai.class, id));
		pegawai = result.now();
		
		return pegawai;
	}
	
}

