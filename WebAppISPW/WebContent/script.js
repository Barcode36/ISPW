
 $('.datepicker').pickadate({
    selectMonths: true, 
    selectYears: 15,
    format: 'dd/mm/yyyy'
  });
  
  $(function(){
    console.log(new Date().toISOString().substring(0,10))
  })