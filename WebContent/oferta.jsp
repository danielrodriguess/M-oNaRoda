<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mão na roda - Oferta</title>
        <link rel="icon" href="../icone.ico" type="image/x-icon">
<script src="jquery-1.8.2.js"></script>
<script src="jquery.ajaxfileupload.js"></script>
<script src="../jquery-1.8.2.js"></script>
<script src="../jquery.ajaxfileupload.js"></script>
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

<script lang="Javascript">
function enviar_mensagem(){
	if(document.getElementById("mensagem").value == ""){
		alert('Escreva uma mensagem');
		document.getElementById("mensagem").focus();
		return false
	}else{
		document.forms["form"].submit();
	}
}
</script>
    </head>
    <body>
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
						<jsp:include page='gerenciamensagem1.jsp'></jsp:include>
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
                            <img src="../${usuario.foto}" alt="">
                        </div>

                        <div class="sp-info">
                            ${usuario.nome}
                            <i class="zmdi zmdi-caret-down"></i>
                        </div>
                    </a>

                    <ul class="main-menu">
                        <li class=''>
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
            <c:if test="${empty erro}">
                <div class="container container-alt">
                <c:if test="${not empty sessionScope.usuario && empty favorito && usuario.id != ofertante.id}">
                <form method'='post' action='/MaoNaRoda/adicionar'>
                <input type='hidden' name='acao' value='favorito'>
                <input type='hidden' name='idoferta' value='${oferta.id}'>
                <input type='hidden' name='iduser' value='${usuario.id}'>
	<div class="m-t-30">
         <input type='submit' class="btn btn-primary btn-sm" value='Adicionar aos favoritos' style='margin-left: 0%;position: relative;margin-top: -10%;'>
     </div>
     </form>
     </c:if>
     <c:if test="${not empty sessionScope.usuario && not empty favorito && usuario.id != ofertante.id}">
                <form method'='post' action='/MaoNaRoda/adicionar'>
                <input type='hidden' name='acao' value='removerfavoritooferta'>
                <input type='hidden' name='idoferta' value='${oferta.id}'>
                <input type='hidden' name='id' value='${favorito}'>
	<div class="m-t-30">
         <input type='submit' class="btn btn-primary btn-sm" value='Remover dos favoritos' style='margin-left: 0%;position: relative;margin-top: -10%;'>
     </div>
     </form>
     </c:if>
                    <div class="block-header">
                        <h2>${ofertante.nome}
                        </h2>
                    </div>

                    <div class="card" id="profile-main">
                        <div class="pm-overview c-overflow">

                            <div class="pmo-pic">
                                <div class="p-relative">
                                    
                                        <img class="img-responsive" src="../${ofertante.foto}" alt="">
								<c:if test="${not empty sessionScope.usuario && ofertante.id != usuario.id}">
                                    <div class="dropdown pmop-message">
                                        <a data-toggle="dropdown" href="" class="btn bgm-white btn-float z-depth-1">
                                            <i class="zmdi zmdi-comment-text-alt"></i>
                                        </a>

                                        <div class="dropdown-menu">
                                        <form name='form' method='post' action='/MaoNaRoda/mensagem'>
                                        <input type='hidden' name='acao' value='envia'>
                                        <input type='hidden' name='idoferta' value='${oferta.id}'>
                                        <input type='hidden' name='idofertante' value='${ofertante.id}'>
                                        <input type='hidden' name='id' value='${usuario.id}'>
                                            <textarea placeholder="Digite sua mensagem" name='mensagem' id='mensagem'></textarea>
                                            <a onclick="return enviar_mensagem();"><button class="btn bgm-green btn-float"><i class="zmdi zmdi-mail-send"></i>
                                            </button></a>
                                           </form>
                                        </div>
                                    </div>
                                    </c:if>
                                    <!-- 
								<div class="centered">
								        <input type="file" name="file" /><br />
								        <div id="upload" style="display: none;">Carregando</div>
								        <div id="message"></div>
								</div> -->
                                </div>
                            </div>

                            <div class="pmo-block pmo-contact hidden-xs">
                                <h2>Contato</h2>
								<c:if test="${not empty sessionScope.usuario}">
                                <ul>
                                	<li><i class="zmdizmdi-accounts-alt"></i> ${ofertante.nome}</li>
									<c:if test="${not empty ofertante.telefonecelular}">
									<li><i class="zmdi zmdi-phone"></i> ${ofertante.telefonecelular}</li>
									</c:if>
									<c:if test="${not empty ofertante.telefonefixo}">
									<li><i class="zmdi zmdi-phone"></i> ${ofertante.telefonefixo}</li>
									</c:if>
			
                                    <li><i class="zmdi zmdi-email"></i> ${ofertante.email}</li>
                                </ul>
                                </c:if>
                                <c:if test="${empty sessionScope.usuario}">
                                	Cadastre-se e entre em contato com o ofertante
                                </c:if>
                            </div>
                        </div>

                        <div class="pm-body clearfix">
                        <c:if test="${not empty sessionScope.usuario}">
                            <ul class="tab-nav tn-justified">
                                <li class="active"><a href="">Oferta</a></li>
                                	<li><a href="/MaoNaRoda/recomendacao/${oferta.idusuario}">Avaliações desse usuário</a></li>
                            </ul>
                            </c:if>
                            <div class="pmb-block">
                                 <img class="img-responsive" src="../${oferta.foto }" alt="">
                                <div class="pmbb-body p-l-30">
                                </div>
                            </div>

                            <div class="pmb-block">
                                <div class="pmbb-header">
                                    <h2><i class="zmdi zmdi-view-list-alt"></i> Algumas informações</h2>
                                </div>
                                <div class="pmbb-body p-l-30">
                                    <div class="pmbb-view">
                                        <center><dl class="dl-horizontal">
                                             <b><dt>${oferta.titulo}</dt></b>
                                        </dl></center><br>
                                       <br><br><dl class="dl-horizontal">
                                       Descrição<br>
                                            ${oferta.descricao}
                                        </dl><br>
                                        <br><br><dl class="dl-horizontal">
                                            <dt>Período</dt><br>
                                            <c:if test="${oferta.manha == 'sim'}">
                                            	<dt><b>Manhã</b><dt><br>
                                            </c:if>
                                            <c:if test="${oferta.tarde == 'sim'}">
                                            	<dt><b>Tarde</b><dt><br>
                                            </c:if>
                                            <c:if test="${oferta.noite == 'sim'}">
                                            	<dt><b>Noite</b><dt><br>
                                            </c:if>                                         
                                            
                                        </dl>
                                        <br><br><dl class="dl-horizontal">
                                            <dt>Essa oferta é remunerada?</dt><br>
                                            <c:if test="${oferta.remunerado == 'paga'}">
                                            <b>Sim</b>
                                            </c:if>
                                            <c:if test="${oferta.remunerado == 'gratis'}">
                                            <b>Não</b>
                                            </c:if>
                                        </dl>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:if>
                 <c:if test="${not empty erro}">
			<center>${erro}</center>
		</c:if>
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
