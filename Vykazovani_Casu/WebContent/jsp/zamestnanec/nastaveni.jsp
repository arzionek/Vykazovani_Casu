<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../zahlavi.jsp" flush="true" />
<jsp:include page="../horni.jsp" flush="true" />
<jsp:include page="../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
<jsp:include page="../menu_top.jsp" flush="true" />
<h2 class="stred">Nastavení:</h2>
<div class="box">
<h3>Nastavení svátků</h3>
<h3>Nastavení pracovních poměrů</h3>
<h3>Import šablon</h3>
<h3>Tagy pro import z Google calendar</h3>
</div>
</div>  

<jsp:include page="../spodni.jsp" flush="true" />