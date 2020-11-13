import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:tp2_flutter/stateless/Contacts.dart';

class App extends StatelessWidget {
  Drawer drawer = Drawer(
    child: ListView(
      padding: EdgeInsets.zero,
      children: [
        UserAccountsDrawerHeader(
          accountName: Text("Looser", style: TextStyle(fontSize: 22)),
          accountEmail: Text("looser@MakeAmericaGreatAgain.com"),
          currentAccountPicture: CircleAvatar(
            backgroundImage: AssetImage("assets/images/trump.jpg"),
          ),
        ),
        ListTile(
          leading: CircleAvatar(
            backgroundImage: AssetImage("assets/images/1x.png"),
          ),
          title: Text('KEKWHAT'),
        ),
      ],
    ),
  );

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter TP2',
      theme: ThemeData(
        primarySwatch: Colors.blueGrey,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: Scaffold(
        appBar: AppBar(
          title: Text('My contact app'),
        ),
        drawer: drawer,
        body: Container(
            decoration: BoxDecoration(
              gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [Color(0xff26363c), Color(0xff2c5364)],
              ),
            ),
            child: Contacts()),
      ),
    );
  }
}
