import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFundCatComponent } from './add-fund-cat.component';

describe('AddFundCatComponent', () => {
  let component: AddFundCatComponent;
  let fixture: ComponentFixture<AddFundCatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFundCatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFundCatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
