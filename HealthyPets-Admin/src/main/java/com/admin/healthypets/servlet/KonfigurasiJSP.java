package com.admin.healthypets.servlet;

public class KonfigurasiJSP { 
	
	public static final String JSP_PATH = "/WEB-INF/jsp";
	public static final String REGISTER_PATH = "/WEB-INF/login";
	
//	New Path
	public static final String JSP_ADMIN_PATH = "/WEB-INF/login-admin";
	public static final String JSP_HOME_PATH = "/WEB-INF/home";
	public static final String JSP_PEMILIK_PATH = "/WEB-INF/pemilik";
	public static final String JSP_KLINIK_PATH = "/WEB-INF/klinik";
	public static final String JSP_PEMILIK_KLINIK_PATH = "/WEB-INF/pemilik-klinik";
	public static final String JSP_RESEPSIONIS_PATH = "/WEB-INF/resepsionis";
	public static final String JSP_DRH_PATH = "/WEB-INF/drh";
 
	
	public static final String LOCK_PATH = JSP_HOME_PATH+"/lock-screen.jsp";

	
	public static final String HOME_PATH = JSP_HOME_PATH+"/home.jsp";
	public static final String TAMPIL_PEMILIK_PATH = JSP_PEMILIK_PATH+"/tampil-semua-pemilik.jsp";
	public static final String TAMBAH_PEMILIK_PATH = JSP_PEMILIK_PATH+"/tambah-pemilik.jsp"; 
	public static final String TAMPIL_KLINIK_PEMILIK_PATH = JSP_KLINIK_PATH+"/tampil-klinik.jsp"; 
	public static final String TAMPIL_KLINIK_PATH = JSP_PEMILIK_KLINIK_PATH+"/klinik-pemilik.jsp"; 
	public static final String TAMBAH_KLINIK_PATH = JSP_KLINIK_PATH+"/daftar-klinik.jsp"; 
	public static final String UBAH_KLINIK_PATH = JSP_KLINIK_PATH+"/ubah-klinik.jsp"; 
	public static final String UBAH_PEMILIK_PATH = JSP_PEMILIK_PATH+"/ubah-pemilik.jsp"; 

//	Resepsionis
	public static final String RESEPSIONIS_PATH = JSP_RESEPSIONIS_PATH+"/resepsionis.jsp";
	public static final String DAFTAR_RESEPSIONIS_PATH = JSP_RESEPSIONIS_PATH+"/daftar-resepsionis.jsp";
	public static final String PERBARUI_RESEPSIONIS_PATH = JSP_RESEPSIONIS_PATH+"/ubah-resepsionis.jsp";

//	Dokter Hewan
	public static final String DRH_PATH = JSP_DRH_PATH+"/drh.jsp";
	public static final String DAFTAR_DRH_PATH = JSP_DRH_PATH+"/daftar-drh.jsp";
	public static final String UBAH_DRH_PATH = JSP_DRH_PATH+"/ubah-drh.jsp";
	
	public static final String LOGIN_ADMIN_PATH = JSP_ADMIN_PATH+"/login.jsp";
	
	public static final String REGISTER_ADMIN_PATH = REGISTER_PATH+"/register.jsp";
//	public static final String LOGIN_ADMIN_PATH = REGISTER_PATH+"/login.jsp";

}
