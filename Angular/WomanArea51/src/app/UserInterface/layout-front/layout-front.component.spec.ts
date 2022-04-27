import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LayoutFrontComponent } from './layout-front.component';

describe('LayoutFrontComponent', () => {
  let component: LayoutFrontComponent;
  let fixture: ComponentFixture<LayoutFrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LayoutFrontComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LayoutFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
