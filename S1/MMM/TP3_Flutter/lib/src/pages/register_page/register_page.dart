import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'widgets/body_register.dart';

class RegisterPage extends StatelessWidget {
  const RegisterPage({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xff2E4060),
        leading: IconButton(
          onPressed: () {
            Navigator.pop(context);
          },
          icon: const Icon(Icons.arrow_back),
        ),
      ),
      backgroundColor: const Color(0xff2E4053),
      body: SafeArea(
        child: BodyRegister(),
      ),
    );
  }
}
