<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="zahlavi.jsp" flush="true" />
<jsp:include page="horni.jsp" flush="true" />
        
        <!-- hlavní část dokumentu -->
        <div id="hlavni2">
			<div class="box" style="width: 450px; height: 350px; float: left; margin-left: 24px; margin-top: 80px;">
            <table>
			  <tr style="text-align: center;"><td><b class="nadpis">UŽIVATEL V RÁMCI ZČU</b></td></tr>
			  <tr style="text-align: center;"><td><b class="nadpis">Commit Capo333</b></td></tr>
			</table>
			<table>
                <tr><td><p>&nbsp</p></td></tr>
            </table>
            <table style="border: solid 2px black; height: 200px;">
                <tr><td>&nbsp</td><td>&nbsp</td></tr>
                <c:url var="vstup" value="login"></c:url><tr style="text-align: center;"><td colspan="2"><a href="<c:out value="${vstup}" escapeXml="true" />"><b>Vstoupit do systému</b></a></td></tr>
                <tr><td>&nbsp</td><td>&nbsp</td></tr>
            </table>
            </div>
            <div class="box" style="width: 450px; height: 350px; float: right; margin-right: 24px; margin-top: 80px;">
            <table>
              <tr style="text-align: center;"><td colspan="2"><b class="nadpis">UŽIVATEL MIMO ZČU</b></td></tr>
            </table>
            <table>
              	<c:if test="${requestScope.error == true}" ><tr style="text-align: center;"><td><p class="hlaska_chyba">Špatné uživatelské jméno nebo heslo!</p></td></tr></c:if>
              	<c:if test="${requestScope.error == null}" ><tr style="text-align: center;"><td><p class="hlaska_chyba">&nbsp</p></td></tr></c:if>
            </table>
            <table style="border: solid 2px black; height: 200px;">
              <form action="log" method="post">    
                <tr><td id="" style="padding-left: 10px;">Uživatelské jméno:</td><td><input id="text_login" type="text" name="login" value=""/></td></tr>
                <tr><td id="" style="padding-left: 10px;">Heslo:</td><td><input id="text_heslo" type="password" name="heslo" value=""/></td></tr>
                <tr><td>&nbsp</td><td><input id="tlacitko_prihlasit" type="submit" value="Přihlásit"></td></tr>
              </form>
            </table>
            </div>          
        </div>
        
<jsp:include page="spodni.jsp" flush="true" />