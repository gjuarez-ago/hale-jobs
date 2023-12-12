class ResetPasswordParams {
  ResetPasswordParams({
    required this.dateOfBirth,
    required this.emailContact,
    required this.fatherLN,
    required this.gender,
    required this.location,
    required this.motherLN,
    required this.names,
  });
  late final String dateOfBirth;
  late final String emailContact;
  late final String fatherLN;
  late final String gender;
  late final String location;
  late final String motherLN;
  late final String names;

  ResetPasswordParams.fromJson(Map<String, dynamic> json) {
    dateOfBirth = json['dateOfBirth'];
    emailContact = json['emailContact'];
    fatherLN = json['fatherLN'];
    gender = json['gender'];
    location = json['location'];
    motherLN = json['motherLN'];
    names = json['names'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['dateOfBirth'] = dateOfBirth;
    _data['emailContact'] = emailContact;
    _data['fatherLN'] = fatherLN;
    _data['gender'] = gender;
    _data['location'] = location;
    _data['motherLN'] = motherLN;
    _data['names'] = names;
    return _data;
  }
}
