import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../add_tweet_page/add_tweet_page.dart';
import 'widgets/body_home.dart';
import 'widgets/home_drawer.dart';

class HomePage extends StatelessWidget {
  const HomePage({
    Key key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xff2E4060),
        title: const Text('Tweets'),
      ),
      drawer: const HomeDrawer(),
      backgroundColor: const Color(0xff2E4053),
      body: const BodyHome(),
      floatingActionButton: FloatingActionButton(
        backgroundColor: Colors.deepOrange,
        onPressed: () {
          Navigator.push<AddTweetPage>(context, MaterialPageRoute<AddTweetPage>(builder: (context) => const AddTweetPage()));
        },
        child: const Icon(Icons.message),
      ),
    );
  }
}
