<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="main.java.model.*" import="main.java.dao.*" import="java.util.ArrayList"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
EnviaDao envia = new EnviaDao();
ArrayList<Usuario> listuser = new ArrayList<Usuario>();
Object id = request.getSession(false).getAttribute("idusuario");
Integer iduser = Integer.parseInt(id.toString());
listuser = envia.PegarTudo(iduser);
if(listuser.size() > 0){
	String verificar = "verificar";
	Integer mensagem = envia.contamensagem(iduser);
	request.setAttribute("verificauser",verificar);
	request.setAttribute("listmensagem",envia.listmensagem);
	request.setAttribute("mensagem",mensagem);
	request.setAttribute("listuser",listuser);
}else{
	String verificar = "";
	Integer mensagem = envia.contamensagem(iduser);
	request.setAttribute("verificauser",verificar);
	request.setAttribute("mensagem",mensagem);
}
%>
<li class="dropdown">
	<a data-toggle="dropdown" href="">
	<i class="him-icon zmdi zmdi-email"></i>
	<c:if test="${mensagem == 0}">
    </c:if>
    <c:if test="${mensagem > 0}">
    <i class="him-counts">${mensagem}</i>
    </c:if>
	</a>
	<div class="dropdown-menu dropdown-menu-lg pull-right">
    <div class="list-group">
        <div class="lg-header">
            Mensagens
        </div>
        
        <div class="lg-body">
        
        <c:if test="${not empty verificauser}">
        <c:forEach items="${listuser}" var="listuser">
        
        <c:if test="${listuser.id != usuario.id}">
        <a class="list-group-item media" href="/MaoNaRoda/mensagem?acao=chat&id=${listuser.id}">
                <div class="pull-left">
                <img class="lgi-img" src="../${listuser.foto}" alt="">
                </div>
                <div class="media-body">
                    <div class="lgi-heading">${listuser.nome}</div>
                </div>
                <c:forEach items="${listmensagem}" var="listmensagem">
                <c:if test="${listuser.id == listmensagem.idremetente}">
                <c:if test="${listremetente.numero == 0}">
                
                </c:if>
                <c:if test="${listmensagem.numero > 0 && listmensagem.numero != 1}">
                <i class="him-counts">${listmensagem.numero} novas mensagens</i>
                </c:if>
                <c:if test="${listmensagem.numero == 1}">
                <i class="him-counts">${listmensagem.numero} nova mensagem</i>
                </c:if>
                </c:if>
                </c:forEach>
            </a>
            </c:if>
            
            </c:forEach>
            </c:if>
            <c:if test="${empty verificauser}">
            <center><i class="him-counts">Você não possui nenhuma mensagem</i></center>
            </c:if>
            </div>
    </div>
    </div>
</li>  
                   
                            
                                
                                
                            
                            
                