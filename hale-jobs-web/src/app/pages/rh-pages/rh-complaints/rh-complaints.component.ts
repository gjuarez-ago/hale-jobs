import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-rh-complaints',
  templateUrl: './rh-complaints.component.html',
  styleUrls: ['./rh-complaints.component.css']
})
export class RhComplaintsComponent implements OnInit {

 
  public subscriptions: Subscription[] = [];


   // * Variables de la tabla
   public pageSize: number = 10;
   public current: number = 1;
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
    private router: Router,
    private message: NzMessageService,
    private ngxSpinner :  NgxSpinnerService
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

  data = [
    {
      author: 'Han Solo',
      avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
      content:
        'We supply a series of design principles, practical patterns and high quality design resources' +
        '(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
      datetime: 2022
    },
    {
      author: 'Han Solo',
      avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
      content:
        'We supply a series of design principles, practical patterns and high quality design resources' +
        '(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
      datetime: 2022
    }
  ];


  getComplaints() {
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