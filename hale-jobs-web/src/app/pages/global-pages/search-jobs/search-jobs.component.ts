import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-search-jobs',
  templateUrl: './search-jobs.component.html',
  styleUrls: ['./search-jobs.component.css']
})
export class SearchJobsComponent implements OnInit {

  selectedValue = null;

  
  constructor(

    private spinner: NgxSpinnerService,
  ) { }

  ngOnInit(): void {
    // this.spinner.show();
  }

}
