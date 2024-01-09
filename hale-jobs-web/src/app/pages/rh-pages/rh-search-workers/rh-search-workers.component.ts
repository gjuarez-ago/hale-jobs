import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { Subscription } from 'rxjs';
import { User } from 'src/app/models/core/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { GenericService } from 'src/app/services/generic.service';
import { WorkersService } from 'src/app/services/workers.service';

@Component({
  selector: 'app-rh-search-workers',
  templateUrl: './rh-search-workers.component.html',
  styleUrls: ['./rh-search-workers.component.css'],
})
export class RhSearchWorkersComponent implements OnInit {

  public user: User| undefined;
  public userId : any;

  
  // * Variables de la tabla
  public data: any[] = [];
  public pageSize: number = 10 ;
  public current: number = 1;
  public subscriptions: Subscription[] = [];
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
  public listModwork: any[] = [];
  public listStates: any[] = [];
  public listRangeAmount: any[] = [];
  public listCities: any[] = [];
  citySelected: any;
  stateSelected : any;

  constructor(
    private authenticationService: AuthService,
    private fb: FormBuilder,
    private modal: NzModalService,
    private message: NzMessageService,
    private router: Router,
    private ngxSpinner: NgxSpinnerService,
    private userService: WorkersService,
    private genericService : GenericService,
    private readonly title: Title,   

  ) {
    this.validateForm = this.fb.group({
      jobTitle: [''],
      state: [null],
      city: [null],
      findJob: ['si'],
      salary: [null],
      mod: [null],
    });
  }

  ngOnInit(): void {
    if (this.authenticationService.isUserLoggedIn()) {
      this.user = this.authenticationService.getUserFromLocalCache();
      this.userId = this.user.id;

      this.getStates();
      this.getRangeAmount();
      this.getModWorks();
      this.getUsersPaginate();
      this.title.setTitle("Busqueda de personal")


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

  public clenFilters() {
    this.validateForm = this.fb.group({
      jobTitle: [''],
      state: [null],
      city: [null],
      findJob: ['si'],
      salary: [null],
      mod: [null],
    });
  }

  public getUsersPaginate() {
    this.isLoadingTable = true;
    this.isLoadingGeneral = true;
    this.subscriptions.push(
      this.userService
        .getAllOffersByUserWEB({
          pageNo: this.current - 1,
          pageSize: this.pageSize,
          city: this.validateForm.value['city']
            ? this.validateForm.value['city']
            : '',
          jobTitle: this.validateForm.value['jobTitle']
            ? this.validateForm.value['jobTitle']
            : '',
          mod: this.validateForm.value['mod']
            ? this.validateForm.value['mod']
            : '',
          salary: this.validateForm.value['salary']
            ? this.validateForm.value['salary']
            : '',
            findJob: this.validateForm.value['findJob']
            ? this.validateForm.value['findJob']
            : '',
          state: this.validateForm.value['state']
            ? this.validateForm.value['state']
            : '',
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
    if (!this.validateForm.valid) {
      for (const i in this.validateForm.controls) {
        if (this.validateForm.controls.hasOwnProperty(i)) {
          this.validateForm.controls[i].markAsDirty();
          this.validateForm.controls[i].updateValueAndValidity();
        }
      }

      this.createMessage('warning', 'Es necesario llenar todos los campos!');
      return;
    }

    this.ngxSpinner.show();
    let form = this.validateForm.value;

    this.isLoadingTable = true;
    this.isLoadingGeneral = true;
    this.subscriptions.push(
      this.userService
        .getAllOffersByUserWEB({
          pageNo: this.current - 1,
          pageSize: this.pageSize,
          city: form.city,
          jobTitle: form.jobTitle,
          mod: form.mod,
          salary: form.salary,
          findJob: form.findJob,
          state: form.city,
        })
        .subscribe(
          (response: any) => {
            this.data = response.content;
            this.total = response.totalElements;
            this.totalElementByPage = response.numberOfElements;
            this.isLoadingTable = false;
            this.isLoadingGeneral = false;
            this.ngxSpinner.hide();
          },
          (errorResponse: HttpErrorResponse) => {
            this.isLoadingTable = false;
            this.isLoadingGeneral = false;
            this.ngxSpinner.hide();
            this.message.create('error', errorResponse.error.message);
          }
        )
    );
  }

  public navigateViewJob(element : any) {    
    const url = this.router.serializeUrl(
      this.router.createUrlTree([`/dashboard/view-worker/${element.username}`]));
       window.open('#' + url, '_blank');        
  }

  listOfData: Person[] = [
    {
      key: '1',
      name: 'John Brown',
      age: 32,
      address: 'New York No. 1 Lake Park',
    },
    {
      key: '2',
      name: 'Jim Green',
      age: 42,
      address: 'London No. 1 Lake Park',
    },
    {
      key: '3',
      name: 'Joe Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
    },
  ];

  generateExcel() {}

  openCreateDrawer() {}

  createMessage(type: string, message: string): void {
    this.message.create(type, message);
  }


  provinceChange(value:any): void {
    if(value) {
      this.citySelected = undefined;
      this.getCities(value);
    }else {
      this.listCities = [];
      this.citySelected = undefined;
    }
  }


  // Llenar catalogos

  getModWorks() {
    this.isLoadingGeneral = true;
    this.genericService.getAllTypeOfJobs().subscribe(
      (response: any) => {
      
        this.listModwork = response.map((prop: any, key: any) => {
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

  
  getStates() {
    this.isLoadingGeneral = true;
    this.genericService.getAllStates().subscribe(
      (response: any) => {
       
        this.listStates = response.map((prop: any, key: any) => {
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


  getCities(p : any) {
    this.isLoadingGeneral = true;
    this.genericService.getAllCities(p).subscribe(
      (response: any) => {
      
        this.listCities = response.map((prop: any, key: any) => {
          return {
            ...prop,
            key: key + 1,
          };
        });        
        this.isLoadingGeneral = false;
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar los municipios');
        this.isLoadingGeneral = false;
      }
    )
  }

  public getImage(e : any) {

    if(e == null) {
      return "https://uxwing.com/wp-content/themes/uxwing/download/peoples-avatars/no-profile-picture-icon.png";
    }

    return e;
  }


}

interface Person {
  key: string;
  name: string;
  age: number;
  address: string;
}
