<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mão na roda - Suas tags</title>
		<link rel="icon" href="icone.ico" type="image/x-icon">
        <!-- Vendor CSS -->
        <link href="vendors/bower_components/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet">
        <link href="vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
        <link href="vendors/bower_components/sweetalert/dist/sweetalert.css" rel="stylesheet">
        <link href="vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" rel="stylesheet">
        <link href="vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet">
        
        <link href="../vendors/bower_components/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet">
        <link href="../vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
        <link href="../vendors/bower_components/sweetalert/dist/sweetalert.css" rel="stylesheet">
        <link href="../vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" rel="stylesheet">
        <link href="../vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet">

        <!-- CSS -->
        <link href="css/app_1.min.css" rel="stylesheet">
        <link href="css/app_2.min.css" rel="stylesheet">
        
        <link href="../css/app_1.min.css" rel="stylesheet">
        <link href="../css/app_2.min.css" rel="stylesheet">

 <script> 
			function enviar_formulario(){ 
				document.forms["form"].submit();
			} 
</script>
    </head>
    <body>
    <c:if test="${empty sessionScope.usuario}">
    	<meta http-equiv='refresh' content='0, url=/MaoNaRoda/index'>
     </c:if>
        <header id="header" class="clearfix" data-ma-theme="teal">
            <ul class="h-inner">
                <li class="hi-trigger ma-trigger" data-ma-action="sidebar-open" data-ma-target="#sidebar">
                    <div class="line-wrap">
                        <div class="line top"></div>
                        <div class="line center"></div>
                        <div class="line bottom"></div>
                    </div>
                </li>

                <li class="hi-logo hidden-xs">
                    <a href="/MaoNaRoda/index">Mão na roda</a>
                </li>
				<li class="pull-right">
            <ul class="hi-menu">

                <li data-ma-action="search-open">
                    <a href=""><i class="him-icon zmdi zmdi-search"></i></a>
                </li>
                <c:if test="${not empty sessionScope.usuario}">
      		<li class="pull-right">
				<ul class="hi-menu">
						<jsp:include page='gerenciamensagem.jsp'></jsp:include>
				</ul>
			</li>
			</c:if>
			</ul>
			</li>
                
            </ul>
            <!-- Top Search Content -->
                        <div class="h-search-wrap" style='height:85px;'>
                <div class="hsw-inner">
                    <i class="hsw-close zmdi zmdi-arrow-left" data-ma-action="search-close"></i>
                    <form method='get' action='/MaoNaRoda/pesquisa'>
                    <input type="hidden" name='acao' value='pesquisa'>
                    	<input type="text" name='termo'><br>
					  Filtrar por: 
					  <input type='checkbox' name='filtro' value='carros'>Carros
					  <input type='checkbox' name='filtro' value='vans'>Vans
					  <input type='checkbox' name='filtro' value='manha'>Manhã
					  <input type='checkbox' name='filtro' value='tarde'>Tarde
					  <input type='checkbox' name='filtro' value='noite'>Noite
					  <input type='checkbox' name='filtro' value='origem'>Origem
					  <input type='checkbox' name='filtro' value='destino'>Destino
					  <input type='checkbox' name='filtro' value='tipooferta'>Grátis
					  <input type="hidden" name='filtro' value=''>
					<select name='ordenacao' id='ordenacao' style='margin-left: 6.5%'>
					  <option name='vazio'>Ordenar por</option>
					  <option name="reputacao" value='reputacao'>Reputação</option>
					</select>
                    </form>
                </div>
            </div>
        </header>

        <section id="main">
            <aside id="sidebar" class="sidebar c-overflow">
            <c:if test="${not empty sessionScope.usuario}">
                <div class="s-profile">
                    <a href="" data-ma-action="profile-menu-toggle">
                        <div class="sp-pic">
                            <img src="${usuario.foto}" alt="">
                        </div>

                        <div class="sp-info">
                            ${usuario.nome}
                            <i class="zmdi zmdi-caret-down"></i>
                        </div>
                    </a>

                    <ul class="main-menu">
                        <li>
                            <a href="/MaoNaRoda/gerenciamento/perfil"><i class="zmdi zmdi-account"></i> Meu perfil</a>
                        </li>
                        <li class=''>
                            <a href="/MaoNaRoda/cadastro/oferta"><i class="zmdi zmdi-car"></i> Cadastro de Oferta</a>
                        </li>
                        <li>
                            <a href="/MaoNaRoda/login?acao=logout"><i class="zmdi zmdi-time-restore"></i> Sair</a>
                        </li>
                    </ul>
                </div>
				</c:if>
				<c:if test="${not empty sessionScope.usuario}">
                <ul class="main-menu">
                    <li class="">
                        <a href="/MaoNaRoda/index"><i class="zmdi zmdi-home"></i> Página inicial</a>
                    </li>
                    <li class="">
                        <a href="/MaoNaRoda/perfil/meusfavoritos"><i class="zmdi zmdi-view-list"></i> Meus favoritos</a>
                    </li>                         
                    <li class="">
                        <a href="/MaoNaRoda/index/minhasofertas"><i class="zmdi zmdi-chart"></i> Minhas ofertas</a>
                    </li> 
                </ul>
                </c:if>
               <c:if test="${empty sessionScope.usuario}">
            <ul class="main-menu">
                    <li class="">
                        <a href="/MaoNaRoda/login"><i class="zmdi zmdi-square-right"></i>Entre já</a>
                    </li>        
                </ul>
           		</c:if>
            </aside>

			 <section id="main">

           

            <section id="content">
                <div class="container container-alt">

                    <div class="block-header">
                        <h2>${editarOferta.titulo}
                        </h2>
                    </div>

                    <div class="card" id="profile-main">
                        <div class="pm-overview c-overflow">

                            <div class="pmo-pic">
                                <div class="p-relative">
                                    <a href="">
                                        <img class="img-responsive" src="../${editarOferta.foto}" alt="">
                                    </a>

                                    <div class="dropdown pmop-message">
                                        <a data-toggle="dropdown" href="" class="btn bgm-white btn-float z-depth-1">
                                            <i class="zmdi zmdi-comment-text-alt"></i>
                                        </a>

                                        <div class="dropdown-menu">
                                            <textarea placeholder="Digite sua mensagem"></textarea>

                                            <button class="btn bgm-green btn-float"><i class="zmdi zmdi-mail-send"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="pmo-block pmo-contact hidden-xs">
                                <h2>Contato</h2>

                                <ul>
									<c:if test="${not empty sessionScope.usuario.telefonecelular}">
									<li><i class="zmdi zmdi-phone"></i> ${usuario.telefonecelular}</li>
									</c:if>
									<c:if test="${not empty sessionScope.usuario.telefonefixo}">
									<li><i class="zmdi zmdi-phone"></i> ${usuario.telefonefixo}</li>
									</c:if>
			
                                    <li><i class="zmdi zmdi-email"></i> ${usuario.email}</li>
                                </ul>
                            </div>
                        </div><div class="card" id="profile-main">
                        <div class="pm-overview c-overflow">

                            <div class="pmo-pic">
                                <div class="p-relative">
                                    <a href="">
                                        <img class="img-responsive" src="${usuario.foto}" alt="">
                                    </a>

                                    <div class="dropdown pmop-message">
                                        <a data-toggle="dropdown" href="" class="btn bgm-white btn-float z-depth-1">
                                            <i class="zmdi zmdi-comment-text-alt"></i>
                                        </a>

                                        <div class="dropdown-menu">
                                            <textarea placeholder="Digite sua mensagem"></textarea>

                                            <button class="btn bgm-green btn-float"><i class="zmdi zmdi-mail-send"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="pmo-block pmo-contact hidden-xs">
                                <h2>Contato</h2>

                                <ul>
									<c:if test="${not empty sessionScope.usuario.telefonecelular}">
									<li><i class="zmdi zmdi-phone"></i> ${usuario.telefonecelular}</li>
									</c:if>
									<c:if test="${not empty sessionScope.usuario.telefonefixo}">
									<li><i class="zmdi zmdi-phone"></i> ${usuario.telefonefixo}</li>
									</c:if>
			
                                    <li><i class="zmdi zmdi-email"></i> ${usuario.email}</li>
                                </ul>
                            </div>
                        </div>
								<form method='post' name='form' action='/MaoNaRoda/cadastro'>
											<input type='hidden' name='acao' value='editoferta'>
											<input type='hidden' name='id' value='${oferta}'>			
										</form>
                        <div class="pm-body clearfix">
                            <ul class="tab-nav tn-justified">
                                <li class=""><a href="javascript:enviar_formulario();">Editar de Oferta</a></li>
                                	<li class='active'><a href="/MaoNaRoda/adicionar?acao=mostrartag&idoferta=${editarOferta.id}">Adicionar tags</a></li>
                            </ul>
                            <form name='form' action='/MaoNaRoda/adicionar' method='post'>
                            <input type='hidden' name='acao' value='tag'>
                            <input type='hidden' name='idoferta' value='${oferta}'>
                            <div class="pmb-block">
                                <div class="pmbb-header">
                                    <h2><i class="zmdi zmdi-pin"></i> Adicionar tag</h2>
                                </div>
                                 <div class="">
                                    <div class="">
                                       
                                        <div class="">
                                    <textarea placeholder="Tag" rows="1" cols="80" name='descricao' style="resize:none;" required></textarea>
                                </div><br>
                                    </div>
                                </div>
                                    <div class="m-t-30">
                                            <input type="submit" class="btn btn-primary btn-sm" value="Adicionar">
                                        </div>
                                    </form>
                                    <br><br>
                                    <center><b>Minhas tags</b></center>
                                    <c:forEach items="${listafavorito}" var="favorito">
                                    		${favorito.descricao}<br>
                                    		<a href='/MaoNaRoda/adicionar?acao=removertag&id=${favorito.id}&idoferta=${oferta}'>Apagar tag</a>
                                    </c:forEach>
                               </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </section>
        </section>

        <footer id="footer">
            Copyright &copy; 2016 Mão na roda

            <ul class="f-menu">
         <li><a href="/MaoNaRoda/contato/erro">Reporte um erro</a></li>
         <li><a href="/MaoNaRoda/contato">Contato</a></li>
    </ul>
        </footer>

        <!-- Page Loader -->
        <div class="page-loader">
            <div class="preloader pls-blue">
                <svg class="pl-circular" viewBox="25 25 50 50">
                    <circle class="plc-path" cx="50" cy="50" r="20" />
                </svg>

                <p>Carregando...</p>
            </div>
        </div>
        <script src="vendors/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

        <script src="vendors/bower_components/flot/jquery.flot.js"></script>
        <script src="vendors/bower_components/flot/jquery.flot.resize.js"></script>
        <script src="vendors/bower_components/flot.curvedlines/curvedLines.js"></script>
        <script src="vendors/sparklines/jquery.sparkline.min.js"></script>
        <script src="vendors/bower_components/jquery.easy-pie-chart/dist/jquery.easypiechart.min.js"></script>

        <script src="vendors/bower_components/moment/min/moment.min.js"></script>
        <script src="vendors/bower_components/fullcalendar/dist/fullcalendar.min.js "></script>
        <script src="vendors/bower_components/simpleWeather/jquery.simpleWeather.min.js"></script>
        <script src="vendors/bower_components/Waves/dist/waves.min.js"></script>
        <script src="vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
        <script src="vendors/bower_components/sweetalert/dist/sweetalert.min.js"></script>
        <script src="vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="js/app.min.js"></script>
        
        <script src="../vendors/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="../vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

        <script src="../vendors/bower_components/flot/jquery.flot.js"></script>
        <script src="../vendors/bower_components/flot/jquery.flot.resize.js"></script>
        <script src="../vendors/bower_components/flot.curvedlines/curvedLines.js"></script>
        <script src="../vendors/sparklines/jquery.sparkline.min.js"></script>
        <script src="../vendors/bower_components/jquery.easy-pie-chart/dist/jquery.easypiechart.min.js"></script>

        <script src="../vendors/bower_components/moment/min/moment.min.js"></script>
        <script src="../vendors/bower_components/fullcalendar/dist/fullcalendar.min.js "></script>
        <script src="../vendors/bower_components/simpleWeather/jquery.simpleWeather.min.js"></script>
        <script src="../vendors/bower_components/Waves/dist/waves.min.js"></script>
        <script src="../vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
        <script src="../vendors/bower_components/sweetalert/dist/sweetalert.min.js"></script>
        <script src="../vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="../js/app.min.js"></script>
        
        <script src="js/jquery.mask.min.js"/></script>
        <script src="../js/jquery.mask.min.js"/></script>
        
        <script src="js/jquery-2.1.0.js"/></script>
        <script src="../js/jquery-2.1.0.js"/></script>
    </body>
  </html>
