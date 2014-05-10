<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="box">
	<h3 class="zvyraznit3">Zadávání činností:</h3>
	<p>
		Při přechodu na záložku <i>Zadávání činností</i> jsou automaticky
		zobrazeny již <i>Zadané činnosti</i> za zvolené období. Je zde také
		přehled pracovních poměrů v jejich hodinové dotaci. V případě kolize s
		nějakým omezením, je u dané činnosti zobrazen výstražný vykřičník. Již
		zadané činnosti je možné editovat nebo mazat.
	</p>
	<p>
		Samostatnou záložka má <i>Nová činnost</i>. Pod touto záložkou se
		nachází formulář pro zadání nové činnosti. Je zde nutné vyplnit datum,
		čas "od-do", pracovní poměr (ke kterému se činnost vztahuje), název
		činnosti, případně pak popisek k činnosti.
	</p>

</div>

<div class="box">
	<h3 class="zvyraznit3">Export dat:</h3>
	<p>
		Záložka <i>Export dat</i> slouží k vygenerování přislušného výkazu. Je
		zde nutné vyplnit o jaký pracovní poměr se jedná (potvrdit kliknutím na 
		ikonku uložit), datum "od-do" a je nutné zvolit šablonu příslušného výkazu. Pracovní výkazy se vždy
		generují za zvolené období. Vygenerovaný soubor je ve formátu ".xls". 
	</p>

</div>

<div class="box">
	<h3 class="zvyraznit3">Import dat:</h3>
	<p>
		Import dat slouží k přenosu činností z externího kalendáře do systému
		vykazování času. Konkrétně se jedná o Google kalendář. Při přechodu na
		záložku <i>Import dat</i> se automaticky zobrazí již <i>Zadané
			importy</i> za zvolené období. Již zadané importy si lze zpětně stáhnout
		nebo smazat.
	</p>
	<p>
		Pod dílčí záložkou <i>Nový import dat</i> můžeme importovat nové
		činnosti z externího kalendáře. Je nutné vybrat příslušný <b>.ics</b>
		soubor na disku a zvolit odpovídající šablonu.

	</p>

</div>

<div class="box">
	<h3 class="zvyraznit3">Nastavení číselníků:</h3>
	<p>
	Při přechodu na	záložku <i>Nastavení číselníků</i> se automaticky zobrazí číselník <i>Činnosti</i>.
	Je zde možné přidat novou činnost - její kód a název. Činnosti je možné upravovat, mazat ale pouze
	jen činnosti, které nebyli nikdy použity. Soubor činností obsahuje několik přednastavených činností.
	</p>
	<p>
	Dalším je číselník <i>Svátky</i>. Jedná se o státní svátky, které jsou pevně definované. Posouvá se
	jen Velikonoční pondělí a je nutné ho každý rok posunout. Je zde také prostor pro přidání dalšího svátku.
	Nově přidané svátky lze editovat a mazat. Tyto operace nelze provést s pevně definovanými svátky.
	</p>
	<p>
	Číselník <i>Pracovní poměry</i> zachycuje všechny pracovní poměry příslušného uživatele. Pro přidání
	nového poměru je nutné zadat kód, název, velikost úvazku, typ úvazku. Velikost úvazku se zadává
	v rozmezí 0-1, typ úvazku se volí z již připravených variant. Již zadané pracovní poměry lze editovat, mazat 
	pouze v případě, že k poměru nebyla zatím přiřazena žádná činnost.
	</p>
	<p>
	Do číselníku <i>Šablony</i> ukládají šablony potřebných pracovních výkazů. Pri vkládání nové šablony
	je nutné zadat kód, název, vybrat typ šablony (výběr z předem nadefinovaných variant), soubor 
	(odpovídající <b>.xls</b> soubor z pevného disku), a zvolit odpovídající pracovní poměr. Již 
	uložené šablony je možné si stáhnout, editovat označení nebo smazat. 
	Defaultně jsou v systému již uloženy šablony, které systém aktuálně podporuje. Uživatel si tuto šablonu stáhne,
	předvyplní potřebné a zpět nahraje. Při exportu pak zvolí příslušnou šablonu.
	</p>
	<p>
	K tomu, jak bude probíhat převod z externího kalendáře do systému, slouží číselník <i>Kalendář</i>.
	Při zadávání nové definice kalendáře do číselníku je nutné zadat kód, název, tag úvazku(pracovního poměru)
	, tag činnosti, tag popisu. Tyto tři tagy slouží k odlišení pracovního poměru, činnosti a popisu činnosti 
	ve strukturovaném zápisu v	externím kalendáři. Je nutné rozlišit od sebe pracovní poměr, činnost
	a popis činnosti. Poté je možné dekódovat externí kalendář a přenést činnosti (jejich popis a označení) do systému. 
	Definice kalendáře v číselníku je možné	editovat a mazat.
	</p>
</div>