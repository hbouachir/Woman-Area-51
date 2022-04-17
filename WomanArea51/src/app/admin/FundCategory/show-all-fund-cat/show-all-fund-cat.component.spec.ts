import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllFundCatComponent } from './show-all-fund-cat.component';

describe('ShowAllFundCatComponent', () => {
  let component: ShowAllFundCatComponent;
  let fixture: ComponentFixture<ShowAllFundCatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowAllFundCatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllFundCatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
