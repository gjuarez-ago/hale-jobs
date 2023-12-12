class CarouselModel {
  String image;
  CarouselModel(this.image);
}

List<CarouselModel> carousels =
    carouselsData.map((e) => CarouselModel(e['image']!)).toList();

var carouselsData = [
  {'image': 'assets/images/promocion3.png'},
  {'image': 'assets/images/promocion1.png'},
  {'image': 'assets/images/promocion2.png'},
];
