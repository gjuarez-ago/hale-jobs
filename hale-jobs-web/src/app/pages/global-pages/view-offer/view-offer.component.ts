import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/core/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-view-offer',
  templateUrl: './view-offer.component.html',
  styleUrls: ['./view-offer.component.css']
})
export class ViewOfferComponent implements OnInit {

  public isLoadingGeneral: boolean = false;
  public user: User| undefined;
  public userId : any;
  public subcriptions: Subscription[] = [];
  public currentElement : any;
  offerId: any;
  
  constructor(
    private readonly meta: Meta,
    private readonly title: Title,   
    private authenticationService: AuthService,
    private fb: FormBuilder,
    private modal: NzModalService,
    private message: NzMessageService,
    private router: Router,
    private actRoute: ActivatedRoute,
    private ngxSpinner: NgxSpinnerService,
    private offerService : OfferService,
    private notification: NzNotificationService,

  ) { }

  ngOnInit(): void {
    if (this.authenticationService.isUserLoggedIn()) {
      this.user = this.authenticationService.getUserFromLocalCache();
      this.userId = this.user.id;
      this.offerId = this.actRoute.snapshot.params.id;
      this.getOfferById(this.offerId);
    } else {
      this.router.navigateByUrl("/auth/login");
    }
  }

  public onBack() {
    this.router.navigateByUrl('/dashboard/my-company');
  }

  public getOfferById(id: any) {
    this.isLoadingGeneral = true;
    this.ngxSpinner.show();
    this.offerService.findOfferById(id).subscribe(
      (response: any) => {
        this.currentElement = response;
        this.title.setTitle(this.currentElement.title + " - "+  this.currentElement.company.name)
        console.log(this.currentElement);
        this.isLoadingGeneral=false;
        this.ngxSpinner.hide();
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error",  errorResponse.error.message);
        this.isLoadingGeneral=false;
        this.ngxSpinner.hide();
      }
    )
  }

  public getWorkPlace(item: any): string {
    let works = [
      { value: 'Jornada Completa', id: 'A' },
      { value: 'Media jornada', id: 'B' },
      { value: 'Pasantias', id: 'C' },
      { value: 'Por proyecto', id: 'D' },
    ];
    let index: any = works.find((e: any) => e.id == item);
    return index.value;
  }
  
  public getUrgency(item: any): string {
    let urgency = [
      { value: 'Solicita personal urgentemente', id: 'A' },
      { value: 'Urgencia moderada', id: 'B' },
      { value: 'Solicita personal', id: 'C' },
    ];
    let index: any = urgency.find((e: any) => e.id == item);
    return index.value;
  }  


  public getDays(fecha :any) {
  
    let r = 1;
    var fechaIni : any = new Date(fecha);
// Crear objeto de fecha final (actual)
var fechaFin : any = new Date();
var diff = fechaFin - fechaIni;

    let diferenciaDias = Math.floor(diff / (1000 * 60 * 60 * 24));

    if(diferenciaDias == 0) {
      return "Hace 1 día";
    }

    if(diferenciaDias == 7) {
      return "Hace 1 semana";
    }

    if(diferenciaDias != 7 && diferenciaDias != 0) {
      return "Hace " + diferenciaDias + " días";
    }

    return diferenciaDias;
  }

  createNotification(type: string, message: string): void {
    this.notification.create(
      type,
      'Upps!',
      `${message}`,
      { nzPlacement: 'bottomLeft' }
    );
  }



}
