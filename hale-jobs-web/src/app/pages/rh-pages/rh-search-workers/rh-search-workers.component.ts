import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { WorkersService } from 'src/app/services/workers.service';

@Component({
  selector: 'app-rh-search-workers',
  templateUrl: './rh-search-workers.component.html',
  styleUrls: ['./rh-search-workers.component.css']
})
export class RhSearchWorkersComponent implements OnInit {

   // * Variables de la tabla
   public data : any[] = [];
   public pageSize: number = 10;
   public current: number = 1;
   public subscriptions : Subscription[] = [];
   public total: number = 0;
   public totalElementByPage = 0;
 
  //  public data : Content[] = [];
  //  public temp : Content[] = [];
   public isLoadingTable = false;
 
   // * Variables para visualizar la orden
 
   public visibleDrawer = false;
   public isLoadingDrawer = false;
  //  public viewElement :  Loan | undefined = undefined;
 
 
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
    private ngxSpinner: NgxSpinnerService,
    private userService : WorkersService
  ) {

    this.validateForm = this.fb.group({
      jobTitle: [''],
      state: [null],
      city: [null],
      speciality: [null],
      salary: [null],
      workPlace: [null]
    });


   }

  ngOnInit(): void {
  }



  public getUsersPaginate() {

    this.isLoadingTable = true;
    this.isLoadingGeneral = true;
    this.subscriptions.push(
      this.userService
        .getAllOffersByUserWEB({
          pageNo: this.current - 1,
          pageSize: this.pageSize,
          city: this.validateForm.value["city"] ? this.validateForm.value["city"] : '',
          jobTitle: this.validateForm.value["jobTitle"] ? this.validateForm.value["jobTitle"] : '',
          mod:  this.validateForm.value["mod"] ? this.validateForm.value["mod"] : '',
          salary:  this.validateForm.value["salary"] ? this.validateForm.value["salary"] : '',
          speciality:  this.validateForm.value["speciality"] ?  this.validateForm.value["speciality"] : '',
          state:  this.validateForm.value["state"] ? this.validateForm.value["state"] : '',
        })
        .subscribe(
          (response: any) => {
  
            this.data = response.content;
            this.total = response.totalElements;
            this.totalElementByPage = response.numberOfElements;
  
            this.isLoadingTable = false;
            this.isLoadingGeneral = false;
          },
          (errorResponse: HttpErrorResponse) => {
            this.isLoadingTable = false;
            this.isLoadingGeneral = false;
            this.message.create('error', errorResponse.error.message);
          }
        )
    );
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


getHistory() {
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