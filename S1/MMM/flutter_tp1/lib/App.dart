import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_tp1/stateful/StarButton.dart';
import 'package:flutter_tp1/stateless/IconButtonColoredText.dart';

class App extends StatelessWidget {
  final Container header = Container(
    //color: Colors.red,
    margin: const EdgeInsets.only(top: 20),
    child: Row(
      children: [
        // Titre + adresse
        Expanded(
          flex: 2,
          child: Padding(
            //color: Colors.blue,
            padding: const EdgeInsets.only(top: 5, right: 20, bottom: 5, left: 20),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text("Annexe café", style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16)),
                Padding(padding: const EdgeInsets.only(top: 3)),
                Text("18 Rue Saint-Michel, 35000 Rennes", style: TextStyle(color: Colors.grey))
              ],
            ),
          ),
        ),
        // étoiles
        Expanded(
          flex: 1,
          child: StarButton(defaultStarValue: 1337),
        ),
      ],
    ),
  );

  final Container menu = Container(
    //color: Colors.teal,
    margin: const EdgeInsets.only(top: 10),
    child: Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        IconButtonColoredText(text: 'APPELER', icon: Icons.phone),
        IconButtonColoredText(text: 'ITINERAIRE', icon: Icons.directions),
        IconButtonColoredText(text: 'SHARE', icon: Icons.share),
      ],
    ),
  );
  final Container text = Container(
    //color: Colors.deepPurpleAccent,
    margin: const EdgeInsets.only(top: 20, bottom: 20),
    padding: const EdgeInsets.only(left: 20, right: 20),
    child: Row(
      children: [
        Expanded(
            child: Text(
                "Ut justo odio, auctor id aliquet et, sollicitudin ac risus."
                " Vestibulum ac leo sed magna ullamcorper fermentum id ut augue."
                " Cras semper, elit a aliquet consectetur, dui sapien finibus elit, quis auctor libero est molestie magna."
                " Mauris congue nibh eget ante auctor, in commodo augue ultrices. Fusce sed ligula id mauris viverra tristique."
                " Phasellus felis dui, pretium in turpis nec, rhoncus aliquet mi. Curabitur finibus ipsum at massa laoreet luctus."
                " Duis magna enim, congue quis mauris non, tincidunt pharetra lectus. Fusce posuere vitae orci elementum iaculis."
                " Cras id ultrices erat. Praesent vitae cursus justo. Proin lacinia arcu ut orci pellentesque commodo."
                " Nulla dapibus, justo quis dapibus consequat, lectus tellus eleifend erat, vel pellentesque purus nisi vitae leo."
                " Integer metus dolor, tempus sed metus nec, condimentum semper diam.",
                textAlign: TextAlign.justify))
      ],
    ),
  );

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blueGrey,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text("Annexe café page"),
        ),
        body: ListView(
          children: [
            Image.asset('assets/images/cafe_restaurant.jpg', fit: BoxFit.fitWidth),
            // Titre, adresse, étoiles
            header,
            // Appeler, Itinéraire, Share
            menu,
            // Text
            text,
          ],
        ),
      ),
    );
  }
}
