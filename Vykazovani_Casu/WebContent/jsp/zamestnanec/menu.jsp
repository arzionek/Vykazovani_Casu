<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!-- levá část dokumentu -->
        <div id="menu">
           <c:url var="uvod" value="uvodni">

		   </c:url>
	       <a href="<c:out value="${uvod}" escapeXml="true" />">Úvodní stránka</a>
           
           <c:url var="nastaveni" value="nastaveni">

		   </c:url> 
           <a href="<c:out value="${nastaveni}" escapeXml="true" />">Nastavení</a>
           
           <c:url var="zadane" value="prehled">

		   </c:url>
           <a href="<c:out value="${zadane}" escapeXml="true" />">Zadané činnosti</a>
           
           <c:url var="importGoogle" value="import">

		   </c:url>
           <a href="<c:out value="${importGoogle}" escapeXml="true" />">Import dat</a>
           
           <c:url var="export" value="export">

		   </c:url>
           <a href="<c:out value="${export}" escapeXml="true" />">Export dat</a>
        </div>