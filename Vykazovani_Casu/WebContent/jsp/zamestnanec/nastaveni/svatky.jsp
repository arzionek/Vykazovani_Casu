<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page trimDirectiveWhitespaces="true" %>

<jsp:include page="../../zahlavi.jsp" flush="true" />
<jsp:include page="../../horni.jsp" flush="true" />
<jsp:include page="../../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
  <jsp:include page="../../menu_top.jsp" flush="true" />
  <jsp:include page="../../zahlavi_komponenta_datum.jsp" flush="true" />
  <script type="text/javascript">
    var ulozitTooltip = 'Uložit svátek.';
    var upravitTooltip = 'Upravit svátek.';
    var odstranitTooltip = 'Odstranit svátek.';
    var novyTooltip = 'Nový svátek.';
  </script>
  <h2 class="stred">Nastavení číselníků:</h2>
  
  <jsp:include page="menu.jsp" flush="true" />
  
  <div class="box2">	
    <c:url var="ulozit" value="nastaveni">
      <c:param name="akce" value="${akce.nastaveniSvatkyVlozit}"/>
  </c:url>
  <form action="<c:out value="${ulozit}" escapeXml="true" />" method="post">
  <input type="hidden" name="objektId" value="${objekt.id}" />
  <div class="box">
  <table>
    <c:if test="${objekt.id != null}"><tr><td class="zvyraznit2">Upravit svátek:</td></tr></c:if>
    <c:if test="${objekt.id == null}"><tr><td class="zvyraznit2">Nový svátek:</td></tr></c:if>
    <c:if test="${duplicitniZadani != null}" ><tr><td class="hlaska_chyba">${chyby.duplicitniZadaniZprava}</td></tr></c:if> 
    <c:if test="${povinnyUdaj != null}" ><tr><td class="hlaska_chyba">${chyby.povinnyUdajZprava}</td></tr></c:if>
    <c:if test="${platneDatum != null}" ><tr><td class="hlaska_chyba">${chyby.platneDatumZprava}</td></tr></c:if>
    <c:if test="${maximalniDelka != null}" ><tr><td class="hlaska_chyba">${chyby.maximalniDelkaZprava}</td></tr></c:if>
  </table>
  <table>
    <tr><td style="width: 100px;"><b>*Kód:</b></td><td><input type="text" required="true" name="kod" value="${objekt.kod}" <c:if test="${fn:contains(duplicitniZadani,'kod') || fn:contains(povinnyUdaj,'kod') || fn:contains(maximalniDelka,'kod')}">class="povinne"</c:if>/></td></tr>
    <tr><td style="width: 100px;"><b>*Název:</b></td><td><input type="text" required="true" name="nazev" value="${objekt.nazev}" <c:if test="${fn:contains(duplicitniZadani,'nazev') || fn:contains(povinnyUdaj,'nazev') || fn:contains(maximalniDelka,'nazev')}">class="povinne"</c:if>/></td></tr>
    <tr><td style="width: 100px;"><b>*Datum:</b></td><td><input type="text" required="true" name="datum" id="datepicker" value="${objekt.datum2}" <c:if test="${fn:contains(duplicitniZadani,'datum') || fn:contains(platneDatum,'datum')}">class="povinne"</c:if>/></td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td colspan="2" class="popisek"> Povinné údaje označeny * </td></tr>
  </table>
  </div>
  <div class="box" style="padding-top: 0px; padding-bottom: 0px;">
  <table>
    <tr><td style="float: left;"><input onmouseover="tooltip(ulozitTooltip, this, 100)" type="image" alt="Uložit" src="img/ulozit.png" name="ulozit" value="Uložit"/></td>
    <c:url var="novy" value="nastaveni">
      <c:param name="akce" value="${akce.nastaveniSvatky}"/>
    </c:url>
    <td style="float: left;"><a href="<c:out value="${novy}" escapeXml="true" />"><img onmouseover="tooltip(novyTooltip, this, 100)" alt="Nový" src="img/papir.png" /></a></td></tr>
  </table>
  </div>
  </form>
  </div>
  
  <div class="box2">
  <table>
    <tr><td class="zvyraznit2">Uložené svátky:</td></tr>
  </table>
  <c:forEach items="${objekty}" var="o">
  <table style="border: solid black 1px; margin-bottom: 20px">
    <tr><td><c:out value="${o.datum2}" />, <c:out value="${o.kod}" /> - <c:out value="${o.nazev}" /></td>
    <td class="vpravo">
      <c:if test="${o.uzivatel != null}">
        <c:url var="upravit" value="nastaveni">
          <c:param name="akce" value="${akce.nastaveniSvatkyUpravit}"/>
        </c:url>
        <form action="<c:out value="${upravit}" escapeXml="true" />" method="post">
          <input type="hidden" name="objektId" value="${o.id}" />
          <input onmouseover="tooltip(upravitTooltip, this, 100)" type="image" alt="Upravit" src="img/upravit.png" name="upravit" value="Upravit" class="vpravo2"/>
        </form>
      </c:if>
      <c:if test="${o.uzivatel == null}">
        <img src="img/upravit2.png" alt="Upravit" class="vpravo2"/>
      </c:if>
    </td>
    <td class="vpravo">
      <c:if test="${o.uzivatel != null}">
        <c:url var="odstranit" value="nastaveni">
          <c:param name="akce" value="${akce.nastaveniSvatkySmazat}"/>
        </c:url>
        <form action="<c:out value="${odstranit}" escapeXml="true" />" method="post">
          <input type="hidden" name="objektId" value="${o.id}" />
          <input onmouseover="tooltip(odstranitTooltip, this, 100)" type="image" alt="Odstranit" src="img/odstranit.png" name="odstranit" value="Odstranit" class="vpravo2"/>
        </form>
      </c:if>
      <c:if test="${o.uzivatel == null}">
        <img src="img/odstranit2.png" alt="Odstranit" class="vpravo2"/>
      </c:if>
    </td>
  </table>
  </c:forEach>
  </div>
</div>

<jsp:include page="../../spodni.jsp" flush="true" />
