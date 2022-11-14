import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GananciapormodeloymarcaComponent } from './gananciapormodeloymarca.component';

describe('GananciapormodeloymarcaComponent', () => {
  let component: GananciapormodeloymarcaComponent;
  let fixture: ComponentFixture<GananciapormodeloymarcaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GananciapormodeloymarcaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GananciapormodeloymarcaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
