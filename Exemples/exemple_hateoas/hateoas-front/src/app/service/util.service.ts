import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtilService {



  constructor(
    private readonly router: Router,
    ) {}

  handleError(error: any): Observable<any> {
    console.error(error);
   // this.router.navigate(['/error']);
   return throwError(() => new Error(error));
  }

  backHome() {
    this.router.navigate(['/home']);
  }

  toSearch() {
    this.router.navigate(['/search']);
  }

  toBill() {
    this.router.navigate(['/bill']);
  }
}
