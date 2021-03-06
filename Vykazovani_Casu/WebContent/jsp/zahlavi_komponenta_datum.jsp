<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>

  <script type="text/javascript">
    $(function() {
     $( "#datepicker" ).datepicker();
    });
    $(function() {
     $( ".datepicker" ).datepicker();
    });
    $(function() {
      $('.date-picker').datepicker( {
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        dateFormat: 'mm.yy',
        onClose: function(dateText, inst) { 
            var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
            var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
            $(this).datepicker('setDate', new Date(year, month, 1));
        }
      }).focus(function () {
        $(".ui-datepicker-calendar").hide(); 
      });
    });
    
    
    $.datepicker.regional['cs'] = {
      closeText: 'Zavřít',
      prevText: 'Předchozí',
      nextText: 'Další',
      currentText: 'Aktuální',
      monthNames: ['Leden','Únor','Březen','Duben','Květen','Červen', 'Červenec','Srpen','Září','Říjen','Listopad','Prosinec'],
      monthNamesShort: ['Le','Ún','Bř','Du','Kv','Čn', 'Čc','Sr','Zá','Ří','Li','Pr'],
      dayNames: ['Neděle','Pondělí','Úterý','Středa','Čtvrtek','Pátek','Sobota'],
      dayNamesShort: ['Ne','Po','Út','St','Čt','Pá','So',],
      dayNamesMin: ['Ne','Po','Út','St','Čt','Pá','So'],
      weekHeader: 'Sm',
      dateFormat: '${datepickerFormat}',
      firstDay: 1,
      isRTL: false,
      showMonthAfterYear: false,
      yearSuffix: ''};
    $.datepicker.setDefaults($.datepicker.regional['cs']);
    var changeMonth = $('.selector').datepicker('option', 'changeMonth');
    $('.selector').datepicker('option', 'changeMonth', true);
    var changeYear = $('.selector').datepicker('option', 'changeYear');
    $('.selector').datepicker('option', 'changeYear', true);
    $("#datepicker").datepicker();
  </script>