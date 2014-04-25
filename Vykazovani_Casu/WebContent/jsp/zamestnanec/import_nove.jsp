<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true"%>

<jsp:include page="../zahlavi.jsp" flush="true" />
<jsp:include page="../horni.jsp" flush="true" />
<jsp:include page="../zamestnanec/menu.jsp" flush="true" />

<!-- hlavní část dokumentu -->
<div id="hlavni">
  <jsp:include page="../menu_top.jsp" flush="true" />
  <h2 class="stred">Import dat</h2>
  <jsp:include page="../zamestnanec/import_menu.jsp" flush="true" />


  <div class="box2">
    <c:url var="ulozit" value="novyImport">
      <c:param name="akce" value="${akce.importNahrat}" />
    </c:url>
    <form action="<c:out value="${ulozit}" escapeXml="true" />"
      method="post" enctype="multipart/form-data">
      <input type="hidden" name="objektId" value="${objekt.id}" />
      <div class="box">
        <table>
          <tr>
            <td class="zvyraznit2">Nový import:</td>
          </tr>
        </table>
        <table>
          <tr>
            <td style="width: 200px;"><b>*Soubor:</b></td>
            <td><input type="file" required="true" name="soubor" /></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" class="popisek">Povinné údaje označeny
              *</td>
          </tr>
        </table>
      </div>
      <div class="box" style="padding-top: 0px; padding-bottom: 0px;">
        <table>
          <tr>
            <td style="float: left;"><input
              onmouseover="tooltip(ulozitTooltip, this, 100)"
              type="image" alt="Odeslat" src="img/ulozit.png"
              name="odeslat" value="Odeslat" /></td>
            <c:url var="novy" value="novyImport">

            </c:url>
            <td style="float: left;"><a
              href="<c:out value="${novy}" escapeXml="true" />"><img
                onmouseover="tooltip(novyTooltip, this, 100)" alt="Nový"
                src="img/papir.png" /></a></td>
          </tr>
        </table>
      </div>
    </form>
  </div>

</div>

<jsp:include page="../spodni.jsp" flush="true" />