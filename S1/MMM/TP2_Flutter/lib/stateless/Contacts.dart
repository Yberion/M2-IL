import 'dart:math';

import 'package:english_words/english_words.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:validators/validators.dart';

class Contacts extends StatelessWidget {
  final List<String> _contacts = List();

  String getUpperCaseLetters(String str) {
    StringBuffer buffer = StringBuffer();

    for (int i = 0; i < str.length; ++i) {
      if (isUppercase(str[i])) {
        buffer.write(str[i]);
      }
    }

    return buffer.toString();
  }

  void generateRandomContacts() {
    List<WordPair> ls = generateWordPairs().take(2).toList();
    _contacts.addAll(ls.map((element) {
      return element.asPascalCase;
    }));
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(itemBuilder: (context, index) {
      if (index >= _contacts.length) {
        generateRandomContacts();
      }

      int randomNumber = Random().nextInt(99999999) + 600000000;

      return Card(
        child: ListTile(
          leading: CircleAvatar(
            backgroundColor: Colors.blueAccent,
            child: Text(getUpperCaseLetters(_contacts[index])),
          ),
          title: Text(_contacts[index]),
          subtitle: Text("+33" + randomNumber.toString()),
        ),
      );
    });
  }
}
