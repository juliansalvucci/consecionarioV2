import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaisListaComponent } from './pais-lista.component';

describe('PaisListaComponent', () => {
  let component: PaisListaComponent;
  let fixture: ComponentFixture<PaisListaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaisListaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PaisListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
