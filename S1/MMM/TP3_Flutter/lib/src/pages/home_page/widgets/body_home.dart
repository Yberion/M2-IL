import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class BodyHome extends StatelessWidget {
  const BodyHome({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    //final _firebaseUser = context.watch<User>();

    return StreamBuilder<QuerySnapshot>(
      stream: FirebaseFirestore.instance.collection('Tweets').orderBy('writtenDate', descending: true).snapshots(),
      builder: (context, snapshot) {
        if (!snapshot.hasData) {
          return const Center(
            child: CircularProgressIndicator(),
          );
        }
        return ListView(
          children: snapshot.data.docs.map((document) {
            return Card(
              child: ListTile(
                contentPadding: const EdgeInsets.only(left: 10, top: 5, bottom: 5),
                leading: CircleAvatar(
                  backgroundImage: document.data().containsKey('urlPhoto')
                      ? NetworkImage(document['urlPhoto'].toString())
                      : const NetworkImage('https://www.iconninja.com/files/280/269/67/avatar-anonym-person-user-default-unknown-head-icon.png'),
                ),
                title: document.data().containsKey('pseudo') ? Text(document['pseudo'].toString()) : const Text(''),
                subtitle: document.data().containsKey('contenu') ? Text(document['contenu'].toString()) : const Text(''),
              ),
            );
          }).toList(),
        );
      },
    );
  }
}
