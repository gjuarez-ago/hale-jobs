import 'package:flutter/material.dart';
import 'package:hale_app/src/models/response/http_response.dart';

@immutable
class RecoveryPasswordState {
  final String? messageError;
  final HttpResponse? response;

  const RecoveryPasswordState({this.messageError, this.response});
}

class InitialRPSState extends RecoveryPasswordState {}

class IsLoadingRPS extends RecoveryPasswordState {}

class ErrorRPS extends RecoveryPasswordState {
  const ErrorRPS({required String errorMessage})
      : super(messageError: errorMessage);
}

class SuccessRPS extends RecoveryPasswordState {
  const SuccessRPS({required HttpResponse response})
      : super(response: response);
}
