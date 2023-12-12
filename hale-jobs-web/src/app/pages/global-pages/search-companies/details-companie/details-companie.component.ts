import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-details-companie',
  templateUrl: './details-companie.component.html',
  styleUrls: ['./details-companie.component.scss']
})
export class DetailsCompanieComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  data = [
    'Racing car sprays burning fuel into crowd.',
    'Japanese princess to wed commoner.',
    'Australian walks 100km after outback crash.',
    'Man charged over missing wedding girl.',
    'Los Angeles battles huge wildfires.'
  ];
}
