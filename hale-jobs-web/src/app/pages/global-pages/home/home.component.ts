import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthService } from 'src/app/services/auth.service';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  radioValue = 'A';
  selectedValue = null;
  searchForm! : FormGroup;
  
  images = [
    {path: 'http://www.carmen.gob.mx/_boletines/efecto%20boletines/images/584_grande.jpeg'},
    {path: 'https://images.pexels.com/photos/12383337/pexels-photo-12383337.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'},

];


  constructor(
    private authenticationService: AuthService,
    private fb: FormBuilder,
    private modal: NzModalService,
    private message: NzMessageService,
    private router: Router,
    private ngxSpinner: NgxSpinnerService,
    private searchService : SearchService,

  ) { 

    
    this.searchForm = this.fb.group({
      value: [null, [Validators.required]],
    });


  }

  ngOnInit(): void {
  }

  public submitForm() {

   
  
    if (!this.searchForm.valid) {
      for (const i in this.searchForm.controls) {
        if (this.searchForm.controls.hasOwnProperty(i)) {
          this.searchForm.controls[i].markAsDirty();
          this.searchForm.controls[i].updateValueAndValidity();
        }
      }
      return;
    }

    let form = this.searchForm.value;


    this.searchService.search({
      title: form.value,
      subcategory: null,
      urgency: null,
      category: 1,
      typeOfJob: null,
      rangeAmount: null,
      state: '',
    });

    this.router.navigate(['/search']);




  }

}




interface Option {
  label: string;
  value: string;
  age: number;
}