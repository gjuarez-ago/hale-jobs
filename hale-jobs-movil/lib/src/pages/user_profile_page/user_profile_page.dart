import 'package:flutter/material.dart';
import 'package:hale_app/src/utils/constants.dart';
import 'package:hale_app/src/widgets/header_widget.dart';
import 'package:shared_preferences/shared_preferences.dart';

class UserProfilePage extends StatefulWidget {
  const UserProfilePage({Key? key}) : super(key: key);

  static String routeName = "user_profile_page";

  @override
  State<UserProfilePage> createState() => _UserProfilePageState();
}

class _UserProfilePageState extends State<UserProfilePage> {
  String name = "Gabriel";
  String surname = "Juarez de la Cruz";
  String initials = "GA";
  bool dactilar = false;
  final double _headerHeight = 250;

  @override
  void initState() {
    initialData();
    super.initState();
  }

  void initialData() async {
    // SharedPreferences preferences = await SharedPreferences.getInstance();

    // setState(() {
    //   dactilar = preferences.getBool("dactilar")!;
    //   name = preferences.getString("name")!;
    //   surname = preferences.getString("surname")!;
    //   initials = getInitials(name!, surname!);
    // });
  }

  bool isSwitchedNot = false;
  bool isSwitchedOfe = false;
  bool isSwitchedAct = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Mi perfil",
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
        ),
        // centerTitle: true,
        elevation: 0.0,
        iconTheme: const IconThemeData(color: Colors.white),
        backgroundColor: Constants.blueGeneric,
      ),
      body: GestureDetector(
        child: SingleChildScrollView(
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
              SafeArea(
                child: FittedBox(
                  child: Container(
                    // height: MediaQuery.of(context).size.height,
                    width: MediaQuery.of(context).size.width,
                    margin: const EdgeInsets.all(kFixPadding),
                    padding: const EdgeInsets.all(kFixPadding),
                    decoration: BoxDecoration(
                        color: kWhiteColor,
                        shape: BoxShape.rectangle,
                        borderRadius: BorderRadius.circular(8.0),
                        boxShadow: const [
                          BoxShadow(color: kLightColor, blurRadius: 2.0)
                        ]),
                    child: Column(
                      children: [
                        Card(
                          elevation: 8.0,
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(10.0)),
                          //color: Colors.orange,
                          color: Constants.blueGeneric,
                          child: ListTile(
                            onTap: () {
                              // Navigator.of(context).push(MaterialPageRoute(
                              //   builder: (context) => const EditUserPage(),
                              // ));
                            },
                            title: Text(
                              '$name $surname',
                              style: const TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.w500,
                              ),
                            ),
                            leading: CircleAvatar(
                              radius: 20,
                              backgroundColor:
                                  const Color.fromARGB(255, 244, 245, 246),
                              child: Text(
                                initials,
                                style: const TextStyle(
                                    color: Color.fromARGB(255, 19, 19, 19)),
                              ),
                            ),
                            trailing: const Icon(
                              Icons.edit,
                              color: Colors.white,
                            ),
                          ),
                        ),
                        const SizedBox(height: 10.0),
                        Card(
                          elevation: 4.0,
                          margin:
                              const EdgeInsets.fromLTRB(32.0, 8.0, 32.0, 16.0),
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(10.0)),
                          child: Column(
                            children: <Widget>[
                              const SizedBox(height: 10.0),
                              ListTile(
                                leading: const Icon(
                                  Icons.lock_outline,
                                  //color: Colors.orange,
                                  color: Color.fromRGBO(0, 34, 91, 1),
                                ),
                                title: const Text(
                                  "Cambiar Contraseña",
                                ),
                                trailing:
                                    const Icon(Icons.keyboard_arrow_right),
                                onTap: () {
                                  // Navigator.of(context).push(MaterialPageRoute(
                                  //   builder: (context) =>
                                  //       const ChangePasswordPage(),
                                  // ));
                                },
                              ),
                              const SizedBox(height: 10.0),
                              ListTile(
                                leading: const Icon(
                                  Icons.build_circle,
                                  //color: Colors.orange,
                                  color: Color.fromRGBO(0, 34, 91, 1),
                                ),
                                title: const Text(
                                  "Acerca de nosotros",
                                ),
                                trailing:
                                    const Icon(Icons.keyboard_arrow_right),
                                onTap: () {
                                  // Navigator.of(context).push(MaterialPageRoute(
                                  //   builder: (context) => const AboutPage(),
                                  // ));
                                },
                              ),
                              const SizedBox(height: 10.0),
                              ListTile(
                                leading: const Icon(
                                  Icons.support_agent,
                                  //color: Colors.orange,
                                  color: Color.fromRGBO(0, 34, 91, 1),
                                ),
                                title: const Text(
                                  "Servicio al cliente",
                                ),
                                trailing:
                                    const Icon(Icons.keyboard_arrow_right),
                                onTap: () {
                                  // Navigator.of(context).push(MaterialPageRoute(
                                  //   builder: (context) => const ChangePasswordPage(),
                                  // ));
                                },
                              ),
                              const SizedBox(height: 10.0),
                            ],
                          ),
                        ),
                        const SizedBox(height: 20.0),
                        const Text(
                          "Otros ajustes",
                          style: TextStyle(
                            fontSize: 20.0,
                            fontWeight: FontWeight.bold,
                            color: Colors.black,
                          ),
                        ),
                        SwitchListTile(
                          //activeColor: Colors.orange,
                          activeColor: const Color.fromARGB(255, 48, 49, 51),
                          contentPadding: const EdgeInsets.all(0),
                          value: isSwitchedNot,
                          title: const Text(
                            "Recibir Notificaciones",
                          ),
                          onChanged: (value) {
                            setState(() {
                              isSwitchedNot = value;
                              print(isSwitchedNot);
                            });
                          },
                        ),
                        SwitchListTile(
                          activeColor: const Color.fromARGB(255, 48, 49, 51),
                          contentPadding: const EdgeInsets.all(0),
                          value: isSwitchedOfe,
                          title: const Text(
                            "Recibir Ofertas",
                          ),
                          onChanged: (value) {
                            setState(() {
                              isSwitchedOfe = value;
                              print(isSwitchedOfe);
                            });
                          },
                        ),
                        SwitchListTile(
                          activeColor: const Color.fromARGB(255, 48, 49, 51),
                          contentPadding: const EdgeInsets.all(0),
                          value: dactilar,
                          title: const Text(
                            "Activar huella dactilar",
                          ),
                          onChanged: (value) async {
                            SharedPreferences preferences =
                                await SharedPreferences.getInstance();

                            setState(() {
                              dactilar = value;
                              preferences.setBool("dactilar", value);
                              // print(dactilar);
                            });
                          },
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  String getInitials(String name, String surname) {
    return '${name[0]}${surname[0]}';
  }
}
