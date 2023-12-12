import 'package:hale_app/src/models/params/user_register_params.dart';
import 'package:hale_app/src/models/response/http_response.dart';
import 'package:hale_app/src/models/response/login_response.dart';
import 'package:hale_app/src/models/response/user_response.dart';
import 'package:hale_app/src/services/auth_service.dart';

class AuthRepository {
  AuthService service = AuthService();

  Future<LoginResponse> login(
          {required String user, required String password}) =>
      service.login(user, password);

  Future<UserResponse> register({required UserRegisterParams params}) =>
      service.register(params);

  Future<HttpResponse> recoveryPassword({required String username}) =>
      service.recoveryPassword(username);

  Future<UserResponse> verifyUser(
          {required String username, required String code}) =>
      service.verifyUser(username, code);
}
