<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="main.java.dao.LoginDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="icon" href="icone.ico" type="image/x-icon">
		<jsp:include page="cabecalho.jsp">
			<jsp:param value="Mão na Roda" name="titulo" />
		</jsp:include>
	</head>
	<body>
		<jsp:include page='header.jsp'></jsp:include>
		<section id="main">
			<jsp:include page='menu-esquerdo.jsp'></jsp:include>
			<section id="content">
				<div class="container">
					<c:if test="${not empty listaoferta}">
					<center>Todas as ofertas</center>
                <c:forEach items="${listaoferta}" var="oferta">
				<a href='/MaoNaRoda/pesquisa/${oferta.id}'><div class="list-group-item media">
                                <div class="pull-left">
                                    <img class="lgi-img" src="${oferta.foto}" alt="">
                                </div></a>

                             <div class="media-body">
                                    <div class="lgi-heading">${oferta.titulo}</div>
                                    <small class="lgi-text">${oferta.descricao}</small>
                                </div>
                            </div>
                             </c:forEach>
                            </c:if>
                            <c:if test="${empty listaoferta}">
                           	 <center>Não existem ofertas cadastradas no sistema ainda. :(</center>
                            </c:if>
				</div>       
			</section>
		</section>
		<jsp:include page='rodape.jsp'></jsp:include>
		<jsp:include page='scripts.jsp'></jsp:include>
	</body>
</html>