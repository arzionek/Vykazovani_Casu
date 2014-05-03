<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page trimDirectiveWhitespaces="true" %>

<jsp:include page="../zahlavi.jsp" flush="true" />
<jsp:include page="../horni.jsp" flush="true" />
<jsp:include page="../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
<jsp:include page="../menu_top.jsp" flush="true" />
	<script type="text/javascript">
    var odstranitTooltip = 'Odstranit kalendář.';
  </script>
<h2 class="stred">Přehled importů</h2>
<jsp:include page="../zamestnanec/import_menu.jsp" flush="true" />
<div class="box2">
  <c:forEach items="${kalendare}" var="kalendar">
  <table style="border: solid black 1px; margin-bottom: 20px; margin-top: 20px;">
    <tr><td><c:out value="${kalendar.datumImportu}" /> - ${fn:length(kalendar.kalendarCinnost)} záznamů.</td>
    <td class="vpravo">
        <c:url var="odstranit" value="import">
          <c:param name="akce" value="${akce.importSmazat}"/>
        </c:url>
        <form action="<c:out value="${odstranit}" escapeXml="true" />" method="post">
          <input type="hidden" name="objektId" value="${kalendar.id}" />
          <input onmouseover="tooltip(odstranitTooltip, this, 100)" type="image" alt="Odstranit" src="img/odstranit.png" name="odstranit" value="Odstranit" class="vpravo2"/>
        </form>
    </td></tr>
  </table>
  </c:forEach>
  </div>
</div>  
<jsp:include page="../spodni.jsp" flush="true" />