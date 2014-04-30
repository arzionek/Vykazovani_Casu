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
  var novyTooltip = 'Nový export.';
</script>
<h2 class="stred">Export dat</h2>
<div class="box2"> 
  <c:url var="ulozit" value="export">

  </c:url>
  <form action="<c:out value="${ulozit}" escapeXml="true" />" method="post">
  <input type="hidden" name="objektId" value="${objekt.id}" />
  <div class="box">
  <table>
  
  </table>
  <table>
    <tr><td style="width: 200px;"><b>*Pracovní poměr:</b></td><td><select name="pomer" <c:if test="${objekt.pracovniPomer.id != null}">disabled="disabled"</c:if>>
      <c:forEach items="${pomery}" var="pomer">
        <option value="${pomer.id}" <c:if test="${pomer.id == objekt.pracovniPomer.id}">selected="selected"</c:if>><c:out value="${pomer.kod}" /> - <c:out value="${pomer.nazev}" /></option>  
      </c:forEach>
    </select></td></tr>
  </table>
  <table>
    <tr><td style="width: 200px;"><b>*Datum od:</b></td><td><input type="text" required="true" name="datumOd" value="${objekt.datumOd2}" class="datepicker<c:if test="${fn:contains(povinnyUdaj,'datumOd') || fn:contains(platneDatumPorovnani,'datumOd') || fn:contains(platneDatum,'datumOd')}"> povinne</c:if>"/></td></tr>
    <tr><td style="width: 200px;"><b>*Datum do:</b></td><td><input type="text" required="true" name="datumDo" value="${objekt.datumDo2}" class="datepicker<c:if test="${fn:contains(povinnyUdaj,'datumDo') || fn:contains(platneDatum,'datumDo')}"> povinne</c:if>"/></td></tr>
    <tr><td style="width: 200px;"><b>*Šablona:</b></td><td><select name="sablona" required="true" <c:if test="${objekt.pracovniPomer.id == null}">disabled="disabled"</c:if>>
      <c:forEach items="${sablony}" var="sablona">
        <option value="${sablona.id}" <c:if test="${sablona.id == objekt.sablona.id}">selected="selected"</c:if>><c:out value="${sablona.typ}" /> - <c:out value="${pomer.nazev}" /></option>  
      </c:forEach>
    </select></td></tr>
    <tr><td colspan="2" class="popisek"> Povinné údaje označeny * </td></tr>
  </table>
  </div>
  <div class="box" style="padding-top: 0px; padding-bottom: 0px;">
  <table>
    <tr><td style="float: left;"><input onmouseover="tooltip(ulozitTooltip, this, 100)" type="image" alt="Uložit" src="img/ulozit.png" name="ulozit" value="Uložit"/></td>
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