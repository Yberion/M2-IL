import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'widgets/body_login.dart';

class LoginPage extends StatelessWidget {
  const LoginPage({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xff2E4053),
      body: SafeArea(
        child: BodyLogin(),
      ),
    );
  }
}
