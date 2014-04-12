<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            
<div class="box2">
  <div id="menu2">
  <table>
    <tr><td>
      <c:url var="svatky" value="nastaveni">
		    <c:param name="akce" value="${akce.odhlasit}"/>
	    </c:url>
      <a href="<c:out value="${svatky}" escapeXml="true" />">Svátky</a>
	  
	    <c:url var="pomery" value="nastaveni">
		    <c:param name="akce" value="${akce.odhlasit}"/>
	    </c:url>
      <a href="<c:out value="${pomery}" escapeXml="true" />">Pracovní poměry</a>
      
      <c:url var="cinnosti" value="nastaveni">
		    <c:param name="akce" value="${akce.nastaveniCinnosti}"/>
	    </c:url>
      <a href="<c:out value="${cinnosti}" escapeXml="true" />">Činnosti</a>
      
      <c:url var="sablony" value="nastaveni">
		    <c:param name="akce" value="${akce.odhlasit}"/>
	    </c:url>
      <a href="<c:out value="${sablony}" escapeXml="true" />">Šablony</a>
      
      <c:url var="kalendare" value="nastaveni">
		    <c:param name="akce" value="${akce.odhlasit}"/>
	    </c:url>
      <a href="<c:out value="${kalendare}" escapeXml="true" />">Kalendáře</a>
    </tr>
  </table>
  </div>
</div>