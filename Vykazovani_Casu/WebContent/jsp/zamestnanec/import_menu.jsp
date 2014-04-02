<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            
<div class="box2">
  <div id="menu2">
  <table>
    <tr><td>
      <c:url var="novy" value="import">
		<c:param name="akce" value="${akce.odhlasit}"/>
	  </c:url>
      <a href="<c:out value="${novy}" escapeXml="true" />">Nový import dat</a>
    
      <c:url var="prehled" value="import">
	    
	  </c:url>
      <a href="<c:out value="${prehled}" escapeXml="true" />">Zadané importy</a>
    </tr>
  </table>
  </div>
</div>