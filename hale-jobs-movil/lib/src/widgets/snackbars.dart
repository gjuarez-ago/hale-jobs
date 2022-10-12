import 'package:flutter/material.dart';

ScaffoldFeatureController<SnackBar, SnackBarClosedReason> snackbarRoundInfo(
    BuildContext context, String title, Color color) {
  return ScaffoldMessenger.of(context).showSnackBar(SnackBar(
    behavior: SnackBarBehavior.floating,
    content: Row(
      children: [
        const Icon(Icons.info_outline, color: Colors.white),
        const SizedBox(
          width: 3,
        ),
        Text(
          title,
          overflow: TextOverflow.ellipsis,
          style: const TextStyle(fontSize: 16),
        ),
      ],
    ),
    backgroundColor: color,
    // behavior: SnackBarBehavior.floating,
    dismissDirection: DismissDirection.up,
    shape: ShapeBorder.lerp(const StadiumBorder(), const StadiumBorder(), 10),
    action: SnackBarAction(
      label: 'Cerrar',
      disabledTextColor: Colors.white,
      textColor: const Color.fromARGB(255, 248, 248, 248),
      onPressed: () {
        //Do whatever you want
      },
    ),
  ));
}

ScaffoldFeatureController<SnackBar, SnackBarClosedReason> snackbarRoundSuccess(
    BuildContext context, String title, Color color) {
  return ScaffoldMessenger.of(context).showSnackBar(SnackBar(
    behavior: SnackBarBehavior.floating,
    content: Row(
      children: [
        const Icon(Icons.info_outline, color: Colors.white),
        const SizedBox(
          width: 3,
        ),
        Text(
          title,
          overflow: TextOverflow.ellipsis,
          style: const TextStyle(fontSize: 16),
        ),
      ],
    ),
    backgroundColor: color,
    // behavior: SnackBarBehavior.floating,
    dismissDirection: DismissDirection.up,
    shape: ShapeBorder.lerp(const StadiumBorder(), const StadiumBorder(), 10),
    action: SnackBarAction(
      label: 'Cerrar',
      disabledTextColor: Colors.white,
      textColor: const Color.fromARGB(255, 248, 248, 248),
      onPressed: () {
        //Do whatever you want
      },
    ),
  ));
}

ScaffoldFeatureController<SnackBar, SnackBarClosedReason> snackbarRoundError(
    BuildContext context, String title, Color color) {
  return ScaffoldMessenger.of(context).showSnackBar(SnackBar(
    behavior: SnackBarBehavior.floating,
    content: Row(
      children: [
        const Icon(Icons.error, color: Colors.white),
        const SizedBox(
          width: 3,
        ),
        Text(
          title,
          overflow: TextOverflow.ellipsis,
          style: const TextStyle(fontSize: 16),
        ),
      ],
    ),
    backgroundColor: color,
    // behavior: SnackBarBehavior.floating,
    dismissDirection: DismissDirection.up,
    shape: ShapeBorder.lerp(const StadiumBorder(), const StadiumBorder(), 10),
    action: SnackBarAction(
      label: 'Cerrar',
      disabledTextColor: Colors.white,
      textColor: const Color.fromARGB(255, 248, 248, 248),
      onPressed: () {
        //Do whatever you want
      },
    ),
  ));
}

ScaffoldFeatureController<SnackBar, SnackBarClosedReason> snackbarBottom(
    BuildContext context, String title, Color color) {
  return ScaffoldMessenger.of(context).showSnackBar(SnackBar(
    content: Row(
      children: [
        const Icon(Icons.info_outline, color: Colors.white),
        const SizedBox(
          width: 3,
        ),
        Text(
          title,
          style: const TextStyle(fontSize: 16),
        ),
      ],
    ),
    backgroundColor: color,
    duration: const Duration(milliseconds: 1000),
    action: SnackBarAction(
      label: 'Cerrar',
      disabledTextColor: Colors.white,
      textColor: const Color.fromARGB(255, 248, 248, 248),
      onPressed: () {
        //Do whatever you want
      },
    ),
  ));
}
