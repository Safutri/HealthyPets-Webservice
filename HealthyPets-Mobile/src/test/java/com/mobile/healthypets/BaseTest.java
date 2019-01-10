package com.mobile.healthypets;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import static com.googlecode.objectify.ObjectifyService.ofy;
//import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
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

public class BaseTest {
	 private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(
		          // Set no eventual consistency, that way queries return all results.
		          // https://cloud.google
		          // .com/appengine/docs/java/tools/localunittesting
		          // #Java_Writing_High_Replication_Datastore_tests
		          new LocalDatastoreServiceTestConfig()
		.setDefaultHighRepJobPolicyUnappliedJobPercentage(0));
	 
	 
	 private Closeable closeable;
//	  private DatastoreService ds;

	  @Before
	  public void setUp() throws Exception {

	    helper.setUp();
	    DatastoreServiceFactory.getDatastoreService();
	    
	    ObjectifyService.register(Pemakai.class);
	    ObjectifyService.register(Pegawai.class);
	    ObjectifyService.register(Pemilik.class);
	    ObjectifyService.register(Klinik.class);
		ObjectifyService.register(Resepsionis.class);
	    ObjectifyService.register(DokterHewan.class); 
		ObjectifyService.register(Admin.class);
		ObjectifyService.register(User.class);
		ObjectifyService.register(Klien.class);
		ObjectifyService.register(Hewan.class);
		ObjectifyService.register(RekamMedik.class);
		ObjectifyService.register(RekamMedikDrh.class);
		ObjectifyService.register(Ambulator.class);
		ObjectifyService.register(AntrianHewan.class);
		ObjectifyService.register(HasilLab.class);
		ObjectifyService.register(PilihDrh.class);
		ObjectifyService.register(AntrianKlien.class);
		
	    closeable = ObjectifyService.begin();

	    //cleanDatastore(ds, "default");
	  }

	  @After
	  public void tearDown() {
	    //cleanDatastore(ds, "default");
//		  hapusSemua();
	    helper.tearDown();
	    closeable.close();
	}
	  
	  public void hapusSemuaKlinik() {
		  List<Klinik> klinik = ofy().load().type(Klinik.class).list();
		  for(Klinik satuKlinik : klinik)
			  ofy().delete().key(satuKlinik.getKey()).now();
		  	  
	  }
	  
	  public void hapusSemua() {
		  List<Resepsionis> resepsionis = ofy().load().type(Resepsionis.class).list();
		  for(Resepsionis satuResepsionis : resepsionis)
			  ofy().delete().key(satuResepsionis.getKey()).now();
		  	  
	  }
	   
}
