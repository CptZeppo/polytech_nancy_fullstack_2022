import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {


  private search: any;
  private booking: any;

  constructor() { }

  saveSearch(result: any) {
    this.search = result;
  }
  getSearch(): any {
    return this.search;
  }

  saveBooking(result: any) {
    this.booking = result;
  }
  getBooking(): any {
    return this.booking;
  }
}
