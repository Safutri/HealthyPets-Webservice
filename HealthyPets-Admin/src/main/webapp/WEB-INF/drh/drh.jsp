<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>Admin - HealthyPets</title>

    <!-- Bootstrap core CSS -->
    <link href="Theme/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="Theme/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="Theme/assets/js/bootstrap-datepicker/css/datepicker.css" />
    <link rel="stylesheet" type="text/css" href="Theme/assets/js/bootstrap-daterangepicker/daterangepicker.css" />
        
    <!-- Custom styles for this template -->
    <link href="Theme/assets/css/style.css" rel="stylesheet">
    <link href="Theme/assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
              </div>
            <!--logo start-->
            <a href="index.html" class="logo"><b>HealthyPets</b></a>
            <!--logo end-->
            <div class="nav notify-row" id="top_menu">
                <!--  notification start --> 
                <!--  notification end -->
            </div>
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="/">Logout</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
 <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
               <li class="centered">
              	  <p><img src="Theme/assets/img/logo.jpg" class="img-circle" width="60"></p>
              	  <h5>HealthyPets</h5>
               </li>
                  <li class="mt">
                      <a class="active" href="/home">
                          <i class="fa fa-dashboard"></i>
                          <span>Home</span>
                      </a>
                  </li> 
                 
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-tasks"></i>
                          <span>Layanan</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="/pemilik">Tambah Klinik Hewan</a></li>
                      </ul>
                      
                       <ul class="sub">
                          <li><a  href="/all-pemilik">Data Pemilik Klinik</a></li>
                      </ul>
                       <ul class="sub">
                          <li><a  href="/lihat-klinik">Data Klinik Terdaftar</a></li>
                      </ul>
                      
                  </li>
                  
                  
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-book"></i>
                          <span>Extra Pages</span>
                      </a>
                      <ul class="sub"> 
                          <li><a  href="/lock">Lock Screen</a></li>
                      </ul>
                  </li>
                  
                 </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
          	<h3>Resepsionis ${resepsionis.nama_klinik}</h3> 
          <!-- INPUT MESSAGES -->
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                           <form class="form-horizontal tasi-form">
                              <div class="form-group has-success">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Nama Klinik Hewan</strong></label>
                                  <div class="col-lg-10">
                                     <label>${resepsionis.nama_klinik}</label> 
                                  </div>
                              </div> 
                              <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Email Klinik Hewan</strong></label>
                                  <div class="col-lg-10">
                                       <label>${resepsionis.email_klinik}</label>
                                  </div>
                              </div>
                              <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Nama</strong></label>
                                  <div class="col-lg-10">
                                      <label>${resepsionis.nama}</label>   
                                  </div>
                              </div>
                              <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Email</strong></label>
                                  <div class="col-lg-10">
                                      <label>${resepsionis.email}</label>   
                                  </div>
                              </div>
                              <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Alamat</strong></label>
                                  <div class="col-lg-10">
                                      <label>${resepsionis.alamat}</label> 
                                  </div>
                              </div> 
                              
                               <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Telp</strong></label>
                                  <div class="col-lg-10">
                                       <label>${resepsionis.telp}</label> 
                                  </div>
                                  
                                    <ul class="nav pull-left top-menu"> 
			                                  	 <li><a class="logout" href="/daftar-drh?id=${resepsionis.id}">Tambah Dokter Hewan</a></li>			                                       
								               </ul> 
								               
                              </div> 
                             
                           </form>
                           </div>
          			</div><!-- /form-panel --> 
          		</div>     		 
		</section> 
      </section><!-- /MAIN CONTENT -->
      
      
      
          
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
          	<h3>Dokter Hewan ${resepsionis.nama_klinik} </h3> 
          <!-- INPUT MESSAGES -->
          <c:forEach var="drh" items="${drh}">
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">          			
                           <form class="form-horizontal tasi-form">
                              
                              <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Nama</strong></label>
                                  <div class="col-lg-10">
                                      <label>${drh.nama}</label>   
                                  </div>
                              </div>
                              <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Email</strong></label>
                                  <div class="col-lg-10">
                                      <label>${drh.email}</label>   
                                  </div>
                              </div>
                              <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Alamat</strong></label>
                                  <div class="col-lg-10">
                                      <label>${drh.alamat}</label> 
                                  </div>
                              </div> 
                              
                               <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>Telp</strong></label>
                                  <div class="col-lg-10">
                                       <label>${drh.telp}</label> 
                                  </div>
                                  </div>
                                  
                               <div class="form-group has-warning">
                                  <label class="col-sm-2 control-label col-lg-2" for="inputSuccess"><strong>No. Praktik</strong></label>
                                  <div class="col-lg-10">
                                       <label>${drh.no_praktik}</label> 
                                  </div>
                                  
                                  <ul class="nav pull-left top-menu">                                  
 					                     <li><a class="logout" href="/perbarui-drh?id=${drh.id}">Perbarui Dokter Hewan</a></li>
					               </ul>
                              	</div> 
                           </form>                         
          			</div><!-- /form-panel --> 
          		</div>
          	</div>	
          	  </c:forEach>       		 
		</section> 
      </section><!-- /MAIN CONTENT -->
    
 
      

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2017 - HealthyPets 
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="Theme/assets/js/jquery.js"></script>
    <script src="Theme/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="Theme/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="Theme/assets/js/jquery.scrollTo.min.js"></script>
    <script src="Theme/assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    <!--common script for all pages-->
    <script src="Theme/assets/js/common-scripts.js"></script>

    <!--script for this page-->
    <script src="Theme/assets/js/jquery-ui-1.9.2.custom.min.js"></script>

	<!--custom switch-->
	<script src="Theme/assets/js/bootstrap-switch.js"></script>
	
	<!--custom tagsinput-->
	<script src="Theme/assets/js/jquery.tagsinput.js"></script>
	
	<!--custom checkbox & radio-->
	
	<script type="text/javascript" src="Theme/assets/js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="Theme/assets/js/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript" src="Theme/assets/js/bootstrap-daterangepicker/daterangepicker.js"></script>
	
	<script type="text/javascript" src="Theme/assets/js/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	
	
	<script src="Theme/assets/js/form-component.js"></script>    
    
    
  <script>
      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });

  </script>

  </body>
</html>
