<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mão na roda - Avaliações (${ofertante.nome})</title>
		<link rel="icon" href="../icone.ico" type="image/x-icon">
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

function cache() {
	imagens = new Image();
	imagens.src='../silver_star.png';
	imagens.src='../gold_star.png';
}
	function vota(id) {
		var valor = 0;
	if(id==1) {
		valor = 1;
		document.getElementById('camponota').value = valor;
	document.getElementById('nota').innerHTML="<font class='ajax'>Ruím</font>";
	}
	if(id==2) {
		valor = 2;
		document.getElementById('camponota').value = valor;
	document.getElementById('nota').innerHTML="<font class='ajax'>Regular</font>";
	}
	if(id==3) {
		valor = 3;
		document.getElementById('camponota').value = valor;
	document.getElementById('nota').innerHTML="<font class='ajax'>Bom</font>";
	}
	if(id==4) {
		valor = 4;
		document.getElementById('camponota').value = valor;
	document.getElementById('nota').innerHTML="<font class='ajax'>Muito bom</font>";
	}
	if(id==5) {
		valor = 5;
		document.getElementById('camponota').value = valor;
	document.getElementById('nota').innerHTML="<font class='ajax'>Ótimo</font>";
	}
	for(i = 0; i < id; i++) {
	document.getElementById(i+1).src="../gold_star.png";
	}
	}

	function retira(id) {
	for(i = 5; i > id; i--) {
	document.getElementById(i).src="../silver_star.png";
	}
	}
</script>
    </head>
    <body onLoad="cache()">
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
    <li class="active">
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
      <div class="card" id="profile-main">
      <div class="pm-overview c-overflow">

          <div class="pmo-pic">
              <div class="p-relative">
                  
                      <img class="img-responsive" src="../${ofertante.foto}" alt="">
              </div>
          </div>

          <div class="pmo-block pmo-contact hidden-xs">
              <h2>Contato</h2>

              <ul>
<c:if test="${not empty sessionScope.usuario.telefonecelular}">
<li><i class="zmdi zmdi-phone"></i> ${ofertante.telefonecelular}</li>
</c:if>
<c:if test="${not empty sessionScope.usuario.telefonefixo}">
<li><i class="zmdi zmdi-phone"></i> ${ofertante.telefonefixo}</li>
</c:if>

                  <li><i class="zmdi zmdi-email"></i> ${ofertante.email}</li>
              </ul>
          </div>
      </div>
      <div class="pm-body clearfix">
          <ul class="tab-nav tn-justified">
              <li class=""><a href="/MaoNaRoda/pesquisa/${ofertaatual}">Oferta</a></li>
              	<li class='active'><a href="">Avaliações desse usuário</a></li>
          </ul>
          <c:if test="${not empty sessionScope.usuario && ofertante.id != usuario.id}">
          <form name='form1' action='/MaoNaRoda/adicionar' method='post'>
          <input type='hidden' name='acao' value='recomendacao'>
          <input type='hidden' name='idavaliacao' value='${ofertante.id}'>
          <input type='hidden' name='idavaliador' value='${usuario.id}'>
          <div class="pmb-block">
              <div class="pmbb-header">
                  <h2><i class="zmdi zmdi-mail-send"></i> Reputação desse usuário</h2>
              </div>
               <div class="">
                  <div class="">
                     
                      <div class="">
                  <textarea placeholder="Comentário" rows="1" cols="80" name='comentario' id='comentario' style="resize:none;" required></textarea>
              </div><br>
                  </div>
              </div>
               <div class="">
                  <div class="">
                     
                      <div class="">
                <table cellspacing=2 cellpading=2>
				<tr>
				<td width="16" onmouseover="vota('1')" onmouseout="retira('1')"><img id="1" src="../gold_star.png" border="0"></td>
				<td width="16" onmouseover="vota('2')" onmouseout="retira('2')"><img id="2" src="../gold_star.png" border="0"></a></td>
				<td width="16" onmouseover="vota('3')" onmouseout="retira('3')"><img id="3" src="../gold_star.png" border="0"></a></td>
				<td width="16" onmouseover="vota('4')" onmouseout="retira('4')"><img id="4" src="../gold_star.png" border="0"></a></td>
				<td width="24" onmouseover="vota('5')" onmouseout="retira('5')"><img id="5" src="../gold_star.png" border="0"></a></td>
				<td id="nota" width="65"></td>
				</tr>
				</table>
              </div><br>
                  </div>
              </div>
              <input type='hidden' name='nota' id='camponota'>
                  <div class="m-t-30">
                          <input type="submit" class="btn btn-primary btn-sm" value="Enviar avaliação">
                     </div>
                  </form>
                  </c:if>
                  <c:if test="${not empty usuarioquecomentou}">
                  <br><br>
                  <b>Média de reputação: ${resultado}</b>
                  <c:forEach items="${usuarioquecomentou}" var="usuarioo">
                  <c:forEach items="${listarecomendacao}" var="recomendacao">
                  <div class='s-profile'>
                  <div class="pull-left">
                              <img class="lgi-img" src="../${usuarioo.foto}" alt="">
                                    </div>
</div>
                            			
                            		Nome: ${usuario.nome}<br>
                  		
                  		Nota: ${recomendacao.nota}<br>
                  		Mensagem: ${recomendacao.mensagem}<br>
                  		<c:if test="${recomendacao.idavaliador == usuario.id}">
                  			<a href='/MaoNaRoda/adicionar?acao=apagarrecomendacao&id=${recomendacao.id}&idofer=${ofertante.id}'><b>Apagar essa avaliação</b></a><br><br>
                  		</c:if>
<br><br>
                         </c:forEach>
                  </c:forEach>
                  </c:if>
                  <c:if test="${empty usuarioquecomentou}">
                  	<b>Esse usuário não foi avaliado ainda</b>
                  </c:if>
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