import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  radioValue = 'A';


  selectedValue = null;


  
  
  images = [
    {path: 'http://www.carmen.gob.mx/_boletines/efecto%20boletines/images/584_grande.jpeg'},
    {path: 'https://images.pexels.com/photos/12383337/pexels-photo-12383337.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'},

];


  constructor() { }

  ngOnInit(): void {
  }


}




interface Option {
  label: string;
  value: string;
  age: number;
}