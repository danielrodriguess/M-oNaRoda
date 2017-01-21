<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mão na roda - Configurações de perfil</title>
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
						<jsp:include page='gerenciamensagem1.jsp'></jsp:include>
				</ul>
			</li>
			</c:if>
			</ul>
			</li>
            </ul>
            <!-- Top Search Content -->
                        <<div class="h-search-wrap" style='height:85px;'>
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
                            <img src="../${usuario.foto }" alt="">
                        </div>

                        <div class="sp-info">
                            ${usuario.nome}
                            <i class="zmdi zmdi-caret-down"></i>
                        </div>
                    </a>

                    <ul class="main-menu">
                        <li class='active'>
                            <a href=""><i class="zmdi zmdi-account"></i> Meu perfil</a>
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
                        <h2>${usuario.nome}
                        </h2>
                    </div>

                    <div class="card" id="profile-main">
                        <div class="pm-overview c-overflow">

                            <div class="pmo-pic">
                                <div class="p-relative">
                                        <img class="img-responsive" src="../${usuario.foto}" alt=""> 
								<form method="post" action="${pageContext.request.contextPath}/upload"
									encType="multipart/form-data">
									<input type="file" name="file" value="Selecione a imagem" accept="image/png">
									<input type="submit" value="Enviar foto">
									</form>
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

                        <div class="pm-body clearfix">
                            <ul class="tab-nav tn-justified">
                                <li class="active"><a href="">Sobre</a></li>
                            </ul>
                            <div class="pmb-block">
                                <div class="pmbb-header">
                                    <h2><i class="zmdi zmdi-account m-r-10"></i> Informações</h2>

                                    <ul class="actions">
                                        <li class="dropdown">
                                            <a href="" data-toggle="dropdown">
                                                <i class="zmdi zmdi-more-vert"></i>
                                            </a>

                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li>
                                                    <a data-ma-action="profile-edit" href="">Editar</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                <div class="pmbb-body p-l-30">
                                    <div class="pmbb-view">
                                        <dl class="dl-horizontal">
                                            <dt>Nome</dt>
                                            <dd>${usuario.nome}</dd>
                                        </dl>
                                        <c:if test="${not empty sessionScope.usuario.datadenascimento}">                 
                                        <dl class="dl-horizontal">
                                            <dt>Data de nascimento</dt>
                                            <dd>${usuario.datadenascimento}</dd>
                                        </dl>
                                        </c:if>
                                        <c:if test="${empty sessionScope.usuario.datadenascimento}"> 
                                        <dl class="dl-horizontal">
                                            <dt>Data de nascimento</dt>
                                            <dd><b>Defina agora sua data de nascimento</b></dd>
                                        </dl>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.usuario.endereco}">                 
                                        <dl class="dl-horizontal">
                                            <dt>Endereço</dt>
                                            <dd>${usuario.endereco}</dd>
                                        </dl>
                                        </c:if>
                                        <c:if test="${empty sessionScope.usuario.endereco}">                 
                                        <dl class="dl-horizontal">
                                            <dt>Endereço</dt>
                                            <b><dd>Defina seu endereço</dd></b>
                                        </dl>
                                        </c:if>
                                    </div>
									<form action='/MaoNaRoda/cadastro' method='post'>
									<input type='hidden' name='acao' value='editinformacoesusuario'>
                                    <div class="pmbb-edit">
                                        <dl class="dl-horizontal">
                                            <dt class="p-t-10">Nome</dt>
                                            <dd>
                                                <div class="fg-line">
                                                    <input type="text" class="form-control" name='nome'
                                                           value="${usuario.nome}">
                                                </div>

                                            </dd>
                                        </dl>
                                        
                                        <dl class="dl-horizontal">
                                            <dt class="p-t-10">Data de nascimento</dt>
                                            <dd>
                                                <div class="dtp-container dropdown fg-line">
                                                    <input type='text' class="form-control date-picker" name='data'
                                                           data-toggle="dropdown" id='data' value="${usuario.datadenascimento}">
                                                </div>
                                            </dd>
                                        </dl>
                                        
                                        <dl class="dl-horizontal">
                                            <dt class="p-t-10">Endereço</dt>
                                            <dd>
                                                <div class="dtp-container dropdown fg-line">
                                                    <input type='text' class="form-control date-picker" name='endereco'
                                                           data-toggle="dropdown" id='data' value="${usuario.endereco}">
                                                </div>
                                            </dd>
                                        </dl>

                                        <div class="m-t-30">
                                            <input type='submit' class="btn btn-primary btn-sm" value='Salvar'>
                                            <button data-ma-action="profile-edit-cancel" class="btn btn-link btn-sm">Cancelar</button>
                                        </div>
                                    </div>
                                    </form>
                                    <script type='text/javascript'>
                                    	$("#data").mask("99/99/9999");
                                   </script>
                                </div>
                            </div>

                            <div class="pmb-block">
                                <div class="pmbb-header">
                                    <h2><i class="zmdi zmdi-phone m-r-10"></i> Informações de contato</h2>

                                    <ul class="actions">
                                        <li class="dropdown">
                                            <a href="" data-toggle="dropdown">
                                                <i class="zmdi zmdi-more-vert"></i>
                                            </a>

                                            <ul class="dropdown-menu dropdown-menu-right">
                                                <li>
                                                    <a data-ma-action="profile-edit" href="">Editar</a>
                                                </li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                <div class="pmbb-body p-l-30">
                                    <div class="pmbb-view">
                                    <c:if test="${not empty sessionScope.usuario.telefonecelular}"> 
                                        <dl class="dl-horizontal">
                                            <dt>Celular</dt>
                                            <dd>${usuario.telefonecelular}</dd>
                                        </dl>
                                        </c:if>
                                        <c:if test="${not empty sessionScope.usuario.telefonefixo}"> 
                                       <dl class="dl-horizontal">
                                            <dt>Fixo</dt>
                                            <dd>${usuario.telefonefixo}</dd>
                                        </dl>
                                        </c:if>
                                        <c:if test="${empty sessionScope.usuario.telefonecelular}">
                                        <dl class="dl-horizontal">
                                            <dt>Celular</dt>
                                            <dd><b>Defina seu celular</b></dd>
                                        </dl>
                                        </c:if>
                                        <c:if test="${empty sessionScope.usuario.telefonefixo}">
                                        <dl class="dl-horizontal">
                                            <dt>Fixo</dt>
                                            <dd><b>Defina seu telefone fixo</b></dd>
                                        </dl>
                                        </c:if>
                                        <dl class="dl-horizontal">
                                            <dt>Endereço de email</dt>
                                            <dd>${usuario.email }</dd>
                                        </dl>
                                    </div>
									<form method='post' action='/MaoNaRoda/cadastro'>
									<input type='hidden' name='acao' value='editarcontatoUsuario'>
                                    <div class="pmbb-edit">
                                        <dl class="dl-horizontal">
                                            <dt class="p-t-10">Celular</dt>
                                            <dd>
                                                <div class="fg-line">
                                                    <input type="text" class="form-control"
                                                           name='telefonecelular' id='telefonecelular' value="${usuario.telefonecelular}">
                                                </div>
                                            </dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt class="p-t-10">Fixo</dt>
                                            <dd>
                                                <div class="fg-line">
                                                    <input type="text" class="form-control"
                                                          name='telefonefixo' id='telefonefixo' value="${usuario.telefonefixo}">
                                                </div>
                                            </dd>
                                        </dl>
                                        <dl class="dl-horizontal">
                                            <dt class="p-t-10">Endereço de email</dt>
                                            <dd>
                                                <div class="fg-line">
                                                    <input type="email" class="form-control" name='email' value="${usuario.email }">
                                                </div>
                                            </dd>
                                        </dl>

                                        <div class="m-t-30">
                                            <input type='submit' class="btn btn-primary btn-sm" value='Salvar'>
                                            <button data-ma-action="profile-edit-cancel" class="btn btn-link btn-sm">Cancelar</button>
                                        </div>
                                    </div>
                                    </form>
                                 <div class="m-t-30">
                                            <a href='/MaoNaRoda/cadastro?acao=deleteusuario' class="btn btn-primary btn-sm">Excluir conta</a>
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
