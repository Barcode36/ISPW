<%@ page language="java" contentType="text/html; charset=ISO=8859"
 	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="ISO-8859-1">
<title>PRENOTAZIONI STORICO ESAME</title>
      <!--Import Google Icon Font-->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>

<form class="col s12" name="TabellaAnnoAccademicoEsame" action="TabellaAnnoAccademicoEsame"method="post">

 <div class="card-panel  brown" align="center"><h3> VISUALIZZA STORICO PRENOTAZIONI ESAME : </h3></div>
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
   <div class="container">   
 <div class="row">

<span class="green-text"><strong>Inserisci l'anno accademico:</strong></span>
           
        <div class="row">

          <div class="input-field col s6">
      &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
      
            <input  id="input_text" size="35" placeholder="es.2009-2011 or 2018-2020 "type="text" name="annoAccademico"  data-length="9">
             </div>
        </div>
        
        
        
        
        </div>
        &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
<button class="btn waves-effect  green darken-4"   type="reset" name="action" > Cancella
    <i class="material-icons right">cancella</i>
  </button>
 &emsp;&emsp;&emsp;&emsp;
  <button class="btn waves-effect  green darken-4" type="submit" value="send">Avanti
    <i class="material-icons right">send</i>
  </button>    
  </div>
        </form>
    
</body>
</html>