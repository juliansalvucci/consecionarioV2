import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallePorVendedorComponent } from './detalle-por-vendedor.component';

describe('DetallePorVendedorComponent', () => {
  let component: DetallePorVendedorComponent;
  let fixture: ComponentFixture<DetallePorVendedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetallePorVendedorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetallePorVendedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
