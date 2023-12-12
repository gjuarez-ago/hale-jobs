import 'package:flutter/material.dart';
import 'package:hale_app/src/models/response/user_response.dart';

@immutable
abstract class UserState {
  final String? messageError;
  final UserResponse? responseDesactivate;
  final UserResponse? responseUserById;
  final UserResponse? responseUpdateProfile;
  final UserResponse? responseResetPassword;

  const UserState({
    this.messageError,
    this.responseDesactivate,
    this.responseUserById,
    this.responseUpdateProfile,
    this.responseResetPassword,
  });
}

class InitialUserState extends UserState {}

// Estados para desactivar el perfil del usuario
class IsLoadingDesactivateProfile extends UserState {}

class ErrorDesactivateProfile extends UserState {
  const ErrorDesactivateProfile({required String messageError})
      : super(messageError: messageError);
}

class SuccessDesactivateProfile extends UserState {
  const SuccessDesactivateProfile({required UserResponse responseDesactivate})
      : super(responseDesactivate: responseDesactivate);
}

// Estados para buscar información del usuario por username
class IsLoadingGetUsername extends UserState {}

class ErrorGetUsername extends UserState {
  const ErrorGetUsername({required String messageError})
      : super(messageError: messageError);
}

class SuccessGetUsername extends UserState {
  const SuccessGetUsername({required UserResponse responseUserById})
      : super(responseUserById: responseUserById);
}

// Estados para actualizar el perfil del usuario
class IsLoadingUpdateProfile extends UserState {}

class ErrorUpdateProfile extends UserState {
  const ErrorUpdateProfile({required String messageError})
      : super(messageError: messageError);
}

class SuccessUpdateProfile extends UserState {
  const SuccessUpdateProfile({required UserResponse responseUpdateProfile})
      : super(responseUpdateProfile: responseUpdateProfile);
}

// Estados para restablecer la contraseña dentro del sistema
class IsLoadingRPI extends UserState {}

class ErrorRPI extends UserState {
  const ErrorRPI({required String messageError})
      : super(messageError: messageError);
}

class SuccessRPI extends UserState {
  const SuccessRPI({required UserResponse responseResetPassword})
      : super(responseResetPassword: responseResetPassword);
}
