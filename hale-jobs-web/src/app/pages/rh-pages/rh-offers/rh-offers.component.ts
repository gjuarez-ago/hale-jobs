import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalRef, NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/core/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { GenericService } from 'src/app/services/generic.service';
import { OfferService } from 'src/app/services/offer.service';

@Component({
  selector: 'app-rh-offers',
  templateUrl: './rh-offers.component.html',
  styleUrls: ['./rh-offers.component.css']
})
export class RhOffersComponent implements OnInit {


  confirmDeleteModal?: NzModalRef; // For testing by now

  public selectedValue : any;

  // * Variables de la tabla
  public pageSize: number = 5;
  public current: number = 1;
  public subscriptions: Subscription[] = [];
  public total: number = 0;
  public totalElementByPage = 0;
  public data: any[] = [];
    
  public user: User| undefined;
  public userId : any;

  //  public data : Content[] = [];
  //  public temp : Content[] = [];
  public isLoadingTable = false;

  // * Variables para visualizar la orden

  public visibleModal = false;
  public isLoadingModal = false;

  // * Variables genericas  
  public isLoadingGeneral = false;
  public dateFormat = 'yyyy/MM/dd';
  public statusOffer = "0";

  public listSubcategory : any = [];
  public listLevelStudy : any = [];
  public listRangeAmount : any = [];
  public listTypeOfJob : any = [];

  // * Variables para editar un usuario
  @ViewChild('e') editNgForm: NgForm | undefined;
  public editForm!: FormGroup;
  public visibleEditDrawer = false;
  public isLoadingEditDrawer = false;
  public currentUsername: string | undefined = ''

  // * Variables para realizar el filtrado
  public validateForm!: FormGroup;
  offer: any;

  constructor(
    private genericService : GenericService,
    private authenticationService: AuthService,
    private offerService : OfferService,
    private fb: FormBuilder,
    private modal: NzModalService,
    private message: NzMessageService,
    private router: Router,
    private ngxSpinner: NgxSpinnerService  
  ) {

    this.isLoadingGeneral = false;

    this.validateForm = this.fb.group({
      title: [""],
      subcategory: [""],
      urgency: [""],
      levelStudy: [""],
      workPlace: [""],
      status: [0],
      typeOfJob: [""],
      rangeAmount: [""]
    });


  }

  ngOnInit(): void {
    if (this.authenticationService.isUserLoggedIn()) {
      this.user = this.authenticationService.getUserFromLocalCache();
      this.userId = this.user.id;
      this.getOffers();
      this.getLevelStudy();
      this.getRangeAmount();
      this.getTypeOfJob();
      this.getSubcategories();
    } else {
      this.router.navigateByUrl("/auth/login");
    }


    this.loader();
   
  }


  public loader() {
    this.ngxSpinner.show();

    setTimeout(() => {
      this.ngxSpinner.hide();
    }, 800);
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

  this.isLoadingTable = true;
  this.isLoadingGeneral = true;
  this.subscriptions.push(
    this.offerService
      .getAllOffersByUserWEB({
        pageNo: this.current - 1,
        pageSize: this.pageSize,
        user: this.userId,
        subcategory: form.subcategory ? form.subcategory : '',
        title:  form.title ? form.title : '',
        status:  form.status ,
        urgency:  form.urgency ? form.urgency : '',
        workPlace:  form.workPlace ? form.workPlace : '',
        levelStudy:  form.levelStudy ? form.levelStudy : '',
        typeOfJob: form.typeOfJob ? form.typeOfJob : '',
        rangeAmount: form.rangeAmount ? form.rangeAmount : '',
      })
      .subscribe(
        (response: any) => {

          console.log(response);
          

          this.data = response;
          this.isLoadingGeneral = false;
          this.isLoadingTable = false;
          this.loader();
       
        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoadingTable = false;
          this.isLoadingGeneral = false;
          this.message.create('error', errorResponse.error.message);
          this.ngxSpinner.hide();
        }
      )
  );
    

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

  openCreateDrawer() { }

  openEditDrawer() { this.visibleEditDrawer = true; }
  closeEditDrawer() { this.visibleEditDrawer = false;}


  openViewModal() { this.visibleModal = true; }
  closeViewModal() {this.visibleModal = false; }

  showDeleteModal(consecutive: any): void {
    this.confirmDeleteModal = this.modal.confirm({
      nzTitle: '¿Seguro que deseas eliminar esta oferta?',
      nzContent: 'When clicked the OK button, this dialog will be closed after 1 second',
      nzOnOk: () =>
        new Promise((resolve, reject) => {
          setTimeout(Math.random() > 0.5 ? resolve : reject, 1000);
        }).catch(() => console.log('Oops errors!'))
    });
}

public getSubcategories() {
  this.isLoadingGeneral = true;
  this.subscriptions.push(
    this.genericService
      .getAllSubcategoriesByCategory()
      .subscribe(
        (response: any) => {
          this.listSubcategory = response;
          this.isLoadingGeneral = false;
        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoadingGeneral = false;
          this.message.create('error', errorResponse.error.message);
        }
      )
  );
}

public getRangeAmount() {
  this.isLoadingGeneral = true;
  this.subscriptions.push(
    this.genericService
      .getAllRangeAmount()
      .subscribe(
        (response: any) => {
          this.listRangeAmount = response;
          this.isLoadingGeneral = false;
        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoadingGeneral = false;
          this.message.create('error', errorResponse.error.message);
        }
      )
  );
}

public getLevelStudy() {
  this.isLoadingGeneral = true;
  this.subscriptions.push(
    this.genericService
      .getAllTypeOfLevelStudy()
      .subscribe(
        (response: any) => {
          this.listLevelStudy = response;
          this.isLoadingGeneral = false;
        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoadingGeneral = false;
          this.message.create('error', errorResponse.error.message);
        }
      )
  );
}

public getTypeOfJob() {
  this.isLoadingGeneral = true;
  this.subscriptions.push(
    this.genericService
      .getAllTypeOfJobs()
      .subscribe(
        (response: any) => {
          this.listTypeOfJob = response;
          this.isLoadingGeneral = false;
        },
        (errorResponse: HttpErrorResponse) => {
          this.isLoadingGeneral = false;
          this.message.create('error', errorResponse.error.message);
        }
      )
  );
}

public getOfferById(id : any) {
  this.isLoadingGeneral = true;
  this.ngxSpinner.show();
  this.subscriptions.push(
    this.offerService.findOfferById(id).subscribe(
      (response: any) => {
        this.offer = response;
        this.isLoadingGeneral = false;
        this.ngxSpinner.hide();  
      },
      (errorResponse: HttpErrorResponse) => {
        this.isLoadingGeneral = false;
        this.ngxSpinner.hide();
        this.message.create('error', errorResponse.error.message);
      }
    )
  );
}
  

getOffers() {
  this.isLoadingTable = true;
  this.isLoadingGeneral = true;
  this.subscriptions.push(
    this.offerService
      .getAllOffersByUserWEB({
        pageNo: this.current - 1,
        pageSize: this.pageSize,
        user: this.userId,
        subcategory: this.validateForm.value["subcategory"] ? this.validateForm.value["subcategory"] : '',
        title:  this.validateForm.value["title"] ? this.validateForm.value["title"] : '',
        status:  this.validateForm.value["status"],
        urgency:  this.validateForm.value["urgency"] ?  this.validateForm.value["urgency"] : '',
        workPlace:  this.validateForm.value["workPlace"] ? this.validateForm.value["workPlace"] : '',
        levelStudy:  this.validateForm.value["levelStudy"] ?  this.validateForm.value["levelStudy"] : '',
        typeOfJob: this.validateForm.value["typeOfJob"] ? this.validateForm.value["typeOfJob"] : '',
        rangeAmount: this.validateForm.value["rangeAmount"] ? this.validateForm.value["rangeAmount"] : '',
      })
      .subscribe(
        (response: any) => {
          this.data = response;
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