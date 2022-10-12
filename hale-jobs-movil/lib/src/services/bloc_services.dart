import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:hale_app/src/bloc/bloc.dart';

List<BlocProvider> blocsServices(String token) {
  return [
    BlocProvider<LoginBloc>(
      create: (context) => LoginBloc(),
    ),
    BlocProvider<RegisterBloc>(
      create: (context) => RegisterBloc(),
    ),
    BlocProvider<RecoveryPasswordBloc>(
      create: (context) => RecoveryPasswordBloc(),
    ),
    BlocProvider<UserBloc>(
      create: (context) => UserBloc(),
    ),
  ];
}
