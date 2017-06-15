import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header.component';
import { NavComponent } from './nav.component';
import { FooterComponent } from './footer.component';

@NgModule({
  imports: [BrowserModule],
  declarations: [AppComponent, HeaderComponent, NavComponent, FooterComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
