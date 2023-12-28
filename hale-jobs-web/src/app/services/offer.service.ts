import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Offer } from 'src/app/models/core/offer.model';
import { environment } from 'src/environments/environment';
import { PaginationOffer } from '../models/pagination-offer';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  public key : any = {};
  public keyword = new BehaviorSubject<any>({}); 

  private readonly url: string = `${environment.apiUrl}`

  constructor(private readonly http: HttpClient) { }

  getKeyword(){
    return this.keyword.asObservable();
  }
  
  search(k: any) {
     this.key = k;
     this.keyword.next(this.key);
  }

  public createOffer(offer: any): Observable<Offer> {
    return this.http.post<Offer>(`${this.url}/offer/create`, offer)
  }

  public deleteOffer(offerId: number, userId: string): Observable<Offer> {
    return this.http.delete<Offer>(`${this.url}/offer/delete/${offerId}/${userId}`)
  }

  public editOffer(offer: any): Observable<Offer> {
    return this.http.post<Offer>(`${this.url}/offer/edit`, offer)
  }

  public findOfferById(offerId: any): Observable<any> {
    return this.http.get<any>(`${this.url}/offer/find/${offerId}`)
  }

  public reportOffer(category: string, comments: string, offerId: string, userId: string): Observable<Offer> {
    return this.http.post<Offer>(`${this.url}/offer/report-offer?category=${category}&comments=${comments}&offerId=${offerId}&userId=${userId}`, {})
  }

  public searchOffersWEB(keyword: string, page: number = 0, pageSize: number = 10): Observable<Offer> {
    return this.http.get<Offer>(`${this.url}/offer/search-offers-w?keyword=${keyword}&page=${page}&pageSize=${pageSize}`)
  }

  public selectPostulate(amountAcepted: string, offerId: string, userId: string): Observable<Offer> {
    return this.http.post<Offer>(`${this.url}/offer/select-postulate?amountAcepted=${amountAcepted}&offerId=${offerId}&userId=${userId}`, {})
  }

  public getAllOffersByUserWEB(pagination : PaginationOffer): Observable<any> {

    const params = new HttpParams({
      fromObject: {
        pageNo: pagination.pageNo,
        pageSize: pagination.pageSize,
        user: pagination.user,
        subcategory: pagination.subcategory,
        title: pagination.title,
        status: pagination.status,
        urgency: pagination.urgency,
        workPlace: pagination.workPlace,
        levelStudy: pagination.levelStudy,
        typeJob: pagination.typeOfJob,
        rangeAmount: pagination.rangeAmount
      }
    });

    return this.http.get<any>(`${this.url}/offer/view-offer-w`,{params: params})
  }

}
