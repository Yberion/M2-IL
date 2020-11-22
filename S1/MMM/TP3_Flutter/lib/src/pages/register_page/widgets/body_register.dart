import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'bottom_part.dart';
import 'main_part.dart';

class BodyRegister extends StatelessWidget {
  BodyRegister({
    Key key,
  }) : super(key: key);

  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _pseudoController = TextEditingController();
  final TextEditingController _photoUrlController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      behavior: HitTestBehavior.translucent,
      onTap: () {
        final currentFocus = FocusScope.of(context);

        if (!currentFocus.hasPrimaryFocus) {
          currentFocus.unfocus();
        }
      },
      child: Column(
        children: [
          MainPart(
            emailController: _emailController,
            passwordController: _passwordController,
            pseudoController: _pseudoController,
            photoUrlController: _photoUrlController,
          ),
          BottomPart(
            emailController: _emailController,
            passwordController: _passwordController,
            pseudoController: _pseudoController,
            photoUrlController: _photoUrlController,
          )
        ],
      ),
    );
  }
}
