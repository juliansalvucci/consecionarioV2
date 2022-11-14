import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VentasPorCategoriasComponent } from './ventas-por-categorias.component';

describe('VentasPorCategoriasComponent', () => {
  let component: VentasPorCategoriasComponent;
  let fixture: ComponentFixture<VentasPorCategoriasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VentasPorCategoriasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VentasPorCategoriasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
