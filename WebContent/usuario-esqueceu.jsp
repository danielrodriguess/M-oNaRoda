<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mão na roda - Esqueceu a senha</title>
		<link rel="icon" href="icone.ico" type="image/x-icon">
        <link href="vendors/bower_components/animate.css/animate.min.css" rel="stylesheet">
        <link href="vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" rel="stylesheet">

        <link href="css/app_1.min.css" rel="stylesheet">
        <link href="css/app_2.min.css" rel="stylesheet">
        <style type='text/css'>
      	.login-page{
		    background-image: url('carro.jpg');
		    background-repeat: no-repeat;
		    background-size:100%;
		    bottom: 0;
		    color: black;
		    left: 0;
		    overflow: auto;
		    padding: 3em;
		    position: absolute;
		    right: 0;
		    text-align: center;
		    top: 0;
		
		}
      	</style>
        <script> 		
			function enviar_login(){
				if(document.getElementById("senha1").value == ""){
					alert('Por favor, preencha o campo senha');
					document.getElementById("senha1").focus();
					return false
				}else{
					document.forms["form1"].submit();
				}
			}
			
</script>
    </head>

    <body>
    <div class=login-page>
    <c:if test="${empty sessionScope.usuario}">
           <br><br><br><br><br><br><br><br><br><br> <!-- Login -->
            
            <div class="lc-block toggled" id="l-login">

            <form name="form1" action="login" method="post">
            <input type='hidden' name='acao' value='atualizandoSenha'>
            <input type='hidden' name='url' value='${url}'>
            <input type='hidden' name='email' value='${email}'>
                <div class="lcb-form">
                    <div class="input-group m-b-20">
                        <span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
                        <div class="fg-line">
                            <input type="password" class="form-control" name='senha' id='senha1' placeholder="Nova senha">
                        </div>
                    </div>
                    <a href="javascript:enviar_login();" id='login' class="btn btn-login btn-success btn-float"><i class="zmdi zmdi-arrow-forward"></i></a>
                </div>
                
				</form>
            </div>
        <script src="vendors/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

        <script src="vendors/bower_components/Waves/dist/waves.min.js"></script>

        <script src="js/app.min.js"></script>
        </c:if>
        <c:if test="${not empty sessionScope.usuario}">
        	<meta http-equiv='refresh' content='0, url=index'>
        </c:if>
        </div>
    </body>
</html>