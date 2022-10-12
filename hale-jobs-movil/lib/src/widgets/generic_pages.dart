import 'package:flutter/material.dart';

Widget emptyWidget = Stack(
  children: [
    Positioned(
      top: 24,
      bottom: 350,
      left: 24,
      right: 24,
      child: Container(
        padding: const EdgeInsets.all(20),
        child: Image.asset('assets/images/empty-box.png'),
      ),
    ),
    Positioned(
      top: 120,
      bottom: 0,
      left: 24,
      right: 24,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: const [
          Text(
            'Sin solicitudes',
            textAlign: TextAlign.center,
            style: TextStyle(
                fontSize: 30,
                letterSpacing: 2,
                color: Color(0xff2f3640),
                fontFamily: 'Anton',
                fontWeight: FontWeight.bold),
          ),
          Text(
            'No hay solicitudes pendientes por pagar.',
            textAlign: TextAlign.center,
            style: TextStyle(
              fontSize: 20,
              color: Color(0xff2f3640),
            ),
          ),
        ],
      ),
    )
  ],
);

Widget errorWidget = Stack(
  children: [
    Positioned(
      top: 24,
      bottom: 350,
      left: 24,
      right: 24,
      child: Container(
        padding: const EdgeInsets.all(20),
        child: Image.asset('assets/images/browser.png'),
      ),
    ),
    Positioned(
      top: 120,
      bottom: 0,
      left: 24,
      right: 24,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: const [
          Text(
            'Uy, un error ',
            textAlign: TextAlign.center,
            style: TextStyle(
                fontSize: 30,
                letterSpacing: 2,
                color: Color(0xff2f3640),
                fontFamily: 'Anton',
                fontWeight: FontWeight.bold),
          ),
          Text(
            'Ha ocurrido un error, contacte al administrador.',
            textAlign: TextAlign.center,
            style: TextStyle(
              fontSize: 20,
              color: Color(0xff2f3640),
            ),
          ),
        ],
      ),
    )
  ],
);

Widget comingSoonWidget = Stack(
  children: [
    Positioned(
      top: 24,
      bottom: 350,
      left: 24,
      right: 24,
      child: Container(
        padding: const EdgeInsets.all(20),
        child: Image.asset('assets/images/coming-soon.png'),
      ),
    ),
    Positioned(
      top: 120,
      bottom: 0,
      left: 24,
      right: 24,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: const [
          Text(
            'Proximamente',
            textAlign: TextAlign.center,
            style: TextStyle(
                fontSize: 30,
                letterSpacing: 2,
                color: Color(0xff2f3640),
                fontFamily: 'Anton',
                fontWeight: FontWeight.bold),
          ),
          Text(
            'Nos encontramos trabajando en ello',
            textAlign: TextAlign.center,
            style: TextStyle(
              fontSize: 20,
              color: Color(0xff2f3640),
            ),
          ),
        ],
      ),
    )
  ],
);
