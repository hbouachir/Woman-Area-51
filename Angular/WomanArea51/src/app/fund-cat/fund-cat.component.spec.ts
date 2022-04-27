import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FundCatComponent } from './fund-cat.component';

describe('FundCatComponent', () => {
  let component: FundCatComponent;
  let fixture: ComponentFixture<FundCatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FundCatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FundCatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
