import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs/internal/Subscription';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  
  public validateForm!: FormGroup;
  public subcriptions : Subscription[] = [];
  public sub: Subscription = new Subscription;
  public isSpinning = false;

  
  constructor( private actRoute: ActivatedRoute, private fb: FormBuilder, private router: Router, private message: NzMessageService) { }

  ngOnInit(): void {
    
    // if(this.authenticationService.isUserLoggedIn()) {
    //   this.router.navigateByUrl("/dashboard/principal");
    // }

    this.validateForm = this.fb.group({
      email: [this.actRoute.snapshot.params.email],
      token: [this.actRoute.snapshot.params.token],
      password: [null, [Validators.required]],
      newPassword: [null, [Validators.required]],
    });  
  }

  submitForm(): void {

    for (const i in this.validateForm.controls) {
      if (this.validateForm.controls.hasOwnProperty(i)) {
        this.validateForm.controls[i].markAsDirty();
        this.validateForm.controls[i].updateValueAndValidity();
      }
    }

    if(!this.validateForm.valid) {
      this.createMessage("warning", "Es necesario llenar todos los campos!");
      return ; 
    }

    if(this.validateForm.value["password"] != this.validateForm.value["newPassword"]) {
      this.createMessage("warning", "Las contraseñas no coinciden");
      return ; 
    }
    

    this.isSpinning = true;
    let form = this.validateForm.value;
    

    // this.subcriptions.push(
    //   this.authenticationService.resetPassword(form).subscribe(
    //     (response: BaseResponse) => {
    //       this.isSpinning = false;
    //       this.createMessage("success", this.mes.messageText(response.message));
    //       this.router.navigateByUrl('/auth/login');
    //     },
    //     (errorResponse: HttpErrorResponse) => {
    //       this.isSpinning = false;
    //       this.createMessage("error",  this.mes.messageText(errorResponse.error.message));
    //     }
    //   )
    // );
      
  }

    createMessage(type: string, message: string): void {
      this.message.create(type, message);
    }

}
