import 'dart:convert';
import 'package:hale_app/src/models/response/user_response.dart';
import 'package:hale_app/src/utils/constants.dart';
import 'package:logger/logger.dart';
import 'package:http/http.dart' as http;

class UserService {
  final Logger logger = Logger();
  String host = Constants.apiUrl;

  // * : Servicio para desabilitar la cuenta del usuario
  Future<UserResponse> desactivateUserById(String username) async {
    var uri = Uri.https(
      host,
      '/user/reset-password',
    );

    final http.Response response = await http.post(
      uri,
      headers: Constants.headersPublic,
      body: jsonEncode(<String, dynamic>{"username": username}),
    );

    if (response.statusCode == 200) {
      return UserResponse.fromJson(json.decode(response.body));
    }

    if (response.statusCode == 400) {
      return throw ("Ocurrio un error con la consulta");
    }

    if (response.statusCode == 500) {
      return throw ("Problemas en el servidor");
    }

    return throw ("No funciona el servicio por el momento");
  }

  // * : Buscar información del usuario por username
  Future<UserResponse> getInformationUser(String username) async {
    var uri = Uri.https(
      host,
      '/user/find/$username',
    );

    final http.Response response = await http.get(
      uri,
      headers: Constants.headersPublic,
    );

    if (response.statusCode == 200) {
      return UserResponse.fromJson(json.decode(response.body));
    }

    if (response.statusCode == 400) {
      return throw ("Ocurrio un error con la consulta");
    }

    if (response.statusCode == 500) {
      return throw ("Problemas en el servidor");
    }

    return throw ("No funciona el servicio por el momento");
  }

  // * : Servicio para que el usuario actualice su información publica
  Future<UserResponse> updateProfile(dynamic status) async {
    var uri = Uri.https(
      host,
      '/user/reset-password',
    );

    final http.Response response = await http.post(
      uri,
      headers: Constants.headersPublic,
      body: jsonEncode(<String, dynamic>{"status": status}),
    );

    if (response.statusCode == 200) {
      return UserResponse.fromJson(json.decode(response.body));
    }

    if (response.statusCode == 400) {
      return throw ("Ocurrio un error con la consulta");
    }

    if (response.statusCode == 500) {
      return throw ("Problemas en el servidor");
    }

    return throw ("No funciona el servicio por el momento");
  }

  // * : Servicio para desabilitar el recibo de correos
  Future<UserResponse> desactivateAnnouncements(
      String username, String status) async {
    var uri = Uri.https(
      host,
      '/user/reset-password',
    );

    final http.Response response = await http.post(
      uri,
      headers: Constants.headersPublic,
      body: jsonEncode(
          <String, dynamic>{"username": username, "password": status}),
    );

    if (response.statusCode == 200) {
      return UserResponse.fromJson(json.decode(response.body));
    }

    if (response.statusCode == 400) {
      return throw ("Ocurrio un error con la consulta");
    }

    if (response.statusCode == 500) {
      return throw ("Problemas en el servidor");
    }

    return throw ("No funciona el servicio por el momento");
  }

  // ** : Cambiar con la contraseña dentro del sistema
  Future<UserResponse> resetPasswordInside(
      String email, String password) async {
    var uri = Uri.https(
      host,
      '/user/reset-password',
    );

    final http.Response response = await http.post(
      uri,
      headers: Constants.headersPublic,
      body: jsonEncode(
          <String, dynamic>{"username": email, "password": password}),
    );

    if (response.statusCode == 200) {
      return UserResponse.fromJson(json.decode(response.body));
    }

    if (response.statusCode == 400) {
      return throw ("Ocurrio un error con la consulta");
    }

    if (response.statusCode == 500) {
      return throw ("Problemas en el servidor");
    }

    return throw ("No funciona el servicio por el momento");
  }
}
