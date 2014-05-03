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
    var ulozitTooltip = 'Uložit.';
    var odstranitTooltip = 'Odstranit kalendář.';
    var stahnoutTooltip = 'Stáhnout kalendář.';
  </script>
<h2 class="stred">Přehled importů</h2>
<jsp:include page="../zamestnanec/import_menu.jsp" flush="true" />
<jsp:include page="../zahlavi_komponenta_datum.jsp" flush="true" />
<div class="box2">
  <table>
    <c:if test="${platneDatum != null}" ><tr><td class="hlaska_chyba">${chyby.platneDatumZprava}</td></tr></c:if>
    <c:if test="${platneDatumPorovnani != null}" ><tr><td class="hlaska_chyba">${chyby.platneDatumPorovnaniZprava}</td></tr></c:if>
  </table>
  <c:url var="ulozit" value="import">
    <c:param name="akce" value="${akce.importObdobi}"/>
  </c:url>
  <form action="<c:out value="${ulozit}" escapeXml="true" />" method="post">
  	<table>
    	<tr>
          <td style="width: 15px;"><b>Od:</b></td><td style="width: 80px;"><input id="datumOd" type="text" name="datumOd" value="${datumOd.datum}" class="datepicker<c:if test="${fn:contains(platneDatum,'datumOd') || fn:contains(platneDatumPorovnani,'datumOd')}"> povinne</c:if>"/></td>
          <td style="width: 15px;"><b>Do:</b></td><td style="width: 80px;"><input id="datumDo" type="text" name="datumDo" value="${datumDo.datum}" class="datepicker<c:if test="${fn:contains(platneDatum,'datumDo')}"> povinne</c:if>"/></td>
          <td><input onmouseover="tooltip(ulozitTooltip, this, 100)" type="image" alt="Uložit" src="img/ulozit.png" name="ulozit" value="Uložit"/></td>
      </tr>
    </table>
  </form>

  <c:forEach items="${kalendare}" var="kalendar">
  <table style="border: solid black 1px; margin-bottom: 20px; margin-top: 20px;">
    <tr><td><c:out value="${kalendar.datumImportu}" /> - ${fn:length(kalendar.kalendarCinnost)} záznamů.</td>
    <td class="vpravo">
        <c:url var="stahnout" value="import">
          <c:param name="akce" value="${akce.importStahnout}"/>
        </c:url>
        <form action="<c:out value="${stahnout}" escapeXml="true" />" method="post">
          <input type="hidden" name="objektId" value="${kalendar.id}" />
          <input onmouseover="tooltip(stahnoutTooltip, this, 100)" type="image" alt="Stáhnout" src="img/sipka2.png" name="stahnout" value="Stáhnout" class="vpravo2"/>
        </form>
    </td>
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