import{Component}from'@angular/core';
import {HttpClient}from '@angular/common/http';
import {HttpParams}from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Calculator';
  dailyRate;
  country = 'PL';
  countries;
  data;
  error;
  errorMessage;

  constructor(private http: HttpClient) {
    this.http.get('countries').subscribe(data => this.countries = data);
  }

  onCompute() {
    if(typeof this.dailyRate !== 'undefined' && this.dailyRate > 0 && typeof this.country !== 'undefined'){
      const params = new HttpParams().set('country', this.country).set('dailyRate', this.dailyRate);
      this.http.get('rate', { params: params }).subscribe(
          data => {this.data = data; this.error = null; this.errorMessage=null;},
          error => {this.error = error; this.errorMessage = error.error.message; console.error('An error occurred:', error.error.message);});
    }else{
        this.errorMessage = 'Provide correct parameters.';
    }
  }
}
