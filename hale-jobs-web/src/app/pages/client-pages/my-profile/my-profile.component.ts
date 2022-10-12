import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  switchValue = false;

  
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

  public visible = false;
  public visibleChangePassword = false;



  constructor() { }

  ngOnInit(): void {
  }


  // Change password

  
  openChangePassword(): void {
    this.visibleChangePassword = true;
  }

  closeChangePassword(): void {
    this.visibleChangePassword = false;
  }



  open(): void {
    this.visible = true;
  }

  close(): void {
    this.visible = false;
  }

}


interface Person {
  key: string;
  name: string;
  age: number;
  address: string;
}