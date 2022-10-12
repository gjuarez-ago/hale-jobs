import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AboutComponent } from './pages/global-pages/about/about.component';
import { AdminDashboardComponent } from './pages/admin-pages/admin-dashboard/admin-dashboard.component';
import { AdminUsersComponent } from './pages/admin-pages/admin-users/admin-users.component';
import { AuthComponent } from './pages/auth/auth.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { RecoveryPasswordComponent } from './pages/auth/recovery-password/recovery-password.component';
import { RegisterComponent } from './pages/auth/register/register.component';
import { HomeComponent } from './pages/global-pages/home/home.component';
import { MyProfileComponent } from './pages/client-pages/my-profile/my-profile.component';
import { SearchJobsComponent } from './pages/global-pages/search-jobs/search-jobs.component';
import { SearchOffersComponent } from './pages/global-pages/search-offers/search-offers.component';
import { TermsConditionsComponent } from './pages/global-pages/terms-conditions/terms-conditions.component';
import { ViewJobComponent } from './pages/global-pages/view-job/view-job.component';
import { ViewOfferComponent } from './pages/global-pages/view-offer/view-offer.component';
import { ViewWorkerComponent } from './pages/global-pages/view-worker/view-worker.component';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { RhDashboardComponent } from './pages/rh-pages/rh-dashboard/rh-dashboard.component';
import { AdminPermissionsComponent } from './pages/admin-pages/admin-permissions/admin-permissions.component';
import { RhOffersComponent } from './pages/rh-pages/rh-offers/rh-offers.component';
import { RhCreateOfferComponent } from './pages/rh-pages/rh-create-offer/rh-create-offer.component';
import { RhEditOfferComponent } from './pages/rh-pages/rh-edit-offer/rh-edit-offer.component';
import { RhExamsComponent } from './pages/rh-pages/rh-exams/rh-exams.component';
import { RhPreviewExamComponent } from './pages/rh-pages/rh-preview-exam/rh-preview-exam.component';
import { RhViewExamByOfferComponent } from './pages/rh-pages/rh-view-exam-by-offer/rh-view-exam-by-offer.component';
import { RhCompanyComponent } from './pages/rh-pages/rh-company/rh-company.component';
import { RhViewCoworkersComponent } from './pages/rh-pages/rh-view-coworkers/rh-view-coworkers.component';
import { RhSearchWorkersComponent } from './pages/rh-pages/rh-search-workers/rh-search-workers.component';
import { RhContactBookComponent } from './pages/rh-pages/rh-contact-book/rh-contact-book.component';
import { RhComplaintsComponent } from './pages/rh-pages/rh-complaints/rh-complaints.component';
import { RhNotificationsComponent } from './pages/rh-pages/rh-notifications/rh-notifications.component';
import { RhProfileComponent } from './pages/rh-pages/rh-profile/rh-profile.component';
import { SearchCompaniesComponent } from './pages/global-pages/search-companies/search-companies.component';
import { SupportCandidatesComponent } from './pages/global-pages/support-candidates/support-candidates.component';
import { PrivacyPoliciesComponent } from './pages/global-pages/privacy-policies/privacy-policies.component';
import { ViewCompaniesComponent } from './pages/global-pages/view-companies/view-companies.component';
import { MyPostulationsComponent } from './pages/client-pages/my-postulations/my-postulations.component';
import { MyHistoryComponent } from './pages/client-pages/my-history/my-history.component';
import { MyCompaniesComponent } from './pages/client-pages/my-companies/my-companies.component';
import { MyPaymentsComponent } from './pages/client-pages/my-payments/my-payments.component';
import { PaymentClientComponent } from './pages/client-pages/payment-client/payment-client.component';
import { MyNotificationsComponent } from './pages/client-pages/my-notifications/my-notifications.component';
import { SettingsProfileComponent } from './pages/client-pages/settings-profile/settings-profile.component';
import { MyExamsComponent } from './pages/client-pages/my-exams/my-exams.component';
import { AdminOffersIComponent } from './pages/admin-pages/admin-offers-i/admin-offers-i.component';
import { AdminOffersComponent } from './pages/admin-pages/admin-offers/admin-offers.component';
import { AdminCommentsComponent } from './pages/admin-pages/admin-comments/admin-comments.component';
import { AdminComplaintsComponent } from './pages/admin-pages/admin-complaints/admin-complaints.component';
import { AdminPaymentsComponent } from './pages/admin-pages/admin-payments/admin-payments.component';
import { AdminPostulationsComponent } from './pages/admin-pages/admin-postulations/admin-postulations.component';
import { AdminPostulationsIComponent } from './pages/admin-pages/admin-postulations-i/admin-postulations-i.component';
import { AdminReviewsByBusinessComponent } from './pages/admin-pages/admin-reviews-by-business/admin-reviews-by-business.component';
import { AdminReviewsByUserComponent } from './pages/admin-pages/admin-reviews-by-user/admin-reviews-by-user.component';
import { AdminWorkersExperiencesComponent } from './pages/admin-pages/admin-workers-experiences/admin-workers-experiences.component';
import { AdminNotficationsComponent } from './pages/admin-pages/admin-notfications/admin-notfications.component';
import { AdminOpinionsByCompanyComponent } from './pages/admin-pages/admin-opinions-by-company/admin-opinions-by-company.component';
import { AdminTypeOfPaymentsComponent } from './pages/admin-pages/admin-type-of-payments/admin-type-of-payments.component';
import { AdminTypeOfJobComponent } from './pages/admin-pages/admin-type-of-job/admin-type-of-job.component';
import { CvProfileComponent } from './pages/client-pages/cv-profile/cv-profile.component';
import { PreviewProfileComponent } from './pages/client-pages/preview-profile/preview-profile.component';

const routes: Routes = [
  { path: 'dashboard', component: WelcomeComponent, children: [

     { path: "my-statisticts", component: RhDashboardComponent },
     { path: "my-offers", component: RhOffersComponent },
     { path: "create-offer", component: RhCreateOfferComponent },
     { path: "edit-offer/:id", component: RhEditOfferComponent },
     { path: "my-exams", component: RhExamsComponent },
     { path: "create-exam", component: RhExamsComponent },
     { path: "preview-exam/:id", component: RhPreviewExamComponent },
     { path: "view-exam/:user", component: RhViewExamByOfferComponent },
     { path: "my-company", component: RhCompanyComponent },
     { path: "my-company-coworkers", component: RhViewCoworkersComponent },
     { path: "search-workers", component: RhSearchWorkersComponent },
     { path: "view-worker/:id", component: RhDashboardComponent },
     { path: "my-contacts", component: RhContactBookComponent },
     { path: "complaints", component: RhComplaintsComponent },
     { path: "my-notifications", component: RhNotificationsComponent },
     { path: "my-profile", component: RhProfileComponent },
     { path: "chat-one-to-one", component: RhContactBookComponent },

    //  Admin
    { path: "statisticts", component: AdminDashboardComponent },
    { path: "offers-informals", component: AdminOffersIComponent },
    { path: "offers-formals", component: AdminOffersComponent },
    { path: "admin-comments", component: AdminCommentsComponent },
    { path: "admin-complaints", component: AdminComplaintsComponent },
    { path: "admin-payments", component: AdminPaymentsComponent },
    { path: "admin-postulations-i", component: AdminPostulationsIComponent },
    { path: "admin-postulations-f", component: AdminPostulationsComponent },
    { path: "reviews-b", component: AdminReviewsByBusinessComponent },
    { path: "reviews-p", component: AdminReviewsByUserComponent },
    { path: "worker-experiences", component: AdminWorkersExperiencesComponent },
    { path: "admin-notifications", component: AdminNotficationsComponent },
    { path: "admin-companies", component: AdminOpinionsByCompanyComponent },
    { path: "admin-users", component: AdminUsersComponent },

    // Catalogs
    { path: "permissions", component: AdminPermissionsComponent },    
    // { path: "cities", component: AdminPermissionsComponent },
    // { path: "states", component: AdminPermissionsComponent },
    { path: "type-of-payments", component: AdminTypeOfPaymentsComponent },
    { path: "type-of-jobs", component: AdminTypeOfJobComponent },

    // Others
    { path: "faq", component: AdminPermissionsComponent },    
    { path: "privacy", component: AdminPermissionsComponent },

    // Default

     { path: '', pathMatch: 'full', redirectTo: '/login' },
    ],
  // canActivate: [ AuthGuard ]
  },
  {path: 'auth', component: AuthComponent, children : [
    { path: "login", component: LoginComponent },
    { path: "register", component: RegisterComponent },
    { path: "recovery-password", component: RecoveryPasswordComponent },
  ]
  },
  { path: 'profile', component: MyProfileComponent,  children: [
      { path: 'cv', component: CvProfileComponent },
      { path: 'settings-profile', component: SettingsProfileComponent },
      { path: 'my-postulations', component: MyPostulationsComponent }, 
      { path: 'view-cv', component: PreviewProfileComponent }, 
      { path: 'my-exams', component: MyExamsComponent }, 
      { path: 'my-history', component: MyHistoryComponent }, 
      { path: 'my-companies', component: MyCompaniesComponent },
      { path: 'my-payments', component: MyPaymentsComponent }, 
      { path: 'payment', component: PaymentClientComponent },
      { path: 'notifications', component: MyNotificationsComponent },
  ]},
  {
    path: '', component: NavbarComponent, children: [
      
      // Global pages
      { path: 'home', component: HomeComponent},
      { path: 'search', component: SearchOffersComponent}, 
      { path: 'search-jobs', component: SearchJobsComponent },
      { path: 'companies', component: SearchCompaniesComponent },
      { path: 'company/:id', component: ViewCompaniesComponent },
      { path: 'view-job/:id', component: ViewJobComponent },
      { path: 'packs', component: PaymentClientComponent},
      
      { path: 'support-candidates', component: SupportCandidatesComponent },
      { path: 'faq', component: AboutComponent },
      { path: 'about', component: AboutComponent },
      { path: "terms-and-conditions", component: TermsConditionsComponent },
      { path: "privacy-policies", component: PrivacyPoliciesComponent },

      // { path: 'view/:id', component: ViewOfferComponent },
      { path: '', pathMatch: 'full', redirectTo: '/home' },
      
    ],
    data: { animation: 'HomePage' }
  },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
