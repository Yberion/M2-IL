import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../../../services/firebase_services/auth_service.dart';
import '../../../wrappers/auth_wrapper/auth_wrapper.dart';

class BottomPart extends StatelessWidget {
  const BottomPart({
    @required TextEditingController emailController,
    @required TextEditingController passwordController,
    @required TextEditingController pseudoController,
    @required TextEditingController photoUrlController,
    Key key,
  })  : _emailController = emailController,
        _passwordController = passwordController,
        _pseudoController = pseudoController,
        _photoUrlController = photoUrlController,
        super(key: key);

  final TextEditingController _emailController;
  final TextEditingController _passwordController;
  final TextEditingController _pseudoController;
  final TextEditingController _photoUrlController;

  @override
  Widget build(BuildContext context) {
    return Expanded(
      flex: 2,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          RaisedButton.icon(
            onPressed: () {
              context.read<AuthService>().signUp(
                    email: _emailController.text.trim(),
                    password: _passwordController.text.trim(),
                    pseudo: _pseudoController.text.trim(),
                    photoUrl: _photoUrlController.text.trim(),
                  );
              Navigator.pop(context);
              Navigator.push<AuthWrapper>(context, MaterialPageRoute<AuthWrapper>(builder: (context) => const AuthWrapper()));
            },
            icon: const Icon(Icons.arrow_forward_rounded),
            label: const Text('Inscription'),
            color: Colors.deepOrangeAccent,
          ),
          const Padding(padding: EdgeInsets.only(top: 5)),
        ],
      ),
    );
  }
}
