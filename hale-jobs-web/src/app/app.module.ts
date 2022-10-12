import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { IconsProviderModule } from './icons-provider.module';
import { NgxCaptchaModule } from 'ngx-captcha';

import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';

import { NzAffixModule } from 'ng-zorro-antd/affix';
import { NzAlertModule } from 'ng-zorro-antd/alert';
import { NzAnchorModule } from 'ng-zorro-antd/anchor';
import { NzAutocompleteModule } from 'ng-zorro-antd/auto-complete';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzBackTopModule } from 'ng-zorro-antd/back-top';
import { NzBadgeModule } from 'ng-zorro-antd/badge';
import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzCalendarModule } from 'ng-zorro-antd/calendar';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzCarouselModule } from 'ng-zorro-antd/carousel';
import { NzCascaderModule } from 'ng-zorro-antd/cascader';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzCollapseModule } from 'ng-zorro-antd/collapse';
import { NzCommentModule } from 'ng-zorro-antd/comment';
import { NzNoAnimationModule } from 'ng-zorro-antd/core/no-animation';
import { NzTransButtonModule } from 'ng-zorro-antd/core/trans-button';
import { NzWaveModule } from 'ng-zorro-antd/core/wave';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzEmptyModule } from 'ng-zorro-antd/empty';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzI18nModule } from 'ng-zorro-antd/i18n';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzImageModule } from 'ng-zorro-antd/image';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzInputNumberModule } from 'ng-zorro-antd/input-number';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzMentionModule } from 'ng-zorro-antd/mention';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzMessageModule } from 'ng-zorro-antd/message';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzNotificationModule } from 'ng-zorro-antd/notification';
import { NzPageHeaderModule } from 'ng-zorro-antd/page-header';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { NzPopconfirmModule } from 'ng-zorro-antd/popconfirm';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
import { NzProgressModule } from 'ng-zorro-antd/progress';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { NzRateModule } from 'ng-zorro-antd/rate';
import { NzResultModule } from 'ng-zorro-antd/result';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzSkeletonModule } from 'ng-zorro-antd/skeleton';
import { NzSliderModule } from 'ng-zorro-antd/slider';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzStatisticModule } from 'ng-zorro-antd/statistic';
import { NzStepsModule } from 'ng-zorro-antd/steps';
import { NzSwitchModule } from 'ng-zorro-antd/switch';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzTagModule } from 'ng-zorro-antd/tag';
import { NzTimePickerModule } from 'ng-zorro-antd/time-picker';
import { NzTimelineModule } from 'ng-zorro-antd/timeline';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';
import { NzTransferModule } from 'ng-zorro-antd/transfer';
import { NzTreeModule } from 'ng-zorro-antd/tree';
import { NzTreeViewModule } from 'ng-zorro-antd/tree-view';
import { NzTreeSelectModule } from 'ng-zorro-antd/tree-select';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { NzResizableModule } from 'ng-zorro-antd/resizable';
import { NzPipesModule } from 'ng-zorro-antd/pipes';
import { NzSpaceModule } from 'ng-zorro-antd/space';



import { AppComponent } from './app.component';
import { HomeComponent } from './pages/global-pages/home/home.component';
import { AboutComponent } from './pages/global-pages/about/about.component';
import { AdminPaymentsComponent } from './pages/admin-pages/admin-payments/admin-payments.component';
import { AdminUsersComponent } from './pages/admin-pages/admin-users/admin-users.component';
import { AdminPermissionsComponent } from './pages/admin-pages/admin-permissions/admin-permissions.component';
import { FaqComponent } from './pages/global-pages/faq/faq.component';
import { TermsConditionsComponent } from './pages/global-pages/terms-conditions/terms-conditions.component';
import { AdminNotficationsComponent } from './pages/admin-pages/admin-notfications/admin-notfications.component';
import { AdminOffersComponent } from './pages/admin-pages/admin-offers/admin-offers.component';
import { AuthComponent } from './pages/auth/auth.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { RecoveryPasswordComponent } from './pages/auth/recovery-password/recovery-password.component';
import { ResetPasswordComponent } from './pages/auth/reset-password/reset-password.component';
import { SearchOffersComponent } from './pages/global-pages/search-offers/search-offers.component';
import { ViewOfferComponent } from './pages/global-pages/view-offer/view-offer.component';
import { ViewWorkerComponent } from './pages/global-pages/view-worker/view-worker.component';
import { AdminReviewsByUserComponent } from './pages/admin-pages/admin-reviews-by-user/admin-reviews-by-user.component';
import { AdminReviewsByBusinessComponent } from './pages/admin-pages/admin-reviews-by-business/admin-reviews-by-business.component';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { AdminDashboardComponent } from './pages/admin-pages/admin-dashboard/admin-dashboard.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';


import {IvyCarouselModule} from 'angular-responsive-carousel';
import { NgxSpinnerModule } from "ngx-spinner";

import {GalleriaModule} from 'primeng/galleria';
import { MyProfileComponent } from './pages/client-pages/my-profile/my-profile.component';
import {SliderModule} from 'primeng/slider';
import { SearchJobsComponent } from './pages/global-pages/search-jobs/search-jobs.component';
import { ViewJobComponent } from './pages/global-pages/view-job/view-job.component';
import { ChartsModule } from 'ng2-charts';
import { RegisterCompanyComponent } from './pages/auth/register-company/register-company.component';
import { ViewJobsIComponent } from './pages/client-pages/view-jobs-i/view-jobs-i.component';
import { ViewJobsFComponent } from './pages/client-pages/view-jobs-f/view-jobs-f.component';
import { MyDashboardComponent } from './pages/client-pages/my-dashboard/my-dashboard.component';
import { MyPostulationsComponent } from './pages/client-pages/my-postulations/my-postulations.component';
import { MyCompaniesComponent } from './pages/client-pages/my-companies/my-companies.component';
import { MyNotificationsComponent } from './pages/client-pages/my-notifications/my-notifications.component';
import { MyPaymentsComponent } from './pages/client-pages/my-payments/my-payments.component';
import { PaymentClientComponent } from './pages/client-pages/payment-client/payment-client.component';
import { RhDashboardComponent } from './pages/rh-pages/rh-dashboard/rh-dashboard.component';
import { RhOffersComponent } from './pages/rh-pages/rh-offers/rh-offers.component';
import { RhExamsComponent } from './pages/rh-pages/rh-exams/rh-exams.component';
import { RhCompanyComponent } from './pages/rh-pages/rh-company/rh-company.component';
import { RhProfileComponent } from './pages/rh-pages/rh-profile/rh-profile.component';
import { RhViewPostulatesComponent } from './pages/rh-pages/rh-view-postulates/rh-view-postulates.component';
import { RhViewCoworkersComponent } from './pages/rh-pages/rh-view-coworkers/rh-view-coworkers.component';
import { RhViewExamByOfferComponent } from './pages/rh-pages/rh-view-exam-by-offer/rh-view-exam-by-offer.component';
import { RhPreviewExamComponent } from './pages/rh-pages/rh-preview-exam/rh-preview-exam.component';
import { RhHistoryComponent } from './pages/rh-pages/rh-history/rh-history.component';
import { RhCreateOfferComponent } from './pages/rh-pages/rh-create-offer/rh-create-offer.component';
import { RhPaymentsComponent } from './pages/rh-pages/rh-payments/rh-payments.component';
import { RhBuyMembershipComponent } from './pages/rh-pages/rh-buy-membership/rh-buy-membership.component';
import { RhEditOfferComponent } from './pages/rh-pages/rh-edit-offer/rh-edit-offer.component';
import { RhContactBookComponent } from './pages/rh-pages/rh-contact-book/rh-contact-book.component';
import { RhNotificationsComponent } from './pages/rh-pages/rh-notifications/rh-notifications.component';
import { SupportCandidatesComponent } from './pages/global-pages/support-candidates/support-candidates.component';
import { PrivacyPoliciesComponent } from './pages/global-pages/privacy-policies/privacy-policies.component';
import { SearchCompaniesComponent } from './pages/global-pages/search-companies/search-companies.component';
import { ViewCompaniesComponent } from './pages/global-pages/view-companies/view-companies.component';
import { MyHistoryComponent } from './pages/client-pages/my-history/my-history.component';
import { AdminOffersIComponent } from './pages/admin-pages/admin-offers-i/admin-offers-i.component';
import { AdminCommentsComponent } from './pages/admin-pages/admin-comments/admin-comments.component';
import { AdminComplaintsComponent } from './pages/admin-pages/admin-complaints/admin-complaints.component';
import { AdminPostulationsComponent } from './pages/admin-pages/admin-postulations/admin-postulations.component';
import { AdminPostulationsIComponent } from './pages/admin-pages/admin-postulations-i/admin-postulations-i.component';
import { AdminWorkersExperiencesComponent } from './pages/admin-pages/admin-workers-experiences/admin-workers-experiences.component';
import { AdminOpinionsByCompanyComponent } from './pages/admin-pages/admin-opinions-by-company/admin-opinions-by-company.component';
import { AdminTypeOfPaymentsComponent } from './pages/admin-pages/admin-type-of-payments/admin-type-of-payments.component';
import { AdminTypeOfJobComponent } from './pages/admin-pages/admin-type-of-job/admin-type-of-job.component';
import { AdminFilesComponent } from './pages/admin-pages/admin-files/admin-files.component';
import { RhSearchWorkersComponent } from './pages/rh-pages/rh-search-workers/rh-search-workers.component';
import { RhComplaintsComponent } from './pages/rh-pages/rh-complaints/rh-complaints.component';
import { SettingsProfileComponent } from './pages/client-pages/settings-profile/settings-profile.component';
import { MyExamsComponent } from './pages/client-pages/my-exams/my-exams.component';
import { PreviewProfileComponent } from './pages/client-pages/preview-profile/preview-profile.component';
import { CvProfileComponent } from './pages/client-pages/cv-profile/cv-profile.component';



registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    AdminPaymentsComponent,
    AdminUsersComponent,
    AdminPermissionsComponent,
    FaqComponent,
    TermsConditionsComponent,
    AdminNotficationsComponent,
    AdminOffersComponent,
    AuthComponent,
    LoginComponent,
    WelcomeComponent,
    RecoveryPasswordComponent,
    ResetPasswordComponent,
    SearchOffersComponent,
    ViewOfferComponent,
    ViewWorkerComponent,
    AdminReviewsByUserComponent,
    AdminReviewsByBusinessComponent,
    AdminDashboardComponent,
    RegisterComponent,
    NavbarComponent,
    FooterComponent,
    MyProfileComponent,
    SearchJobsComponent,
    ViewJobComponent,
    RegisterCompanyComponent,
    ViewJobsIComponent,
    ViewJobsFComponent,
    MyDashboardComponent,
    MyPostulationsComponent,
    MyCompaniesComponent,
    MyNotificationsComponent,
    MyPaymentsComponent,
    PaymentClientComponent,
    RhDashboardComponent,
    RhOffersComponent,
    RhExamsComponent,
    RhCompanyComponent,
    RhProfileComponent,
    RhViewPostulatesComponent,
    RhViewCoworkersComponent,
    RhViewExamByOfferComponent,
    RhPreviewExamComponent,
    RhHistoryComponent,
    RhCreateOfferComponent,
    RhPaymentsComponent,
    RhBuyMembershipComponent,
    RhEditOfferComponent,
    RhContactBookComponent,
    RhNotificationsComponent,
    SupportCandidatesComponent,
    PrivacyPoliciesComponent,
    SearchCompaniesComponent,
    ViewCompaniesComponent,
    MyHistoryComponent,
    AdminOffersIComponent,
    AdminCommentsComponent,
    AdminComplaintsComponent,
    AdminPostulationsComponent,
    AdminPostulationsIComponent,
    AdminWorkersExperiencesComponent,
    AdminOpinionsByCompanyComponent,
    AdminTypeOfPaymentsComponent,
    AdminTypeOfJobComponent,
    AdminFilesComponent,
    RhSearchWorkersComponent,
    RhComplaintsComponent,
    SettingsProfileComponent,
    MyExamsComponent,
    PreviewProfileComponent,
    CvProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxSpinnerModule,    
    HttpClientModule,
    BrowserAnimationsModule,
    NgxCaptchaModule,
    IvyCarouselModule,
    ChartsModule,
    
    // Prime Modules
    SliderModule,
    // End 

    IconsProviderModule,
    NzAffixModule,
    NzAlertModule,
    NzAnchorModule,
    NzAutocompleteModule,
    NzAvatarModule,
    NzBackTopModule,
    NzBadgeModule,
    NzButtonModule,
    NzBreadCrumbModule,
    NzSpaceModule,
    NzCalendarModule,
    NzCardModule,
    NzCarouselModule,
    NzCascaderModule,
    NzCheckboxModule,
    NzCollapseModule,
    NzCommentModule,
    NzDatePickerModule,
    NzDescriptionsModule,
    NzDividerModule,
    NzDrawerModule,
    NzDropDownModule,
    NzEmptyModule,
    NzFormModule,
    NzGridModule,
    NzI18nModule,
    NzIconModule,
    NzImageModule,
    NzInputModule,
    NzInputNumberModule,
    NzLayoutModule,
    NzListModule,
    NzMentionModule,
    NzMenuModule,
    NzMessageModule,
    NzModalModule,
    NzNoAnimationModule,
    NzNotificationModule,
    NzPageHeaderModule,
    NzPaginationModule,
    NzPopconfirmModule,
    NzPopoverModule,
    NzProgressModule,
    NzRadioModule,
    NzRateModule,
    NzResultModule,
    NzSelectModule,
    NzSkeletonModule,
    NzSliderModule,
    NzSpinModule,
    NzStatisticModule,
    NzStepsModule,
    NzSwitchModule,
    NzTableModule,
    NzTabsModule,
    NzTagModule,
    NzTimePickerModule,
    NzTimelineModule,
    NzToolTipModule,
    NzTransButtonModule,
    NzTransferModule,
    NzTreeModule,
    NzTreeViewModule,
    NzTreeSelectModule,
    NzTypographyModule,
    NzUploadModule,
    NzWaveModule,
    NzResizableModule,
    NzPipesModule,
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
