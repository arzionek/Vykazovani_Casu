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
    var ulozitTooltip = 'Uložit činnost.';
    var novyTooltip = 'Nová činnost.';
  </script>
	<h2 class="stred">Nová činnost:</h2>
	
	<jsp:include page="../zamestnanec/zadane_cinnosti_menu.jsp" flush="true" />
	
	<div class="box2"> 
    <c:url var="ulozit" value="vytvoreni">
      <c:param name="akce" value="${akce.vytvoreniVlozit}"/>
    </c:url>
  <form action="<c:out value="${ulozit}" escapeXml="true" />" method="post">
  <input type="hidden" name="objektId" value="${objekt.id}" />
  <div class="box">
  <table>
    <c:if test="${objekt.id != null}"><tr><td class="zvyraznit2">Upravit činnost:</td></tr></c:if>
    <c:if test="${objekt.id == null}"><tr><td class="zvyraznit2">Nová činnost:</td></tr></c:if>
    <c:if test="${duplicitniZadani != null}" ><tr><td class="hlaska_chyba">${chyby.duplicitniZadaniZprava}</td></tr></c:if> 
    <c:if test="${povinnyUdaj != null}" ><tr><td class="hlaska_chyba">${chyby.povinnyUdajZprava}</td></tr></c:if>
    <c:if test="${platneDatum != null}" ><tr><td class="hlaska_chyba">${chyby.platneDatumZprava}</td></tr></c:if>
    <c:if test="${platneDatumPorovnani != null}" ><tr><td class="hlaska_chyba">${chyby.platneDatumPorovnaniZprava}</td></tr></c:if>
  </table>
  <table>
    <tr><td style="width: 200px;"><b>*Datum:</b></td><td><input type="text" required="true" name="datum" id="datepicker" value="${objekt.datum2}" <c:if test="${fn:contains(duplicitniZadani,'casOd') || fn:contains(povinnyUdaj,'datum') || fn:contains(platneDatum,'datum')}">class="povinne"</c:if>/></td></tr>
    <tr><td style="width: 200px;"><b>*Čas od (hh:mm):</b></td><td><input type="text" required="true" name="casOd" value="${objekt.casOd2}" <c:if test="${fn:contains(duplicitniZadani,'casOd') || fn:contains(platneDatumPorovnani,'casOd') || fn:contains(povinnyUdaj,'casOd') || fn:contains(platneDatum,'casOd')}">class="povinne"</c:if>/></td></tr>
    <tr><td style="width: 200px;"><b>*Čas do (hh:mm):</b></td><td><input type="text" required="true" name="casDo" value="${objekt.casDo2}" <c:if test="${fn:contains(povinnyUdaj,'casDo') || fn:contains(platneDatum,'casDo')}">class="povinne"</c:if>/></td></tr>
    <tr><td style="width: 200px;"><b>*Pracovní poměr:</b></td><td><select name="pomer" required="true" <c:if test="${fn:contains(povinnyUdaj,'pomer')}">class="povinne"</c:if>>
      <c:forEach items="${pomery}" var="pomer">
        <option value="${pomer.id}" <c:if test="${pomer.id == objekt.pracovniPomer.id}">selected="selected"</c:if>><c:out value="${pomer.kod}" /> - <c:out value="${pomer.nazev}" /></option>  
      </c:forEach>
    </select></td></tr>
    <tr><td style="width: 200px;"><b>*Pracovní činnost:</b></td><td><select name="cinnost" required="true" <c:if test="${fn:contains(povinnyUdaj,'cinnost')}">class="povinne"</c:if>>
      <c:forEach items="${cinnosti}" var="cinnost">
        <option value="${cinnost.id}" <c:if test="${cinnost.id == objekt.cinnost.id}">selected="selected"</c:if>><c:out value="${cinnost.kod}" /> - <c:out value="${cinnost.nazev}" /></option>  
      </c:forEach>
    </select></td></tr>
    <tr><td style="width: 200px;">Popis činnosti:</td><td><textarea name="popis" style="width: 400px;">${objekt.popis}</textarea></td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td colspan="2" class="popisek"> Povinné údaje označeny * </td></tr>
  </table>
  </div>
  <div class="box" style="padding-top: 0px; padding-bottom: 0px;">
  <table>
    <tr><td style="float: left;"><input onmouseover="tooltip(ulozitTooltip, this, 100)" type="image" alt="Uložit" src="img/ulozit.png" name="ulozit" value="Uložit"/></td>
    <c:url var="novy" value="vytvoreni">

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