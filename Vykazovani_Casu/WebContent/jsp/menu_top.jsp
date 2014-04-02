<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<script type="text/javascript">
  			var odhlasitTooltip = 'Odhlásit se.';
		</script>
        <div id="menu_top" class="box2" style="margin-top: 5px; margin-bottom: 15px; padding: 0px;">           
            <c:url var="odhlaseni" value="uvodni">
			  <c:param name="akce" value="${akce.odhlasit}"/>
			</c:url>
			<table style="margin: 0px; width: 744px;">
			<form action="<c:out value="${odhlaseni}" escapeXml="true" />" method="post">
              <tr>
                <td style="float: right"><input onmouseover="tooltip(odhlasitTooltip, this, 100)" type="image" src="img/odhlasit.png" alt="Odhlásit" name="odhlasit" value=""/></td>
                <td style="float: right"><b>Přihlášen jako:&nbsp&nbsp&nbsp</b> 
                  ${uzivatel.celeJmeno}
                </td>
              </tr>  
            </form>
            </table>
        </div>