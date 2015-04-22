<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<jsp:include page="meta.jsp" />
	
	<body background="imagens/FOLHA_DE_CADERNO_BRANCA_2.jpg">
		<h1 class="titulo1">Jogo da Forca</h1>
		<h2 class="titulo2">Informe seu e-mail</h2>
		<form action="login" method="post" >
			<input type="hidden" name="acao" value="login">
			<input type="text" class="input-email" name="email" autofocus>
			<button type="submit" class="bt-jogar">Jogar</button>
			${mensagem}			
		</form>
	<jsp:include page="rodape.jsp" />