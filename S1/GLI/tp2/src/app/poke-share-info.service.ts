import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class PokeShareInfoService {
    public value = new Subject<string>();

    constructor() {}

    getObservable(): Subject<string> {
        return this.value;
    }

    public setValue(newValue: string): void {
        this.value.next(newValue);
    }
}
