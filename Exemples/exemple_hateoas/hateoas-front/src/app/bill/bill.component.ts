import { Component, OnInit } from '@angular/core';
import { DataService } from '../service/data.service';
import { RestService } from '../service/rest.service';
import { UtilService } from '../service/util.service';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {

  booking: any;
  shows: any[];

  constructor(
    private readonly dataService: DataService,
    private readonly util: UtilService,
  ) {
     this.shows = [];
    }

  ngOnInit(): void {

    this.booking = this.dataService.getBooking();
    if (!this.booking) {
      this.backHome();
    }

    this.shows = <any[]> this.booking.shows;
  }

  backHome(): void {
    this.util.backHome();
  }

}
