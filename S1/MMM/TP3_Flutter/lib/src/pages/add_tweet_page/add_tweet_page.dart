import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class AddTweetPage extends StatelessWidget {
  const AddTweetPage({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final _textFieldController = TextEditingController();
    final _firebaseUser = context.watch<User>();

    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xff2E4060),
        title: const Text('Ajouter un Tweet'),
        leading: IconButton(
          onPressed: () {
            Navigator.pop(context);
          },
          icon: const Icon(Icons.arrow_back),
        ),
      ),
      backgroundColor: const Color(0xff2E4053),
      body: GestureDetector(
        behavior: HitTestBehavior.translucent,
        onTap: () {
          final currentFocus = FocusScope.of(context);

          if (!currentFocus.hasPrimaryFocus) {
            currentFocus.unfocus();
          }
        },
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.end,
            children: [
              Card(
                child: ListTile(
                  contentPadding: const EdgeInsets.only(left: 10, top: 5, bottom: 5, right: 10),
                  leading: CircleAvatar(
                    backgroundImage: _firebaseUser.photoURL != null && _firebaseUser.photoURL.isNotEmpty
                        ? NetworkImage(_firebaseUser.photoURL)
                        : const NetworkImage('https://www.iconninja.com/files/280/269/67/avatar-anonym-person-user-default-unknown-head-icon.png'),
                  ),
                  title: Text(_firebaseUser.displayName),
                  subtitle: TextField(
                    maxLength: 280,
                    controller: _textFieldController,
                    keyboardType: TextInputType.multiline,
                    minLines: 1,
                    maxLines: 7,
                  ),
                ),
              ),
              Container(
                margin: const EdgeInsets.only(right: 5),
                child: RaisedButton.icon(
                  onPressed: () {
                    final _tweet = {
                      'contenu': _textFieldController.text.toString(),
                      'pseudo': _firebaseUser.displayName,
                      'urlPhoto': _firebaseUser.photoURL,
                      'writtenDate': DateTime.now()
                    };
                    FirebaseFirestore.instance.collection('Tweets').add(_tweet);
                    Navigator.pop(context);
                  },
                  icon: const Icon(Icons.arrow_forward_rounded),
                  label: const Text('Tweeter'),
                  color: Colors.deepOrangeAccent,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
