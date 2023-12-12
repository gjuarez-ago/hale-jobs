import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:hale_app/src/pages/welcome_page/welcome_page.dart';
import 'package:hale_app/src/routes/routes.dart';
import 'package:hale_app/src/services/bloc_services.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MultiBlocProvider(
      // providers: blocsServices(token == null ? "" : ""),
      providers: blocsServices(""),
      child: MaterialApp(
        title: 'Macropay PS',
        debugShowCheckedModeBanner: false,
        initialRoute: WelcomePage.routeName,
        theme: ThemeData(
          // scaffoldBackgroundColor: Colors.blueAccent,
          // visualDensity: VisualDensity.adaptivePlatformDensity,
          fontFamily: 'Comfortaa',
          // appBarTheme: const AppBarTheme(elevation: 0.0),
        ),
        routes: getApplicationRoutes(),
      ),
    );
  }
}
