import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';

@Component({
  selector: 'app-new-company',
  templateUrl: './new-company.component.html',
  styleUrls: ['./new-company.component.css']
})
export class NewCompanyComponent implements OnInit {

  @ViewChild('f') myForm: NgForm | undefined;
  public createForm!: FormGroup;
  public visibleCreateDrawer = false;
  public isLoadingCreateDrawer = false;

  public selectedValue = null;
  
  constructor() { }

  ngOnInit(): void {
  }


  submitForm() {
    
  }

}
