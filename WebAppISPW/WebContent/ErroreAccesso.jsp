<%@ page language="java" contentType="text/html; charset=ISO=8859"
 	pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html> 
  <html>
    <head>
    <meta charset="ISO-8859-1">
<title>Error</title>
      <!--Import Google Icon Font-->
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
<form name="Errore" action="Errore" method="get">
<div class="card-panel red darken-2" align="center"><h3>ERRORE ACCESSO:</h3></div>

    <div class="col s12 m8 offset-m2 l6 offset-l3">
        <div class="card-panel grey lighten-5 z-depth-1">
          <div class="row valign-wrapper">
            <div class="col s2">
              <img src="img/err.jpg" alt="Errore" class="circle responsive-img"> <!-- notice the "circle" class -->
            </div>
            <div class="col s10">
             &emsp;
                  <span class="text"> ERRORE ACCESSO:
                
              </span>&emsp;&emsp;&emsp;&emsp;&emsp;  &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
          &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;&nbsp;&emsp;&emsp;&emsp;&emsp;&emsp;
 

     
                      <button class=" large btn waves-effect green darken-4" onClick="window.confirm('Vuoi veramente tornare alla schermata del login?')"type="submit"  value="logout" name="logout">Logout
    <i class="material-icons right arrow_back">logout</i>
  </button>
    
            

            </div>
          </div>
        </div>
      </div>
      
        

      <!--JavaScript at end of body for optimized loading-->
      <script type="text/javascript" src="js/materialize.min.js"></script>
   </form>
    </body>
  </html>