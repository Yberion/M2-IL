import { PokeServiceRes, PokemonDetail } from './pokemon';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class PokeAPIService {
    url = 'https://pokeapi.co/api/v2/pokemon/';

    constructor(private http: HttpClient) {}

    getPokemons(): Observable<PokeServiceRes> {
        return this.http.get<PokeServiceRes>(this.url);
    }

    getPokemonInfo(id: string): Observable<PokemonDetail> {
        return this.http.get<PokemonDetail>(this.url + id + '/');
    }
}
