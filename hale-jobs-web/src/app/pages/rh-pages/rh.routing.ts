import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { SideMenuRh } from '../../components/side-menu-rh/side-menu-rh.component';
import { RhDashboardComponent } from './rh-dashboard/rh-dashboard.component';
import { RhOffersComponent } from './rh-offers/rh-offers.component';
import { RhCompanyComponent } from './rh-company/rh-company.component';
import { RhSearchWorkersComponent } from './rh-search-workers/rh-search-workers.component';
import { RhContactBookComponent } from './rh-contact-book/rh-contact-book.component';
import { RhComplaintsComponent } from './rh-complaints/rh-complaints.component';
import { RhNotificationsComponent } from './rh-notifications/rh-notifications.component';
import { RhProfileComponent } from './rh-profile/rh-profile.component';
import { AdminDashboardComponent } from '../admin-pages/admin-dashboard/admin-dashboard.component';
import { AdminOffersComponent } from '../admin-pages/admin-offers/admin-offers.component';
import { AdminCommentsComponent } from '../admin-pages/admin-comments/admin-comments.component';
import { AdminComplaintsComponent } from '../admin-pages/admin-complaints/admin-complaints.component';
import { AdminPaymentsComponent } from '../admin-pages/admin-payments/admin-payments.component';
import { AdminPostulationsIComponent } from '../admin-pages/admin-postulations-i/admin-postulations-i.component';
import { AdminPostulationsComponent } from '../admin-pages/admin-postulations/admin-postulations.component';
import { AdminReviewsByBusinessComponent } from '../admin-pages/admin-reviews-by-business/admin-reviews-by-business.component';
import { AdminReviewsByUserComponent } from '../admin-pages/admin-reviews-by-user/admin-reviews-by-user.component';
import { AdminWorkersExperiencesComponent } from '../admin-pages/admin-workers-experiences/admin-workers-experiences.component';
import { AdminNotficationsComponent } from '../admin-pages/admin-notfications/admin-notfications.component';
import { AdminOpinionsByCompanyComponent } from '../admin-pages/admin-opinions-by-company/admin-opinions-by-company.component';
import { AdminUsersComponent } from '../admin-pages/admin-users/admin-users.component';
import { AdminPermissionsComponent } from '../admin-pages/admin-permissions/admin-permissions.component';
import { AdminTypeOfPaymentsComponent } from '../admin-pages/admin-type-of-payments/admin-type-of-payments.component';
import { AdminTypeOfJobComponent } from '../admin-pages/admin-type-of-job/admin-type-of-job.component';
import { RhHistoryComponent } from './rh-history/rh-history.component';
import { NewOfferComponent } from './rh-offers/new-offer/new-offer.component';
import { NewCompanyComponent } from './rh-company/new-company/new-company.component';
import { UpdateCompanyComponent } from './rh-company/update-company/update-company.component';
import { UpdateOfferComponent } from './rh-offers/update-offer/update-offer.component';
import { ViewProfileComponent } from './rh-search-workers/view-profile/view-profile.component';

const routes: Routes = [
    {
        path: 'dashboard', component: SideMenuRh, children: [
          { path: "my-statistics", component: RhDashboardComponent },
          { path: "my-offers", component: RhOffersComponent},
          { path: "new-offer", component: NewOfferComponent },
          { path: "update-offer/:id", component: UpdateOfferComponent },
          
          { path: "my-company", component: RhCompanyComponent },
          { path: "new-company", component: NewCompanyComponent },
          { path: "update-company/:id", component: UpdateCompanyComponent },

          { path: "search-workers", component: RhSearchWorkersComponent },
          { path: "view-worker/:id", component: ViewProfileComponent },
          { path: "my-contacts", component: RhContactBookComponent },
          { path: "complaints", component: RhComplaintsComponent },
          { path: "my-notifications", component: RhNotificationsComponent },
          { path: "my-profile", component: RhProfileComponent },
          { path: "chat-one-to-one", component: RhContactBookComponent },
          { path: "history", component: RhHistoryComponent },
          

          { path: "statisticts", component: AdminDashboardComponent },
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
          { path: "type-of-payments", component: AdminTypeOfPaymentsComponent },
          { path: "type-of-jobs", component: AdminTypeOfJobComponent },
          
          { path: "faq", component: AdminPermissionsComponent },
          { path: "privacy", component: AdminPermissionsComponent },
    
          { path: '', pathMatch: 'full', redirectTo: '/auth/login' },
        ],
        // canActivate: [ AuthGuard ]
      },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RhRoutingModule {}
