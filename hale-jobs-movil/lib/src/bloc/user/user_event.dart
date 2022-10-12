import 'package:flutter/material.dart';

@immutable
abstract class UserEvent {
  final String? username;
  final bool? status;
  final String? password;
  final dynamic profileParams;

  const UserEvent(
      {this.username, this.status, this.password, this.profileParams});
}

// Evento para desactivar el perfil del usuario
class EventDesactivateUser extends UserEvent {
  const EventDesactivateUser({required String username})
      : super(username: username);
}

// Evento para buscar un usuario por ID
class EventGetUser extends UserEvent {
  const EventGetUser({required String username}) : super(username: username);
}

// Evento para generar una referencia de pago.
class EventUpdateProfile extends UserEvent {
  const EventUpdateProfile({required dynamic profileParams})
      : super(profileParams: profileParams);
}

// Evento para restablecer la contraseña dentro de la app
class EventRPInside extends UserEvent {
  const EventRPInside({required String username, required String password})
      : super(username: username, password: password);
}
