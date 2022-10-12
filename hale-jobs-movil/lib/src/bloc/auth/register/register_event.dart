import 'package:flutter/material.dart';
import 'package:hale_app/src/models/params/user_register_params.dart';

@immutable
class RegisterEvent {
  final UserRegisterParams params;

  const RegisterEvent({required this.params});
}

// Evento para registrarse dentro de la
class EventRegister extends RegisterEvent {
  const EventRegister({
    required UserRegisterParams params,
  }) : super(params: params);
}
