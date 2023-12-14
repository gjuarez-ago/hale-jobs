import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxModulesComponents } from 'src/app/modules/ngx-modules-component';
import { NgZoroModulesComponents } from 'src/app/modules/ngzoro-modules-component';


import { SideMenuRh } from '../../components/side-menu-rh/side-menu-rh.component';
import { RhDashboardComponent } from './rh-dashboard/rh-dashboard.component';
import { RhOffersComponent } from './rh-offers/rh-offers.component';
import { RhExamsComponent } from './rh-exams/rh-exams.component';
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
import { AdminTypeOfJobComponent } from '../admin-pages/admin-type-of-job/admin-type-of-job.component';
import { AdminTypeOfPaymentsComponent } from '../admin-pages/admin-type-of-payments/admin-type-of-payments.component';
import { RouterModule } from '@angular/router';
import { ComponentsModule } from 'src/app/components/components.module';
import { ChartsModule } from 'ng2-charts';
import { NewOfferComponent } from './rh-offers/new-offer/new-offer.component';
import { NewCompanyComponent } from './rh-company/new-company/new-company.component';
import { UpdateCompanyComponent } from './rh-company/update-company/update-company.component';


const welcomeComponents: any = [
  SideMenuRh,
  RhDashboardComponent,
  RhOffersComponent,
  RhExamsComponent,
  RhCompanyComponent,
  RhSearchWorkersComponent,
  RhContactBookComponent,
  RhComplaintsComponent,
  RhNotificationsComponent,
  RhProfileComponent,
  NewOfferComponent,
  NewCompanyComponent,
  AdminDashboardComponent,
  AdminOffersComponent,
  AdminCommentsComponent,
  AdminComplaintsComponent,
  AdminPaymentsComponent,
  AdminPostulationsIComponent,
  AdminPostulationsComponent,
  AdminReviewsByBusinessComponent,
  AdminReviewsByUserComponent,
  AdminWorkersExperiencesComponent,
  AdminNotficationsComponent,
  AdminOpinionsByCompanyComponent,
  AdminUsersComponent,
  AdminPermissionsComponent,
  AdminTypeOfJobComponent,
  AdminTypeOfPaymentsComponent,
  UpdateCompanyComponent,
]

@NgModule({
  declarations: [
    welcomeComponents,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgxModulesComponents,
    NgZoroModulesComponents,
    RouterModule,
    ComponentsModule,
    ChartsModule,
  ],
  exports: [
    welcomeComponents,
    ReactiveFormsModule,
    NgxModulesComponents,
    NgZoroModulesComponents,
    ChartsModule
  ]
})
export class RhModule { }
