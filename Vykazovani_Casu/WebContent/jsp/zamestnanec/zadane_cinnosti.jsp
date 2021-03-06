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
	<script type="text/javascript">
	  var ulozitTooltip = 'Uložit.';
    var upravitTooltip = 'Upravit činnost.';
    var odstranitTooltip = 'Odstranit činnost.';
  </script>
	<h2 class="stred">Zadávání činností:</h2>
	
	<jsp:include page="../zamestnanec/zadane_cinnosti_menu.jsp" flush="true" />
	<jsp:include page="../zahlavi_komponenta_datum.jsp" flush="true" />
	<div class="box2">
	  <table>
	    <c:if test="${platneDatum != null}" ><tr><td class="hlaska_chyba">${chyby.platneDatumZprava}</td></tr></c:if>
	  </table>
	  <c:url var="ulozit" value="prehled">
	    <c:param name="akce" value="${akce.prehledObdobi}"/>
	  </c:url>
	  <form action="<c:out value="${ulozit}" escapeXml="true" />" method="post">
	  <table>
	    <tr>
	      <td style="width: 15px;"><b>Měsíc:</b></td><td style="width: 80px;"><input id="mesic" type="text" name="mesic" value="${datumOd.datum}" class="date-picker<c:if test="${fn:contains(platneDatum,'mesic') || fn:contains(platneDatumPorovnani,'mesic')}"> povinne</c:if>"/></td>
	      <td><input onmouseover="tooltip(ulozitTooltip, this, 100)" type="image" alt="Uložit" src="img/ulozit.png" name="ulozit" value="Uložit"/></td>
	    </tr>
	  </table>
	  </form>
	</div>
	<div class="box2">
	<table>
      <tr><td><b><c:out value="${obdobi}" />:</b></td></tr>
  </table>
	<table>
	<c:forEach items="${pomery}" var="p">
    <tr><td style="width: 250px;"><span style="font-style: italic;"><c:out value="${p.kod}" /> - <c:out value="${p.nazev}" /></span></td>
      <td>fond: <c:out value="${p.mesicniFond}" /> hod., 
      <span <c:if test="${fn:contains(p.varovani,chyby.pomerMesicniFond)}" >style="color: red;"</c:if>>odpracováno: <c:out value="${p.odpracovano}" /> hod.</span>
      <c:if test="${fn:contains(p.varovani,chyby.pomerMesicniFond)}" >&nbsp;<img onmouseover="tooltip('${chyby.pomerMesicniFondZprava}', this, 100)" src="img/varovani.png" alt="Varování" /></c:if>
      </td>
    </tr>
  </c:forEach>
  </table>
	</div>
	<div class="box2">
  <c:forEach items="${objekty}" var="o">
  <table style="border: solid black 1px; margin-bottom: 20px; margin-top: 20px;">
    <tr><td <c:if test="${fn:contains(o.varovani,chyby.praceVikend) || fn:contains(o.varovani,chyby.praceSvatek)}" >style="color: red;"</c:if>>
      <c:out value="${o.datum3}" />,&nbsp;<c:out value="${o.casOd2}" /> - <c:out value="${o.casDo2}" />
      <c:if test="${fn:contains(o.varovani,chyby.praceVikend)}" >&nbsp;<img onmouseover="tooltip('${chyby.praceVikendZprava}', this, 100)" src="img/varovani.png" alt="Varování" /></c:if>
      <c:if test="${fn:contains(o.varovani,chyby.praceSvatek)}" >&nbsp;<img onmouseover="tooltip('${chyby.praceSvatekZprava}', this, 100)" src="img/varovani.png" alt="Varování" /></c:if></td>
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
    </td></tr>
    <tr><td style="padding-left: 20px;"><c:out value="${o.pracovniPomer.kod}" /> - <c:out value="${o.cinnost.nazev}" /></td><td></td><td></td></tr>
    <tr><td style="padding-left: 20px;"><c:out value="${o.popis}" /></td><td></td><td></td></tr>
  </table>
  </c:forEach>
  </div>
	
</div>  

<jsp:include page="../spodni.jsp" flush="true" />