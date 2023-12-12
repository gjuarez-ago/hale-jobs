class HttpResponse {
  HttpResponse({
    required this.timeStamp,
    required this.httpStatus,
    required this.reason,
    required this.message,
    required this.httpStatusCode,
  });
  late final String timeStamp;
  late final String httpStatus;
  late final String reason;
  late final String message;
  late final int httpStatusCode;

  HttpResponse.fromJson(Map<String, dynamic> json) {
    timeStamp = json['timeStamp'];
    httpStatus = json['httpStatus'];
    reason = json['reason'];
    message = json['message'];
    httpStatusCode = json['httpStatusCode'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['timeStamp'] = timeStamp;
    _data['httpStatus'] = httpStatus;
    _data['reason'] = reason;
    _data['message'] = message;
    _data['httpStatusCode'] = httpStatusCode;
    return _data;
  }
}
