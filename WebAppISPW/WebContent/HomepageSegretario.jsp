 <%@ page language="java" contentType="text/html; charset=ISO=8859"
 	pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html> 
  <html>
    <head>
    <meta charset="ISO-8859-1">
<title>HomepageSegretario</title>
      <!--Import Google Icon Font-->
      
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
    <form name="MenuSegretario" action="MenuSegretario" method="get">
    
<div class="card-panel brown" align="center"><h3>HomePageSegretario:</h3></div>

    <div class="col s12 m8 offset-m2 l6 offset-l3">
        <div class="card-panel grey lighten-5 z-depth-1">
          <div class="row valign-wrapper">
            <div class="col s2">
              <img src="img/segretaria.jpg" alt="" class="circle responsive-img"> <!-- notice the "circle" class -->
            </div>
            <div class="col s10">
     &emsp;
              <span class=" green darken-4-text"> SALVE SEGRETARIO:
                
              </span>
        &emsp;&emsp;&emsp;&emsp;&emsp;  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
          &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;
 
                      <button class=" large btn waves-effect brown" type="submit"  value="logout" name="logout"onClick="window.confirm('Vuoi veramente tornare alla schermata del login?')">Logout
    <i class="material-icons right arrow_back">logout</i>
  </button>
    
  </div>
            </div>
          </div>
        </div>
   
    

                   <div class="divider"></div>
  <div class="section">
    <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prenota un Evento
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="btn waves-effect   green darken-4" type="submit" name="evento" onClick="window.confirm('Vuoi confermare la scelta fatta?')"value="evento">Prenota
    <i class="material-icons right create"></i>
  </button></h5>
  </div>
  <div class="divider"></div>
  <div class="section">
    <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Visualizza Prenotazioni Attive 
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    <button class="btn waves-effect  green darken-4" type="submit"  name="prenotazioniAttive" onClick="window.confirm('Vuoi confermare la scelta fatta?')"value="prenotazioniAttive">Visualizza
    <i class="material-icons right description"></i>  
  </button></h5>
  </div>
  <div class="divider"></div>
  <div class="section">
    <h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Visualizza Storico Prenotazioni
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;
   <button class="btn waves-effect  green darken-4"  type="submit"name="prenotazioniStorico" onClick="window.confirm('Vuoi confermare la scelta fatta?')" value="prenotazioniStorico">Visualizza
    <i class="material-icons right description"></i>
  </button></h5>
   </div>
      <!--JavaScript at end of body for optimized loading-->
      <script type="text/javascript" src="js/materialize.min.js"></script>
      </form>
    </body>
  </html>