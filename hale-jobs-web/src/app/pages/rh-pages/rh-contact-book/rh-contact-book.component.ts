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
  selector: 'app-rh-contact-book',
  templateUrl: './rh-contact-book.component.html',
  styleUrls: ['./rh-contact-book.component.css']
})
export class RhContactBookComponent implements OnInit {

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



 
constructor(
  private authenticationService: AuthService,
  private fb: FormBuilder,
  private modal: NzModalService,
  private message: NzMessageService,
  private router: Router,
  private ngxSpinner: NgxSpinnerService
) {

 this.validateForm = this.fb.group({
   loanId: [''],
   username: [''],
   surnames: [''],
 });


}

ngOnInit(): void {
}

submitForm(): void {

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



getContactBooks() {
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