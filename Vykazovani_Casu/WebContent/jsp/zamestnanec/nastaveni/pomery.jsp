<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../../zahlavi.jsp" flush="true" />
<jsp:include page="../../horni.jsp" flush="true" />
<jsp:include page="../../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
  <jsp:include page="../../menu_top.jsp" flush="true" />
  <script type="text/javascript">
    var ulozitTooltip = 'Uložit pracovní poměr.';
    var upravitTooltip = 'Upravit pracovní poměr.';
    var odstranitTooltip = 'Odstranit pracovní poměr.';
    var novyTooltip = 'Nový pracovní poměr';
  </script>
  <h2 class="stred">Nastavení systému:</h2>
  
  <jsp:include page="menu.jsp" flush="true" />
  	
  <div class="box2">	
  <div class="box">
  <table>
    <c:if test="${objekt.id != -1}"><tr><td class="zvyraznit2">Upravit pracovní poměr:</td></tr></c:if>
    <c:if test="${objekt.id == -1}"><tr><td class="zvyraznit2">Nový pracovní poměr:</td></tr></c:if>
    <c:if test="${error2 == true}" ><tr><td class="hlaska_chyba">Označené údaje musí být vyplněny!</td></tr></c:if> 
    <c:if test="${error3 == true}" ><tr><td class="hlaska_chyba">Musí být zadáno kladné číslo!</td></tr></c:if>
  </table>
  <c:url var="ulozit" value="nastaveni">
	  <c:param name="akce" value="${akce.nastaveniPomeruVlozit}"/>
  </c:url>
  <form action="<c:out value="${ulozit}" escapeXml="true" />" method="post">
  <input type="hidden" name="objektId" value="${objekt.id}" /> 
  <table>
    <tr><td style="width: 100px;"><b>*Kód:</b></td><td><input type="text" required="true" name="kod" value="${objekt.kod}" <c:if test="${error2 == true}">class="povinne"</c:if>/></td></tr>
    <tr><td style="width: 100px;"><b>*Název:</b></td><td><input type="text" required="true" name="nazev" value="${objekt.nazev}" <c:if test="${error2 == true}">class="povinne"</c:if>/></td></tr>
    <tr><td style="width: 160px;"><b>*Velikost úvazku:</b></td><td><input type="text" required="true" name="velikost" value="${objekt.velikostUvazku}" <c:if test="${error2 == true || error3 == true}">class="povinne"</c:if>/></td></tr>
    <tr><td>&nbsp</td></tr>
    <tr><td colspan="2" class="popisek"> Povinné údaje označeny * </td></tr>  
  </table>
  </div>
  <div class="box" style="padding-top: 0px; padding-bottom: 0px;">  
  <table>
    <tr><td style="float: left;"><input onmouseover="tooltip(ulozitTooltip, this, 100)" type="image" alt="Uložit" src="img/ulozit.png" name="ulozit" value="Uložit"/></td></td>
    </form>
    <c:url var="novy" value="nastaveni">
	    <c:param name="akce" value="${akce.nastaveniPomeru}"/>
    </c:url>
    <form action="<c:out value="${novy}" escapeXml="true" />" method="post">
      <td style="float: left;"><input onmouseover="tooltip(novyTooltip, this, 100)" type="image" alt="Nový" src="img/papir.png" name="novy" value="Nový"/></td></tr>
    </form>  
  </table> 
  </div>
  </div>  
  
  <div class="box2">
  <table>
    <tr><td class="zvyraznit2">Uložené pracovní poměry:</td></tr> 
  </table>
  <c:forEach items="${objekty}" var="o">
  <table style="border: solid black 1px; margin-bottom: 20px">
    <tr><td>${o.kod} - ${o.nazev} - ${o.velikostUvazku}</td>
    <c:url var="upravit" value="nastaveni">
	  <c:param name="akce" value="${akce.nastaveniPomeruUpravit}"/>
    </c:url>  
    <form action="<c:out value="${upravit}" escapeXml="true" />" method="post">
      <input type="hidden" name="objektId" value="${o.id}" />
      <td class="vpravo"><input onmouseover="tooltip(upravitTooltip, this, 100)" type="image" alt="Upravit" src="img/upravit.png" name="upravit" value="Upravit" class="vpravo2"/></td>
    </form>
    <td class="vpravo">
      <c:if test="${empty o.kalendarCinnost && empty o.sablonaVykaz}">
        <c:url var="odstranit" value="nastaveni">
	      <c:param name="akce" value="${akce.nastaveniPomeruSmazat}"/>
        </c:url>   
        <form action="<c:out value="${odstranit}" escapeXml="true" />" method="post">
          <input type="hidden" name="objektId" value="${o.id}" />
          <input onmouseover="tooltip(odstranitTooltip, this, 100)" type="image" alt="Odstranit" src="img/odstranit.png" name="odstranit" value="Odstranit" class="vpravo2"/>
        </form>
      </c:if>
      <c:if test="${!empty o.kalendarCinnost || !empty o.sablonaVykaz}">
        <img src="img/odstranit2.png" alt="Odstranit" class="vpravo2"/>
      </c:if>
    </td>    
  </table> 
  </c:forEach> 
  </div> 
</div>

<jsp:include page="../../spodni.jsp" flush="true" />