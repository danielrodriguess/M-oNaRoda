<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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