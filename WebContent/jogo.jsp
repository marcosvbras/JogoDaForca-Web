<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<jsp:include page="meta.jsp" />
	
	<body background="imagens/FOLHA_DE_CADERNO_BRANCA_2.jpg">
		<div class="div-titulo"><h1 class="titulo">Jogo da Forca</h1><div>
		<div class="corpo1">
			
		</div>
		<div class="corpo2">
			<p class="text-chances">Chances: </p>
			<form action="jogo" method="post">
				<input type="text" class="input-tentativa" name="tentativa" autofocus>
				<button class="bt-enviar" type="submit">Enviar</button>
			</form>
			<div class="div-letra div-left"></div>
			<div class="div-letra div-left"></div>
			<div class="div-letra div-left"></div>
			<div class="div-letra div-left"></div>
			<div class="div-letra div-left"></div>
			<div class="div-letra"></div><br/>
			<p class="text-dicas">Dica: ${palavra.dica}</p>
		</div>
		<b><a href="ranking.html" class="link-ranking">VER RANKING</a></b>
		
		<jsp:include page="rodape.jsp" />