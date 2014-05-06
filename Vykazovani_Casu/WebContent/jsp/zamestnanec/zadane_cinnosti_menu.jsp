<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
            
<div class="box2">
  <div id="menu2">
  <table>
    <tr><td>
      <c:url var="novy" value="vytvoreni">

	  </c:url>
      <a href="<c:out value="${novy}" escapeXml="true" />">Nová činnost</a>
    
      <c:url var="prehled" value="prehled">
	    
	  </c:url>
      <a href="<c:out value="${prehled}" escapeXml="true" />">Zadané činnosti</a>
    </tr>
  </table>
  </div>
</div>