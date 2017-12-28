<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="HeyDriverWebsite.View.index" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Tesis">
    <meta name="author" content="webthemez">
    <title>HeyDriver | Official Site</title>
	<!-- core CSS -->
    <!-- Morris Charts CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/owl.carousel.css" rel="stylesheet">
    <link href="css/owl.transitions.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet"> 
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/heydriver.ico"> 
    <!-- Resources -->
    <script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
    <script src="//www.amcharts.com/lib/3/pie.js"></script>
    <script src="https://www.amcharts.com/lib/3/serial.js"></script>
    <script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <script src="https://www.amcharts.com/lib/3/themes/black.js"></script>
</head> 

<body id="home">

    <header id="header">
        <nav id="main-nav" class="navbar navbar-default navbar-fixed-top" role="banner">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html" style="margin-left:10px"><img src="images/heydriver.png" width="140" height="53" alt="logo"></a>
                </div>
				
                <div class="collapse navbar-collapse navbar-right">
                    <ul class="nav navbar-nav">
                        <li class="scroll active"><a href="#home">Inicio</a></li> 
                        <li class="scroll"><a href="#services">Services</a></li>
                        <li class="scroll"><a href="#about">Who We</a></li>
                        <li class="scroll"><a href="#pricing">Pricing</a></li>
                        <li class="scroll"><a href="#portfolio">Estadísticas</a></li>
                        <li class="scroll"><a href="#our-team">Team</a></li>
                        <li class="scroll"><a href="#contact-us">Contact</a></li>                        
                    </ul>
                </div>
            </div><!--/.container-->
        </nav><!--/nav-->
    </header><!--/header-->

    <section id="main-slider">
        <div class="owl-carousel">
            <div class="item" style="background-image: url(images/slider/bg1.jpg);">
                <div class="slider-inner">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="carousel-content">
                                    <h2>Clear <span>Car</span> Wash</h2> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/.item-->
            <div class="item" style="background-image: url(images/slider/bg2.jpg);">
                <div class="slider-inner">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="carousel-content">
                                    <h2>Clear <span>Car</span> Wash</h2> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/.item-->
             <div class="item" style="background-image: url(images/slider/bg3.jpg);">
                <div class="slider-inner">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="carousel-content">
                                    <h2>Best <span>Car</span> Servicing</h2> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/.item-->
        </div><!--/.owl-carousel-->
    </section><!--/#main-slider-->

    <section id="hero-text" class="wow fadeIn">
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <h2>We take care of your Car Servcing </h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa semper aliquam quis mattis quam. Morbi vitae tortor tempus, placerat leo et, suscipit lectus. Phasellus ut euismod massa, eu eleifend ipsum. 
                    </p>
                </div> 
            </div>
        </div>
    </section><!--/#hero-text-->

      <section id="services" >
        <div class="container">

            <div class="section-header">
                <h2 class="section-title text-center wow fadeInDown">Services</h2>
                <p class="text-center wow fadeInDown">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa <br> semper aliquam quis mattis quam.</p>
            </div>

            <div class="row">
                <div class="features">
                    <div class="col-md-4 col-sm-6 wow fadeInUp" data-wow-duration="300ms" data-wow-delay="0ms">
                        <div class="media service-box">
                            <div class="pull-left">
                                <i class="fa fa-heart"></i>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Car Checkup</h4>
                                <p>Backed by some of the biggest names in the industry, Firefox OS is an open platform that fosters greater</p>
                            </div>
                        </div>
                    </div><!--/.col-md-4-->

                    <div class="col-md-4 col-sm-6 wow fadeInUp" data-wow-duration="300ms" data-wow-delay="100ms">
                        <div class="media service-box">
                            <div class="pull-left">
                                <i class="fa fa-compass"></i>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Car Wash</h4>
                                <p>Backed by some of the biggest names in the industry, Firefox OS is an open platform that fosters greater</p>
                            </div>
                        </div>
                    </div><!--/.col-md-4-->

                    <div class="col-md-4 col-sm-6 wow fadeInUp" data-wow-duration="300ms" data-wow-delay="200ms">
                        <div class="media service-box">
                            <div class="pull-left">
                                <i class="fa fa-bell"></i>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Car Repair</h4>
                                <p>Morbi vitae tortor tempus, placerat leo et, suscipit lectus. Phasellus ut euismod massa, eu eleifend ipsum.</p>
                            </div>
                        </div>
                    </div><!--/.col-md-4-->
                
                    <div class="col-md-4 col-sm-6 wow fadeInUp" data-wow-duration="300ms" data-wow-delay="300ms">
                        <div class="media service-box">
                            <div class="pull-left">
                                <i class="fa fa-cube"></i>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Car Paint</h4>
                                <p>Morbi vitae tortor tempus, placerat leo et, suscipit lectus. Phasellus ut euismod massa, eu eleifend ipsum.</p>
                            </div>
                        </div>
                    </div><!--/.col-md-4-->

                    <div class="col-md-4 col-sm-6 wow fadeInUp" data-wow-duration="300ms" data-wow-delay="400ms">
                        <div class="media service-box">
                            <div class="pull-left">
                                <i class="fa fa-yelp"></i>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Car Decor</h4>
                                <p>Morbi vitae tortor tempus, placerat leo et, suscipit lectus. Phasellus ut euismod massa, eu eleifend ipsum.</p>
                            </div>
                        </div>
                    </div><!--/.col-md-4-->

                    <div class="col-md-4 col-sm-6 wow fadeInUp" data-wow-duration="300ms" data-wow-delay="500ms">
                        <div class="media service-box">
                            <div class="pull-left">
                                <i class="fa fa-life-ring"></i>
                            </div>
                            <div class="media-body">
                                <h4 class="media-heading">Car Polish</h4>
                                <p>Morbi vitae tortor tempus, placerat leo et, suscipit lectus. Phasellus ut euismod massa, eu eleifend ipsum.</p>
                            </div>
                        </div>
                    </div><!--/.col-md-4-->
                </div>
            </div><!--/.row-->    
        </div><!--/.container-->
    </section><!--/#services-->
   

 <section id="about">
        <div class="container">

            <div class="section-header">
                <h2 class="section-title text-center wow fadeInDown">About Us</h2>
                <p class="text-center wow fadeInDown">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa <br> semper aliquam quis mattis quam.</p>
            </div>

            <div class="row">
                <div class="col-sm-6 wow fadeInLeft">
                  <img class="img-responsive" src="images/about.png" alt="">
                </div>

                <div class="col-sm-6 wow fadeInRight"> 
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa semper aliquam quis mattis quam. Morbi vitae tortor tempus, placerat leo et, suscipit lectus. Phasellus ut euismod massa, eu eleifend ipsum.</p>

                    <p>Nulla eu neque commodo, dapibus dolor eget, dictum arcu. In nec purus eu tellus consequat ultricies. Donec feugiat tempor turpis, rutrum sagittis mi venenatis at. Sed molestie lorem a blandit congue. Ut pellentesque odio quis leo volutpat, vitae vulputate felis condimentmolestie lorem a blandit congue. Ut pellentesque odio quis leo volutpat, vitae vulputate felis condimentum. </p>

					<p>Praesent vulputate fermentum lorem, id rhoncus sem vehicula eu. Quisque ullamcorper, orci adipiscing auctor viverra, velit arcu malesuada metus, in volutpat tellus sem at justo.</p>
 

                </div>
            </div>
        </div>
    </section><!--/#about-->


    <section id="pricing">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title text-center wow fadeInDown">Our Pricing</h2>
                <p class="text-center wow fadeInDown">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa <br> semper aliquam quis mattis quam.</p>
            </div>

            <div class="row"> 
			<div class="col-md-4">     
                        <ul class="price" style="width:100%">
						<li class="header">Basic</li>
						<li class="grey">Car Checkup</li>
						<li>Car Wash</li>
						<li>Car Interior Clean</li>
						<li>Waxing</li>
						<li>Polishing</li>
						<li class="grey"><a href="javascript:void(0)" class="button w3-hover-green">Buy Now</a></li>
						</ul>
                        </div>
						<div class="col-md-4">     
                             <ul class="price" style="width:100%">
						<li class="header">Advance</li>
						<li class="grey">Car Checkup</li>
						<li>Car Wash</li>
						<li>Car Interior Clean</li>
						<li>Waxing</li>
						<li>Polishing</li>
						<li class="grey"><a href="javascript:void(0)" class="button w3-hover-green">Buy Now</a></li>
						</ul>
                        </div>
						<div class="col-md-4">  
						   <ul class="price" style="width:100%">
						<li class="header">Prime</li>
						<li class="grey">Car Checkup</li>
						<li>Car Wash</li>
						<li>Car Interior Clean</li>
						<li>Waxing</li>
						<li>Polishing</li>
						<li class="grey"><a href="javascript:void(0)" class="button w3-hover-green">Buy Now</a></li>
						</ul>
                        </div>
			</div>
        </div>
    </section><!--/#pricing-->
  

 
  <form id="form" runat="server">
      
    <asp:ScriptManager ID="ScriptManager1" runat="server" EnablePageMethods="true">
    </asp:ScriptManager>
      <section id="portfolio">
            <div class="container">
                <div class="section-header">
                    <h2 class="section-title text-center wow fadeInDown">Estadísticas</h2>
                </div>

                <div class="row">
                    <div class="section-header">
                        <h3 class="text wow fadeInDown" style="color:white">Marcas y modelos</h3>
                    </div>

            
            
                    <div class="col-md-12" style="margin-bottom:50px;text-align:left">
                        <div class="col-xs-4">
                            <label>Marca:  </label>
                            <input list="listado_marcas" name="listado" runat="server" id="marca" style="height:30px; width:100%" autocomplete="off" class="form-control" onblur="CargarModelos()">
                            <datalist id="listado_marcas" runat="server">
                                                
                            </datalist>
                        </div>
                        <div class="col-xs-4">
                            <label>Modelo:  </label>
                            <input list="listado_modelos" name="listado" runat="server" id="modelo" style="height:30px; width:100%" autocomplete="off" class="form-control">
                            <datalist id="listado_modelos" runat="server">
                                                
                            </datalist>
                        </div>
                        <div class="col-xs-4">
                            <label> </label>

                            <input type="button" value="Aceptar" class="form-control" style="background-color:#2ECC71; border-color:#2ECC71; color:white; width:75%; margin-top:4px;" onclick="CargarDatos()">
                        </div>
                    </div>
                
                    <div class="col-lg-12">
                        <div class="col-lg-6">
                            <div id="chartdiv" class="wow fadeInDown"></div> 
                        </div>
                        <div class="col-lg-6">
                            <div id="piediv" class="wow fadeInDown"></div> 
                        </div>
                    </div>
                </div>
                <br />
                <div class="row">
                    <div class="section-header">
                        <h3 class="text wow fadeInDown" style="color:white">Fallas</h3>
                    </div>

            
            
                    <div class="col-md-12" style="margin-bottom:50px;text-align:left">
                        <div class="col-xs-4">
                            <label>Fallas:  </label>
                            <asp:DropDownList runat="server" ID="listado_fallas" style="height:30px; width:100%" class="form-control">
                                <asp:ListItem>Consumo desproporcionado de combustible</asp:ListItem>
                                <asp:ListItem>Mezcla de Aire/Combustible muy pobre</asp:ListItem>
                                <asp:ListItem>Mezcla de Aire/Combustible muy rica</asp:ListItem>
                                <asp:ListItem>Sensor MAF sucio o averiado</asp:ListItem>
                                <asp:ListItem>Inyectores sucios o averiados</asp:ListItem>
                                <asp:ListItem>Bobina Averiada</asp:ListItem>
                                <asp:ListItem>Bujías propensas a daños</asp:ListItem>
                                <asp:ListItem>Vehículo propenso a recalentamiento</asp:ListItem>
                                <asp:ListItem>Radiador Averiado</asp:ListItem>
                                <asp:ListItem>Alternador defectuoso</asp:ListItem>
                            </asp:DropDownList>
                        </div>
                        <div class="col-xs-4">
                            <label>Marca:  </label>
                            <input list="marcas_listado" name="listado" runat="server" id="marcas_lista" style="height:30px; width:100%" autocomplete="off" class="form-control">
                            <datalist id="marcas_listado" runat="server">
                                                
                            </datalist>
                        </div>
                        <div class="col-xs-4">
                            <label> </label>

                            <input type="button" value="Aceptar" class="form-control" style="background-color:#2ECC71; border-color:#2ECC71; color:white; width:75%; margin-top:4px;" onclick="CargarDatosFallas()">
                        </div>
                    </div>
                
                    <div class="col-lg-12">
                        <div class="col-lg-6">
                            <div id="chartdiv2" class="wow fadeInDown"></div> 
                        </div>
                        <div class="col-lg-6">
                            <div id="piediv2" class="wow fadeInDown"></div> 
                        </div>
                    </div>
                </div>


            </div><!--/.container-->
        </section><!--/#portfolio-->
    </form>

   
   
    <section id="our-team">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title text-center wow fadeInDown">Team</h2>
                <p class="text-center wow fadeInDown">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa <br> semper aliquam quis mattis quam.</p>
            </div>

            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <div class="team-member wow fadeInUp" data-wow-duration="400ms" data-wow-delay="0ms">
                        <div class="team-img">
                            <img class="img-responsive" src="images/team/01.jpg" alt="">
                        </div>
                        <div class="team-info">
                            <h3>John Deo</h3>
                            <span>Manager</span>
                        </div>  
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="team-member wow fadeInUp" data-wow-duration="400ms" data-wow-delay="100ms">
                        <div class="team-img">
                            <img class="img-responsive" src="images/team/02.jpg" alt="">
                        </div>
                        <div class="team-info">
                            <h3>Mike Timobbs</h3>
                            <span>Painter</span>
                        </div>  
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="team-member wow fadeInUp" data-wow-duration="400ms" data-wow-delay="200ms">
                        <div class="team-img">
                            <img class="img-responsive" src="images/team/03.jpg" alt="">
                        </div>
                        <div class="team-info">
                            <h3>Remo Silvaus</h3>
                            <span>Enginer</span>
                        </div>  
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="team-member wow fadeInUp" data-wow-duration="400ms" data-wow-delay="300ms">
                        <div class="team-img">
                            <img class="img-responsive" src="images/team/04.jpg" alt="">
                        </div>
                        <div class="team-info">
                            <h3>Niscal Deon</h3>
                            <span>Enginer</span>
                        </div>  
                    </div>
                </div>
            </div>

        </div>
    </section><!--/#our-team-->

    
    <section id="testimonial">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2">

                    <div id="carousel-testimonial" class="carousel slide text-center" data-ride="carousel">
                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <div class="item active">
                                <p><img class="img-thumbnail" src="images/pic1.jpg" alt=""></p>
                                <h4>John Deo</h4> 
                                <p>Fusce non fermentum mi. Praesent vel lobortis elit. Nulla sodales, risus quis sollicitudin iaculis, felis dolor aliquet purus, eget elementum velit nunc eu dolor. Curabitur elit tellus.</p>
                            </div>
                            <div class="item">
                                <p><img class="img-thumbnail" src="images/pic2.jpg" alt=""></p>
                                <h4>Gramth Larry</h4> 
                                <p>Fusce non fermentum mi. Praesent vel lobortis elit. Nulla sodales, risus quis sollicitudin iaculis, felis dolor aliquet purus, eget elementum velit nunc eu dolor. Curabitur elit tellus, dictu.</p>
                            </div>
                        </div>

                        <!-- Controls -->
                        <div class="btns">
                            <a class="btn btn-primary btn-sm" href="#carousel-testimonial" role="button" data-slide="prev">
                                <span class="fa fa-angle-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="btn btn-primary btn-sm" href="#carousel-testimonial" role="button" data-slide="next">
                                <span class="fa fa-angle-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section><!--/#testimonial-->

    
    <section id="contact-us">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title text-center wow fadeInDown">Contact Us</h2>
                <p class="text-center wow fadeInDown">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa <br> semper aliquam quis mattis quam.</p>
            </div>
        </div>
    </section><!--/#contact-us-->


    <section id="contact">
        
        <div class="container-wrapper">
            <div class="container contact-info">
                <div class="row">
				  <div class="col-sm-4 col-md-4">
                        <div class="contact-form"> 
                            <address>
                              <strong>Clear Car Wash</strong><br>
                              12345 NewYork, Street 125<br>
                              United States 94107<br>
                              <abbr title="Phone">P:</abbr> (123) 456-7890
                            </address>
					</div></div>
                    <div class="col-sm-8 col-md-8">                      
						<div class="contact-form">											
						  
		                    <!--NOTE: Update your email Id in "contact_me.php" file in order to receive emails from your contact form-->
		                    <form name="sentMessage" id="contactForm"  novalidate> 
		                    <div class="control-group">
		                    <div class="controls">
		                    <input type="text" class="form-control" 
		                    placeholder="Full Name" id="name" required
		                    data-validation-required-message="Please enter your name" />
		                    <p class="help-block"></p>
		                    </div>
		                    </div> 	
		                    <div class="control-group">
		                    <div class="controls">
		                    <input type="email" class="form-control" placeholder="Email" 
		                    id="email" required
		                    data-validation-required-message="Please enter your email" />
		                    </div>
		                    </div> 	

		                    <div class="control-group">
		                    <div class="controls">
		                    <textarea rows="10" cols="100" class="form-control" 
		                    placeholder="Message" id="message" required
		                    data-validation-required-message="Please enter your message" minlength="5" 
		                    data-validation-minlength-message="Min 5 characters" 
		                    maxlength="999" style="resize:none"></textarea>
		                    </div>
		                    </div> 		 
		                    <div id="success"> </div> <!-- For success/fail messages -->
		                    <button type="submit" class="btn btn-primary pull-right">Send</button><br />
		                    </form>

							 					
						</div>
                    </div>
                </div>
            </div>
        </div>
        <div id="google-map" style="height:400px" data-latitude="40.7141667" data-longitude="-74.00638891"></div> 
   </section><!--/#bottom-->

    <footer id="footer">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    &copy; 2018 Company Name. Template by <a target="_blank" href="http://webthemez.com/" title="Free Bootstrap Themes and HTML Templates">WebThemez.com</a>
                </div>
                <div class="col-sm-6">
                    <ul class="social-icons">
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li> 
                        <li><a href="#"><i class="fa fa-youtube"></i></a></li>
                        <li><a href="#"><i class="fa fa-github"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </footer><!--/#footer-->
    <style>
        #chartdiv {
	        width		: 100%;
	        height		: 500px;
	        font-size	: 11px;
            
        }	
        
        #piediv {
	        width		: 100%;
	        height		: 500px;
	        font-size	: 11px;
        }	
        #chartdiv2 {
	        width		: 100%;
	        height		: 500px;
	        font-size	: 11px;
            
        }	
        
        #piediv2 {
	        width		: 100%;
	        height		: 500px;
	        font-size	: 11px;
        }		
    </style>
    <script>
        AmCharts.addInitHandler(function(chart) {
            // check if there are graphs with autoColor: true set
            for(var i = 0; i < chart.graphs.length; i++) {
                var graph = chart.graphs[i];
                if (graph.autoColor !== true)
                    continue;
                var colorKey = "autoColor-"+i;
                graph.lineColorField = colorKey;
                graph.fillColorsField = colorKey;
                for(var x = 0; x < chart.dataProvider.length; x++) {
                    var color = chart.colors[7]
                    chart.dataProvider[x][colorKey] = color;
                }
            }
  
        }, ["serial"]);
        var chart = AmCharts.makeChart("chartdiv", {
            "type": "serial",
            "theme": "light",
            "dataProvider": [{
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }],
            "valueAxes": [{
                "gridColor": "#FFFFFF",
                "gridAlpha": 0.2,
                "dashLength": 0,
                "color": "#FFFFFF"
            }],
            "gridAboveGraphs": true,
            "startDuration": 1,
            "graphs": [{
                "balloonText": "[[category]]: <b>[[value]]</b>",
                "fillAlphas": 0.8,
                "lineAlpha": 0.2,
                "type": "column",
                "valueField": "value",
                "autoColor": true,
            }],
            "chartCursor": {
                "categoryBalloonEnabled": false,
                "cursorAlpha": 0,
                "zoomable": false
            },
            "categoryField": "title",
            "categoryAxis": {
                "gridPosition": "start",
                "gridAlpha": 0,
                "tickPosition": "start",
                "tickLength": 20,
                "color": "#FFFFFF"
            },
            "export": {
                "enabled": true
            }

        });
        var chart = AmCharts.makeChart("chartdiv2", {
            "type": "serial",
            "theme": "light",
            "dataProvider": [{
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }],
            "valueAxes": [{
                "gridColor": "#FFFFFF",
                "gridAlpha": 0.2,
                "dashLength": 0,
                "color": "#FFFFFF"
            }],
            "gridAboveGraphs": true,
            "startDuration": 1,
            "graphs": [{
                "balloonText": "[[category]]: <b>[[value]]</b>",
                "fillAlphas": 0.8,
                "lineAlpha": 0.2,
                "type": "column",
                "valueField": "value",
                "autoColor": true,
            }],
            "chartCursor": {
                "categoryBalloonEnabled": false,
                "cursorAlpha": 0,
                "zoomable": false
            },
            "categoryField": "title",
            "categoryAxis": {
                "gridPosition": "start",
                "gridAlpha": 0,
                "tickPosition": "start",
                "tickLength": 20,
                "color": "#FFFFFF"
            },
            "export": {
                "enabled": true
            }

        });
    </script>	
    <script>
        var chart = AmCharts.makeChart("piediv", {
            "type": "pie",
            "theme": "black",
            "dataProvider": [{
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0,
                "color": "#ff0000"
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0,
                "color": "#00ff00"
            }],
            "valueField": "value",
            "titleField": "title",
            "colorField": "color",
            "balloon": {
                "fixedPosition": true
            },
            "export": {
                "enabled": true
            }
        });
        var chart = AmCharts.makeChart("piediv2", {
            "type": "pie",
            "theme": "black",
            "dataProvider": [{
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0,
                "color": "#ff0000"
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0
            }, {
                "title": "",
                "value": 0,
                "color": "#00ff00"
            }],
            "valueField": "value",
            "titleField": "title",
            "colorField": "color",
            "balloon": {
                "fixedPosition": true
            },
            "export": {
                "enabled": true
            }
        });
    </script>	

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://maps.google.com/maps/api/js?sensor=true"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/mousescroll.js"></script>
    <script src="js/smoothscroll.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/jquery.inview.min.js"></script>
    <script src="js/wow.min.js"></script>
    <script src="contact/jqBootstrapValidation.js"></script>
    <script src="contact/contact_me.js"></script>
    <script src="js/contact.js"></script>
    <script src="js/custom-scripts.js"></script>

    <script>
        function CargarModelos() {
            PageMethods.GetModelos(document.getElementById("marca").value, OnSuccess);
        }
        function OnSuccess(response, userContext, methodName) {
            if ((response != "Por favor seleccione un vehículo del listado mostrado") && (response != "Ha ocurrido un error, por favor vuelva a intentar") && (response != "No hay nada")) {
                $('#listado_modelos').html(response);
                $('#modelo').text("Sólo usar la marca");
            } else {
                alert(response);
            }
        }
        function CargarDatos() {
            PageMethods.GetDatos(document.getElementById("marca").value, document.getElementById("modelo").value, Exito);
        }
        function Exito(response, userContext, methodName) {
            if ((response != "Por favor revise los datos ingresados") && (response != "Ha ocurrido un error, por favor vuelva a intentar") && (response != "No hay nada")) {
                var splitted = response.split(",");
                var chart = AmCharts.makeChart("chartdiv", {
                    "type": "serial",
                    "theme": "light",
                    "dataProvider": [{
                        "title": splitted[0],
                        "value": splitted[1]
                    }, {
                        "title": splitted[2],
                        "value": splitted[3]
                    }, {
                        "title": splitted[4],
                        "value": splitted[5]
                    }, {
                        "title": splitted[6],
                        "value": splitted[7]
                    }, {
                        "title": splitted[8],
                        "value": splitted[9]
                    }],
                    "valueAxes": [{
                        "gridColor": "#FFFFFF",
                        "gridAlpha": 0.2,
                        "dashLength": 0,
                        "color": "#FFFFFF"
                    }],
                    "gridAboveGraphs": true,
                    "startDuration": 1,
                    "graphs": [{
                        "balloonText": "[[category]]: <b>[[value]]</b>",
                        "fillAlphas": 0.8,
                        "lineAlpha": 0.2,
                        "type": "column",
                        "valueField": "value",
                        "autoColor": true,
                    }],
                    "chartCursor": {
                        "categoryBalloonEnabled": false,
                        "cursorAlpha": 0,
                        "zoomable": false
                    },
                    "categoryField": "title",
                    "categoryAxis": {
                        "gridPosition": "start",
                        "gridAlpha": 0,
                        "tickPosition": "start",
                        "tickLength": 20,
                        "color": "#FFFFFF",
                        "autoWrap": true
                    },
                    "export": {
                        "enabled": true
                    }

                });
                var chart1 = AmCharts.makeChart("piediv", {
                    "type": "pie",
                    "radius": 70,
                    "theme": "black",
                    "dataProvider": [{
                        "title": splitted[0],
                        "value": splitted[1]
                    }, {
                        "title": splitted[2],
                        "value": splitted[3]
                    }, {
                        "title": splitted[4],
                        "value": splitted[5]
                    }, {
                        "title": splitted[6],
                        "value": splitted[7]
                    }, {
                        "title": splitted[8],
                        "value": splitted[9]
                    }, {
                        "title": splitted[10],
                        "value": splitted[11]
                    }, {
                        "title": splitted[12],
                        "value": splitted[13]
                    }, {
                        "title": splitted[14],
                        "value": splitted[15]
                    }, {
                        "title": splitted[16],
                        "value": splitted[17]
                    }, {
                        "title": splitted[18],
                        "value": splitted[19]
                    }],
                    "valueField": "value",
                    "titleField": "title",
                    "colorField": "color",
                    "outlineAlpha": 0.4,
                    "depth3D": 15,
                    "angle": 30,
                    "fontSize": 10,
                    "balloon": {
                        "fixedPosition": true
                    },
                    "export": {
                        "enabled": true
                    }
                });
            }else {
                alert(response);
            }
        }
	</script>

    <script>
        function CargarDatosFallas() {
            PageMethods.GetDatosFallas(document.getElementById("listado_fallas").value, document.getElementById("marcas_lista").value, ExitoFallas);
        }
        function ExitoFallas(response, userContext, methodName) {
            if ((response != "Por favor revise los datos ingresados") && (response != "Ha ocurrido un error, por favor vuelva a intentar") && (response != "No hay nada")) {
                var splitted = response.split(",");
                var chart = AmCharts.makeChart("chartdiv2", {
                    "type": "serial",
                    "theme": "light",
                    "dataProvider": [{
                        "title": splitted[0],
                        "value": splitted[1]
                    }, {
                        "title": splitted[2],
                        "value": splitted[3]
                    }, {
                        "title": splitted[4],
                        "value": splitted[5]
                    }, {
                        "title": splitted[6],
                        "value": splitted[7]
                    }, {
                        "title": splitted[8],
                        "value": splitted[9]
                    }],
                    "valueAxes": [{
                        "gridColor": "#FFFFFF",
                        "gridAlpha": 0.2,
                        "dashLength": 0,
                        "color": "#FFFFFF"
                    }],
                    "gridAboveGraphs": true,
                    "startDuration": 1,
                    "graphs": [{
                        "balloonText": "[[category]]: <b>[[value]]</b>",
                        "fillAlphas": 0.8,
                        "lineAlpha": 0.2,
                        "type": "column",
                        "valueField": "value",
                        "autoColor": true,
                    }],
                    "chartCursor": {
                        "categoryBalloonEnabled": false,
                        "cursorAlpha": 0,
                        "zoomable": false
                    },
                    "categoryField": "title",
                    "categoryAxis": {
                        "gridPosition": "start",
                        "gridAlpha": 0,
                        "tickPosition": "start",
                        "tickLength": 20,
                        "color": "#FFFFFF",
                        "autoWrap": true
                    },
                    "export": {
                        "enabled": true
                    }

                });
                var chart1 = AmCharts.makeChart("piediv2", {
                    "type": "pie",
                    "radius": 70,
                    "theme": "black",
                    "dataProvider": [{
                        "title": splitted[0],
                        "value": splitted[1]
                    }, {
                        "title": splitted[2],
                        "value": splitted[3]
                    }, {
                        "title": splitted[4],
                        "value": splitted[5]
                    }, {
                        "title": splitted[6],
                        "value": splitted[7]
                    }, {
                        "title": splitted[8],
                        "value": splitted[9]
                    }, {
                        "title": splitted[10],
                        "value": splitted[11]
                    }, {
                        "title": splitted[12],
                        "value": splitted[13]
                    }, {
                        "title": splitted[14],
                        "value": splitted[15]
                    }, {
                        "title": splitted[16],
                        "value": splitted[17]
                    }, {
                        "title": splitted[18],
                        "value": splitted[19]
                    }],
                    "valueField": "value",
                    "titleField": "title",
                    "colorField": "color",
                    "outlineAlpha": 0.4,
                    "depth3D": 15,
                    "angle": 30,
                    "fontSize": 10,
                    "balloon": {
                        "fixedPosition": true
                    },
                    "export": {
                        "enabled": true
                    }
                });
            } else {
                alert(response);
            }
        }
    </script>

</body>
</html>
