import 'package:bloc/bloc.dart';
import 'package:hale_app/src/bloc/auth/login/login_event.dart';
import 'package:hale_app/src/bloc/auth/login/login_state.dart';
import 'package:hale_app/src/models/response/login_response.dart';
import 'package:hale_app/src/repository/auth_repository.dart';

class LoginBloc extends Bloc<LoginEvent, LoginState> {
  LoginBloc() : super(InitialLoginState());

  final _repositoryAuth = AuthRepository();

  @override
  Stream<LoginState> mapEventToState(LoginEvent event) async* {
    yield IsLoadingAuth();

    if (event is LoginEvent) {
      yield IsLoadingAuth();
      try {
        LoginResponse response = await _repositoryAuth.login(
            user: event.user, password: event.password);

        yield SuccessAuth(response: response);
      } catch (e) {
        yield ErrorAuth(errorMessage: e.toString());
      }
    }
  }
}
