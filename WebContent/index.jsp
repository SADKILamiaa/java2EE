<!doctype html>
<html class="no-js"  lang="en">
	<head>
	                <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
		<title>Travel</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="https://fonts.googleapis.com/css?family=Rufina:400,700" rel="stylesheet" />
		<link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet" />
		<link rel="stylesheet" href="assets/css/animate.css" />
		<link rel="stylesheet" href="assets/css/hover-min.css">
		<link rel="stylesheet"  href="assets/css/datepicker.css" >
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
		<link rel="stylesheet" href="assets/css/owl.theme.default.min.css"/>
        <link rel="stylesheet" href="assets/css/jquery-ui.min.css" />
		<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/css/bootsnav.css"/>
		<link rel="stylesheet" href="assets/css/style.css" />
		
	</head>
	<body>
		
		<jsp:include page="Menu.jsp" />
		<section id="home" class="about-us">
			<div class="container">
				<div class="about-us-content">
					<div class="row">
						<div class="col-sm-12">
							<div class="single-about-us">
								<div class="about-us-txt">
									<h2>
										Explorez la beaut� du beau monde
									</h2>
									
								</div>
							</div>
						</div>
						<div class="col-sm-0">
							<div class="single-about-us">	
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		



		<section  class="travel-box">
        	<div class="container">
        		<div class="row">
        			<div class="col-md-12">
        				<div class="single-travel-boxes">
        				<form  method="post"  action="AffichageVol">
        					<div id="desc-tabs" class="desc-tabs">
								<ul class="nav nav-tabs" role="tablist">
									<li role="presentation">
									 	<a href="#flights" aria-controls="flights" role="tab" data-toggle="tab">
									 		<i class="fa fa-plane"></i>
									 		vols
									 	</a>
									</li>
								</ul>
									<div role="tabpanel" id="flights">
										<div class="tab-para">
											<div class="trip-circle">
												<div class="single-trip-circle">
													<input type="radio" id="radio01" value="radio01" name="radio" onclick="fct()" checked="checked" />
  													<label for="radio01">
  														<span class="round-boarder">
  															<span class="round-boarder1"></span>
  														</span>aller-retour
  													</label>
												</div>
												<div class="single-trip-circle">
													<input type="radio" id="radio02" value="radio02" name="radio" onclick="fonct()"/>
  													<label for="radio02">
  														<span class="round-boarder">
  															<span class="round-boarder1"></span>
  														</span>aller-simple
  													</label>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-4 col-md-4 col-sm-12">
													<div class="single-tab-select-box">
														<h2>De</h2>
														<div class="travel-select-icon">
															<select class="form-control "  name="de" value="de">
															<c:forEach items="${list_nomAeroports}" var="nom_aero" varStatus="boucle">
					
															  	<option  value="${nom_aero.nom}" >
															  	<c:out value="${ nom_aero.nom }"></c:out>
															  	</option>
															 </c:forEach> 	
															</select>
														</div>
													</div>
												</div>
												<div class="col-lg-2 col-md-3 col-sm-4">
													<div class="single-tab-select-box">
														<h2>D�part</h2>
														<div class="travel-check-icon">
															<form action="#">
																<input type="text"  name="depart" value="depart" class="form-control" data-toggle="datepicker"
																placeholder="12 -01 - 2017 ">
															</form>
														</div>
													</div>
												</div>
												<div class="col-lg-2 col-md-3 col-sm-4"  id="retour">
													<div class="single-tab-select-box">
														<h2>retour</h2>
														<div class="travel-check-icon">
															<form action="#">
																<input type="text"  name="retour" value="retour" class="form-control" data-toggle="datepicker" placeholder="22 -01 - 2017 ">
															</form>
														</div>
													</div>
												</div>

												<div class="col-lg-2 col-md-1 col-sm-4">
													<div class="single-tab-select-box">
														<h2>adultes</h2>
														<div class="travel-select-icon">
															<select class="form-control "  name="adulte" value="adulte">
															  	<option value="default">5</option>
															  	<option value="10">10</option>
															  	<option value="15">15</option>
															  	<option value="20">20</option>
															</select>
														</div>
													</div>
												</div>
												<div class="col-lg-2 col-md-1 col-sm-4">
													<div class="single-tab-select-box">
														<h2>Enfants</h2>
														<div class="travel-select-icon">
															<select class="form-control "  name="enfant" value="enfant">
															  	<option value="default">1</option>
															  	<option value="2">2</option>
															  	<option value="4">4</option>
															  	<option value="8">8</option>
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-4 col-md-4 col-sm-12">
													<div class="single-tab-select-box">
														<h2>�</h2>
														<div class="travel-select-icon">
															<select class="form-control "  name="a" value="a">
															  <c:forEach items="${list_nomAeroports}" var="nom_aero" varStatus="boucle">
					
															  	<option value="${nom_aero.nom}" >
															  	<c:out value="${nom_aero.nom}"></c:out>
															  	</option>
															 </c:forEach>
															</select>
														</div>
													</div>
												</div>
												
												<div class="clo-sm-5">
													<div class="about-btn pull-right">
														<button  class="about-view travel-btn" id="chercherVol" value="chercherVol" name="chercherVol">
								                               Chercher 
														</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								</form>
							</div>
        				</div>
        			</div>
        		</div>
        	</div>

        </section>
		
		
		
		
		
		
		
		
		<section id="gallery" class="gallery">
			<div class="container">
				<div class="gallery-details">
					<div class="gallary-header text-center">
						<h2>
							top destination
						</h2>
						<p>
							Where do you wanna go? How much you wanna explore?  
						</p>
					</div><!--/.gallery-header-->
					<div class="gallery-box">
						<div class="gallery-content">
						  	<div class="filtr-container">
						  		<div class="row">

						  			<div class="col-md-6">
						  				<div class="filtr-item">
											<img src="assets/images/gallary/g1.jpg" alt="portfolio image"/>
											<div class="item-title">
												<a href="#">
													china
												</a>
												<p><span>20 tours</span><span>15 places</span></p>
											</div><!-- /.item-title -->
										</div><!-- /.filtr-item -->
						  			</div><!-- /.col -->

						  			<div class="col-md-6">
						  				<div class="filtr-item">
											<img src="assets/images/gallary/g2.jpg" alt="portfolio image"/>
											<div class="item-title">
												<a href="#">
													venuzuala
												</a>
												<p><span>12 tours</span><span>9 places</span></p>
											</div> <!-- /.item-title-->
										</div><!-- /.filtr-item -->
						  			</div><!-- /.col -->

						  			<div class="col-md-4">
						  				<div class="filtr-item">
											<img src="assets/images/gallary/g3.jpg" alt="portfolio image"/>
											<div class="item-title">
												<a href="#">
													brazil
												</a>
												<p><span>25 tours</span><span>10 places</span></p>
											</div><!-- /.item-title -->
										</div><!-- /.filtr-item -->
						  			</div><!-- /.col -->

						  			<div class="col-md-4">
						  				<div class="filtr-item">
											<img src="assets/images/gallary/g4.jpg" alt="portfolio image"/>
											<div class="item-title">
												<a href="#">
													australia 
												</a>
												<p><span>18 tours</span><span>9 places</span></p>
											</div> <!-- /.item-title-->
										</div><!-- /.filtr-item -->
						  			</div><!-- /.col -->

						  			<div class="col-md-4">
						  				<div class="filtr-item">
											<img src="assets/images/gallary/g5.jpg" alt="portfolio image"/>
											<div class="item-title">
												<a href="#">
													netharlands
												</a>
												<p><span>14 tours</span><span>12 places</span></p>
											</div> <!-- /.item-title-->
										</div><!-- /.filtr-item -->
						  			</div><!-- /.col -->

						  			<div class="col-md-8">
						  				<div class="filtr-item">
											<img src="assets/images/gallary/g6.jpg" alt="portfolio image"/>
											<div class="item-title">
												<a href="#">
													turkey
												</a>
												<p><span>14 tours</span><span>6 places</span></p>
											</div> <!-- /.item-title-->
										</div><!-- /.filtr-item -->
						  			</div><!-- /.col -->

						  		</div><!-- /.row -->

						  	</div><!-- /.filtr-container-->
						</div><!-- /.gallery-content -->
					</div><!--/.galley-box-->
				</div><!--/.gallery-details-->
			</div><!--/.container-->

		</section><!--/.gallery-->
		<!--gallery end-->
		<jsp:include page="footer.jsp"/>
		
		
		   <script>
		   function fct() {
				 var x = document.getElementById("retour");
				 
			       
			        	
			        	
			            
				    x.style.display = "block";
				
				}
			function  fonct()  {
				 var x = document.getElementById("retour");

				 
			    	x.style.display="none";

				 
				}
             </script>
		
		<script  src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
		<script  src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/bootsnav.js"></script>
		<script src="assets/js/jquery.filterizr.min.js"></script>
		<script  src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <script src="assets/js/jquery-ui.min.js"></script>
		<script src="assets/js/jquery.counterup.min.js"></script>
		<script src="assets/js/waypoints.min.js"></script>
        <script  src="assets/js/owl.carousel.min.js"></script>
		<script src="assets/js/jquery.sticky.js"></script>
        <script  src="assets/js/datepicker.js"></script>
		
	</body>

		
</html>