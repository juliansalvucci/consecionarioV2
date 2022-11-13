import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GananciaycantidadpormarcaComponent } from './gananciaycantidadpormarca.component';

describe('GananciaycantidadpormarcaComponent', () => {
  let component: GananciaycantidadpormarcaComponent;
  let fixture: ComponentFixture<GananciaycantidadpormarcaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GananciaycantidadpormarcaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GananciaycantidadpormarcaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
