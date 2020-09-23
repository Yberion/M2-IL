import { PokeShareInfoService } from './../poke-share-info.service';
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

    constructor(private pokeShareInfoService: PokeShareInfoService) {
        this.pokeShareInfoService
            .getObservable()
            .subscribe((e) => console.log('e ' + e));
    }

    ngOnInit(): void {}
}
