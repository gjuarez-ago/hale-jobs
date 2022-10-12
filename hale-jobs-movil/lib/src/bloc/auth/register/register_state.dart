import 'package:flutter/material.dart';
import 'package:hale_app/src/models/response/user_response.dart';

@immutable
class RegisterState {
  final String? messageError;
  final UserResponse? response;

  const RegisterState({this.messageError, this.response});
}

class InitialRegisterState extends RegisterState {}

class IsLoadingRegister extends RegisterState {}

class ErrorRegister extends RegisterState {
  const ErrorRegister({required String errorMessage})
      : super(messageError: errorMessage);
}

class SuccessRegister extends RegisterState {
  const SuccessRegister({required UserResponse response})
      : super(response: response);
}
