import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-rh-company',
  templateUrl: './rh-company.component.html',
  styleUrls: ['./rh-company.component.css']
})
export class RhCompanyComponent implements OnInit {

  switchValue = false;
  
  constructor() { }

  ngOnInit(): void {
  }

}
