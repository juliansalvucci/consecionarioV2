import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GananciaporempleadoComponent } from './gananciaporempleado.component';

describe('GananciaporempleadoComponent', () => {
  let component: GananciaporempleadoComponent;
  let fixture: ComponentFixture<GananciaporempleadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GananciaporempleadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GananciaporempleadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
