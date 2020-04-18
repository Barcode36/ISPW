 <%@ page language="java" contentType="text/html; charset=ISO=8859"
 	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title>Prenota un Evento</title>
   <!--Import Google Icon Font-->
      
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
<body>

  <div class="card-panel brown" align="center"><h3>Prenota un Evento:</h3></div>
  
           
  
  
<form class="form-inline" action="PrenotaEvento" method="post">
<div class="row">
 <div class="container">
   <span class="brown-text">INFORMAZIONI AULA:
              </span>
      <div class="row">
        <div class="input-field col s6">
             <input id="proiettori" name="proiettori"  required="required" type="text"placeholder="es.0-2" class="validate">
          <label class="green-text"for="proiettori">N° Proiettori</label>
        </div>
        <div class="row">
        <div class="input-field col s6">
             <input id="posti" type="text" required="required" name="posti" placeholder="es.1-750" class="validate">
          <label for="posti"class="green-text">N° Posti</label>      
      </div>
        </div>
        </div>
       

  
  <fieldset>
 	<p>
      <label>
        &nbsp;&nbsp;&nbsp;<input type="checkbox" class="filled-in" name="lav" value="true" checked="checked" />
        <span class="green-text">Lavagna</span>
      </label>

    </p>
    

    <p>
      <label>
      &nbsp;&nbsp;&nbsp;  <input type="checkbox" name="mic" class="filled-in" value="true"checked="checked" />
        <span class="green-text">Microfono</span>
      </label>
    </p>
    <p>
      <label>
      &nbsp;&nbsp;&nbsp;  <input type="checkbox" name="prese"class="filled-in" value="true" checked="checked" />
        <span class="green-text"> Prese Individuali</span>
      </label>
    </p>
     <p>
      <label>
      &nbsp;&nbsp;&nbsp;  <input type="checkbox" name="lavInt" class="filled-in" value="true"checked="checked" />
        <span class="green-text">Lavagna Interattiva</span>
      </label>
    </p> 
    <p>
      <label>
      &nbsp;&nbsp;&nbsp;  <input type="checkbox" name="ethernet" class="filled-in" value="true"checked="checked" />
        <span class="green-text"> Cavo Ethernet</span>
      </label>
    </p> 
  </fieldset>
 </div>
 
      <div class="container">
      <div class="row">
        <div class="input-field col s2" >
   
    <label class="green-text">Laboratorio</label>
           &emsp;&emsp;&emsp;&emsp;<select  name="Laboratorio">
      <option value="" disabled selected>Choose your option</option>
      <option value="LabDisegno">LabDisegno</option>
      <option value="LabElettronica">LabElettronica</option>
      <option value="LabInformatica">LabInformatica</option>
    </select>
  </div>
        </div>
        <div class="row">
        <div class="input-field col s2">
        <label class="green-text">Tipo Aula:</label>
             &emsp;&emsp;&emsp;&emsp;<select  name="TipoAula">
      <option value="" disabled selected>Choose your option</option>
      <option value="Didattica">Didattica</option>
      <option value="Convegni">Convegni</option>
      
    </select >&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
    
  </div>     
   </div>
      </div>
       </div>
        <div class="container">
<span class="brown-text">INFORMAZIONI EVENTO:
              </span>  
   
   <div class="row">
        <div class="input-field col s2">
        
    <label class="green-text">Fascia Oraria:</label>
         &emsp;&emsp;&emsp;&emsp;<select  name="Orario">
      <option value="" disabled selected>Choose your option</option>
      <option value="Fascia9_11">Fascia9_11</option>
      <option value="Fascia11_13"> Fascia11_13 </option>
            <option value="Fascia14_16">Fascia14_16</option>
      <option value="Fascia16_18">Fascia16_18</option>
            
    </select>
  </div>
        </div>
      <div class="row">
        <div class="input-field col s2">
        
    <label class="green-text">Evento:</label>
           &emsp;&emsp;&emsp;&emsp;<select   name="Evento">
      <option value="" disabled selected>Choose your option</option>
      <option value="SedutaDiLaurea">SedutaDiLaurea</option>
      <option value="TestIngresso">TestIngresso</option>
    </select>
  </div>
        </div>
        <div class="row">
        <div class="input-field col s2">
        
    <label class="green-text">Macroarea:</label>
                  &emsp;&emsp;&emsp;&emsp;<select  name="Macroarea">
      <option value="" disabled selected>Choose your option</option>
      <option value="Medicina">Medicina</option>
      <option value="Giurisprudenza">Giurisprudenza</option>
      <option value="Scienze">Scienze</option>
      <option value="Lettere">Lettere</option>
         <option value="Ingegneria">Ingegneria</option>
      <option value="Economia">Economia</option>
      
    </select>
  </div>     
      </div>
    &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
  <div class="row">
        <div class="input-field col s6">
             <input id="corsoStudi" name="corsoStudi" required="required" type="text"placeholder="es.Informatica" class="validate">
          <label for="corsoStudi" class="green-text">Corso Studi</label>
        </div>
        
        <div class="row">
        <div class="input-field col s6">
             <input id="titolo" name="titolo" type="text" required="required" placeholder="Inserisci Titolo" class="validate">
          <label for="titolo" class="green-text">Titolo Evento</label>      
      </div>
        </div>
        </div>
      <div class="row">
        <h6 class="green-text">Data dell'evento</h6>
          <input type="date" required="required" name="date" placeholder="es. 06/08/2020 or 06-08-2020" class="datepicker" >
          <br />
      </div></div>
 &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
<button class="btn waves-effect  green darken-4"   type="reset" name="cancella" > Cancella
    <i class="material-icons right">cancella</i>
  </button>
 &emsp;&emsp;&emsp;&emsp;
  <button class="btn waves-effect  green darken-4" type="submit" onClick="window.confirm('Vuoi confermare la scelta fatta?')" value="prenotazioni" name="prenota">Prenota
    <i class="material-icons right">send</i>
  </button>
  </form>
       <script src="script.js"></script>
    <!--JavaScript at end of body for optimized loading-->
      <script type="text/javascript" src="js/materialize.min.js"></script>
  <script type = "text/javascript"
         src = "https://code.jquery.com/jquery-2.1.1.min.js"></script>           
      <script src = "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js">
      </script> 
      
      <script>
         $(document).ready(function() {
            $('select').material_select();
         });
      </script>
</body>
</html>