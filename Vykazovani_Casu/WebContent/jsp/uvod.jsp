<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2 class="stred">Úvodní stránka:</h2>
<div class="box2">
  <div class="box">
  <table>
  <tr>
    <td>Datum a čas serveru: <span id="datum">${cas.datum}&nbsp</span><span id="cas"></span></td>
	  <script type="text/javascript">
	    ziskatCas();
	  	window.setInterval("ziskatCas()", 1000);
	  </script>  
    <td class="bunka2"><form action="#navod" method="post"><input type="image" src="img/otaznik.png" alt="návod"/></form></td></tr>
  </table>
  </div>
  <c:forEach items="${system}" var="sys">
    <div class="box">
      <table>${sys}</table>
    </div>
  </c:forEach>
</div>
			

			