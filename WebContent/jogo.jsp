<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<jsp:include page="meta.jsp" />

	<body background="imagens/FOLHA_DE_CADERNO_BRANCA_2.jpg">
		<div class="div-titulo"><h1 class="titulo">Jogo da Forca</h1><div>
		<div class="corpo1">
			<img src="imagens/${chances}.png" class="img-forca1">
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
			
			<c:if test="${arrayTentativas eq null}">
			<div class="div-letra div-left"></div>
			<div class="div-letra div-left"></div>
			<div class="div-letra div-left"></div>
			<div class="div-letra div-left"></div>
			<div class="div-letra div-left"></div>
			<div class="div-letra div-left"></div>
			</c:if>
			<c:forEach items="${arrayTentativas}" var="letra">
	       		<c:if test="${letra ne null}">
	       			<div class="div-letra div-left">${letra}</div>
	       		</c:if>
	       		<c:if test="${letra eq null}">
	       			<div class="div-letra div-left"></div>
	       		</c:if>
	       		
	       </c:forEach> 	
			<br/>
			<br/><p class="text-dicas">Dica: ${palavra.dica}</p>
			
				<p class="error-message">${mensagem}</p>
		</div>
		<!--<form method="post" action="login">
			<button class="bt-new" type="submit">Novo Jogo</button>
		</form>-->
		<b><a href="login" class="link-ranking" type="submit" value="ranking" >NOVO JOGO</a></b>
		<b><a href="ranking" class="link-ranking" type="submit" value="ranking" name="acao" style="margin-left:150px;">VER RANKING</a></b>
			
		<jsp:include page="rodape.jsp" />