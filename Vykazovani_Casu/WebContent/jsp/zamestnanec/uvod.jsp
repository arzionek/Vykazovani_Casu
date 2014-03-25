<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../zahlavi.jsp" flush="true" />
<jsp:include page="../horni.jsp" flush="true" />
<jsp:include page="../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
<jsp:include page="../menu_top.jsp" flush="true" />
<jsp:include page="../uvod.jsp" flush="true" />
            
<div class="box2" id="navod"> 
  <jsp:include page="../zamestnanec/navod.jsp" flush="true" /> 
</div>
</div>  

<jsp:include page="../spodni.jsp" flush="true" />