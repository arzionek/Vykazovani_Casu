<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <!-- levá část dokumentu -->
        <div id="menu">
           <c:url var="uvod" value="uvodni">

		   </c:url>
	       <a href="<c:out value="${uvod}" escapeXml="true" />">Úvodní stránka</a>
           
           <c:url var="nastaveni" value="uvodni">

		   </c:url> 
           <a href="<c:out value="${nastaveni}" escapeXml="true" />">Nastavení</a>
           
           <c:url var="sprava" value="uvodni">

		   </c:url>
           <a href="<c:out value="${sprava}" escapeXml="true" />">Správa činností</a>
           
           <c:url var="provestImport" value="uvodni">

		   </c:url>
           <a href="<c:out value="${provestImport}" escapeXml="true" />">Import</a>
           
           <c:url var="provestExport" value="uvodni">

		   </c:url>
           <a href="<c:out value="${provestExport}" escapeXml="true" />">Export</a>
        </div>