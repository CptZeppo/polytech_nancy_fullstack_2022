import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { UtilService } from './util.service';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  private readonly baseURL = 'http://localhost:8080'

  constructor(
    private readonly http: HttpClient,
    private readonly util: UtilService,
    ){ 
    
  }

  getTheater(): Observable<any> {
    return this.http.get<any>(`${this.baseURL}/theaters/`)
    .pipe( catchError((error) => this.util.handleError(error)));
  }

  search(theaterHref: any, selectedDate: any, path: String): Observable<any> {
    return this.http.post<any>(`${path}`, {
      theaterURI: theaterHref,
      startTime: selectedDate
    })
    .pipe( catchError((error) => this.util.handleError(error)));
  }

  get(path: String): Observable<any> {
    return this.http.get<any>(`${path}`)
    .pipe( catchError((error) => this.util.handleError(error)));
  }

  post(path: any): Observable<any> {
    return this.http.post<any>(`${path}`, null)
    .pipe( catchError((error) => this.util.handleError(error)));
  }
}
