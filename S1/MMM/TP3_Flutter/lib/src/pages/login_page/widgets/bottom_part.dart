import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../../../services/firebase_services/auth_service.dart';
import '../../register_page/register_page.dart';

class BottomPart extends StatelessWidget {
  const BottomPart({
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
      flex: 2,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          RaisedButton.icon(
            onPressed: () {
              context.read<AuthService>().signIn(
                    email: _emailController.text.trim(),
                    password: _passwordController.text.trim(),
                  );
            },
            icon: const Icon(Icons.arrow_forward_rounded),
            label: const Text('Connexion'),
            color: Colors.deepOrangeAccent,
          ),
          const Padding(padding: EdgeInsets.only(top: 5)),
          GestureDetector(
            onTap: () {
              Navigator.push<RegisterPage>(context, MaterialPageRoute<RegisterPage>(builder: (context) => const RegisterPage()));
            },
            child: const Text(
              'Pas de compte ? Inscrivez-vous.',
              style: TextStyle(color: Colors.white70, fontWeight: FontWeight.bold),
            ),
          ),
        ],
      ),
    );
  }
}
