import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {


  constructor(
    private readonly http: HttpClient, 
    private readonly router: Router
    ) {}


word = '';
infos = '';



hello() {
  this.http.get<any>('http://localhost:8080/hello', {observe: 'response'})
  .subscribe(resp => {
    console.log(resp);
    this.word = resp.body.data;
  });
}


info() {
  this.http.get<any>('http://localhost:8080/infos', {observe: 'response'})
  .subscribe({
    next: (resp) => {
    console.log(resp);
    console.log(resp.headers.keys());
    const nbToken =  resp.headers.get('X-Rate-Limit-Remaining')
    this.infos = `${nbToken} tokens restant`
  },
  error:  (err) => {
    console.error(err);
    console.log(err.headers.keys());
   const temps =  err.headers.get('x-rate-limit-retry-after-seconds')
    this.infos = `Ressayer après ${temps} secondes`;
    this.router.navigate(['/waiting', temps]);
  }
});

}
}
