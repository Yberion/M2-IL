import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../../../services/firebase_services/auth_service.dart';

class HomeDrawer extends StatelessWidget {
  const HomeDrawer({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final _firebaseUser = context.watch<User>();

    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: [
          UserAccountsDrawerHeader(
            decoration: const BoxDecoration(
              color: Color(0xff2E4060),
            ),
            accountName: Text(_firebaseUser.displayName, style: const TextStyle(fontSize: 22)),
            accountEmail: Text(_firebaseUser.email),
            currentAccountPicture: CircleAvatar(
              backgroundImage: _firebaseUser.photoURL != null && _firebaseUser.photoURL.isNotEmpty
                  ? NetworkImage(_firebaseUser.photoURL)
                  : const NetworkImage('https://www.iconninja.com/files/280/269/67/avatar-anonym-person-user-default-unknown-head-icon.png'),
            ),
            otherAccountsPictures: [
              IconButton(
                icon: const Icon(Icons.power_settings_new),
                onPressed: () {
                  Navigator.pop(context);
                  context.read<AuthService>().signOut();
                },
              )
            ],
          ),
        ],
      ),
    );
  }
}
