import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class IconButtonColoredText extends StatelessWidget {
  const IconButtonColoredText({Key key, this.text: 'DEFAULT', this.icon: Icons.airplay, this.color: Colors.lightBlue, this.onPressed})
      : super(key: key);

  final String text;
  final IconData icon;
  final MaterialColor color;
  final VoidCallback onPressed;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(left: 10, right: 10),
      child: Column(
        children: [
          IconButton(
            icon: Icon(this.icon, color: this.color),
            onPressed: this.onPressed,
            iconSize: 48,
          ),
          Text('$text', style: TextStyle(fontWeight: FontWeight.bold, color: this.color)),
        ],
      ),
    );
  }
}
