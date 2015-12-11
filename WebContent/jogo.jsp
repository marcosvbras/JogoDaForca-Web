<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<jsp:include page="meta.jsp" />

	<body background="imagens/FOLHA_DE_CADERNO_BRANCA_2.jpg">
		<div class="div-titulo"><h1 class="titulo">Jogo da Forca</h1><div>
		<div class="corpo1">
			<img src="imagens/f${chances}.png" class="img-forca1">
		</div>
		<div class="corpo2">
			<p class="text-chances">Chances: ${chances}</p>
			<form action="jogo" method="post">
				<input type="hidden" name="acao" value="verificar">
				<input type="text" class="input-tentativa" name="tentativa" autofocus>
				<c:if test="${mensagem ne null}">
	       			<button class="bt-enviar" type="submit" disabled>Enviar</button>
	       		</c:if>
				<c:if test="${mensagem eq null}">
	       			<button class="bt-enviar" type="submit">Enviar</button>
	       		</c:if>
			</form>
			
	       	<p class="text-letras">${letras}</p>
	       	 	
			<p class="text-dicas">Dica: ${objetoPalavra.dica}</p>
			
			<p class="error-message">${mensagem}</p>
		</div>
		<!--<form method="post" action="login">
			<button class="bt-new" type="submit">Novo Jogo</button>
		</form>-->
		<b><a href="login" class="link-ranking" type="submit" value="ranking" style="margin-left: 4%;">NOVO JOGO</a></b>
		<b><a href="ranking" class="link-ranking" type="submit" value="ranking" name="acao" style="margin-left:20%">VER RANKING</a></b>
			
		<jsp:include page="rodape.jsp" />