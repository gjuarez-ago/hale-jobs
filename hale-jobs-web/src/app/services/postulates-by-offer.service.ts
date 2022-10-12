import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostulatesByOfferService {

  private host = environment.apiUrl;

  constructor(private http: HttpClient) { }
}
