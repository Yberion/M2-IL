$(document).ready(function() {
 
    var city = localStorage.getItem("city"); //on récupere la variable localStorage ayant pour clé city, puis on la met dans une variable
    var cardSelector = $("#card"); //on mets notre sélecteur dans une variable
   
    function getWeather() { // on crée une fonction qui récupere la météo avec les instructions suivantes
      if (city == null) { // on teste si la variable city est nulle
        cardSelector.append("<p>Vous n'avez pas encore renseign&eacute; de ville.</p>"); // on affiche un message dans la card
      } else { // sinon ...
        $("#card *:not(div)").remove();
   
        var myAPPID = "7e181b27cf94564bb5e54c5402ff9bb7"; //ici on déclare notre APPID pour OpenWeatherMap
   
       
        $.getJSON("http://api.openweathermap.org/data/2.5/weather?APPID=" + myAPPID + "&q=" + city, function(result) { // on mets le résultat dans une variable result qui vaut le code JSON qu'on voit dans le navigateur
          var cityName = result.name; // le nom de la ville est directement accesible donc pas de souci
          var weatherType = result.weather[0].main; // la description du temps est dans le tableau weather (un tableau est défini par des []), on vise le premier (0 = le premier en programmation), puis on prend la valeur de main
          var iconCode = result.weather[0].icon; // Meme chose qu'au dessus sauf qu'on prend la valeur de icon
          var temp = result.main.temp; // cette fois ci on va dans main qui n'est pas un tableau donc pas de '[]', on va de main a temp sans souci
          var tempInCelsius = (temp - 273.15).toFixed(1); // notre temperature est en Kelvin donc on effectue notre soustration pour l'avoir en Celsius, puis le toFixed permet d'arrondir une valeur, le 1 correspond à un chiffre apres la virgule
   
          // ici on rempli la card avec nos valeurs, premierement la liste d'information, puis ensuite on affiche l'image avec le code icone
          cardSelector.append("<ul><li>Ville : " + cityName + "</li><li>Type de météo : " + weatherType + "</li><li> Température : " + tempInCelsius + " &deg;C</li></ul>");
          cardSelector.append("<img src='img/" + iconCode + ".png' alt='Weather Icon' width='80px' height='80px'>");
   
          // voila notre utilisateur voit les informations météo de sa ville
        });
      }
    }
   
    function submitForm() { // on crée une fonction qui récupere la valeur du formulaire
      var mycity = $('input').val(); // on récupere la valeur de notre input avec .val() et on la mets dans une variable
      if (mycity.length >= 3) { // si la variable donc la ville de l'utilisateur est plus grande ou egale que 3 caracteres alors ...
        localStorage.setItem("city", mycity); // on crée une variable localStorage, avec pour clé city et comme valeur la ville de l'utilisateur
        city = mycity; // on donne la ville à la variable city qui est utilisée dans la fonction getWeather
        getWeather(); // on appelle la fonction getWeather pour récuperer la météo de cette ville, ville qui est stockée dans la variable city
      } else { // si le champs fait 2 caracteres ou moins on ...
        alert('empty field'); // affiche une erreur
      }
    }
   
    $('#getWeather').on('touchstart', function() { // quand on commence à toucher le bouton avec l'id getWeather, alors ...
      submitForm(); // ... on appelle la fonction submitForm qui va traiter ce qu'il y a dans le champ de la ville
    });
   
    $('form').submit(function(event) { // quand on soumet le formulaire, c'est à dire qu'on appuie sur la touche Entrée, alors ...
      event.preventDefault(); // ici on annule le comportement par défault qui est de recharger la page quand on soumet un formulaire
      submitForm(); // ... on appelle la fonction submitForm qui va traiter ce qu'il y a dans le champ de la ville
    });
   
    getWeather(); // ici on appelle à l'allumage de l'application la fonction getWeather
  });