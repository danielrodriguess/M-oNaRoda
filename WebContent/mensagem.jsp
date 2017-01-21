<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mão na roda</title>

        <!-- Vendor CSS -->
        <link href="vendors/bower_components/fullcalendar/dist/fullcalendar.min.css" rel="stylesheet">
        <link href="vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
        <link href="vendors/bower_components/sweetalert/dist/sweetalert.css" rel="stylesheet">
        <link href="vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" rel="stylesheet">
        <link href="vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet">

        <!-- CSS -->
        <link href="css/app_1.min.css" rel="stylesheet">
        <link href="css/app_2.min.css" rel="stylesheet">
			<script> 
			function enviar_formulario(){ 
				if(document.form.mensagem.value == ""){
					alert('Por favor, digite su ');
					document.getElementById("mensagem").focus();
					return false
				}else{
					document.forms["form"].submit();	
				}
			} 		
		</script>
    </head>
    <body>
    <c:if test="${empty sessionScope.usuario}">
<meta http-equiv='refresh' content='0, url=/MaoNaRoda/index2.jsp'>
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
				<a href="/MaoNaRoda/index2.jsp">Mão na roda</a>
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
                    <li class="active">
                        <a href="/MaoNaRoda/index2.jsp"><i class="zmdi zmdi-home"></i> Página inicial</a>
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

			 <section id="content">
                <div class="container container-alt">
                    <div class="messages card">
                        <div class="m-sidebar">
                            <header>
                                <h2 class="hidden-xs">Mensagens</h2>
                            </header>

                            

                            <div class="list-group c-overflow">
                                <c:forEach items="${userremetenteee}" var="user">
                                
                                    <div class="pull-left">
                                        <img src="${user.foto}" alt="" class="lgi-img">
                                    </div>
                                    <div class="media-body">
                                        <div class="lgi-heading">${user.nome}</div>
                                        
                                    </div>
                                </c:forEach>
                            </div>

                        </div>

                        <div class="m-body">
                        <c:forEach items="${userremetenteee}" var="user">
                            <header class="mb-header">
                                <div class="mbh-user clearfix">
                                    <img src="${user.foto}" alt="">
                                    <div class="p-t-5">${user.nome}</div>
                                </div>

                                
                            </header>
							</c:forEach>
                            <div class="mb-list">
                           
                            
                                <div class="mbl-messages c-overflow">
                                <c:forEach items="${listmensagemmm}" var="mensagem">
                                <c:if test="${mensagem.iddestinatario != usuario.id}">
                                    <div class="mblm-item mblm-item-right">
                                        <div>
                                           ${mensagem.mensagem}
                                        </div>
                                        
                                    </div>
                                    </c:if>
                                    <c:if test="${mensagem.iddestinatario == usuario.id}">
                                    <div class="mblm-item mblm-item-left">
                                        <div>
                                           ${mensagem.mensagem}
                                        </div>
                                        
                                    </div>
                                    </c:if>
                                  </c:forEach>  
                                </div>
                                <div class="mbl-compose">
                                <form method='post' name='form' action='mensagem'>
                                <input type='hidden' name='acao' value='enviamensagem'>
                                <textarea placeholder="Digite sua mensagem..." name='mensagem' id='mensagem' required></textarea>
								               <a href='javascript:enviar_formulario();'><button><i class="zmdi zmdi-mail-send"></i></button></a>
                                    </form>
                                    </div>

                            </div>
                        </div>
                    </div>
                </div>
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
    </body>
  </html>
