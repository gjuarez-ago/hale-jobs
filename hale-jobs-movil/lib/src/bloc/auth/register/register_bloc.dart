import 'package:bloc/bloc.dart';
import 'package:hale_app/src/bloc/auth/register/register_event.dart';
import 'package:hale_app/src/bloc/auth/register/register_state.dart';
import 'package:hale_app/src/models/response/user_response.dart';
import 'package:hale_app/src/repository/auth_repository.dart';

class RegisterBloc extends Bloc<RegisterEvent, RegisterState> {
  RegisterBloc() : super(InitialRegisterState());

  final _repositoryAuth = AuthRepository();

  @override
  Stream<RegisterState> mapEventToState(RegisterEvent event) async* {
    yield IsLoadingRegister();

    if (event is EventRegister) {
      yield IsLoadingRegister();
      try {
        UserResponse response =
            await _repositoryAuth.register(params: event.params);

        yield SuccessRegister(response: response);
      } catch (e) {
        yield ErrorRegister(errorMessage: e.toString());
      }
    }
  }
}
