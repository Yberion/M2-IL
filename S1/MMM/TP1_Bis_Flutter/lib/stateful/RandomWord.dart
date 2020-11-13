import 'package:english_words/english_words.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class RandomWord extends StatefulWidget {
  const RandomWord({Key key}) : super(key: key);

  @override
  _RandomWord createState() => _RandomWord();
}

class _RandomWord extends State<RandomWord> {
  WordPair wordPair = WordPair.random();

  void _randomizeText() {
    setState(() {
      this.wordPair = WordPair.random();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Text(wordPair.asPascalCase),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _randomizeText,
        tooltip: 'Refresh',
        child: Icon(Icons.refresh),
      ),
    );
  }
}
