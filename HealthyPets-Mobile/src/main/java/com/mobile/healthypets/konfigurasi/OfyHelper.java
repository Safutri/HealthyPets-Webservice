package com.mobile.healthypets.konfigurasi;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.mobile.healthypets.admin.Admin;
import com.mobile.healthypets.model.ambulator.Ambulator;
import com.mobile.healthypets.model.antriandrh.AntrianKlien;
import com.mobile.healthypets.model.antrianhewan.AntrianHewan;
import com.mobile.healthypets.model.drh.DokterHewan;
import com.mobile.healthypets.model.hasillab.HasilLab;
import com.mobile.healthypets.model.hewan.Hewan;
import com.mobile.healthypets.model.klien.Klien;
import com.mobile.healthypets.model.klinik.Klinik;
import com.mobile.healthypets.model.pegawai.Pegawai;
import com.mobile.healthypets.model.pegawai.Pemakai;
import com.mobile.healthypets.model.pegawai.User;
import com.mobile.healthypets.model.pemilik.Pemilik;
import com.mobile.healthypets.model.pilihdrh.PilihDrh;
import com.mobile.healthypets.model.rekammedik.RekamMedik;
import com.mobile.healthypets.model.rekammedik.RekamMedikDrh;
import com.mobile.healthypets.model.resepsionis.Resepsionis; 

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 **/
public class OfyHelper implements ServletContextListener {
	
  public void contextInitialized(ServletContextEvent event) {
	  
    // This will be invoked as part of a warmup request, 
	// or the first user request if no warmup request.
 
	ObjectifyService.register(Klinik.class);    
	ObjectifyService.register(Admin.class);
	ObjectifyService.register(Pemakai.class);
	ObjectifyService.register(User.class);
	ObjectifyService.register(Pemilik.class);
	ObjectifyService.register(Pegawai.class);
	ObjectifyService.register(Resepsionis.class);
	ObjectifyService.register(DokterHewan.class); 
	ObjectifyService.register(Klien.class);
	ObjectifyService.register(Hewan.class);
	ObjectifyService.register(RekamMedik.class);
	ObjectifyService.register(RekamMedikDrh.class);
	ObjectifyService.register(Ambulator.class);
	ObjectifyService.register(AntrianHewan.class);
	ObjectifyService.register(HasilLab.class);
	ObjectifyService.register(PilihDrh.class);
	ObjectifyService.register(AntrianKlien.class);

  }
  
  public void contextDestroyed(ServletContextEvent event) {
    // App Engine does not currently invoke this method.
  }
}
