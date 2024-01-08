import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {

    public itemList : any = []
    public wishesList = new BehaviorSubject<any>([]);
 
    public itemListHistory : any = []
    public wishesListHistory = new BehaviorSubject<any>([]);
  
    constructor() { }
  
    getProducts() { 
      return this.wishesList.asObservable();
    }
  
    setProduct(lst : any){
      this.itemList = [];
      this.itemList.push(...lst);
      this.wishesList.next(this.itemList);
    }
    
    addtoFavorites(product : any){
      this.itemList = this.updateFavorites(product);
      this.wishesList.next(this.itemList);
      localStorage.setItem('MyProducts_History', JSON.stringify(this.wishesList.value));
    }
  
    updateFavorites(product : any){

      // En caso de que exista un producto con las mismas caracteristicas este se elimina
      this.itemList.map((a:any, index:any)=>{
        // console.log(product);
        // console.log(a);
        if(product.id == a.id){
          this.itemList.splice(index,1);
        }
      });
  
      // Se inserta el nuevo independientemente si existe o no
      this.itemList.push(product);  
      return this.itemList;
    }
  
     removeItem(product: any){   
      this.itemList.map((a:any, index:any)=>{
        if(product.id == a.id){
          this.itemList.splice(index,1);
        }
      });
  
      this.wishesList.next(this.itemList);    
      localStorage.setItem('MyProducts_History', JSON.stringify(this.wishesList.value));
    }
  
    removeAllItems(){
      this.itemList = []
      this.wishesList.next(this.itemList);
    }

    // Lógica para las busquedas historicas

    



}