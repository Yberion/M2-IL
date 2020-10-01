import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PokedetailComponent } from './pokedetail.component';

describe('PokedetailComponent', () => {
  let component: PokedetailComponent;
  let fixture: ComponentFixture<PokedetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PokedetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PokedetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
