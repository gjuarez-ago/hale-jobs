import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-job',
  templateUrl: './view-job.component.html',
  styleUrls: ['./view-job.component.css']
})
export class ViewJobComponent implements OnInit {

  onBack(): void {
    console.log('onBack');
  }
  
  constructor() { }

  ngOnInit(): void {
  }

}
