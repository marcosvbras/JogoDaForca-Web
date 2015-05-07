<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<jsp:include page="meta.jsp" />
	
	<body background="imagens/FOLHA_DE_CADERNO_BRANCA_2.jpg">
		<h1 class="titulo1">Ranking</h1>
		<p class="text-email1">Email:</p>
		<p class="text-palavra1">Palavras Acertadas:</p>
		<br/><table align="center" cellspace=0>
			<c:forEach items="${listaRanking}" var="lista">
				<tr>
					<td width=400><p class="text-email2">${lista.email }</p></td>
					<td width=200><p class="text-palavra2">${lista.acertos }</p></td>
				</tr>
		    </c:forEach>
		</table>
		
	<jsp:include page="rodape.jsp" />