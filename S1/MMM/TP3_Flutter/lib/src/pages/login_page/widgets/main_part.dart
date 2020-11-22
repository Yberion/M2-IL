import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../../../template_widgets/text_field_custom.dart';

class MainPart extends StatelessWidget {
  const MainPart({
    @required TextEditingController emailController,
    @required TextEditingController passwordController,
    Key key,
  })  : _emailController = emailController,
        _passwordController = passwordController,
        super(key: key);

  final TextEditingController _emailController;
  final TextEditingController _passwordController;

  @override
  Widget build(BuildContext context) {
    return Expanded(
      flex: 8,
      child: Container(
        decoration: const BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.only(
              bottomLeft: Radius.circular(40),
              bottomRight: Radius.circular(40),
            )),
        child: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.only(
              top: 150,
              bottom: 50,
            ),
            child: Column(
              children: [
                const Text('Connexion', style: TextStyle(fontSize: 35, color: Colors.blueGrey)),
                Column(
                  children: [
                    TextFieldCustom(
                      controller: _emailController,
                      textInputType: TextInputType.emailAddress,
                      hintText: 'Email',
                      obscureText: false,
                    ),
                    TextFieldCustom(
                      controller: _passwordController,
                      textInputType: TextInputType.text,
                      hintText: 'Mot de passe',
                      obscureText: true,
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
