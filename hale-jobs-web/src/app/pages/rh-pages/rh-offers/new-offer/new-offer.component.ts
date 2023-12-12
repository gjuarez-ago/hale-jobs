import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-new-offer',
  templateUrl: './new-offer.component.html',
  styleUrls: ['./new-offer.component.css']
})
export class NewOfferComponent implements OnInit {

    // * Variables para agregar un usuario  
    public createForm!: FormGroup;
    public visibleCreateDrawer = false;
    public isLoadingCreateDrawer = false;

    public currentStep = 0;
    public index = '';

    public addFormBeneficts! : FormGroup;
    public addFormActivities! : FormGroup;
    public addFormHabilities! : FormGroup;


    public listBeneficts : any = [];
    public listActivities : any = [];
    public listHabilities : any = [];

    public ofertaForm : any;

  constructor(
    private authenticationService: AuthService,
    private fb: FormBuilder,
    private modal: NzModalService,
    private message: NzMessageService,
    private router: Router,
    private ngxSpinner: NgxSpinnerService

  ) { 

    this.createForm = this.fb.group({
      title: [null, [Validators.required]],
      category: [null, [Validators.required]],
      company: [null, [Validators.required]],
      urgency: [null, [Validators.required]],
      typePayment: [null, [Validators.required]],
      typeJob: [null, [Validators.required]],
      salary: [null, [Validators.required]],
      levelStudy: [null, [Validators.required]],
      workPlace: [null, [Validators.required]],
      numberPostulates: [null, [Validators.required]],
      description: [null, [Validators.required]],
      state: [null],
      city: [null],
    });

    this.addFormBeneficts = this.fb.group({
      value: [null, [Validators.required]],
    });

    this.addFormActivities = this.fb.group({
      value: [null, [Validators.required]],
    });
    
    this.addFormHabilities = this.fb.group({
      value: [null, [Validators.required]],
    });
    
  }

  ngOnInit(): void {

    this.ngxSpinner.show();

    setTimeout(() => {
      this.ngxSpinner.hide();
    }, 2000);
    
  }


  
  preStep(): void {
    this.currentStep -= 1;
  }

  nextStep(): void {
    this.changeContent();
  }

  doneSteps(): void {
    console.log('done');
  }

  
  changeContent(): void {

  
    switch (this.currentStep) {
      case 0: {

        for (const i in this.createForm.controls) {
          if (this.createForm.controls.hasOwnProperty(i)) {
            this.createForm.controls[i].markAsDirty();
            this.createForm.controls[i].updateValueAndValidity();
          }
        }
      
        if (!this.createForm.valid) {
          this.createMessage('warning', 'Es necesario llenar todos los campos!');
          return;
        }
      
        this.currentStep += 1;
        this.ofertaForm = this.createForm.value;  

        break;
      }
      case 1: {

        if(this.listActivities.length == 0) {
          this.createMessage('warning', 'Es necesario agregar una actividad!');
          return;
        }   

        if(this.listBeneficts.length == 0) {
          this.createMessage('warning', 'Es necesario agregar una beneficio!');
          return;
        }   

        if(this.listHabilities.length == 0) {
          this.createMessage('warning', 'Es necesario agregar una habilidad!');
          return;
        }   

        this.currentStep += 1;

        break;
      }
      case 2: {
        this.currentStep += 1;

        break;
      }
      default: {
        this.index = 'error';
      }
    }
  }


  submitForm(): void {

  }

  submitActivities() {
    for (const i in this.addFormActivities.controls) {
      if (this.addFormActivities.controls.hasOwnProperty(i)) {
        this.addFormActivities.controls[i].markAsDirty();
        this.addFormActivities.controls[i].updateValueAndValidity();
      }
    }
  
    if (!this.addFormActivities.valid) {
      return;
    }

    let form  = this.addFormActivities.value;  

    this.listActivities.push(form.value);
    this.addFormActivities.reset();




  }


  addHabilities() {
    for (const i in this.addFormHabilities.controls) {
      if (this.addFormHabilities.controls.hasOwnProperty(i)) {
        this.addFormHabilities.controls[i].markAsDirty();
        this.addFormHabilities.controls[i].updateValueAndValidity();
      }
    }
  
    if (!this.addFormHabilities.valid) {
      return;
    }

    let form  = this.addFormHabilities.value;  
    this.listHabilities.push(form.value);
    this.addFormHabilities.reset();


  }

  addBeneficts() {
    for (const i in this.addFormBeneficts.controls) {
      if (this.addFormBeneficts.controls.hasOwnProperty(i)) {
        this.addFormBeneficts.controls[i].markAsDirty();
        this.addFormBeneficts.controls[i].updateValueAndValidity();
      }
    }
  
    if (!this.addFormBeneficts.valid) {
      return;
    }

    let form  = this.addFormBeneficts.value;  
    this.listBeneficts.push(form.value);
    this.addFormBeneficts.reset();


  }




  createMessage(type: string, message: string): void {
    this.message.create(type, message);
  }

}
