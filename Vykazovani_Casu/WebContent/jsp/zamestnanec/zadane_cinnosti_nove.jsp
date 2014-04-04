<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../zahlavi.jsp" flush="true" />
<jsp:include page="../horni.jsp" flush="true" />
<jsp:include page="../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
<jsp:include page="../menu_top.jsp" flush="true" />
<h2 class="stred">Vkládání činností:</h2>
<jsp:include page="../zamestnanec/zadane_cinnosti_menu.jsp" flush="true" />
</div>  

<jsp:include page="../spodni.jsp" flush="true" />