import{Component}from'@angular/core';
import {HttpClient}from '@angular/common/http';
import {HttpParams}from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Demo';
  dailyRate;
  country = 'PL';
  countries =[
      { name: 'Poland', symbol: 'PL' },
      { name: 'Germany', symbol: 'DE' },
      { name: 'United Kingdom', symbol: 'UK' }
      ];
  data = {};

  constructor(private http: HttpClient) { }

  onCompute(){
    let params = new HttpParams().set('dailyRate', this.dailyRate).set('country',this.country);
    this.http.get('rate', { params: params }).subscribe(data => this.data = data);
  }
}
