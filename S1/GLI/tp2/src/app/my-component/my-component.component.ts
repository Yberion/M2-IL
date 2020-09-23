import { PokeShareInfoService } from './../poke-share-info.service';
import { PokeAPIService } from './../poke-apiservice.service';
import { Pokemon, PokemonDetail } from './../pokemon';
import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-my-component',
    templateUrl: './my-component.component.html',
    styleUrls: ['./my-component.component.css'],
    providers: [PokeAPIService],
})
export class MyComponentComponent implements OnInit {
    id: string;
    selectedPokeId: string;
    searchPokeName = '';
    pokeDetail: PokemonDetail;
    pokes: Pokemon[] = [];
    myDate: Date;
    checked = true;

    constructor(
        private pokeService: PokeAPIService,
        private pokeShareInfoService: PokeShareInfoService
    ) {}

    ngOnInit(): void {
        this.pokeService.getPokemons().subscribe((data) => {
            data.results.forEach((element, index) => {
                this.pokes.push(
                    new Pokemon('' + (index + 1), element.name, element.url)
                );
            });
        });
    }

    go(): void {
        if (this.selectedPokeId !== '') {
            this.pokeService
                .getPokemonInfo(this.selectedPokeId)
                .subscribe((data) => {
                    this.pokeDetail = data;
                    this.pokeShareInfoService.setValue(this.selectedPokeId);
                });
        }
    }
}
