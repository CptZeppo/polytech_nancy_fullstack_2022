import { NgModule, LOCALE_ID } from '@angular/core';
import { CommonModule, registerLocaleData } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar'; 
import {MatIconModule} from '@angular/material/icon';
import { ToolbarComponent } from './shared/toolbar/toolbar.component'; 
import {MatCardModule} from '@angular/material/card'; 
import {MatFormFieldModule} from '@angular/material/form-field'; 
import {MatButtonModule} from '@angular/material/button'; 
import {MatSelectModule} from '@angular/material/select'; 
import {MatDatepickerModule} from '@angular/material/datepicker';
import { BillComponent } from './bill/bill.component';
import { SearchComponent } from './search/search.component'; 
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import {MatListModule} from '@angular/material/list'; 
import {MatGridListModule} from '@angular/material/grid-list';
import { ShowComponent } from './shared/show/show.component';
import localeFr from '@angular/common/locales/fr';
registerLocaleData(localeFr);

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    BillComponent,
    SearchComponent,
    HomeComponent,
    ShowComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatButtonModule,
    MatSelectModule,
    MatDatepickerModule,
    MatListModule,
    MatGridListModule,
  ],
  providers: [
    {
      provide: LOCALE_ID,
      useValue: 'fr-FR'
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
