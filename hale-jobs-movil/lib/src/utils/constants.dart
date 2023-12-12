import 'package:flutter/material.dart';

class Constants {
  static const double padding = 20;
  static const double avatarRadius = 55;
  static const Color blueGeneric = Color.fromARGB(255, 0, 0, 0);

  static const String apiUrl = '192.168.0.11:8081';
  // static const String apiUrl_QA = '192.168.162.58:8081';
  // static const String apiUrl_PRD = 'portal.macropay.mx';

  static const Map<String, String> headersPublic = {
    "Content-Type": "application/json"
  };

  static const Map<String, String> headersProtect = {
    "Content-Type": "application/json",
    'Authorization': 'Bearer ',
  };
}

const kPrimaryColor = Color(0xFFFF8084);
const kAccentColor = Color(0xFFF1F1F1);
const kWhiteColor = Color(0xFFFFFFFF);
const kLightColor = Color(0xFF808080);
const kDarkColor = Color(0xFF303030);
const kTransparent = Colors.transparent;

const kDefaultPadding = 24.0;
const kLessPadding = 10.0;
const kFixPadding = 16.0;
const kLess = 4.0;

const kShape = 30.0;

const kRadius = 0.0;
const kAppBarHeight = 56.0;

const kHeadTextStyle = TextStyle(
  fontSize: 24.0,
  fontWeight: FontWeight.bold,
);

const kSubTextStyle = TextStyle(
  fontSize: 18.0,
  color: kLightColor,
);

const kTitleTextStyle = TextStyle(
  fontSize: 20.0,
  color: kPrimaryColor,
);

const kDarkTextStyle = TextStyle(
  fontSize: 23.0,
  color: kDarkColor,
);

const kDivider = Divider(
  color: kAccentColor,
  thickness: kLessPadding,
);

const kSmallDivider = Divider(
  color: kAccentColor,
  thickness: 5.0,
);

const String pieChart = 'assets/images/pieChart.png';
const String trophy = 'assets/images/trophy.png';
const String chat = 'assets/images/chat.png';
const String whiteShape = 'assets/images/whitebg.svg';
const String logo = 'assets/images/shoppingBag.png';
const String profile = 'assets/images/profile.jpg';
const String bg = 'assets/images/background.jpg';
const String manShoes = 'assets/images/manShoes.jpg';
const String success = 'assets/images/success.gif';
const String chatBubble = 'assets/images/emptyChat.png';
const String emptyOrders = 'assets/images/orders.png';
const String callCenter = 'assets/images/center.png';
const String conversation = 'assets/images/conversation.png';

List<Map<String, String>> introData = [
  {
    'image': pieChart,
    'headText': 'Track your routine',
    'descText':
        "Whether it's sets, reps, weight used, you can track it all with our intuitive interface.",
  },
  {
    'image': trophy,
    'headText': 'Set personal goals',
    'descText':
        "We're all in the gym for a reason: goals. set goals for diet and fitness.",
  },
  {
    'image': chat,
    'headText': 'Chat with others',
    'descText': "Inspire and help each other reach fitness and diet goals.",
  },
];

final labels = [
  'Notifications',
  'Payments',
  'Message',
  'My Orders',
  'Setting Account',
  'Call Center',
  'About Application',
];

final icons = [
  Icons.notifications,
  Icons.payment,
  Icons.message,
  Icons.local_dining,
  Icons.settings,
  Icons.person,
  Icons.info,
];

final paymentLabels = [
  'Credit card / Debit card',
  'Cash on delivery',
  'Paypal',
  'Google wallet',
];

final paymentIcons = [
  Icons.credit_card,
  Icons.money_off,
  Icons.payment,
  Icons.account_balance_wallet,
];

final settingLabel = [
  'Account',
  'Address',
  'Telephone',
  'Email',
  'Setting',
  'Order Notifications',
  'Discount Notifications',
  'Credit Card',
  'Logout',
];

List<String> chipsMobile = [
  "IPhone",
  "Samsung",
  "OnePlus",
  "RealMe",
  "Xiomi",
  "Oppo",
  "Vivo",
];

List<String> chipsCategory = [
  "Mobiles",
  "Cloths",
  "Electronics",
  "Furnitures",
  "Shoes",
  "Laptops",
  "Watches",
];

final List<String> sliderImages = [
  "assets/images/books.jpg",
  "assets/images/cameras.jpg",
  "assets/images/mensShoes.jpg",
  "assets/images/watches.jpg",
  "assets/images/headphones.jpg",
  "assets/images/girlsFootwear.jpg",
  "assets/images/joysticks.jpg",
  "assets/images/desktop.jpg",
  "assets/images/gymEquipments.jpg",
];

// Datos para los dropDownSelector

// Rango de ingresos
List<DropdownMenuItem<String>> get dropdownIncomeRangeItems {
  List<DropdownMenuItem<String>> menuItems = const [
    DropdownMenuItem(child: Text("1,000 a 3000 MXN"), value: "1"),
    DropdownMenuItem(child: Text("3,000 a 5000 MXN"), value: "2"),
    DropdownMenuItem(child: Text("5,000 a 8000 MXN"), value: "3"),
    DropdownMenuItem(child: Text("Mas de 8,0000"), value: "4"),
  ];
  return menuItems;
}

// Tipo de ingresos
List<DropdownMenuItem<String>> get dropdownIncomeTypeItems {
  List<DropdownMenuItem<String>> menuItems = const [
    DropdownMenuItem(child: Text("Semanalmente"), value: "1"),
    DropdownMenuItem(child: Text("Quincenalmente"), value: "2"),
    DropdownMenuItem(child: Text("Mensualmente"), value: "3"),
    DropdownMenuItem(child: Text("Ocasionalmente"), value: "4"),
  ];
  return menuItems;
}

// Tipo de empleos
List<DropdownMenuItem<String>> get dropdownJobTypeItems {
  List<DropdownMenuItem<String>> menuItems = const [
    DropdownMenuItem(child: Text("Formal"), value: "1"),
    DropdownMenuItem(child: Text("Informal"), value: "2"),
  ];
  return menuItems;
}

// Tiempo laborando continuamente
List<DropdownMenuItem<String>> get dropdownCurrentJobItems {
  List<DropdownMenuItem<String>> menuItems = const [
    DropdownMenuItem(child: Text("0 a 1 año"), value: "1"),
    DropdownMenuItem(child: Text("1 a 3 años"), value: "2"),
    DropdownMenuItem(child: Text("1 a 5 años"), value: "3"),
    DropdownMenuItem(child: Text("Mas de 5 años"), value: "3"),
  ];
  return menuItems;
}

// ¿Cual es tu grado de estudios?

List<DropdownMenuItem<String>> get dropdownStudyGradeItems {
  List<DropdownMenuItem<String>> menuItems = const [
    DropdownMenuItem(child: Text("Primaria"), value: "1"),
    DropdownMenuItem(child: Text("Secundaria"), value: "2"),
    DropdownMenuItem(child: Text("Preparatoria"), value: "3"),
    DropdownMenuItem(child: Text("Universidad"), value: "3"),
  ];
  return menuItems;
}
