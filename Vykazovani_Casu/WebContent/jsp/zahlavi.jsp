<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
        
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="cs" lang="cs">

   <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Author" content="Vit Stepanek" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<![if !IE]>
		<link rel="stylesheet" href="css/styl.css" type="text/css" />
	<![endif]>	
	<!--[if IE]>
		<link rel="stylesheet" type="text/css" href="css/styl2.css" title="style" />
	<![endif]-->
    <link rel="stylesheet" type="text/css" href="css/tooltip.css" title="style" />
    <link rel="shortcut icon" href="img/favicon.ico"/>
    <script language="javascript" style="text/javascript" src="js/celkem.js"></script>
    <script language="javascript" style="text/javascript" src="js/tooltip.js"></script>
    <script language="javascript" style="text/javascript" src="js/cas.js"></script>
    
    <!-- <link rel="stylesheet" href="js/jquery-ui.css" type="text/css" media="all" /> 
    <link rel="stylesheet" href="js/ui.theme.css" type="text/css" media="all" /> 
    <script src="js/jquery.min.js" type="text/javascript"></script> 
    <script src="js/jquery-ui.min.js" type="text/javascript"></script> -->
    
    
    <link rel="stylesheet" href="js/jquery-ui.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/jquery-ui.js"></script>

    
    
    <title>Vykazování času</title>
    <!--[if IE]>
    <script type="text/javascript">
      var myDivId = 'tooltip';
      var movingX = 10; 
      var movingY = 10;     
      var positionX = 0;
      var positionY = 0;
      var divElement;
      divElement = document.createElement('div');
      divElement.setAttribute('id', myDivId);
      divElement.style.visibility = 'hidden';
      divElement.style.position = 'fixed';
      
      divElement.style.left = '0px';
      divElement.style.top = '0px';
    </script> 
    <script type="text/javascript">
      var hodina = ${cas.hodina};
	  var minuta = ${cas.minuta};
	  var sekunda = ${cas.sekunda};		
    </script>
    <![endif]-->
    <![if !IE]>
    <script type="text/javascript">
      var myDivId = 'tooltip';
      var movingX = 10; 
      var movingY = 10;     
      var positionX = 0;
      var positionY = 0;
      var divElement;
      divElement = document.createElement('div');
      divElement.setAttribute('id', myDivId);
      divElement.style.visibility = 'hidden';
      divElement.style.position = 'absolute';
      
      divElement.style.left = '0px';
      divElement.style.top = '0px';
    </script> 
    <script type="text/javascript">
      var hodina = ${cas.hodina};
	    var minuta = ${cas.minuta};
	    var sekunda = ${cas.sekunda};		
    </script> 
    <![endif]>
   </head>