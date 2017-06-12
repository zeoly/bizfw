import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `
  <h1>{{title}}</h1>
  <h2>{{hero.name}} details!</h2>
  <div><label>id:{{hero.id}}</label></div>
  <div>
    <label>name:</label>
    <input [(ngModel)]="hero.name" placeholder="please input"/>
  </div>`,
})
export class AppComponent {
  title = 'Tour of hero';
  hero: Hero = {
    id: 1,
    name: 'zengyongli'
  }
}

export class Hero {
  id: number;
  name: string;
}