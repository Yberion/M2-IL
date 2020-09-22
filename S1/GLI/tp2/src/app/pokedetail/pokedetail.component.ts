import { PokemonDetail } from './../pokemon';
import { Component, Input, OnInit } from '@angular/core';

@Component({
    selector: 'app-pokedetail',
    templateUrl: './pokedetail.component.html',
    styleUrls: ['./pokedetail.component.css'],
})
export class PokedetailComponent implements OnInit {
    @Input()
    detail: PokemonDetail;

    constructor() {}

    ngOnInit(): void {}
}
