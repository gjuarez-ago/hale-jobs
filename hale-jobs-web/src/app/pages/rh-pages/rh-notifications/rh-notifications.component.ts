import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-rh-notifications',
  templateUrl: './rh-notifications.component.html',
  styleUrls: ['./rh-notifications.component.css']
})
export class RhNotificationsComponent implements OnInit {

// * Variables de la tabla
public pageSize: number = 10;
public current: number = 1;
public subscriptions : Subscription[] = [];
public total: number = 0;
public totalElementByPage = 0;

//  public data : Content[] = [];
//  public temp : Content[] = [];
public isLoadingTable = false;

// * Variables genericas  
public isLoadingGeneral = false;
public dateFormat = 'yyyy/MM/dd';

// * Variables para realizar el filtrado
public validateForm!: FormGroup; 
public loading = false;



 
constructor(
  private authenticationService: AuthService,
  private fb: FormBuilder,
  private modal: NzModalService,
  private message: NzMessageService,
  private router: Router,
  private ngxSpinner: NgxSpinnerService
) {

 this.validateForm = this.fb.group({
  title: [''],
  description: [''],
  category: [''],
 });


}

ngOnInit(): void {
}

submitForm(): void {

  for (const i in this.validateForm.controls) {
    if (this.validateForm.controls.hasOwnProperty(i)) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  }

  if (!this.validateForm.valid) {
    this.createMessage('warning', 'Es necesario llenar todos los campos!');
    return;
  }

  this.ngxSpinner.show();
  let form = this.validateForm.value;

  console.log(form);
  

}


data = [
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


listOfData: Person[] = [
 {
   key: '1',
   name: 'John Brown',
   age: 32,
   address: 'New York No. 1 Lake Park'
 },
 {
   key: '2',
   name: 'Jim Green',
   age: 42,
   address: 'London No. 1 Lake Park'
 },
 {
   key: '3',
   name: 'Joe Black',
   age: 32,
   address: 'Sidney No. 1 Lake Park'
 }
];


generateExcel() {
 
}

openCreateDrawer(){}


getNotifications() {
  this.ngxSpinner.show();
  this.subscriptions.push(
    this.authenticationService.login('').subscribe(
      (response: any) => {
        this.ngxSpinner.hide();
      },
      (errorResponse: HttpErrorResponse) => {
        this.ngxSpinner.hide();
        this.createMessage('error', errorResponse.error.message);
      }
    )
  );
}

createMessage(type: string, message: string): void {
  this.message.create(type, message);
}

}


interface Person {
key: string;
name: string;
age: number;
address: string;
}