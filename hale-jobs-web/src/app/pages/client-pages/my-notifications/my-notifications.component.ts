import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/core/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { CVUserService } from 'src/app/services/cv-user.service';

@Component({
  selector: 'app-my-notifications',
  templateUrl: './my-notifications.component.html',
  styleUrls: ['./my-notifications.component.css']
})
export class MyNotificationsComponent implements OnInit {


  public user: User| undefined;
  public userId !: number;
  public subscriptions: Subscription[] = [];
  public isLoadingGeneral = false;

  public loading = false;

  
  public data : any = [];
  public pageSize: number = 10;
  public current: number = 1;
  public total: number = 0;
  public totalElementByPage = 0;
  public isLoadingTable = false;
 
  listNotifications: any = [];

  change(): void {
    this.loading = true;
    if (this.data.length > 0) {
      setTimeout(() => {
        this.data = [];
        this.loading = false;
      }, 1000);
    } else {
      setTimeout(() => {
        this.data = [
          {
            title: 'Ant Design Title 1'
          },
          {
            title: 'Ant Design Title 2'
          },
          {
            title: 'Ant Design Title 3'
          },
          {
            title: 'Ant Design Title 4'
          }
        ];
        this.loading = false;
      }, 1000);
    }
  }

  
 searchForm!: FormGroup;


 
  constructor(
    private cvService : CVUserService,
    private authenticationService: AuthService,
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router,
    private ngxSpinner: NgxSpinnerService 
  ) {
    this.searchForm = this.fb.group({
      keyword: [null, [Validators.required]],
    });
   }

  ngOnInit(): void {
   
      
    if (this.authenticationService.isUserLoggedIn()) {
      this.user = this.authenticationService.getUserFromLocalCache();
      this.userId = this.user.id;
    } else {
      this.router.navigateByUrl("/auth/login");
    }

  }


   
  getNotifications() {
    this.isLoadingGeneral = true;
    this.cvService.getNotificationsByUser(this.userId).subscribe(
      (response: any) => {
       
        this.listNotifications = response.map((prop: any, key: any) => {
          return {
            ...prop,
            key: key + 1,
          };
        }); 
        this.isLoadingGeneral = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar los estados');
        this.isLoadingGeneral = false;
      }
    )
  }


 submitForm(): void {
  console.log('submit', this.searchForm.value);
 }



}
