class GenericResponse {
  GenericResponse({
    required this.id,
    required this.clave,
    required this.valor,
  });
  late final int id;
  late final String clave;
  late final String valor;

  GenericResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    clave = json['clave'];
    valor = json['valor'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['id'] = id;
    _data['clave'] = clave;
    _data['valor'] = valor;
    return _data;
  }
}
