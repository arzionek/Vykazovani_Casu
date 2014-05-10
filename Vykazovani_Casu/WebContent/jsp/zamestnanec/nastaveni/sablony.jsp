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
  <script type="text/javascript">
    var ulozitTooltip = 'Uložit šablonu.';
    var upravitTooltip = 'Upravit údaje u šablony.';
    var stahnoutTooltip = 'Stáhnout soubor.';
    var odstranitTooltip = 'Odstranit šablonu.';
    var novyTooltip = 'Vyčistit formulář.';
  </script>
  <h2 class="stred">Nastavení číselníků:</h2>
  
  <jsp:include page="menu.jsp" flush="true" />
  	
  <div class="box2">
  <c:url var="ulozit" value="nastaveni">
    <c:param name="akce" value="${akce.nastaveniSablonVlozit}"/>
  </c:url>
  <form action="<c:out value="${ulozit}" escapeXml="true" />" method="post" <c:if test="${objekt.id == null}">enctype="multipart/form-data"</c:if>>
  <input type="hidden" name="objektId" value="${objekt.id}" /> 
  <div class="box">
  <table>
    <c:if test="${objekt.id != null}"><tr><td class="zvyraznit2">Upravit šablonu:</td></tr></c:if>
    <c:if test="${objekt.id == null}"><tr><td class="zvyraznit2">Nová šablona:</td></tr></c:if>
    <c:if test="${duplicitniZadani != null}" ><tr><td class="hlaska_chyba">${chyby.duplicitniZadaniZprava}</td></tr></c:if>
    <c:if test="${povinnyUdaj != null}" ><tr><td class="hlaska_chyba">${chyby.povinnyUdajZprava}</td></tr></c:if>
    <c:if test="${maximalniDelka != null}" ><tr><td class="hlaska_chyba">${chyby.maximalniDelkaZprava}</td></tr></c:if>
    <c:if test="${podporovanyFormat != null}" ><tr><td class="hlaska_chyba">${chyby.podporovanyFormatZprava}</td></tr></c:if>
    <c:if test="${vlozeniZaznamu != null}" ><tr><td class="hlaska_uspech">${oznameni.vlozeniZaznamuZprava}</td></tr></c:if>
  </table>
  <table>
    <tr><td style="width: 100px;"><b>*Kód:</b></td><td><input type="text" required="true" name="kod" value="${objekt.kod}" <c:if test="${fn:contains(duplicitniZadani,'kod') || fn:contains(povinnyUdaj,'kod') || fn:contains(maximalniDelka,'kod')}">class="povinne"</c:if>/></td></tr>
    <tr><td style="width: 100px;"><b>*Název:</b></td><td><input type="text" required="true" name="nazev" value="${objekt.nazev}" <c:if test="${fn:contains(duplicitniZadani,'nazev') || fn:contains(povinnyUdaj,'nazev') || fn:contains(maximalniDelka,'nazev')}">class="povinne"</c:if>/></td></tr>
    <tr><td style="width: 200px;"><b>*Typ šablony:</b></td><td><select name="typ">
      <c:forEach items="${typy}" var="typ">
        <option value="${typ}" <c:if test="${typ == objekt.typ}">selected="selected"</c:if>><c:out value="${typ}" /></option>  
      </c:forEach>
    </select></td></tr>
    <c:if test="${objekt.id == null}"><tr><td style="width: 200px;"><b>*Soubor:</b></td><td><input type="file" required="true" name="soubor" <c:if test="${fn:contains(povinnyUdaj,'soubor') || fn:contains(podporovanyFormat,'soubor')}" >class="povinne"</c:if>/></td></tr></c:if>
    <tr><td style="width: 200px;"><b>*Pracovní poměry:</b></td><td><select multiple="true" required="true" name="pomery" <c:if test="${fn:contains(povinnyUdaj,'pomery')}" >class="povinne"</c:if>>
      <c:forEach items="${pomery}" var="pomer">
        <option value="${pomer.id}" <c:forEach items="${objekt.pracovniPomer}" var="ep"><c:if test="${ep.id == pomer.id}" >selected="selected"</c:if></c:forEach>><c:out value="${pomer.kod}" /> - <c:out value="${pomer.nazev}" /></option>
      </c:forEach>
    </select></td></tr>
    <tr><td>&nbsp;</td></tr>
    <tr><td colspan="2" class="popisek"> Povinné údaje označeny * </td></tr>  
  </table>
  </div>
  <div class="box" style="padding-top: 0px; padding-bottom: 0px;">  
  <table>
    <tr><td style="float: left;"><input onmouseover="tooltip(ulozitTooltip, this, 100)" type="image" alt="Uložit" src="img/ulozit.png" name="ulozit" value="Uložit"/></td>
    <c:url var="novy" value="nastaveni">
	    <c:param name="akce" value="${akce.nastaveniSablon}"/>
    </c:url>
    <td style="float: left;"><a href="<c:out value="${novy}" escapeXml="true" />"><img onmouseover="tooltip(novyTooltip, this, 100)" alt="Nový" src="img/papir.png" /></a></td></tr>
  </table> 
  </div>
  </form>
  </div>  
  
  <div class="box2">
  <table>
    <tr><td class="zvyraznit2">Uložené šablony:</td></tr> 
  </table>
  <c:forEach items="${objekty}" var="o">
  <table style="border: solid black 1px; margin-bottom: 20px">
    <tr><td><c:out value="${o.kod}" /> - <c:out value="${o.nazev}" />, <c:out value="${o.typ}" /></td>
    <td class="vpravo">
      <c:url var="stahnout" value="nastaveni">
        <c:param name="akce" value="${akce.nastaveniSablonStahnout}"/>
      </c:url>  
      <form action="<c:out value="${stahnout}" escapeXml="true" />" method="post">
        <input type="hidden" name="objektId" value="${o.id}" />
        <input onmouseover="tooltip(stahnoutTooltip, this, 100)" type="image" alt="Stáhnout" src="img/xls.png" name="stahnout" value="Stáhnout" class="vpravo2"/>
      </form>
    </td>
    <td class="vpravo">
      <c:if  test="${o.uzivatel != null}">
        <c:url var="upravit" value="nastaveni">
	        <c:param name="akce" value="${akce.nastaveniSablonUpravit}"/>
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
	      <c:param name="akce" value="${akce.nastaveniSablonSmazat}"/>
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