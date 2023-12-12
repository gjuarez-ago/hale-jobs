import { AfterContentChecked, AfterViewInit, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Meta, Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-search-companies',
  templateUrl: './search-companies.component.html',
  styleUrls: ['./search-companies.component.css']
})
export class SearchCompaniesComponent implements OnInit  {

  validateForm: FormGroup = new FormGroup({})
  companies: string[] = ['','','','','','','','','','']

  
  constructor(
    private readonly meta: Meta,
    private readonly title: Title,
    private authenticationService: AuthService,
    private fb: FormBuilder,
    private modal: NzModalService,
    private message: NzMessageService,
    private router: Router,
    private ngxSpinner: NgxSpinnerService 
    ) {

  }

  ngOnInit(): void {

    this.title.setTitle('Búsqueda por empresa')
    this.meta.addTags(
      [
        { name: 'description', content: '' }
      ]
    )
  }

  submitForm() {
    console.log(this.validateForm.value);

  }

  private createForm(): void {
    this.validateForm = new FormBuilder().group({
      name: '',
      category: '',
      letter: '',
      best: ''
    })
  }


}
