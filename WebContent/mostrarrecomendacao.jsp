<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="main.java.model.*" import="main.java.dao.*" import="java.util.ArrayList"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
LoginDao login = new LoginDao();
Object id = request.getSession(false).getAttribute("idusuario");
ArrayList<Oferta> ofertarecomendada = new ArrayList<Oferta>();
if(!id.equals("")){
	Integer iduser = Integer.parseInt(id.toString());
	ofertarecomendada = login.ofertarecomendada(iduser);
}
request.setAttribute("ofertarecomendada", ofertarecomendada);
%>
<c:if test="${not empty ofertarecomendada}">
<center>Ofertas recomendadas com base no seu endereço</center>
<c:forEach items="${ofertarecomendada}" var="ofertarecomendada">
<a href='/MaoNaRoda/pesquisa/${ofertarecomendada.id}'><div class="list-group-item media">
<div class="pull-left">
    <img class="lgi-img" src="${ofertarecomendada.foto}" alt="">
   </div></a>

<div class="media-body">
       <div class="lgi-heading">${ofertarecomendada.titulo}</div>
<small class="lgi-text">${ofertarecomendada.descricao}</small>
    </div>
</div>
 </c:forEach>
</c:if>