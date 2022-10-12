class UserResponse {
  UserResponse({
    required this.consecutive,
    this.names,
    this.surnames,
    this.motherLastName,
    this.fatherLastName,
    required this.username,
    this.gender,
    this.dateOfBirth,
    this.email,
    required this.role,
    this.profileImageUrl,
    required this.joinDate,
    required this.authorities,
    this.location,
    this.lastLoginDate,
    this.lastLoginDateDisplay,
    required this.lastLoanId,
    required this.statusLoan,
    required this.permissions,
    required this.active,
    required this.notLocked,
  });
  late final String consecutive;
  late final dynamic names;
  late final dynamic surnames;
  late final dynamic motherLastName;
  late final dynamic fatherLastName;
  late final String username;
  late final dynamic gender;
  late final dynamic dateOfBirth;
  late final dynamic email;
  late final String role;
  late final dynamic profileImageUrl;
  late final int joinDate;
  late final List<String> authorities;
  late final dynamic location;
  late final dynamic lastLoginDate;
  late final dynamic lastLoginDateDisplay;
  late final String lastLoanId;
  late final int statusLoan;
  late final List<Permissions> permissions;
  late final bool active;
  late final bool notLocked;

  UserResponse.fromJson(Map<String, dynamic> json) {
    consecutive = json['consecutive'];
    names = json['names'];
    surnames = json['surnames'];
    motherLastName = json['motherLastName'];
    fatherLastName = json['fatherLastName'];
    username = json['username'];
    gender = json['gender'];
    dateOfBirth = json['dateOfBirth'];
    email = json['email'];
    role = json['role'];
    profileImageUrl = json['profileImageUrl'];
    joinDate = json['joinDate'];
    authorities = List.castFrom<dynamic, String>(json['authorities']);
    location = json['location'];
    lastLoginDate = json['lastLoginDate'];
    lastLoginDateDisplay = json['lastLoginDateDisplay'];
    lastLoanId = json['lastLoanId'];
    statusLoan = json['statusLoan'];
    permissions = List.from(json['permissions'])
        .map((e) => Permissions.fromJson(e))
        .toList();
    active = json['active'];
    notLocked = json['notLocked'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['consecutive'] = consecutive;
    _data['names'] = names;
    _data['surnames'] = surnames;
    _data['motherLastName'] = motherLastName;
    _data['fatherLastName'] = fatherLastName;
    _data['username'] = username;
    _data['gender'] = gender;
    _data['dateOfBirth'] = dateOfBirth;
    _data['email'] = email;
    _data['role'] = role;
    _data['profileImageUrl'] = profileImageUrl;
    _data['joinDate'] = joinDate;
    _data['authorities'] = authorities;
    _data['location'] = location;
    _data['lastLoginDate'] = lastLoginDate;
    _data['lastLoginDateDisplay'] = lastLoginDateDisplay;
    _data['lastLoanId'] = lastLoanId;
    _data['statusLoan'] = statusLoan;
    _data['permissions'] = permissions.map((e) => e.toJson()).toList();
    _data['active'] = active;
    _data['notLocked'] = notLocked;
    return _data;
  }
}

class Permissions {
  Permissions({
    required this.id,
    required this.keyPermission,
    required this.description,
  });
  late final int id;
  late final String keyPermission;
  late final String description;

  Permissions.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    keyPermission = json['keyPermission'];
    description = json['description'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['keyPermission'] = keyPermission;
    _data['description'] = description;
    return _data;
  }
}
