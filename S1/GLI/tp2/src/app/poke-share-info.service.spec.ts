import { TestBed } from '@angular/core/testing';

import { PokeShareInfoService } from './poke-share-info.service';

describe('PokeShareInfoService', () => {
  let service: PokeShareInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PokeShareInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
