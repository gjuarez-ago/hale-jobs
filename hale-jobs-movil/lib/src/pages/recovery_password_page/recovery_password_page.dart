import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:hale_app/src/bloc/bloc.dart';
import 'package:hale_app/src/pages/login_page/login_page.dart';
import 'package:hale_app/src/utils/constants.dart';
import 'package:hale_app/src/utils/dialogs.dart';
import 'package:hale_app/src/widgets/header_widget.dart';
import 'package:hale_app/src/widgets/snackbars.dart';
import 'package:hale_app/src/widgets/theme_helper.dart';

class RecoveryPasswordPage extends StatefulWidget {
  const RecoveryPasswordPage({Key? key}) : super(key: key);

  static String routeName = "recovery_page_page";

  @override
  State<RecoveryPasswordPage> createState() => _RecoveryPasswordPageState();
}

class _RecoveryPasswordPageState extends State<RecoveryPasswordPage> {
  RecoveryPasswordBloc? _recoveryPasswordBloc;

  final double _headerHeight = 300;
  final _formKey = GlobalKey<FormState>();
  TextEditingController emailText = TextEditingController();

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    emailText.dispose();
    super.dispose();
  }

  void initialData() async {
    _recoveryPasswordBloc = BlocProvider.of<RecoveryPasswordBloc>(context);
  }

  @override
  void initState() {
    initialData();
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text(
            "",
            style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
          ),
          centerTitle: true,
          elevation: 0.0,
          iconTheme: const IconThemeData(color: Colors.white),
          backgroundColor: Constants.blueGeneric,
        ),
        backgroundColor: Colors.white,
        body: BlocConsumer<RecoveryPasswordBloc, RecoveryPasswordState>(
            listener: (context, state) {
          ScaffoldMessenger.of(context).hideCurrentSnackBar();

          if (state is IsLoadingRPS) {
            ProgressDialog.show(context);
          } else if (state is SuccessRPS) {
            ProgressDialog.dissmiss(context);
            snackbarRoundInfo(
                context,
                "Se ha enviado un correo a ${emailText.text}",
                const Color.fromARGB(255, 49, 193, 17));
            Navigator.pushReplacementNamed(context, LoginPage.routeName);
          }

          if (state is ErrorRPS) {
            ProgressDialog.dissmiss(context);
            snackbarRoundInfo(context, "${state.messageError}", Colors.red);
          }
        }, builder: (context, state) {
          return GestureDetector(
            onTap: () {
              FocusScope.of(context).unfocus();
            },
            child: SingleChildScrollView(
              child: Column(
                children: [
                  SizedBox(
                    height: _headerHeight,
                    child: HeaderWidget(
                        _headerHeight, true, Icons.password_rounded),
                  ),
                  SafeArea(
                    child: Container(
                      margin: const EdgeInsets.fromLTRB(25, 10, 25, 10),
                      padding: const EdgeInsets.fromLTRB(10, 0, 10, 0),
                      child: Column(
                        children: [
                          Container(
                            alignment: Alignment.topLeft,
                            margin: const EdgeInsets.fromLTRB(20, 0, 20, 0),
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.start,
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: const [
                                Text(
                                  '¿Olvidaste tu contraseña?',
                                  style: TextStyle(
                                      fontSize: 35,
                                      fontWeight: FontWeight.bold,
                                      color: Colors.black54),
                                  // textAlign: TextAlign.center,
                                ),
                                SizedBox(
                                  height: 10,
                                ),
                                Text(
                                  'Enter the email address associated with your account.',
                                  style: TextStyle(
                                      // fontSize: 20,
                                      fontWeight: FontWeight.bold,
                                      color: Colors.black54),
                                  // textAlign: TextAlign.center,
                                ),
                                SizedBox(
                                  height: 10,
                                ),
                                Text(
                                  'We will email you a verification code to check your authenticity.',
                                  style: TextStyle(
                                    color: Colors.black38,
                                    // fontSize: 20,
                                  ),
                                  // textAlign: TextAlign.center,
                                ),
                              ],
                            ),
                          ),
                          const SizedBox(height: 40.0),
                          Form(
                            key: _formKey,
                            child: Column(
                              children: <Widget>[
                                Container(
                                  child: TextFormField(
                                    controller: emailText,
                                    keyboardType: TextInputType.emailAddress,
                                    decoration: ThemeHelper()
                                        .textInputDecoration(Icons.email,
                                            "Email", "Enter your email"),
                                    validator: (val) {
                                      if (val!.isEmpty) {
                                        return "Email can't be empty";
                                      } else if (!RegExp(
                                              r"^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,253}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,253}[a-zA-Z0-9])?)*$")
                                          .hasMatch(val)) {
                                        return "Enter a valid email address";
                                      }
                                      return null;
                                    },
                                  ),
                                  decoration:
                                      ThemeHelper().inputBoxDecorationShaddow(),
                                ),
                                const SizedBox(height: 40.0),
                                Container(
                                  decoration: ThemeHelper()
                                      .buttonBoxDecoration(context),
                                  child: ElevatedButton(
                                    style: ThemeHelper().buttonStyle(),
                                    child: Padding(
                                      padding: const EdgeInsets.fromLTRB(
                                          40, 10, 40, 10),
                                      child: Text(
                                        "Send".toUpperCase(),
                                        style: const TextStyle(
                                          fontSize: 20,
                                          fontWeight: FontWeight.bold,
                                          color: Colors.white,
                                        ),
                                      ),
                                    ),
                                    onPressed: () async {
                                      onSubmit(context);
                                    },
                                  ),
                                ),
                                const SizedBox(height: 50.0),
                                GestureDetector(
                                  onTap: () {
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (context) =>
                                                const LoginPage()));
                                  },
                                  child: const Text.rich(
                                    TextSpan(
                                      children: [
                                        TextSpan(
                                            text: "Remember your password? "),
                                        TextSpan(
                                          text: 'Login',
                                          style: TextStyle(
                                              fontWeight: FontWeight.bold),
                                        ),
                                      ],
                                    ),
                                  ),
                                ),
                              ],
                            ),
                          )
                        ],
                      ),
                    ),
                  )
                ],
              ),
            ),
          );
        }));
  }

  void onSubmit(BuildContext context) async {
    if (_formKey.currentState!.validate()) {
      _recoveryPasswordBloc?.add(EventRPS(username: emailText.text));
    }
  }
}
