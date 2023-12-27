import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { PaginationCompany } from '../models/pagination-company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  public host = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public createCompany(form: FormData): Observable<any> {
    return this.http.post<any>(`${this.host}/company/create`, form);
  }

  public updateCompany(id: any, userId:any, form: FormData): Observable<any> {
    return this.http.put<any>(`${this.host}/company/update/${id}/${userId}`, form);
  }

  public getCompaniesByOwner(pagination : PaginationCompany): Observable<any> {

    const params = new HttpParams({
      fromObject: {
        pageNo: pagination.pageNo,
        pageSize: pagination.pageSize,
        ownerId: pagination.ownerId,
        category: pagination.category,
        rfc: pagination.rfc,
        name: pagination.name
      }
    });
  
    return this.http.get<any>(`${this.host}/company/get-companies-by-owner`, {params: params});
  }

  public getCompaniesByOwnerWP(user : any): Observable<any> {
    return this.http.get<any>(`${this.host}/company/get-companies-by-user/${user}`);
  }

  public getCompanyById(id: any): Observable<any> {
    return this.http.get<any>(`${this.host}/company/find/${id}`);
  }

  public deleteCompanyById(id: any): Observable<any> {
    return this.http.delete<any>(`${this.host}/company/delete/${id}`);
  }

  public getCompaniesGeneral(params : any): Observable<any> {
    return this.http.get<any>(`${this.host}/company/get-companies`);
  }


}