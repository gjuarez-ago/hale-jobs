import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
}) 
export class WorkersService {

  private url = environment.apiUrl;

  constructor(private http: HttpClient) { }

}