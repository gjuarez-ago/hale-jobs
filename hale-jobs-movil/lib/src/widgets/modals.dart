import 'package:flutter/material.dart';
import 'package:material_dialogs/material_dialogs.dart';
import 'package:material_dialogs/widgets/buttons/icon_button.dart';
import 'package:material_dialogs/widgets/buttons/icon_outline_button.dart';

void modalButtons(BuildContext context, String title, String message,
    String okText, Function fn,
    {bool dismissible = true}) {
  Dialogs.materialDialog(
      msg: message,
      title: title,
      color: Colors.white,
      context: context,
      actions: [
        IconsOutlineButton(
          onPressed: () {
            Navigator.of(context).pop();
          },
          text: 'Cancelar',
          iconData: Icons.cancel_outlined,
          textStyle: const TextStyle(color: Colors.grey),
          iconColor: Colors.grey,
        ),
        IconsButton(
          onPressed: fn,
          text: okText,
          iconData: Icons.exit_to_app,
          color: Colors.red,
          textStyle: const TextStyle(color: Colors.white),
          iconColor: Colors.white,
        ),
      ]);
}

void modalButtonsBottom(BuildContext context, String title, String message,
    String okText, Function fn,
    {bool dismissible = true}) {
  Dialogs.bottomMaterialDialog(
      msg: message,
      title: title,
      barrierDismissible: dismissible,
      context: context,
      actions: [
        IconsOutlineButton(
          onPressed: () {
            Navigator.of(context).pop();
          },
          text: 'Cancelar',
          iconData: Icons.cancel_outlined,
          textStyle: const TextStyle(color: Colors.grey),
          iconColor: Colors.grey,
        ),
        IconsButton(
          onPressed: fn,
          text: okText,
          iconData: Icons.done,
          color: Colors.red,
          textStyle: const TextStyle(color: Colors.white),
          iconColor: Colors.white,
        ),
      ]);
}

void modalButtonsBottomWithAnimation(BuildContext context, String title,
    String message, String okText, Function fn,
    {bool dismissible = true}) {
  Dialogs.bottomMaterialDialog(
      msg: message,
      title: title,
      barrierDismissible: dismissible,
      context: context,
      lottieBuilder: Lottie.asset(
        'assets/images/exit-animation.json',
        fit: BoxFit.contain,
      ),
      actions: [
        IconsOutlineButton(
          onPressed: () {
            Navigator.of(context).pop();
          },
          text: 'Cancelar',
          iconData: Icons.cancel_outlined,
          textStyle: const TextStyle(color: Colors.grey),
          iconColor: Colors.grey,
        ),
        IconsButton(
          onPressed: fn,
          text: okText,
          iconData: Icons.exit_to_app,
          color: Colors.red,
          textStyle: const TextStyle(color: Colors.white),
          iconColor: Colors.white,
        ),
      ]);
}

void successAlert(BuildContext context, String title, String message,
    String otherText, Function fn,
    {bool dismissible = true}) {
  Dialogs.materialDialog(
    barrierDismissible: dismissible,
    color: Colors.white,
    msg: message,
    title: title,
    lottieBuilder: Lottie.asset(
      'assets/images/animation.json',
      fit: BoxFit.contain,
    ),
    context: context,
    actions: [
      IconsButton(
        onPressed: fn,
        text: otherText,
        // iconData: Icons.done,
        color: const Color.fromARGB(255, 6, 110, 48),
        textStyle: const TextStyle(color: Colors.white),
        iconColor: Colors.white,
      ),
    ],
  );
}

void successBottomAlert(BuildContext context, String title, String message,
    String otherText, Function fn) {
  Dialogs.bottomMaterialDialog(
    msg: message,
    title: title,
    color: Colors.white,
    lottieBuilder: Lottie.asset(
      'assets/images/animation.json',
      fit: BoxFit.contain,
    ),
    context: context,
    actions: [
      IconsButton(
        onPressed: () {
          Navigator.of(context).pop();
        },
        text: otherText,
        color: const Color.fromARGB(255, 13, 123, 5),
        textStyle: const TextStyle(color: Colors.white),
        iconColor: Colors.white,
      ),
    ],
  );
}
