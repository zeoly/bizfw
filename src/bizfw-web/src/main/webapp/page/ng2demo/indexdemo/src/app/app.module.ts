import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header.component';
import { NavComponent } from './nav.component';
import { ContentComponent } from './content.component';
import { FooterComponent } from './footer.component';

import { MaterialModule } from '@angular/material';


@NgModule({
  imports: [BrowserModule, BrowserAnimationsModule, FormsModule, MaterialModule],
  declarations: [AppComponent, HeaderComponent, NavComponent, ContentComponent, FooterComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
