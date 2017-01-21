<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="icone.ico" type="image/x-icon">
        <title>Mão na roda - Login</title>

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
			function enviar_formulario(){
				
				if(document.getElementById("nome").value == ""){
					alert('Por favor, preencha o campo nome');
					document.getElementById("nome").focus();
					return false
				}else if(document.getElementById("email").value == "" || document.getElementById("email").value.indexOf('@') ==-1 || document.getElementById("email").value.indexOf('.') ==-1){
					alert('Por favor, digite um email válido');
					document.getElementById("email").focus();
					return false
				}
				else if(document.getElementById("senha").value == ""){
					alert('Por favor, preencha o campo senha');
					document.getElementById("senha").focus();
					return false
				}else{
					document.forms["form"].submit();	
				}
			} 
			
			function enviar_login(){
				if(document.getElementById("email1").value == ""){
					alert('Por favor, preencha o campo email');
					document.getElementById("email1").focus();
					return false
				}else if(document.getElementById("senha1").value == ""){
					alert('Por favor, preencha o campo senha');
					document.getElementById("senha1").focus();
					return false
				}else{
					document.forms["form1"].submit();
				}
			}
			
			function enviar_recuperacao(){
				document.forms["form2"].submit();	
			}
			
</script>
    </head>

    <body>
    <div class=login-page>
    <c:if test="${empty sessionScope.usuario}">
            <br><br><br><br><br><br><br><br><br><br><!-- Login -->
            
            <div class="lc-block toggled" id="l-login">

            <form name="form1" action="login" method="post">
            <input type='hidden' name='acao' value='login'>
                <div class="lcb-form">
                    <div class="input-group m-b-20">
                        <span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
                        <div class="fg-line">
                            <input type="email" class="form-control" name='email' id='email1' placeholder="Email">
                        </div>
                    </div>

                    <div class="input-group m-b-20">
                        <span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
                        <div class="fg-line">
                            <input type="password" class="form-control" name='senha' id='senha1' placeholder="Senha">
                        </div>
                    </div>
                    <a href="javascript:enviar_login();" id='login' class="btn btn-login btn-success btn-float"><i class="zmdi zmdi-arrow-forward"></i></a>
                    <center><b>${loginErro}</b></center>
                </div>
                
				</form>
                <div class="lcb-navigation">
                    <a href="" data-ma-action="login-switch" data-ma-block="#l-register"><i class="zmdi zmdi-plus"></i> <span>Registre-se</span></a>
                    <a href="" data-ma-action="login-switch" data-ma-block="#l-forget-password"><i>?</i> <span>Esqueceu a senha?</span></a>
                </div>
            </div>

            <div class="lc-block" id="l-register">
           <form name="form" action="cadastro" method="post">
            <input type='hidden' name='acao' value='caduser'>
            <input type='hidden' name='telefonecelular' value=''>
            <input type='hidden' name='telefonefixo' value=''>
            <input type='hidden' name='data' value=''>
                <div class="lcb-form">
                    <div class="input-group m-b-20">
                        <span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>
                        <div class="fg-line">
                            <input type="text" class="form-control" name='nome' id='nome' placeholder="Nome">
                        </div>
                    </div>

                    <div class="input-group m-b-20">
                        <span class="input-group-addon"><i class="zmdi zmdi-email"></i></span>
                        <div class="fg-line">
                            <input type="email" class="form-control" name='email' id='email' placeholder="Email">
                        </div>
                    </div>

                    <div class="input-group m-b-20">
                        <span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
                        <div class="fg-line">
                            <input type="password" class="form-control" name='senha' id='senha' placeholder="Senha">
                        </div>
                    </div>

                    <a href="javascript:enviar_formulario();" class="btn btn-login btn-success btn-float"><i class="zmdi zmdi-check"></i></a>
                </div>
			</form>
                <div class="lcb-navigation">
                    <a href="" data-ma-action="login-switch" data-ma-block="#l-login"><i class="zmdi zmdi-long-arrow-right"></i> <span>Entre</span></a>
                    <a href="" data-ma-action="login-switch" data-ma-block="#l-forget-password"><i>?</i> <span>Esqueceu a senha?</span></a>
                </div>
            </div>

            <div class="lc-block" id="l-forget-password">
                <div class="lcb-form">
                    <center><p class="text-left">Coloque seu e-mail para entrarmos em contato com você</p></center>
					<form name="form2" action="login" method="post">
					<input type="hidden" name="acao" value='esqueceu'>
                    <div class="input-group m-b-20">
                        <span class="input-group-addon"><i class="zmdi zmdi-email"></i></span>
                        <div class="fg-line">
                            <input type="email" class="form-control" name='email2' id='email2' placeholder="Email" required>
                        </div>
                    </div>
					</form>
                    <a href="javascript:enviar_recuperacao();" class="btn btn-login btn-success btn-float"><i class="zmdi zmdi-check"></i></a>
                </div>

                <div class="lcb-navigation">
                    <a href="" data-ma-action="login-switch" data-ma-block="#l-login"><i class="zmdi zmdi-long-arrow-right"></i> <span>Entre</span></a>
                    <a href="" data-ma-action="login-switch" data-ma-block="#l-register"><i class="zmdi zmdi-plus"></i> <span>Registre-se</span></a>
                </div>
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