class CarouselModel {
  String image;
  CarouselModel(this.image);
}

List<CarouselModel> carouselsHorizontal =
    carouselsData.map((e) => CarouselModel(e['image']!)).toList();

var carouselsData = [
  {'image': 'assets/images/business-trip.png', 'title': 'Portal'},
  {'image': 'assets/images/facebook_r.png', 'title': 'Facebook'},
  {'image': 'assets/images/whatsapp_r.png', 'title': 'Whatsapp'},
  {'image': 'assets/images/instagram_r.png', 'title': 'Instagram'},
  {'image': 'assets/images/youtube.png', 'title': 'Youtube'},
  {'image': 'assets/images/gmail.png', 'title': 'Gmail'},
  {'image': 'assets/images/linkedin.png', 'title': 'Linkedin'},
  {'image': 'assets/images/web.png', 'title': 'Macropay'},
];

class LimitDays {
  int index;
  int days;
  String label;
  bool selected;

  LimitDays(this.index, this.label, this.days, this.selected);
}
