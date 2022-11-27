import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

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
  let temps: any;
  this.http.get<any>('http://localhost:8080/infos', {observe: 'response'})
  .subscribe({
    next: (resp) => {
    console.log(resp);
    const keys = resp.headers.keys();
    console.log(keys);
    const nbToken =  resp.headers.get('X-Rate-Limit-Remaining')
    this.infos = `${nbToken} tokens restant`
  },
  error:  (err) => {
    console.error(err);
    const keys = err.headers.keys();
    console.log(keys);
    temps =  err.headers.get('x-rate-limit-retry-after-seconds')
    this.infos = `Ressayer après ${temps} secondes`;
    this.router.navigate(['/waiting', temps]);
  }
});

}
}
