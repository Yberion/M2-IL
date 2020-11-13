import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:tp1_bis_flutter/stateful/RandomWord.dart';

class App extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter TP1 Bis',
      theme: ThemeData(
        primarySwatch: Colors.blueGrey,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: RandomWord(),
    );
  }
}

