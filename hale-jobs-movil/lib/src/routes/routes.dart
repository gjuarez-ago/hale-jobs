import 'package:flutter/material.dart';
import 'package:hale_app/src/pages/about_page/about_page.dart';
import 'package:hale_app/src/pages/faq_page/faq_page.dart';
import 'package:hale_app/src/pages/home_page/home_page.dart';
import 'package:hale_app/src/pages/login_page/login_page.dart';
import 'package:hale_app/src/pages/my_offers_page/my_offers_page.dart';
import 'package:hale_app/src/pages/my_services_page/my_services_page.dart';
import 'package:hale_app/src/pages/notification_page/notification_page.dart';
import 'package:hale_app/src/pages/payment_page/payment_page.dart';
import 'package:hale_app/src/pages/recovery_password_page/recovery_password_page.dart';
import 'package:hale_app/src/pages/register_page/register_page.dart';
import 'package:hale_app/src/pages/reset_password_page/reset_password_page.dart';
import 'package:hale_app/src/pages/search_offers_page/search_offers_page.dart';
import 'package:hale_app/src/pages/search_workers_page/search_workers_page.dart';
import 'package:hale_app/src/pages/splash_page/splash_page.dart';
import 'package:hale_app/src/pages/terms_and_conditions_page/terms_and_conditions_page.dart';
import 'package:hale_app/src/pages/user_profile_page/user_profile_page.dart';
import 'package:hale_app/src/pages/validate_account_page/validate_account_page.dart';
import 'package:hale_app/src/pages/welcome_page/welcome_page.dart';

Map<String, WidgetBuilder> getApplicationRoutes() {
  return <String, WidgetBuilder>{
    AboutPage.routeName: (BuildContext context) => const AboutPage(),
    HomePage.routeName: (BuildContext context) => const HomePage(),
    FaqPage.routeName: (BuildContext context) => const FaqPage(),
    LoginPage.routeName: (BuildContext context) => const LoginPage(),
    MyOffersPage.routeName: (BuildContext context) => const MyOffersPage(),
    MyServicesPage.routeName: (BuildContext context) => const MyServicesPage(),
    NotificationPage.routeName: (BuildContext context) =>
        const NotificationPage(),
    PaymentPage.routeName: (BuildContext context) => const PaymentPage(),
    RecoveryPasswordPage.routeName: (BuildContext context) =>
        const RecoveryPasswordPage(),
    RegisterPage.routeName: (BuildContext context) => const RegisterPage(),
    ResetPasswordPage.routeName: (BuildContext context) =>
        const ResetPasswordPage(),
    SearchOffersPage.routeName: (BuildContext context) =>
        const SearchOffersPage(),
    SearchWorkersPage.routeName: (BuildContext context) =>
        const SearchWorkersPage(),
    SplashPage.routeName: (BuildContext context) => const SplashPage(),
    TermsAndConditionsPage.routeName: (BuildContext context) =>
        const TermsAndConditionsPage(),
    UserProfilePage.routeName: (BuildContext context) =>
        const UserProfilePage(),
    ValidateAccountPage.routeName: (BuildContext context) =>
        const ValidateAccountPage(),
    WelcomePage.routeName: (BuildContext context) => const WelcomePage(),
  };
}
