import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../service/data.service';
import { RestService } from '../service/rest.service';
import { UtilService } from '../service/util.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

shows: any[];
selectedShows: any[];
bookingLink: any;

  constructor(
    private readonly dataService: DataService,
    private readonly rest: RestService,
    private readonly util: UtilService,
    ) { 
      this.shows = [];
      this.selectedShows = [];
    }

  ngOnInit(): void {

    const search = this.dataService.getSearch();
    if (!search) {
      this.util.backHome();
    }
    const _links = search._links;


    
    if (_links?.all_shows) {
      this.rest.get(_links.all_shows.href).subscribe(
        (data) => {
          console.log(data);
          this.shows = <any[]>data._embedded.shows;
          console.log(this.shows);
        }
      )
    }


    if (_links?.selection) {
      this.rest.get(_links.selection.href).subscribe(
        (data) => {
          console.log(data);
          this.selectedShows = <any[]>data.selectedShows;
          console.log(this.selectedShows);
        }
      )
    }

  }

  select(show: any) {
    this.rest.post(show._links.select.href).subscribe(
      (data) => {
        console.log(data);
        this.selectedShows = <any[]>data.selectedShows;
        console.log(this.selectedShows);
        const _links = data._links;
        this.bookingLink = _links?.create_booking.href;
      }
    )
    }

    booking(bookingLink: any) {
      this.rest.post(bookingLink).subscribe(
        (data) => {
          this.dataService.saveBooking(data);
          this.util.toBill();
        }
      );

      }

}
