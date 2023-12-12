import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/core/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-rh-profile',
  templateUrl: './rh-profile.component.html',
  styleUrls: ['./rh-profile.component.css']
})
export class RhProfileComponent implements OnInit {

  switchValue = false;

  public user: User| undefined;
  public subscriptions : Subscription[] = [];


  switchValue1 = false;
  switchValue2 = false;
  switchValue3 = false;

  validateForm!: FormGroup;

  submitForm(): void {
    if (this.validateForm.valid) {
      console.log('submit', this.validateForm.value);
    } else {
      Object.values(this.validateForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
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

  public visible = false;
  public visibleChangePassword = false;

  constructor(
    private authenticationService: AuthService,
    private fb: FormBuilder,
    private modal: NzModalService,
    private message: NzMessageService,
    private router: Router,
    private ngxSpinner: NgxSpinnerService  
  ) { }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }

  public onLogOut(): void {
    this.authenticationService.logOut();
    this.router.navigate(['auth/login']);
    this.createMessage("success", "Has cerrado sesión exitosamente 😀");
  }

  createMessage(type: string, message: string): void {
    this.message.create(type, message);
  }

  // Change password


  public changePassword() {
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

  public desactivateProfile() {
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

  public updateProfile() {
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


  

}


interface Person {
  key: string;
  name: string;
  age: number;
  address: string;
}