import { TestBed } from '@angular/core/testing';

import { PokeAPIServiceService } from './poke-apiservice.service';

describe('PokeAPIServiceService', () => {
  let service: PokeAPIServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PokeAPIServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
