<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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