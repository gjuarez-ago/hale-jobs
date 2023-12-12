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
  selector: 'app-my-postulations',
  templateUrl: './my-postulations.component.html',
  styleUrls: ['./my-postulations.component.css']
})
export class MyPostulationsComponent implements OnInit {


  public user: User| undefined;
  public userId !: number;
  public subscriptions: Subscription[] = [];
  public isLoadingGeneral = false;

  public data : any = [];
  public pageSize: number = 10;
  public current: number = 1;
  public total: number = 0;
  public totalElementByPage = 0;
  public isLoadingTable = false;

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

 public searchForm!: FormGroup;
 public listPostulations : any; 

  submitForm(): void {
    console.log('submit', this.searchForm.value);
  }

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

   
  getPostulations() {
    this.isLoadingGeneral = true;
    this.cvService.getPostulationsByUser({user: this.userId, pageSize: this.pageSize, pageNumber : this.current}).subscribe(
      (response: any) => {
       console.log(response);
        this.listPostulations = response.map((prop: any, key: any) => {
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


  removePostulation() {
    this.isLoadingGeneral = true;
    this.cvService.deletePostulationsByUser(1).subscribe(
      (response: any) => {
       console.log(response);
        this.message.create("success", 'Ya no formas parte de este proceso!');
        this.isLoadingGeneral = false;       
      },
      (errorResponse: HttpErrorResponse) => {
        this.message.create("error", 'Ha ocurrido un error al recuperar los estados');
        this.isLoadingGeneral = false;
      }
    )
  }

}


interface Person {
  key: string;
  name: string;
  age: number;
  address: string;
}