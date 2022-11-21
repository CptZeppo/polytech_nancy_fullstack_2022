import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../service/data.service';
import { RestService } from '../service/rest.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private readonly rest: RestService,
    private readonly dataService: DataService,
    private readonly router: Router,
    ) { }

  selectedTheater: any;
  selectedDate: any;
  theaters: any;
  searchHref: any;

  ngOnInit(): void {
    this.rest.getTheater().subscribe(
    (data) => {
      console.log(data);
      this.theaters = data._embedded.theaters;
      this.searchHref = data._links.search.href;
    }
   );
  }

  search() {
    console.log(this.searchHref);
    console.log(this.selectedTheater);
    this.selectedDate = new Date();
    
    const theaterHref = this.selectedTheater._links.self.href;
    console.log(theaterHref);
    this.rest.search(theaterHref, this.selectedDate, this.searchHref).subscribe(
      (result) => {
        console.log(result);
        this.dataService.saveSearch(result);
        this.router.navigate(['/search']);
      }
    );

    
  }

}