<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>

<jsp:include page="../zahlavi.jsp" flush="true" />
<jsp:include page="../horni.jsp" flush="true" />
<jsp:include page="../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
	<jsp:include page="../menu_top.jsp" flush="true" />
	<script type="text/javascript">
    var upravitTooltip = 'Upravit činnost.';
    var odstranitTooltip = 'Odstranit činnost.';
  </script>
	<h2 class="stred">Přehled činností:</h2>
	
	<jsp:include page="../zamestnanec/zadane_cinnosti_menu.jsp" flush="true" />
	
	<div class="box2">
  <c:forEach items="${objekty}" var="o">
  <table style="border: solid black 1px; margin-bottom: 20px; margin-top: 20px;">
    <tr><td><c:out value="${o.datum2}" />&nbsp;<c:out value="${o.casOd2}" /> - <c:out value="${o.casDo2}" /></td><td></td><td></td></tr>
    <tr><td style="padding-left: 20px;"><c:out value="${o.pracovniPomer.kod}" /> - <c:out value="${o.cinnost.nazev}" /></td>
    <c:url var="upravit" value="prehled">
      <c:param name="akce" value="${akce.prehledUpravit}"/>
    </c:url>
    <td class="vpravo">
    <form action="<c:out value="${upravit}" escapeXml="true" />" method="post">
      <input type="hidden" name="objektId" value="${o.id}" />
      <input onmouseover="tooltip(upravitTooltip, this, 100)" type="image" alt="Upravit" src="img/upravit.png" name="upravit" value="Upravit" class="vpravo2"/>
    </form>
    </td>
    <td class="vpravo">
        <c:url var="odstranit" value="prehled">
          <c:param name="akce" value="${akce.prehledSmazat}"/>
        </c:url>
        <form action="<c:out value="${odstranit}" escapeXml="true" />" method="post">
          <input type="hidden" name="objektId" value="${o.id}" />
          <input onmouseover="tooltip(odstranitTooltip, this, 100)" type="image" alt="Odstranit" src="img/odstranit.png" name="odstranit" value="Odstranit" class="vpravo2"/>
        </form>
    </td>
  </table>
  </c:forEach>
  </div>
	
</div>  

<jsp:include page="../spodni.jsp" flush="true" />