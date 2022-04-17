import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditFundCatComponent } from './edit-fund-cat.component';

describe('EditFundCatComponent', () => {
  let component: EditFundCatComponent;
  let fixture: ComponentFixture<EditFundCatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditFundCatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditFundCatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
