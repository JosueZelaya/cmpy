<%-- 
    Document   : boot
    Created on : 24-may-2015, 8:01:11
    Author     : genaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html ng-app="cmpy">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="keywords" content="clasificados, anuncios, compras, ventas, vender, comprar, el salvador,
              olx, comprameya, $comprameya, dinero, carros, electronica, electrodomestigos, categorias, multimedia,
              celulares, laptop, pc, juegos, servicios, escritorio, muebles, lcd" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); } </script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/newIndex/zoomslider.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/newIndex/style.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/newIndex/component.css" />
        <script type="text/javascript" src="js/modernizr-2.6.2.min.js"></script>
        <!--/web-fonts-->
        <link href='//fonts.googleapis.com/css?family=Open+Sans:400,600,600italic,300,300italic,700,400italic' rel='stylesheet' type='text/css'>
        <link href='//fonts.googleapis.com/css?family=Wallpoet' rel='stylesheet' type='text/css'>
        <link href='//fonts.googleapis.com/css?family=Ubuntu:400,500,700,300' rel='stylesheet' type='text/css'>
        <!--//web-fonts-->

        <title>ComprameYa!</title>

        <!--<style> .ng-cloak { display: none !important; } </style>-->
        <link href="${pageContext.request.contextPath}/resources/images/favicon_trans.png" rel="icon" type="image/png">
        <style type="text/css">
            [ng\:cloak], [ng-cloak], .ng-cloak {
                display: none !important;
            }
            .angular-google-map-container { height: 400px; }
        </style>
        <base href="/">
    </head>
    <body ng-cloak class="ng-cloak" flow-prevent-drop>

        <!-- BANNER -->
        <div id="demo-1" data-zs-src='["images/2.jpg", "images/1.jpg", "images/3.jpg"]' data-zs-overlay="dots">
            <div class="demo-inner-content">
                <div class="header-top">
                    <!-- /header-left -->
                    <div class="header-left">
                        <!-- /sidebar -->
                        <div id="sidebar"> 
                            <h4 class="menu">Menu</h4>
                            <ul>
                                <li><a href="upcoming.html">New Cars <i class="glyphicon glyphicon-triangle-bottom"> </i> </a>
                                    <ul>
                                        <li><a href="find.html"><span>Find New Cars</span></a></li>
                                        <li><a href="upcoming.html"><span>Upcoming Cars</span></a></li>
                                        <li><a href="recommend.html"><span>Recommend Me A Car</span></a></li>
                                        <li><a href="upcoming.html"><span>New Launches</span></a></li>
                                        <li><a href="dealer.html"><span>Locate Dealer</span></a></li>
                                        <li class="last"><a href="price.html"><span>On Road Price</span></a></li>
                                    </ul>
                                </li>
                                <li><a href="compare.html">Compare Cars</a></li>
                                <li><a href="cars.html">Used Cars <i class="glyphicon glyphicon-triangle-bottom"> </i></a>
                                    <ul>
                                        <li><a href="used.html">Find Used cars</a></li>
                                        <li><a href="sell.html">Sell Your Car</a></li>
                                        <li><a href="cars.html"><span>Search Used Cars</span></a></li>
                                        <li class="last"><a href="valuation.html"><span>Used Car Valuation</span></a></li>
                                        <li><a href="dealer.html"><span>Locate Dealer</span></a></li>

                                    </ul>
                                </li>
                                <li><a href="sell.html">Sell Your Car</a></li>
                                <li><a href="news.html">News And Reviews</a></li>
                                <li><a href="dealer.html">Dealers And Services</a></li>
                                <li><a href="#">More <i class="glyphicon glyphicon-triangle-bottom"> </i> </a>
                                    <ul>
                                        <li><a href="loan.html"><span>Car Loan</span></a></li>
                                        <li><a href="codes.html"><span>Short Codes</span></a></li>
                                        <li><a href="accessories.html"><span>Car Accessories</span></a></li>
                                        <li><a href="tips.html"><span>Tips and Advices</span></a></li>
                                        <li class="last"><a href="help.html"><span>Privacy Policy</span></a></li>

                                    </ul>
                                </li>
                                <li><a href="insurance.html">Insurance</a></li>
                                <li><a href="app.html">Catchy Carz app</a><li>
                            </ul>
                            <div id="sidebar-btn">
                                <span></span>
                                <span></span>
                                <span></span>
                            </div>
                        </div>

                        <script>
                            var sidebarbtn = document.getElementById('sidebar-btn');
                            var sidebar = document.getElementById('sidebar');
                            sidebarbtn.addEventListener('click', function () {
                                if (sidebar.classList.contains('visible')) {
                                    sidebar.classList.remove('visible');
                                } else {
                                    sidebar.className = 'visible';
                                }
                            });
                        </script>
                        <!-- //sidebar -->
                        <div class="tag"><a href="#" data-toggle="modal" data-target="#myModal1"><span class="glyphicon glyphicon-tag"></span> Discounts On New Cars » </a></div>
                        <div class="tag"><a href="#" data-toggle="modal" data-target="#myModal2"><span class="glyphicon glyphicon-log-in"></span> Sign Up</a></div>
                    </div>
                    <!-- //header-left -->
                    <div class="search-box">
                        <div id="sb-search" class="sb-search">
                            <form action="#" method="post">
                                <input name="name" class="sb-search-input" placeholder="Enter your search term..." type="search" id="search">
                                <input class="sb-search-submit" type="submit" value="">
                                <span class="sb-icon-search"> </span>
                            </form>
                        </div>
                        <!-- search-scripts -->
                        <script src="js/classie.js"></script>
                        <script src="js/uisearch.js"></script>
                        <script>
                            new UISearch(document.getElementById('sb-search'));
                        </script>
                        <!-- //search-scripts -->
                        <ul>

                            <li>
                                <a href="#" data-toggle="modal" data-target="#myModal4"><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>Select Your Location</a></li>

                            <li><button id="showRight" class="navig">Login </button>
                                <div class="cbp-spmenu-push">
                                    <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right" id="cbp-spmenu-s2">
                                        <h3>Login</h3>
                                        <div class="login-inner">
                                            <div class="login-top">
                                                <form action="#" method="post">
                                                    <input type="text" name="email" class="email" placeholder="Email" required=""/>
                                                    <input type="password" name="password" class="password" placeholder="Password" required=""/>	
                                                    <input type="checkbox" id="brand" value="">
                                                    <label for="brand"><span></span> Remember me</label>
                                                </form>
                                                <div class="login-bottom">
                                                    <ul>
                                                        <li>
                                                            <a href="#">Forgot password?</a>
                                                        </li>
                                                        <li>
                                                            <form action="#" method="post">
                                                                <input type="submit" value="LOGIN"/>
                                                            </form>
                                                        </li>
                                                    </ul>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="clearfix"></div>

                                            </div>
                                            <div class="social-icons">
                                                <ul> 
                                                    <li><a href="#"><span class="icons"></span><span class="text">Facebook</span></a></li>
                                                    <li class="twt"><a href="#"><span class="icons"></span><span class="text">Twitter</span></a></li>
                                                    <li class="ggp"><a href="#"><span class="icons"></span><span class="text">Google+</span></a></li>
                                                </ul> 
                                            </div>		
                                        </div> 
                                    </nav>
                                </div> 
                                <script src="js/classie2.js"></script>
                                <script>
                            var menuRight = document.getElementById('cbp-spmenu-s2'),
                                    showRight = document.getElementById('showRight'),
                                    body = document.body;

                            showRight.onclick = function () {
                                classie.toggle(this, 'active');
                                classie.toggle(menuRight, 'cbp-spmenu-open');
                                disableOther('showRight');
                            };

                            function disableOther(button) {
                                if (button !== 'showRight') {
                                    classie.toggle(showRight, 'disabled');
                                }
                            }
                                </script>
                                <!--Navigation from Right To Left-->
                            </li>
                        </ul>

                    </div>

                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
                <!--banner-info-->
                <div class="banner-info">
                    <h1><a href="index.html">Comprame <span class="logo-sub">Ya</span> </a></h1>
                    <h2><span>COMPRA Y VENDE </span> <span> LO QUE QUIERAS! </span></h2>
                    <p>Busca – Encuentra – Compra!</p>

                    <div class="row">
                        <div class="col-lg-4">

                        </div>
                        <div class="col-lg-4">
                            <form class="navbar-form navbar-left" style="width: 100%" role="search">

                                <div class="input-group">
                                    <input ng-keyup="$event.keyCode == 13 && buscar(terminoBusqueda)" type="text" class="form-control input-lg" placeholder="Buscar producto..." ng-model="terminoBusqueda">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default btn-lg" type="button" ui-sref-opts="{reload:true}" ui-sref="home.busqueda({terminoBusqueda: terminoBusqueda})">BuscarYa!</button>
                                    </span>
                                </div>                    
                            </form>
                        </div>
                        <div class="col-lg-4">

                        </div>
                    </div>


                </div>
                <!--//banner-info-->	
            </div>
        </div>
        <!-- //BANNER -->

        <!-- //sign-up-->
        <div class="modal ab fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog sign" role="document">
                <div class="modal-content about">
                    <div class="modal-header one">
                        <button type="button" class="close sg" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>	
                        <div class="discount one">
                            <h3>Sign Up</h3>

                        </div>							
                    </div>
                    <div class="modal-body about">
                        <div class="login-top sign-top one">
                            <form action="#" method="post">
                                <input type="text" name="name" class="name active" placeholder="Your Name" required="">
                                <input type="text" name="email "class="email" placeholder="Email" required="">
                                <input type="password" name="password" class="password" placeholder="Password" required="">		
                                <input type="checkbox" id="brand1" value="">
                                <label for="brand1"><span></span> Remember me?</label>
                                <div class="login-bottom one">
                                    <ul>
                                        <li>
                                            <a href="#">Forgot password?</a>
                                        </li>
                                        <li>

                                            <input type="submit" value="SIGN UP">

                                        </li>
                                        <div class="clearfix"></div>
                                    </ul>
                                </div>	
                            </form>

                        </div>


                    </div>
                    <div class="social-icons">
                        <ul> 
                            <li><a href="#"><span class="icons"></span><span class="text">Facebook</span></a></li>
                            <li class="twt"><a href="#"><span class="icons"></span><span class="text">Twitter</span></a></li>
                            <li class="ggp"><a href="#"><span class="icons"></span><span class="text">Google+</span></a></li>
                        </ul> 
                    </div>

                </div>
            </div>
        </div>
        <!-- //sign-up-->


        <!--/featured_section-->
        <div class="featured_section_w3l">
            <div class="container">
                <h3 class="tittle">FEATURED CARS</h3>
                <div class="inner_tabs">
                    <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#expeditions" id="expeditions-tab" role="tab" data-toggle="tab" aria-controls="expeditions" aria-expanded="true">UpComing cars</a></li>
                            <li role="presentation"><a href="#tours" role="tab" id="tours-tab" data-toggle="tab" aria-controls="tours">Popular</a></li>
                            <li role="presentation"><a href="#tree" role="tab" id="tree-tab" data-toggle="tab" aria-controls="tree">Just Launched</a></li>

                        </ul>
                        <div id="myTabContent" class="tab-content" ng-controller='anunciosController'>
                            <div role="tabpanel" class="tab-pane fade in active" id="expeditions" aria-labelledby="expeditions-tab">
                                <div class="section__content clearfix">
                                    <!-- /card1 -->
                                    <div class="card effect__hover" style="height: 500px" ng-repeat="publicacion in publicaciones" ng-if="publicaciones">                                        
                                        <div class="card__front">
                                            <span class="card__text">
                                                <div class="img-grid">
                                                    <img ng-if="publicacion.tipo === 'GRATIS'" ng-src="/images/getThumbnail/{{publicacion.imgUrl}}" alt="imagen aqui">
                                                    <div class="car_description">
                                                        <h4><a href="single.html">{{publicacion.titulo}}</a></h4>
                                                        <div class="price"><span class="fa fa-rupee"></span><span class="font25">$ {{publicacion.precio}}</span></div>
                                                        <p>Precio</p>
                                                        <div class="date">Mar 2017</div>
                                                        <p>{{publicacion.descripcion.slice(0, 75)}}...</p>  
                                                    </div>

                                                </div>
                                            </span>
                                        </div>
                                        <div class="card__back">
                                            <div class="row">                                
                                                <div ng-if="publicacion.tipo !== 'EXTERNA'" class="col-lg-7 col-md-4 col-sm-2">
                                                    &nbsp;&nbsp;{{publicacion.usuario.persona.nombre}}
                                                    <img class="user-image" ng-if="!publicacion.usuario.rutaImagen" height="28px" width="28px" ng-src="/resources/images/default.jpg" alt="..." uib-tooltip="{{publicacion.usuario.persona.nombre}}" tooltip-placement="top"/>
                                                    <img class="user-image" ng-if="publicacion.usuario.signInProvider === null && publicacion.usuario.rutaImagen" height="28px" width="28px" ng-src="/images/getThumbnail/{{publicacion.usuario.rutaImagen}}" alt="..." uib-tooltip="{{publicacion.usuario.persona.nombre}}" tooltip-placement="top"/>
                                                    <img class="user-image" ng-if="publicacion.usuario.signInProvider !== null && publicacion.usuario.rutaImagen" height="28px" width="28px" ng-src="{{publicacion.usuario.rutaImagen}}" alt="..."  uib-tooltip="{{publicacion.usuario.persona.nombre}}" tooltip-placement="top"/>
                                                </div>
                                                <div ng-if="publicacion.tipo === 'EXTERNA'" class="col-lg-7 col-md-4 col-sm-2">
                                                    {{publicacion.usuario.persona.nombre}}
                                                    <img class="user-image" height="28px" width="28px" ng-src="/resources/images/logo.jpg" alt="..."  uib-tooltip="{{publicacion.link}}" tooltip-placement="top"/>
                                                </div>
                                                <div class="rating col-lg-5 col-md-8 col-sm-10">
                                                    <i class="price-text-color glyphicon glyphicon-star" ng-repeat="estrella in getNumbers(publicacion.usuario.puntaje) track by $index"></i>
                                                    <i class="price-text-color glyphicon glyphicon-star-empty" ng-repeat="estrella in getNumbers(5 - publicacion.usuario.puntaje) track by $index"></i>
                                                </div>
                                            </div>
                                            <span class="card__text">
                                                <div class="login-inner2">
                                                    <h4>Be the first to know</h4>
                                                    <div class="login-top sign-top">
                                                        <form>
                                                            <input type="text" name="name" class="name active" placeholder="Name" required=""/>
                                                            <input type="text" name="email" class="email" placeholder="Email" required=""/>
                                                            <input type="text" name="phone" class="phone" placeholder="Phone" required=""/>
                                                            <div class="section_drop">
                                                                <select id="country1" onchange="change_country(this.value)" class="frm-field required">
                                                                    <option value="null"> Select City</option>
                                                                    <option value="city">Amsterdam</option>
                                                                    <option value="city">Bahrain</option>
                                                                    <option value="city">Cannes</option>
                                                                    <option value="city">Dublin</option>
                                                                    <option value="city">Edinburgh</option>
                                                                    <option value="city">Florence</option>
                                                                    <option value="city">Georgia</option>
                                                                    <option value="city">Hungary</option>
                                                                    <option value="city">Hong Kong</option>
                                                                    <option value="city">Johannesburg</option>
                                                                    <option value="city">Kiev</option>
                                                                    <option value="city">London</option>
                                                                    <option value="city">Others...</option>
                                                                </select>
                                                            </div>	
                                                            <input type="submit" value="Done">

                                                            </div>
                                                        </form>

                                                    </div>
                                            </span>
                                        </div>
                                    </div>
                                    <!-- //card1 -->                                    
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tours" aria-labelledby="tours-tab">
                                <div class="section__content clearfix">
                                    <!-- /card1 -->
                                    <div class="card effect__hover">
                                        <div class="card__front">
                                            <span class="card__text">
                                                <div class="img-grid">
                                                    <img src="images/f4.jpg" alt="Catchy Carz">
                                                    <div class="car_description">
                                                        <h4><a href="single.html"> Ford Mustang GT 350</a></h4>
                                                        <div class="price"><span class="fa fa-rupee"></span><span class="font25">$ 8000 - $ 11000</span></div>
                                                        <p>Estimated Price</p>
                                                        <div class="date">June 2016</div>
                                                        <p>Expected Launch</p>
                                                    </div>

                                                </div>
                                            </span>
                                        </div>
                                        <div class="card__back">
                                            <span class="card__text">
                                                <div class="login-inner2">
                                                    <h4><img src="images/rupee.png" alt="Catchy Carz"> Check On-Road Price</h4>
                                                    <div class="login-top sign-top">
                                                        <form action="#" method="post">
                                                            <input type="text" name="name" class="name active" placeholder="Name" required=""/>
                                                            <input type="text" name="email" class="email" placeholder="Email" required=""/>
                                                            <div class="section_drop">
                                                                <select id="country1" onchange="change_country(this.value)" class="frm-field required">
                                                                    <option value="null"> Select City</option>
                                                                    <option value="null"> Select City</option>
                                                                    <option value="city">Amsterdam</option>
                                                                    <option value="city">Bahrain</option>
                                                                    <option value="city">Cannes</option>
                                                                    <option value="city">Dublin</option>
                                                                    <option value="city">Edinburgh</option>
                                                                    <option value="city">Florence</option>
                                                                    <option value="city">Georgia</option>
                                                                    <option value="city">Hungary</option>
                                                                    <option value="city">Hong Kong</option>
                                                                    <option value="city">Johannesburg</option>
                                                                    <option value="city">Kiev</option>
                                                                    <option value="city">London</option>
                                                                    <option value="city">Others...</option>
                                                                </select>
                                                            </div>
                                                            <input type="password" name="password" class="password" placeholder="Password" required=""/>		
                                                            <input type="submit" value="Check Now">


                                                        </form>
                                                    </div>

                                                </div>
                                            </span>
                                        </div>
                                    </div>
                                    <!-- //card1 -->
                                    <!-- /card2 -->
                                    <div class="card effect__hover">
                                        <div class="card__front">
                                            <span class="card__text">
                                                <div class="img-grid">
                                                    <img src="images/f5.jpeg" alt="Catchy Carz">
                                                    <div class="car_description">
                                                        <h4><a href="#">Ferrari F430</a></h4>
                                                        <div class="price"><span class="fa fa-rupee"></span><span class="font25">$ 7000 - $ 13000</span></div>
                                                        <p>Estimated Price</p>
                                                        <div class="date">July 2016</div>
                                                        <p>Expected Launch</p>
                                                    </div>

                                                </div>
                                            </span>
                                        </div>
                                        <div class="card__back">
                                            <span class="card__text">
                                                <div class="login-inner2">
                                                    <h4><img src="images/rupee.png" alt="Catchy Carz">  Check On-Road Price</h4>
                                                    <div class="login-top sign-top">
                                                        <form action="#" method="post">
                                                            <input type="text" name="name" class="name active" placeholder="Name" required=""/>
                                                            <input type="text" name="email" class="email" placeholder="Email" required=""/>
                                                            <div class="section_drop">
                                                                <select id="country1" onchange="change_country(this.value)" class="frm-field required">
                                                                    <option value="null"> Select City</option>
                                                                    <option value="null"> Select City</option>
                                                                    <option value="city">Amsterdam</option>
                                                                    <option value="city">Bahrain</option>
                                                                    <option value="city">Cannes</option>
                                                                    <option value="city">Dublin</option>
                                                                    <option value="city">Edinburgh</option>
                                                                    <option value="city">Florence</option>
                                                                    <option value="city">Georgia</option>
                                                                    <option value="city">Hungary</option>
                                                                    <option value="city">Hong Kong</option>
                                                                    <option value="city">Johannesburg</option>
                                                                    <option value="city">Kiev</option>
                                                                    <option value="city">London</option>
                                                                    <option value="city">Others...</option>
                                                                </select>
                                                            </div>
                                                            <input type="password" name="password" class="password" placeholder="Password" required=""/>		
                                                            <input type="submit" value="Check Now">


                                                        </form>
                                                    </div>
                                                </div>
                                            </span>
                                        </div>
                                    </div>
                                    <!-- //card2 -->
                                    <!-- /card3 -->
                                    <div class="card effect__hover">
                                        <div class="card__front">
                                            <span class="card__text">
                                                <div class="img-grid">
                                                    <img src="images/f6.jpeg" alt="Catchy Carz">
                                                    <div class="car_description">
                                                        <h4><a href="single.html"> Mercedes-Benz C250 CDI</a></h4>
                                                        <div class="price"><span class="fa fa-rupee"></span><span class="font25">$ 5000 - $ 10000</span></div>
                                                        <p>Estimated Price</p>
                                                        <div class="date">Aug 2016</div>
                                                        <p>Expected Launch</p>
                                                    </div>

                                                </div>
                                            </span>
                                        </div>
                                        <div class="card__back">
                                            <span class="card__text">
                                                <div class="login-inner2">
                                                    <h4><img src="images/rupee.png" alt="Catchy Carz">  Check On-Road Price</h4>
                                                    <div class="login-top sign-top">
                                                        <form action="#" method="post">
                                                            <input type="text" name="name" class="name active" placeholder="Name" required=""/>
                                                            <input type="text" name="email" class="email" placeholder="Email" required=""/>
                                                            <div class="section_drop">
                                                                <select id="country1" onchange="change_country(this.value)" class="frm-field required">
                                                                    <option value="null"> Select City</option>
                                                                    <option value="city">Amsterdam</option>
                                                                    <option value="city">Bahrain</option>
                                                                    <option value="city">Cannes</option>
                                                                    <option value="city">Dublin</option>
                                                                    <option value="city">Edinburgh</option>
                                                                    <option value="city">Florence</option>
                                                                    <option value="city">Georgia</option>
                                                                    <option value="city">Hungary</option>
                                                                    <option value="city">Hong Kong</option>
                                                                    <option value="city">Johannesburg</option>
                                                                    <option value="city">Kiev</option>
                                                                    <option value="city">London</option>
                                                                    <option value="city">Others...</option>
                                                                </select>
                                                            </div>
                                                            <input type="password" name="password" class="password" placeholder="Password" required=""/>		
                                                            <input type="submit" value="Check Now">


                                                        </form>
                                                    </div>
                                                </div>
                                            </span>
                                        </div>
                                    </div>
                                    <!-- //card3 -->
                                </div>

                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="tree" aria-labelledby="tree-tab">

                                <div class="section__content clearfix">
                                    <!-- /card1 -->
                                    <div class="card effect__hover">
                                        <div class="card__front">
                                            <span class="card__text">
                                                <div class="img-grid">
                                                    <img src="images/f7.jpg" alt="Catchy Carz">
                                                    <div class="car_description">
                                                        <h4><a href="single.html">BMW M4</a></h4>
                                                        <div class="price"><span class="fa fa-rupee"></span><span class="font25">$ 6000 - $ 11000</span></div>
                                                        <p>Estimated Price</p>
                                                        <div class="date">Mar 2017</div>
                                                        <p>Expected Launch</p>
                                                    </div>

                                                </div>
                                            </span>
                                        </div>
                                        <div class="card__back">
                                            <span class="card__text">
                                                <div class="login-inner2">
                                                    <h4><img src="images/rupee.png" alt="Catchy Carz"> Check On-Road Price</h4>
                                                    <div class="login-top sign-top">
                                                        <form action="#" method="post">
                                                            <input type="text" name="name" class="name active" placeholder="Name" required=""/>
                                                            <input type="text" name="email" class="email" placeholder="Email" required=""/>
                                                            <div class="section_drop">
                                                                <select id="country1" onchange="change_country(this.value)" class="frm-field required">
                                                                    <option value="null"> Select City</option>
                                                                    <option value="city">Amsterdam</option>
                                                                    <option value="city">Bahrain</option>
                                                                    <option value="city">Cannes</option>
                                                                    <option value="city">Dublin</option>
                                                                    <option value="city">Edinburgh</option>
                                                                    <option value="city">Florence</option>
                                                                    <option value="city">Georgia</option>
                                                                    <option value="city">Hungary</option>
                                                                    <option value="city">Hong Kong</option>
                                                                    <option value="city">Johannesburg</option>
                                                                    <option value="city">Kiev</option>
                                                                    <option value="city">London</option>
                                                                    <option value="city">Others...</option>
                                                                </select>
                                                            </div>
                                                            <input type="password" name="password" class="password" placeholder="Password" required=""/>		
                                                            <input type="submit" value="Check Now">


                                                        </form>
                                                    </div>
                                                </div>
                                            </span>
                                        </div>
                                    </div>
                                    <!-- //card1 -->
                                    <!-- /card2 -->
                                    <div class="card effect__hover">
                                        <div class="card__front">
                                            <span class="card__text">
                                                <div class="img-grid">
                                                    <img src="images/f8.jpg" alt="Catchy Carz">
                                                    <div class="car_description">
                                                        <h4><a href="single.html"> BMW X4 M Sport</a></h4>
                                                        <div class="price"><span class="fa fa-rupee"></span><span class="font25">$ 7000 - $ 12000</span></div>
                                                        <p>Estimated Price</p>
                                                        <div class="date">Mar 2017</div>
                                                        <p>Expected Launch</p>
                                                    </div>

                                                </div>
                                            </span>
                                        </div>
                                        <div class="card__back">
                                            <span class="card__text">
                                                <div class="login-inner2">
                                                    <h4><img src="images/rupee.png" alt="Catchy Carz">  Check On-Road Price</h4>
                                                    <div class="login-top sign-top">
                                                        <form action="#" method="post">
                                                            <input type="text" name="name" class="name active" placeholder="Name" required=""/>
                                                            <input type="text" name="email" class="email" placeholder="Email" required=""/>
                                                            <div class="section_drop">
                                                                <select id="country1" onchange="change_country(this.value)" class="frm-field required">
                                                                    <option value="null"> Select City</option>
                                                                    <option value="city">Amsterdam</option>
                                                                    <option value="city">Bahrain</option>
                                                                    <option value="city">Cannes</option>
                                                                    <option value="city">Dublin</option>
                                                                    <option value="city">Edinburgh</option>
                                                                    <option value="city">Florence</option>
                                                                    <option value="city">Georgia</option>
                                                                    <option value="city">Hungary</option>
                                                                    <option value="city">Hong Kong</option>
                                                                    <option value="city">Johannesburg</option>
                                                                    <option value="city">Kiev</option>
                                                                    <option value="city">London</option>
                                                                    <option value="city">Others...</option>
                                                                </select>
                                                            </div>
                                                            <input type="password" name="password" class="password" placeholder="Password" required=""/>		
                                                            <input type="submit" value="Check Now">


                                                        </form>
                                                    </div>
                                                </div>
                                            </span>
                                        </div>
                                    </div>
                                    <!-- //card2 -->
                                    <!-- /card3 -->
                                    <div class="card effect__hover">
                                        <div class="card__front">
                                            <span class="card__text">
                                                <div class="img-grid">
                                                    <img src="images/f9.jpg" alt="Catchy Carz">
                                                    <div class="car_description">
                                                        <h4><a href="single.html">BMW M3 </a></h4>
                                                        <div class="price"><span class="fa fa-rupee"></span><span class="font25">&nbsp;$ 5000 - $ 10000</span></div>
                                                        <p>Estimated Price</p>
                                                        <div class="date">Mar 2017</div>
                                                        <p>Expected Launch</p>
                                                    </div>

                                                </div>
                                            </span>
                                        </div>
                                        <div class="card__back">
                                            <span class="card__text">
                                                <div class="login-inner2">
                                                    <h4><img src="images/rupee.png" alt="Catchy Carz">  Check On-Road Price</h4>
                                                    <div class="login-top sign-top">
                                                        <form action="#" method="post">
                                                            <input type="text" name="name" class="name active" placeholder="Name" required=""/>
                                                            <input type="text" name="email" class="email" placeholder="Email" required=""/>
                                                            <div class="section_drop">
                                                                <select id="country1" onchange="change_country(this.value)" class="frm-field required">
                                                                    <option value="null"> Select City</option>
                                                                    <option value="city">Amsterdam</option>
                                                                    <option value="city">Bahrain</option>
                                                                    <option value="city">Cannes</option>
                                                                    <option value="city">Dublin</option>
                                                                    <option value="city">Edinburgh</option>
                                                                    <option value="city">Florence</option>
                                                                    <option value="city">Georgia</option>
                                                                    <option value="city">Hungary</option>
                                                                    <option value="city">Hong Kong</option>
                                                                    <option value="city">Johannesburg</option>
                                                                    <option value="city">Kiev</option>
                                                                    <option value="city">London</option>
                                                                    <option value="city">Others...</option>
                                                                </select>
                                                            </div>
                                                            <input type="password" name="password" class="password" placeholder="Password" required=""/>		
                                                            <input type="submit" value="Check Now">


                                                        </form>
                                                    </div>
                                                </div>
                                            </span>
                                        </div>
                                    </div>
                                    <!-- //card3 -->
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--//featured_section-->



        <!-- PARA TRABAJAR EN DESARROLLO USAR ESTOS SCRIPTS -->
<!--        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/blueimp-load-image/js/load-image.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.min.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-sanitize/angular-sanitize.min.js"></script> 
        <script type='text/javascript' src='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.js'></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-animate/angular-animate.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/lodash/dist/lodash.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/underscore/underscore-min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-simple-logger/dist/angular-simple-logger.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-google-maps/dist/angular-google-maps.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-ui-router/release/angular-ui-router.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/ng-flow/dist/ng-flow-standalone.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-swipe/dist/angular-swipe.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/matchmedia/matchMedia.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/matchmedia-ng/matchmedia-ng.js"></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-confirm-modal/angular-confirm.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/sockjs/sockjs.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/stomp-websocket/lib/stomp.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.tpls.min.js"></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/autenticacion.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/autenticacionController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/changePassController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/resetPassController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/autenticacion/autenticacionService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notificacion/notificacion.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notificacion/notificacionService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notificacion/PushNotificationService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/notificacion/notificacionController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/categorias/categorias.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/categorias/catService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/categorias/catController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menuPrincipal/menuPrincipal.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menuPrincipal/menuPrincipalController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/menuPrincipal/busquedaService.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/anuncios.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/anunciosService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/comentariosService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/recursoService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/tiendaService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/anunciosController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/misPublicacionesController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/publicacionesFiltradasController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/busquedaController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/venderController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/empresasController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/productoController.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/anuncios/ubicacionesController.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/maps.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/mapService.js" ></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/maps/mapControllers.js" ></script> 
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/utils.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/modalController.js" ></script>        
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/loadingDirective.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/utils/utilsService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mensajes/mensajes.module.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mensajes/mensajesService.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mensajes/mensajesController.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cmpy/cmpy.module.js" ></script>
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.min.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/index/index.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/commentbox.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/index/publicaciones.css" rel="stylesheet">-->


        <!-- PARA PASAR A PRODUCCIÓN USAR ESTOS SCRIPTS Y COMENTAR LOS ANTERIORES -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/min/app.min.js" ></script>
        <link href="${pageContext.request.contextPath}/resources/min/css/style.min.css" rel="stylesheet" type="text/css" media='all'>
        <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap-css/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-carousel-3d/dist/carousel-3d.min.css" rel="stylesheet" type="text/css" />
        <link href='${pageContext.request.contextPath}/resources/bower_components/angular-loading-bar/build/loading-bar.min.css' rel='stylesheet' type='text/css' media='all' />
        <link href="${pageContext.request.contextPath}/resources/bower_components/angular-toastr/dist/angular-toastr.min.css" rel="stylesheet" type="text/css" />

    </body>
</html>

<script>
                                                                    function openNav() {
                                                                        document.getElementById("mySidenav").style.width = "250px";
                                                                    }

                                                                    function closeNav() {
                                                                        document.getElementById("mySidenav").style.width = "0";
                                                                    }
</script>        

