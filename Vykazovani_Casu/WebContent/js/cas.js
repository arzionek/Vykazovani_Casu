function format(cislo){
	if(cislo<10) return "0" + cislo;
	else return cislo;
};
    	
function ziskatCas (){
	window.document.getElementById("cas").innerHTML = format(hodina) + ":" + format(minuta) + ":" + format(sekunda);
	if(sekunda == 59){
		sekunda = 0;
		if(minuta == 59){
			minuta = 0;
			if(hodina == 23) hodina = 0;
			else hodina++;
		}else
			minuta++;
	}else
		sekunda++;
};
