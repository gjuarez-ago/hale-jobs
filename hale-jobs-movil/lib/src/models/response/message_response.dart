class MessageResponse {
  MessageResponse({
    required this.id,
    required this.consecutive,
    required this.title,
    required this.content,
    required this.typeComment,
    required this.regDateCreated,
  });
  late final int id;
  late final String consecutive;
  late final String title;
  late final String content;
  late final String typeComment;
  late final dynamic regDateCreated;

  MessageResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    consecutive = json['consecutive'];
    title = json['title'];
    content = json['content'];
    typeComment = json['typeComment'];
    regDateCreated = json['regDateCreated'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['consecutive'] = consecutive;
    _data['title'] = title;
    _data['content'] = content;
    _data['typeComment'] = typeComment;
    _data['regDateCreated'] = regDateCreated;
    return _data;
  }
}
