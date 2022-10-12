import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReviewsByBusinessService {

  private host = environment.apiUrl;

  constructor(private http: HttpClient) { }
}
