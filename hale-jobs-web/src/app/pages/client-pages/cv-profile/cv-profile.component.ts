import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-cv-profile',
  templateUrl: './cv-profile.component.html',
  styleUrls: ['./cv-profile.component.css']
})
export class CvProfileComponent implements OnInit {

  validateForm!: FormGroup;
  selectedValue = null;



  constructor(private fb: FormBuilder, 
    private ngxSpinner :  NgxSpinnerService) { }

  ngOnInit(): void {

    // this.ngxSpinner.show();
    
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });

  }


  submitForm(): void {
    console.log('submit', this.validateForm.value);
  }

}
