import 'package:flutter/material.dart';

@immutable
class RecoveryPasswordEvent {
  final String username;

  const RecoveryPasswordEvent({required this.username});
}

// Evento para restablecer la contraseña
class EventRPS extends RecoveryPasswordEvent {
  const EventRPS({
    required String username,
  }) : super(username: username);
}
