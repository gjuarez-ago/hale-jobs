import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-offer',
  templateUrl: './view-offer.component.html',
  styleUrls: ['./view-offer.component.css']
})
export class ViewOfferComponent implements OnInit {


  array = ["https://images.pexels.com/photos/12507122/pexels-photo-12507122.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load", 
  "https://elcomercio.pe/resizer/kj6ZrzUDFt_XEWooHAALfnxLFU0=/980x0/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/XVTEFUJY7JFIBLCOKMO2FTU4HI.jpeg", 
  "https://elcomercio.pe/resizer/c2aCNpsDBranIeTi9hkAaF6f0ic=/980x0/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/QUSE77HFH5BGJPL5IYNR4RNLKQ.jpeg",
  "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSq7GTvEc73B0R28UpyWjQ17VgXEMBudNfoAgWDLu9RKg7dIuz66uepz7yzklSYHQ7f7Ug&usqp=CAU"];



  data: any[] = [];
  submitting = false;
  user = {
    author: 'Han Solo',
    avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png'
  };
  inputValue = '';

  handleSubmit(): void {
    this.submitting = true;
    const content = this.inputValue;
    this.inputValue = '';
    setTimeout(() => {
      this.submitting = false;
      this.data = [
        ...this.data,
        {
          ...this.user,
          content,
          datetime: new Date(),
        }
      ].map(e => ({
        ...e,
      }));
    }, 800);
  }


  constructor() { }

  ngOnInit(): void {
  }

}
