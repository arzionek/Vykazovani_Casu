function getMouseXY(e) {
    var browserIE = document.all?true:false
    if (!browserIE) document.captureEvents(Event.MOUSEMOVE)
    positionX = browserIE?event.clientX + document.body.scrollLeft:e.pageX;
    positionY = browserIE?event.clientY + document.body.scrollTop:e.pageY;
    setPosition();
    return true;
}

function setPosition(){
    var windowWidth = 0;
    if( typeof( window.innerWidth ) == 'number' ) {
        windowWidth = window.innerWidth;
    } else if( document.documentElement && document.documentElement.clientWidth ) {
        windowWidth = document.documentElement.clientWidth;
    } else if( document.body && document.body.clientWidth  ) {
        windowWidth = document.body.clientWidth;
    }
    if( ( positionX +  parseInt(divElement.style.width ) + movingX + 50 ) > windowWidth ){
        positionX -= ( movingX + parseInt(divElement.style.width ) );
    }
    
    
    
    
    divElement.style.left =  ( movingX + positionX ) + 'px';
    divElement.style.top = ( movingY + positionY ) + 'px';
}

function setOpacity( element, alpha ){
    var el = document.getElementById(element);
    
    if( el.style.opacity != undefined ){
        el.style.opacity = alpha;
    }
    else if( el.style.MozOpacity != undefined ){ 
        el.style.MozOpacity = alpha;
    }
    else if( el.style.filter != undefined ){
        el.style.filter = "alpha(opacity=0)";
        el.filters.alpha.opacity = ( alpha * 100 );
    }
    return true;
}

function pulseOn(){
    for( i = 0; i <= 9; i++ ){
        setTimeout("setOpacity('" + myDivId + "'," + i/10 + ");", 25*i);
    }   
}

function tooltip( str, el, width ){
    if( !document.getElementById(myDivId) )
        document.body.appendChild(divElement);
    //divElement.style.width = width + 'px';
    setOpacity(myDivId, 0 );
    divElement.style.visibility = 'visible';
    pulseOn();
    divElement.innerHTML = str;
    el.onmousemove=getMouseXY;
    el.onmouseout = hidetip;
}

function hidetip(){
    divElement.style.visibility = 'hidden';
}