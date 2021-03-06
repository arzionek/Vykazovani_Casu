<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page trimDirectiveWhitespaces="true" %>

<jsp:include page="../zahlavi.jsp" flush="true" />
<jsp:include page="../horni.jsp" flush="true" />
<jsp:include page="../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
<jsp:include page="../menu_top.jsp" flush="true" />
<jsp:include page="../zahlavi_komponenta_datum.jsp" flush="true" />
<script type="text/javascript">
  var ulozitTooltip = 'Provést export.';
  var ulozit2Tooltip = 'Nastavit pracovní poměr.';
  var novyTooltip = 'Vyčistit formulář.';
</script>
<h2 class="stred">Export dat:</h2>
<div class="box2"> 
  <c:url var="ulozit" value="export">
    <c:param name="akce" value="${akce.exportPomer}"/>
  </c:url>
  <form action="<c:out value="${ulozit}" escapeXml="true" />" method="post">
  <div class="box">
  <table>
    <c:if test="${povinnyUdaj != null}" ><tr><td class="hlaska_chyba">${chyby.povinnyUdajZprava}</td></tr></c:if>
    <c:if test="${platneDatum != null}" ><tr><td class="hlaska_chyba">${chyby.platneDatumZprava}</td></tr></c:if>
    <c:if test="${chybnySouborExport != null}" ><tr><td class="hlaska_chyba">${chyby.chybnySouborExportZprava}</td></tr></c:if>
  </table>
  <table>
    <tr><td style="width: 200px;"><b>*Pracovní poměr:</b></td><td><select name="pomer" <c:if test="${objekt.pracovniPomer.id != null}">disabled="disabled"</c:if>>
      <c:forEach items="${pomery}" var="pomer">
        <option value="${pomer.id}" <c:if test="${pomer.id == objekt.pracovniPomer.id}">selected="selected"</c:if>><c:out value="${pomer.kod}" /> - <c:out value="${pomer.nazev}" /></option>  
      </c:forEach>
    </select></td>
    <c:if test="${objekt.pracovniPomer.id == null}"><td style="float: right;"><input onmouseover="tooltip(ulozit2Tooltip, this, 100)" type="image" alt="Uložit" src="img/ulozit.png" name="ulozit" value="Uložit"/></td></c:if>
    </tr>
  </table>
  </form>
  <c:url var="ulozit" value="export">
    <c:param name="akce" value="${akce.exportXls}"/>
  </c:url>
  <form action="<c:out value="${ulozit}" escapeXml="true" />" method="post">
  <input type="hidden" name="pomer" value="${objekt.pracovniPomer.id}" />
  <table>
    <tr><td style="width: 200px;"><b>*Měsíc:</b></td><td><input type="text" required="true" name="mesic" <c:if test="${objekt.pracovniPomer.id == null}">disabled="disabled"</c:if> value="${objekt.datumOd2}" class="date-picker<c:if test="${fn:contains(povinnyUdaj,'mesic') || fn:contains(platneDatum,'mesic')}"> povinne</c:if>"/></td></tr>
    <tr><td style="width: 200px;"><b>*Šablona:</b></td><td><select name="sablona" <c:if test="${objekt.pracovniPomer.id == null}">disabled="disabled"</c:if> <c:if test="${fn:contains(povinnyUdaj,'sablona') || fn:contains(chybnySouborExport,'sablona')}">class="povinne"</c:if>>
      <c:forEach items="${sablony}" var="sablona">
        <option value="${sablona.id}" <c:if test="${sablona.id == objekt.sablonaVykaz.id}">selected="selected"</c:if>><c:out value="${sablona.typ}" /> - <c:out value="${sablona.kod}" /></option>  
      </c:forEach>
    </select></td></tr>
    <tr><td colspan="2" class="popisek"> Povinné údaje označeny * </td></tr>
  </table>
  </div>
  <div class="box" style="padding-top: 0px; padding-bottom: 0px;">
  <table>
    <tr><td style="float: left;">
    <c:if test="${objekt.pracovniPomer.id != null}"><input onmouseover="tooltip(ulozitTooltip, this, 100)" type="image" alt="Exportovat" src="img/xls.png" name="exportovat" value="Exportovat"/></c:if>
    <c:if test="${objekt.pracovniPomer.id == null}"><img src="img/xls2.png" alt="Exportovat"/></c:if>
    </td>
    <c:url var="novy" value="export">

    </c:url>
    <td style="float: left;">
    <a href="<c:out value="${novy}" escapeXml="true" />"><img onmouseover="tooltip(novyTooltip, this, 100)" alt="Nový" src="img/papir.png" /></a>
    </td></tr>
  </table>
  </div>
  </form>
  </div>
</div>  

<jsp:include page="../spodni.jsp" flush="true" />