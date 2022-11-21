import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from './service/data.service';
import { RestService } from './service/rest.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(
    private rest: RestService,
    private dataService: DataService,
    private router: Router,
    ) { }

  selectedTheater: any;
  selectedDate: any;
  theaters: any;

  ngOnInit(): void {
    this.rest.getTheater().subscribe(
    (data) => {
      console.log(data);
      this.theaters = data._embedded.theaters;
    }
   );
  }

  search() {
    this.selectedDate = new Date();
    this.dataService.saveSearch({theater: this.selectedTheater, date: this.selectedDate});
    this.router.navigate(['/search']);
  }

}
