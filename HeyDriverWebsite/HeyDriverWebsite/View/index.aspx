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
    <style> 
        #rcorners {
            border-radius: 25px;
            border: 2px solid #2ECC71;
            background-color: white;
            padding: 20px; 
        }
    </style>
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            transition: 0.3s;
            width: 120%;
            height: 15%;
            background-color:whitesmoke;
        }

        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
        }

        .container {
            padding: 2px 16px;
        }
    </style>

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
                        <li class="scroll"><a href="#about">Sobre nosotros</a></li>
                        <li class="scroll"><a href="#pricing">Fallas y piezas</a></li>
                        <li class="scroll"><a href="#portfolio">Estadísticas</a></li>
                        <li class="scroll"><a href="#our-team">Equipo</a></li>              
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
                                    <h2>Una aplicación para ayudar a tener un vehículo en excelente estado</h2> 
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
                                    <h2>La información que necesitas sobre las fallas más frecuentes en los vehículos</h2> 
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
                <div class="col-sm-12">
                    <div class="col-sm-9">
                        <h2>Hey Driver te proporcionará tranquilidad al manejar</h2>
                        <p>Ayudará a que puedas manejar conociendo todo la información interna que maneja tu vehículo. ¡Descárgala en la App Store! 
                        </p>
                    </div>
                    <div class="col-sm-3">
                        <img class="img-responsive" src="images/google.png" height="120" width="160" alt="">
                    </div>
                    
                </div> 
            </div>
        </div>
    </section><!--/#hero-text-->
   

 <section id="about">
        <div class="container">

            <div class="section-header">
                <h2 class="section-title text-center wow fadeInDown">Sobre nosotros</h2>
                <p class="text-center wow fadeInDown">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget risus vitae massa <br> semper aliquam quis mattis quam.</p>
            </div>

            <div class="row">
                <div class="col-sm-6 wow fadeInLeft">
                  <img class="img-responsive" src="images/about1.png" alt="">
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
                <h2 class="section-title text-center wow fadeInDown">Fallas y piezas</h2>
                <p class="text-center wow fadeInDown">Como es de saber una falla en un vehículo involucra piezas en mal estado o inclusive puede ocasionar que elementos <br> 
                                                      del automovíl que se encontraban en buen estado se deterioren. Gracias a una extensa documentación<br> 
                                                      a continuación mostramos las piezas que se relacionan con las fallas estudiadas
                </p>
            </div>

            <div class="row">
                <label for="sel1">Fallas:</label>
                <select class="form-control" id="selectFalla" onchange="cambiarDiv()">
                    <option>Seleccione una falla...</option>
                    <option>Consumo desproporcionado de combustible</option>
                    <option>Mezcla de Aire/Combustible muy pobre</option>
                    <option>Mezcla de Aire/Combustible muy rica</option>
                    <option>Sensor MAF sucio o averiado</option>
                    <option>Inyectores sucios o averiados</option>
                    <option>Bobina Averiada</option>
                    <option>Bujías propensas a daños</option>
                    <option>Vehículo propenso a recalentamiento</option>
                    <option>Radiador Averiado</option>
                    <option>Alternador defectuoso</option>
                </select>
			</div>
            <br />
            <div class="row" id="rcorners" style="display: none;">
                <div id="consumodesproporcionado" style="display: none;">
                    <p><span style="font-size: 12.0pt; line-height: 107%;">Existen diversas causas que pueden generar este problema, que se encuentra com&uacute;nmente en autos que no tienen un mantenimiento constante. Si tu veh&iacute;culo es relativamente nuevo o tiene pocos kil&oacute;metros de rodaje no deber&iacute;a padecer de un consumo desproporcionado. Algunas de las piezas asociadas a esta falla son las siguientes:</span></p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Filtro de aire de admisi&oacute;n</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Tubo de admisi&oacute;n</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Inyectores</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Buj&iacute;as</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Cables de encendido de buj&iacute;as</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Bobinas de encendido</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Tap&oacute;n del tanque de gasolina</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Filtro de combustible</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Sensor MAF</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Aceite de motor</span></li>
                    </ul>
                </div>
                <div id="acpobre" style="display: none;">
                    <p>Para que una combusti&oacute;n &oacute;ptima se produzca, es necesario que la relaci&oacute;n de aire/combustible se encuentre en un rango de valores aceptable.&nbsp;Esta relaci&oacute;n se da al existir aproxim&aacute;damente 14.7 moleculas de aire por cada molecula de combustible. Pero en este caso,&nbsp;la mezcla contiene una excesiva cantidad de aire en relaci&oacute;n a la cantidad de combustible. Las piezas que se relacionan con esta falla son las siguientes:&nbsp;</p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Inyectores</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Buj&iacute;as</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Tubo de admisi&oacute;n</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Sensor MAF</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Filtro de combustible</span></li>
                    </ul>
                </div>
                <div id="acrica" style="display: none;">
                    <p>Para que una combusti&oacute;n &oacute;ptima se produzca, es necesario que la relaci&oacute;n de aire/combustible se encuentre en un rango de valores aceptable.&nbsp;Esta relaci&oacute;n se da al existir aproxim&aacute;damente 14.7 moleculas de aire por cada molecula de combustible. pero en este caso,&nbsp;la mezcla contiene una excesiva cantidad de combustible en relaci&oacute;n a la cantidad de aire. Las piezas que se relacionan con esta falla son las siguientes:&nbsp; <span style="font-size: 12.0pt; line-height: 107%; font-family: 'Calibri',sans-serif;"><br style="page-break-before: always;" /> </span></p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Inyectores</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Buj&iacute;as</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Bomba de gasolina</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Catalizador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Silenciador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Empacaduras de la c&aacute;mara de combusti&oacute;n</span></li>
                    </ul>
                </div>
                <div id="mafsucio" style="display: none;">
                    <p>El sensor MAF se encarga de medir el flujo de aire que ingresa a las c&aacute;maras del motor, esta informaci&oacute;n viaja hasta el PCM por medio de un cable a trev&eacute;s de una se&ntilde;al de voltaje que cambia de acuerdo al flujo. Las piezas que se relacionan con esta falla son las siguientes:</p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Sensor MAF</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Filtro de aire de admisi&oacute;n</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Tubo de admisi&oacute;n</span></li>
                    </ul>
                </div>
                <div id="inyectoresaveriados" style="display: none;">
                    <p>Los inyectores se encargan de suministrar el combustible para mezclarse con el aire antes de la combusti&oacute;n, ellos se encuentran instalados en el m&uacute;ltiple de admisi&oacute;n y se alimentan de gasolina a presi&oacute;n por la bomba de combustible. Los inyectores fallan principalmente debido a las impurezas contenidas en la gasolina. Las piezas que se relacionan con esta falla son las siguientes:</p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Inyectores</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Empacaduras de la c&aacute;mara de combusti&oacute;n</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Catalizador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Silenciador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Filtro de aire de admisi&oacute;n</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Bomba de gasolina</span></li>
                    </ul>
                </div>
                <div id="bobinaaveriada" style="display: none;">
                    <p>Las bobinas son las encargadas de suministrar a&nbsp;las buj&iacute;as&nbsp;corriente de alta tensi&oacute;n para producir la chispa necesaria para la combusti&oacute;n del motor de combustible. Con una bobina en mal estado el motor no ser&iacute;a capaz de arrancar, ya que la buj&iacute;a no producir&iacute;a la chispa. Las piezas que se relacionan con esta falla son las siguientes:</p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Bobinas de encendido</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Cables de encendido de buj&iacute;as</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Buj&iacute;as</span></li>
                    </ul>
                </div>
                <div id="bujiasaveriada" style="display: none;">
                    <p>La&nbsp;buj&iacute;a&nbsp;es la pieza encargada de producir el encendido de la mezcla de&nbsp;combustible&nbsp;y ox&iacute;geno en los cilindros, a trav&eacute;s de una chispa.&nbsp;Su buen funcionamiento es de suma importancia para la ejecuci&oacute;n &oacute;ptima del proceso de combusti&oacute;n/expansi&oacute;n. Las piezas que se relacionan con esta falla son las siguientes:</p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Aceite de motor</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Inyectores</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Bobinas de encendido</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Cilindros de la c&aacute;mara de combusti&oacute;n</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Buj&iacute;as</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Filtro de combustible</span></li>
                    </ul>
                </div>
                <div id="recalentamiento" style="display: none;">
                    <p>El sobrecalentamiento se aprecia cuando el termostato del veh&iacute;culo excede la temperatura aceptable debido al mal estado de alguno de los componentes del sistema de refrigeraci&oacute;n. Las piezas que se relacionan con esta falla son las siguientes:</p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Radiador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Termostato</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Manguera del calefactor</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Tap&oacute;n del radiador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Manguera superior del radiador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Manguera inferior del radiador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Dep&oacute;sito de refrigerante</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Electro ventilador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Refrigerante</span></li>
                    </ul>
                </div>
                <div id="radiadoraveriado" style="display: none;">
                    <p>El radiador es un componente del sistema de refrigeraci&oacute;n&nbsp;del veh&iacute;culo, cuya funci&oacute;n principal es evitar que se produzcan sobrecalentamientos en el motor, para que &eacute;ste trabaje de manera eficaz, ofreciendo as&iacute; el m&aacute;ximo rendimiento. Por lo general, las&nbsp;principales aver&iacute;as que aparecen en un radiador da&ntilde;ado son p&eacute;rdidas de hermeticidad o con fugas de agua&nbsp;localizadas en las juntas. Las piezas que se relacionan con esta falla son las siguientes:</p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Radiador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Termostato</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Tap&oacute;n del radiador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Manguera superior del radiador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Manguera inferior del radiador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Dep&oacute;sito de refrigerante</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Refrigerante</span></li>
                    </ul>
                </div>
                <div id="alternadoraveriado" style="display: none;">
                    <p>Un&nbsp;alternador&nbsp;es un componente de los veh&iacute;culos, capaz de transformar&nbsp;energ&iacute;a mec&aacute;nica&nbsp;en&nbsp;energ&iacute;a el&eacute;ctrica, generando a su vez corriente alterna&nbsp;mediante&nbsp;inducci&oacute;n electromagn&eacute;tica. La principal falla que ocurren cuando el alternador se encuentra averiado,  es la p&eacute;rdida de energ&iacute;a el&eacute;ctrica en el carro. Las piezas que se relacionan con esta falla son las siguientes:</p>
                    <ul>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Alternador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Polea del alternador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Rotor del alternador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Regulador del alternador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Estator del alternador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Diodera del alternador</span></li>
                        <li><span style="font-size: 12.0pt; line-height: 107%;">Bater&iacute;a</span></li>
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

                
                <br />
                <div class="row">
                    <div class="section-header">
                        <h3 class="text wow fadeInDown" style="color:white">Registro de fallas por vehículo</h3>
                    </div>

            
            
                    <div class="col-md-12" style="margin-bottom:50px;text-align:left">
                        <div class="col-xs-4">
                            <label>Serial de vehículo (VIN):  </label>
                            <input runat="server" id="vinvehiculo" style="height:30px; width:100%" autocomplete="off" class="form-control">
                        </div>
                        <div class="col-xs-4">
                            <label> </label>

                            <input type="button" value="Aceptar" class="form-control" style="background-color:#2ECC71; border-color:#2ECC71; color:white; width:75%; margin-top:4px;" onclick="CargarFallasVIN()">
                        </div>
                    </div>
                
                    <div class="col-lg-12"  style="margin-top:-10px;margin-bottom:20px">
                        <div class="col-lg-4">
                            <div class="wow fadeInDown"><label id="marcacar" style="font-size:medium">Marca: </label></div> 
                        </div>
                        <div class="col-lg-4">
                            <div class="wow fadeInDown"><label id="modelocar" style="font-size:medium">Modelo: </label></div> 
                        </div>
                    </div>
                
                    <div id="tarjetaserror" class="col-lg-12">
                       
                    </div>
                </div>


            </div><!--/.container-->
        </section><!--/#portfolio-->
    </form>

   
   
    <section id="our-team">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title text-center wow fadeInDown">Equipo de trabajo</h2>
                <p class="text-center wow fadeInDown">El equipo encargado de traer este aplicativo para sus vehículos es:</p>
            </div>

            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <div class="team-member wow fadeInUp" data-wow-duration="400ms" data-wow-delay="0ms">
                        <div class="team-img">
                            <img class="img-responsive" src="images/team/01.jpg" alt="">
                        </div>
                        <div class="team-info">
                            <h3>Luis Pérez</h3>
                            <span>Desarrollador móvil y web</span>
                        </div>  
                    </div>
                </div>
                <div class="col-md-3 col-sm-6">
                    <div class="team-member wow fadeInUp" data-wow-duration="400ms" data-wow-delay="100ms">
                        <div class="team-img">
                            <img class="img-responsive" src="images/team/02.jpg" alt="">
                        </div>
                        <div class="team-info">
                            <h3>Cristian Dos Ramos</h3>
                            <span>Desarrollador móvil y web</span>
                        </div>  
                    </div>
                </div>
            </div>

        </div>
    </section><!--/#our-team-->

   

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
	        width		: 110%;
	        height		: 500px;
	        font-size	: 11px;
        }	
        #chartdiv2 {
	        width		: 100%;
	        height		: 500px;
	        font-size	: 11px;
            
        }	
        
        #piediv2 {
	        width		: 110%;
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


    
    <script>
        function CargarFallasVIN() {
            PageMethods.GetFallasVIN(document.getElementById("vinvehiculo").value, ExitoFallasVIN);
        }
        function ExitoFallasVIN(response, userContext, methodName) {
            if ((response != "Por favor revise los datos ingresados") && (response != "Ha ocurrido un error, por favor vuelva a intentar") && (response != "No hay nada")) {
                var splitted = response.split("/");
                document.getElementById('tarjetaserror').innerHTML = "";
                document.getElementById('marcacar').innerHTML = 'Marca: ' + splitted[0].split(",")[0];
                document.getElementById('modelocar').innerHTML = 'Modelo: ' + splitted[0].split(",")[1];
                splitted.forEach(function (element) {
                    console.log(element.split(",")[2]);
                    if (element.split(",")[2] != null) {
                        var imagen = "";

                        if (element.split(",")[2] ==="Consumo desproporcionado de combustible") {
                            imagen = "images/failureIcon/jerrycan.png";
                        }

                        if (element.split(",")[2] ==="Mezcla de Aire/Combustible muy pobre") {
                            imagen = "images/failureIcon/pistonrojo.png";
                        }
                        if (element.split(",")[2] ==="Mezcla de Aire/Combustible muy rica") {
                            imagen = "images/failureIcon/pistonverde.png";
                        }
                        if (element.split(",")[2] ==="Sensor MAF sucio o averiado") {
                            imagen = "images/failureIcon/cpu.png";
                        }
                        if (element.split(",")[2] ==="Inyectores sucios o averiados") {
                            imagen = "images/failureIcon/syringe-needle.png";
                        }
                        if (element.split(",")[2] ==="Bobina Averiada") {
                            imagen = "images/failureIcon/fuse.png";
                        }
                        if (element.split(",")[2] ==="Bujías propensas a daños") {
                            imagen = "images/failureIcon/spark.png";
                        }
                        if (element.split(",")[2] ==="Vehículo propenso a recalentamiento") {
                            imagen = "images/failureIcon/temperature.png";
                        }
                        if (element.split(",")[2] ==="Radiador Averiado") {
                            imagen = "images/failureIcon/pulley.png";
                        }
                        if (element.split(",")[2] ==="Alternador defectuoso") {
                            imagen = "images/failureIcon/motor.png";
                        }
                        document.getElementById('tarjetaserror').innerHTML = document.getElementById('tarjetaserror').innerHTML + "<div class=\"col-lg-2\" style=\"margin-right:20px\">"
                                                                                                                                +   "<div class=\"card\">"
                                                                                                                                + "<img src=\"" + imagen + "\" width=\"240\" height=\"240\" style=\"width:100%; height:100%; background-color:grey\">"
                                                                                                                                +       "<div class=\"container\">"
                                                                                                                                + "<h4 style=\"width:100%\"><font size=\"1.5\"><b>" + element.split(",")[2] + "</b></font></h4>"
                                                                                                                                +       "</div>"
                                                                                                                                +   "</div>"
                                                                                                                                + "</div>";
                    }
                });
            } else {
                alert("Ha ocurrido un error, por favor vuelva a intentar");
            }
        }
    </script>
    <script>
        function cambiarDiv() {
            document.getElementById("rcorners").style.display = "block";
            if ($("#selectFalla").val() === "Consumo desproporcionado de combustible") {
                document.getElementById("consumodesproporcionado").style.display = "block";
                document.getElementById("acpobre").style.display = "none";
                document.getElementById("acrica").style.display = "none";
                document.getElementById("mafsucio").style.display = "none";
                document.getElementById("inyectoresaveriados").style.display = "none";
                document.getElementById("bobinaaveriada").style.display = "none";
                document.getElementById("bujiasaveriada").style.display = "none";
                document.getElementById("recalentamiento").style.display = "none";
                document.getElementById("radiadoraveriado").style.display = "none";
                document.getElementById("alternadoraveriado").style.display = "none";
            } else if ($("#selectFalla").val() === "Mezcla de Aire/Combustible muy pobre") {
                document.getElementById("consumodesproporcionado").style.display = "none";
                document.getElementById("acpobre").style.display = "block";
                document.getElementById("acrica").style.display = "none";
                document.getElementById("mafsucio").style.display = "none";
                document.getElementById("inyectoresaveriados").style.display = "none";
                document.getElementById("bobinaaveriada").style.display = "none";
                document.getElementById("bujiasaveriada").style.display = "none";
                document.getElementById("recalentamiento").style.display = "none";
                document.getElementById("radiadoraveriado").style.display = "none";
                document.getElementById("alternadoraveriado").style.display = "none";
            } else if ($("#selectFalla").val() === "Mezcla de Aire/Combustible muy rica") {
                document.getElementById("consumodesproporcionado").style.display = "none";
                document.getElementById("acpobre").style.display = "none";
                document.getElementById("acrica").style.display = "block";
                document.getElementById("mafsucio").style.display = "none";
                document.getElementById("inyectoresaveriados").style.display = "none";
                document.getElementById("bobinaaveriada").style.display = "none";
                document.getElementById("bujiasaveriada").style.display = "none";
                document.getElementById("recalentamiento").style.display = "none";
                document.getElementById("radiadoraveriado").style.display = "none";
                document.getElementById("alternadoraveriado").style.display = "none";
            } else if ($("#selectFalla").val() === "Sensor MAF sucio o averiado") {
                document.getElementById("consumodesproporcionado").style.display = "none";
                document.getElementById("acpobre").style.display = "none";
                document.getElementById("acrica").style.display = "none";
                document.getElementById("mafsucio").style.display = "block";
                document.getElementById("inyectoresaveriados").style.display = "none";
                document.getElementById("bobinaaveriada").style.display = "none";
                document.getElementById("bujiasaveriada").style.display = "none";
                document.getElementById("recalentamiento").style.display = "none";
                document.getElementById("radiadoraveriado").style.display = "none";
                document.getElementById("alternadoraveriado").style.display = "none";
            } else if ($("#selectFalla").val() === "Inyectores sucios o averiados") {
                document.getElementById("consumodesproporcionado").style.display = "none";
                document.getElementById("acpobre").style.display = "none";
                document.getElementById("acrica").style.display = "none";
                document.getElementById("mafsucio").style.display = "none";
                document.getElementById("inyectoresaveriados").style.display = "block";
                document.getElementById("bobinaaveriada").style.display = "none";
                document.getElementById("bujiasaveriada").style.display = "none";
                document.getElementById("recalentamiento").style.display = "none";
                document.getElementById("radiadoraveriado").style.display = "none";
                document.getElementById("alternadoraveriado").style.display = "none";
            } else if ($("#selectFalla").val() === "Bobina Averiada") {
                document.getElementById("consumodesproporcionado").style.display = "none";
                document.getElementById("acpobre").style.display = "none";
                document.getElementById("acrica").style.display = "none";
                document.getElementById("mafsucio").style.display = "none";
                document.getElementById("inyectoresaveriados").style.display = "none";
                document.getElementById("bobinaaveriada").style.display = "block";
                document.getElementById("bujiasaveriada").style.display = "none";
                document.getElementById("recalentamiento").style.display = "none";
                document.getElementById("radiadoraveriado").style.display = "none";
                document.getElementById("alternadoraveriado").style.display = "none";
            } else if ($("#selectFalla").val() === "Bujías propensas a daños") {
                document.getElementById("consumodesproporcionado").style.display = "none";
                document.getElementById("acpobre").style.display = "none";
                document.getElementById("acrica").style.display = "none";
                document.getElementById("mafsucio").style.display = "none";
                document.getElementById("inyectoresaveriados").style.display = "none";
                document.getElementById("bobinaaveriada").style.display = "none";
                document.getElementById("bujiasaveriada").style.display = "block";
                document.getElementById("recalentamiento").style.display = "none";
                document.getElementById("radiadoraveriado").style.display = "none";
                document.getElementById("alternadoraveriado").style.display = "none";
            } else if ($("#selectFalla").val() === "Vehículo propenso a recalentamiento") {
                document.getElementById("consumodesproporcionado").style.display = "none";
                document.getElementById("acpobre").style.display = "none";
                document.getElementById("acrica").style.display = "none";
                document.getElementById("mafsucio").style.display = "none";
                document.getElementById("inyectoresaveriados").style.display = "none";
                document.getElementById("bobinaaveriada").style.display = "none";
                document.getElementById("bujiasaveriada").style.display = "none";
                document.getElementById("recalentamiento").style.display = "block";
                document.getElementById("radiadoraveriado").style.display = "none";
                document.getElementById("alternadoraveriado").style.display = "none";
            } else if ($("#selectFalla").val() === "Radiador Averiado") {
                document.getElementById("consumodesproporcionado").style.display = "none";
                document.getElementById("acpobre").style.display = "none";
                document.getElementById("acrica").style.display = "none";
                document.getElementById("mafsucio").style.display = "none";
                document.getElementById("inyectoresaveriados").style.display = "none";
                document.getElementById("bobinaaveriada").style.display = "none";
                document.getElementById("bujiasaveriada").style.display = "none";
                document.getElementById("recalentamiento").style.display = "none";
                document.getElementById("radiadoraveriado").style.display = "block";
                document.getElementById("alternadoraveriado").style.display = "none";
            } else if ($("#selectFalla").val() === "Alternador defectuoso") {
                document.getElementById("consumodesproporcionado").style.display = "none";
                document.getElementById("acpobre").style.display = "none";
                document.getElementById("acrica").style.display = "none";
                document.getElementById("mafsucio").style.display = "none";
                document.getElementById("inyectoresaveriados").style.display = "none";
                document.getElementById("bobinaaveriada").style.display = "none";
                document.getElementById("bujiasaveriada").style.display = "none";
                document.getElementById("recalentamiento").style.display = "none";
                document.getElementById("radiadoraveriado").style.display = "none";
                document.getElementById("alternadoraveriado").style.display = "block";
            }
        }
    </script>
</body>
</html>
