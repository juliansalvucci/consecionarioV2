import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RangolistaComponent } from './rangolista.component';

describe('RangolistaComponent', () => {
  let component: RangolistaComponent;
  let fixture: ComponentFixture<RangolistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RangolistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RangolistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
