import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hero } from './hero';
import { HeroService } from './hero.service';

@Component({
  selector: 'my-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css'],
  providers: [HeroService]
})

export class HeroesComponent implements OnInit {
  title = 'Tour of hero';
  heroes: Hero[];
  selectHero: Hero;

  constructor(
    private heroService: HeroService,
    private router: Router
  ) { }

  onSelect(hero: Hero): void {
    this.selectHero = hero;
  }

  getHeroes(): void {
    this.heroService.getHeroes().then(heroes => this.heroes = heroes);
  }

  gotoDetail(): void {
    this.router.navigate(['/detail', this.selectHero.id]);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) {
      return;
    }
    this.heroService.create(name).then(hero => { this.heroes.push(hero); this.selectHero = null; });
  }

  ngOnInit(): void {
    this.getHeroes();
  }
}



