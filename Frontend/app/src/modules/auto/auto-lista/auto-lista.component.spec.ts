import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutoListaComponent } from './auto-lista.component';

describe('AutoListaComponent', () => {
  let component: AutoListaComponent;
  let fixture: ComponentFixture<AutoListaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutoListaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AutoListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
