import 'package:flutter/material.dart';
import 'package:hale_app/src/models/response/login_response.dart';

@immutable
abstract class LoginState {
  final String? messageError;
  final LoginResponse? response;

  const LoginState({this.messageError, this.response});
}

class InitialLoginState extends LoginState {}

class IsLoadingAuth extends LoginState {}

class ErrorAuth extends LoginState {
  const ErrorAuth({required String errorMessage})
      : super(messageError: errorMessage);
}

class SuccessAuth extends LoginState {
  const SuccessAuth({required LoginResponse response})
      : super(response: response);
}
