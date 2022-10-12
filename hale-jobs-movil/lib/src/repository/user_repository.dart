import 'package:hale_app/src/models/response/user_response.dart';
import 'package:hale_app/src/services/user_service.dart';

class UserRepository {
  UserService service = UserService();

  Future<UserResponse> desactivateUserById({required String username}) =>
      service.desactivateUserById(username);

  Future<UserResponse> getInformationUser({required String username}) =>
      service.getInformationUser(username);

  Future<UserResponse> updateProfile({dynamic params}) =>
      service.updateProfile(params);

  Future<UserResponse> desactivateAnnouncements(
          {required String username, required String status}) =>
      service.desactivateAnnouncements(username, status);

  Future<UserResponse> resetPasswordInside(
          {required String username, required String password}) =>
      service.resetPasswordInside(username, password);
}
