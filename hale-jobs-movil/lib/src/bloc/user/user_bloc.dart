import 'package:bloc/bloc.dart';
import 'package:hale_app/src/bloc/user/user_event.dart';
import 'package:hale_app/src/bloc/user/user_state.dart';
import 'package:hale_app/src/models/response/user_response.dart';
import 'package:hale_app/src/repository/user_repository.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  UserBloc() : super(InitialUserState());

  final _repository = UserRepository();

  @override
  Stream<UserState> mapEventToState(UserEvent event) async* {
    // Ver detalle de un pago
    if (event is EventDesactivateUser) {
      yield IsLoadingDesactivateProfile();

      try {
        UserResponse response =
            await _repository.desactivateUserById(username: event.username!);

        yield SuccessDesactivateProfile(responseDesactivate: response);
      } catch (e) {
        yield ErrorDesactivateProfile(messageError: e.toString());
      }
    }

    // Conseguir información del usuario
    if (event is EventGetUser) {
      yield IsLoadingGetUsername();

      try {
        UserResponse response =
            await _repository.getInformationUser(username: event.username!);

        yield SuccessGetUsername(responseUserById: response);
      } catch (e) {
        yield ErrorGetUsername(messageError: e.toString());
      }
    }

    // Actualizar perfil del usuario
    if (event is EventUpdateProfile) {
      yield IsLoadingUpdateProfile();

      try {
        UserResponse response =
            await _repository.updateProfile(params: event.profileParams);

        yield SuccessUpdateProfile(responseUpdateProfile: response);
      } catch (e) {
        yield ErrorUpdateProfile(messageError: e.toString());
      }
    }

    // Restablecer la contraseña dentro del sistema
    if (event is EventRPInside) {
      yield IsLoadingRPI();

      try {
        UserResponse response = await _repository.resetPasswordInside(
            username: event.username!, password: event.password!);

        yield SuccessRPI(responseResetPassword: response);
      } catch (e) {
        yield ErrorRPI(messageError: e.toString());
      }
    }
  }
}
