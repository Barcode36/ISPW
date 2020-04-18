<%@ page language="java" contentType="text/html; charset=ISO=8859"
 	pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html> 
  <html>
    <head>
    <meta charset="ISO-8859-1">
<title>Successo Prenotazione</title>
      <!--Import Google Icon Font-->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
<form name="SuccessPrenotazioneEsame" action="SuccessPrenotazioneEsame" method="get">
<div class="card-panel  brown" align="center"><h3> PRENOTAZIONE AVVENUTA CON SUCCESSO: </h3></div>
 <div class="container">
                      <span class=" text">  COME POSSO ESSERTI UTILE?:
                
              </span>&emsp;&emsp;&emsp;&emsp;&emsp;  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
          &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;
 
 &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;
 
              &emsp;&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;
   &emsp;&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;  &emsp;&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
              
     
                      <button class=" large btn waves-effect  green darken-4" onClick="window.confirm('Vuoi veramente fare un'altra prenotazione di un esame?')
                      "type="submit"  value="formEsame" name="formEsame">Prenotazione Esame
    <i class="material-icons right ">edit</i>
   
  </button>
     
        <button class=" large btn waves-effect green darken-4" onClick="window.confirm('Vuoi veramente tornare alla schermata Home Page ?')
        "type="submit"  value="homepageP" name="homepageP">HomePage Professore
    <i class="material-icons right ">menu</i>
    
  </button>
            
          <button class=" large btn waves-effect green darken-4" onClick="window.confirm('Vuoi veramente tornare alla schermata del login?')
          "type="submit"  value="logout" name="logout">Logout
    <i class="material-icons right arrow_back">logout</i>
  </button>
</div>
      <!--JavaScript at end of body for optimized loading-->
      <script type="text/javascript" src="js/materialize.min.js"></script>
   </form>
    </body>
            </html>