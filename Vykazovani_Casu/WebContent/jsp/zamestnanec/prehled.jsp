<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../zahlavi.jsp" flush="true" />
<jsp:include page="../horni.jsp" flush="true" />
<jsp:include page="../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
<jsp:include page="../menu_top.jsp" flush="true" />
<h2 class="stred">Přehled činností:</h2>
<div class="box">
<h3>Období</h3>
<h3>Pracovní poměr</h3>

<c:url var="export" value="export">
		   </c:url>
	       <h3><a href="<c:out value="${export}" escapeXml="true" />" style="text-decoration: none">Export výkazu</a></h3>
	       
<c:url var="vkladani" value="vkladani">
		   </c:url>
<h3><a href="<c:out value="${vkladani}" escapeXml="true" />" style="text-decoration: none">Vkládání činností</a></h3>

</div>
</div>  

<jsp:include page="../spodni.jsp" flush="true" />