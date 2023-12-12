import 'dart:io';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:curved_navigation_bar/curved_navigation_bar.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:hale_app/src/models/params/carousel_horizontal_model.dart';
import 'package:hale_app/src/pages/notification_page/notification_page.dart';
import 'package:hale_app/src/utils/constants.dart';
import 'package:hale_app/src/widgets/header_widget.dart';
import 'package:hale_app/src/widgets/navigation_drawer.dart';
// import 'package:flutter_swiper_null_safety/flutter_swiper_null_safety.dart';
import 'package:url_launcher/url_launcher.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  static String routeName = "home_page";

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _page = 2;
  GlobalKey<CurvedNavigationBarState> _bottomNavigationKey = GlobalKey();

  double _value = 2000;
  int? current;
  final double _headerHeight = 250;

  List<T> map<T>(List list, Function handler) {
    List<T> result = [];
    for (int i = 0; i < list.length; i++) {
      result.add(handler(i, list[i]));
    }

    return result;
  }

  final data = [
    Post(
      image: 'assets/images/promocion1.png',
      title: 'Finding your ikigai in your middle age',
      author: 'John Johny',
      date: '25 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion2.png',
      title: 'How to Lead Before You Are in Charge',
      author: 'John Johny',
      date: '24 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion3.png',
      title: 'How Minimalism Brought Me',
      author: 'John Johny',
      date: '15 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion1.png',
      title: 'The Most Important Color In UI Design',
      author: 'John Johny',
      date: '11 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion1.png',
      title: 'Finding your ikigai in your middle age',
      author: 'John Johny',
      date: '25 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion2.png',
      title: 'How to Lead Before You Are in Charge',
      author: 'John Johny',
      date: '24 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion3.png',
      title: 'How Minimalism Brought Me',
      author: 'John Johny',
      date: '15 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion1.png',
      title: 'The Most Important Color In UI Design',
      author: 'John Johny',
      date: '11 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion1.png',
      title: 'Finding your ikigai in your middle age',
      author: 'John Johny',
      date: '25 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion2.png',
      title: 'How to Lead Before You Are in Charge',
      author: 'John Johny',
      date: '24 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion3.png',
      title: 'How Minimalism Brought Me',
      author: 'John Johny',
      date: '15 Mar 2020',
    ),
    Post(
      image: 'assets/images/promocion1.png',
      title: 'The Most Important Color In UI Design',
      author: 'John Johny',
      date: '11 Mar 2020',
    ),
  ];

  List<LimitDays> daysButton = [
    LimitDays(1, "7 días", 7, false),
    LimitDays(2, "15 días", 15, true),
    LimitDays(3, "30 días", 30, false),
  ];

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;

    return Scaffold(
        backgroundColor: Colors.black,
        appBar: AppBar(
          title: Container(
            padding: const EdgeInsets.only(left: 16, right: 20),
            alignment: Alignment.center,
            height: 60.0,
            width: 150,
            decoration: const BoxDecoration(
              image: DecorationImage(
                image: AssetImage('assets/images/logo.png'),
                fit: BoxFit.contain,
              ),
            ),
          ),
          centerTitle: true,
          elevation: 0.0,
          iconTheme: const IconThemeData(color: Colors.white),
          backgroundColor: Constants.blueGeneric,
          actions: [
            GestureDetector(
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) => const NotificationPage()));
              },
              child: Container(
                margin: const EdgeInsets.only(
                  top: 16,
                  right: 16,
                ),
                child: Stack(
                  children: <Widget>[
                    const Icon(Icons.notifications),
                    Positioned(
                      right: 0,
                      child: Container(
                        padding: const EdgeInsets.all(1),
                        decoration: BoxDecoration(
                          color: Colors.red,
                          borderRadius: BorderRadius.circular(6),
                        ),
                        constraints: const BoxConstraints(
                          minWidth: 12,
                          minHeight: 12,
                        ),
                        child: const Text(
                          '5',
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 8,
                          ),
                          textAlign: TextAlign.center,
                        ),
                      ),
                    )
                  ],
                ),
              ),
            )
          ],
        ),
        // bottomNavigationBar: Padding(
        //   padding: const EdgeInsets.all(8.0),
        //   child: ElevatedButton(
        //     // color: Color.fromARGB(255, 32, 177, 49),
        //     onPressed: () {},

        //     style: ElevatedButton.styleFrom(
        //       primary: const Color.fromARGB(255, 13, 43, 112),
        //       shape: RoundedRectangleBorder(
        //         borderRadius: BorderRadius.circular(25),
        //       ),
        //       elevation: 15.0,
        //     ),
        //     child: InkWell(
        //       child: SizedBox(
        //         height: kToolbarHeight,
        //         width: double.infinity,
        //         child: Padding(
        //           padding: const EdgeInsets.only(left: 30, right: 30),
        //           child: Row(
        //             mainAxisAlignment: MainAxisAlignment.center,
        //             children: const [
        //               Icon(Icons.monetization_on, size: 25),
        //               SizedBox(width: 10),
        //               Text(
        //                 "Solicitar Credito",
        //                 style: TextStyle(
        //                   fontSize: 20,
        //                   fontWeight: FontWeight.w700,
        //                   color: Color.fromARGB(255, 255, 255, 255),
        //                 ),
        //               ),
        //             ],
        //           ),
        //         ),
        //       ),
        //     ),
        //   ),
        // ),
        bottomNavigationBar: CurvedNavigationBar(
          key: _bottomNavigationKey,
          index: 2,
          height: 60.0,
          items: <Widget>[
            Icon(Icons.add,
                size: 30, color: _page == 0 ? Colors.black : Colors.white),
            Icon(Icons.list,
                size: 30, color: _page == 1 ? Colors.black : Colors.white),
            Icon(Icons.compare_arrows,
                size: 30, color: _page == 2 ? Colors.black : Colors.white),
            Icon(Icons.call_split,
                size: 30, color: _page == 3 ? Colors.black : Colors.white),
            Icon(Icons.perm_identity,
                size: 30, color: _page == 4 ? Colors.black : Colors.white),
          ],
          color: Colors.black,
          buttonBackgroundColor: Colors.white,
          backgroundColor: Colors.yellow,
          animationCurve: Curves.easeInOut,
          animationDuration: Duration(milliseconds: 600),
          onTap: (index) {
            setState(() {
              _page = index;
              print(_page);
            });
          },
          letIndexChange: (index) => true,
        ),
        drawer: const NavigationDrawer(),
        body: SafeArea(
          child: SingleChildScrollView(
            child: SizedBox(
              width: double.infinity,
              child: Stack(
                children: [
                  SizedBox(
                    height: _headerHeight,
                    child: HeaderWidget(
                      _headerHeight,
                      false,
                      Icons.home_max,
                    ), //let's create a common header widget
                  ),
                  Column(
                    children: [_pages.elementAt(_page)],
                  ),
                ],
              ),
            ),
          ),
        ));
  }

  List<Widget> _pages = <Widget>[
    Center(
      child: Text(
        "1",
        style: TextStyle(color: Colors.white, fontSize: 40),
      ),
    ),
    Center(
      child: Text(
        "2",
        style: TextStyle(color: Colors.white, fontSize: 40),
      ),
    ),
    Center(
      child: Text(
        "3",
        style: TextStyle(color: Colors.white, fontSize: 40),
      ),
    ),
    Center(
      child: Text(
        "4",
        style: TextStyle(color: Colors.white, fontSize: 40),
      ),
    ),
    Center(
      child: Text(
        "5",
        style: TextStyle(color: Colors.white, fontSize: 40),
      ),
    ),
  ];
}

class Post {
  final String? title;
  final String? image;
  final String? author;
  final String? date;

  Post({this.title, this.image, this.author, this.date});
}
