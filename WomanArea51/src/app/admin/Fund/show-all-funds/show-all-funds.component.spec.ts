import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllFundsComponent } from './show-all-funds.component';

describe('ShowAllFundsComponent', () => {
  let component: ShowAllFundsComponent;
  let fixture: ComponentFixture<ShowAllFundsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowAllFundsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllFundsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
