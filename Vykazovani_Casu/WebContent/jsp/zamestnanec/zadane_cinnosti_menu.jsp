<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            
<div class="box2">
  <div id="menu2">
  <table>
    <tr><td>
      <c:url var="novy" value="vkladani">

	  </c:url>
      <a href="<c:out value="${novy}" escapeXml="true" />">Nové činnosti</a>
    
      <c:url var="prehled" value="prehled">
	    
	  </c:url>
      <a href="<c:out value="${prehled}" escapeXml="true" />">Zadané činnost</a>
    </tr>
  </table>
  </div>
</div>