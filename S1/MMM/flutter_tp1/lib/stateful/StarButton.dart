import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class StarButton extends StatefulWidget {
  const StarButton({Key key, this.defaultStarValue: 0}) : super(key: key);

  final int defaultStarValue;

  @override
  _StarButtonState createState() => _StarButtonState();
}

class _StarButtonState extends State<StarButton> {
  int _starValue;
  bool _starToggled = true;
  IconData _starIcon = Icons.star;

  @override
  void initState() {
    super.initState();
    this._starValue = widget.defaultStarValue;
  }

  void toggleStar() {
    if (_starToggled) {
      setState(() {
        this._starValue--;
        this._starIcon = Icons.star_border;
      });
    } else {
      setState(() {
        this._starValue++;
        this._starIcon = Icons.star;
      });
    }

    this._starToggled = !_starToggled;
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        IconButton(
          icon: Icon(_starIcon, color: Colors.deepOrange),
          onPressed: toggleStar,
        ),
        Text('$_starValue'),
      ],
    );
  }
}
