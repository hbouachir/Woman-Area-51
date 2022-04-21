import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSelectedCatComponent } from './update-selected-cat.component';

describe('UpdateSelectedCatComponent', () => {
  let component: UpdateSelectedCatComponent;
  let fixture: ComponentFixture<UpdateSelectedCatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateSelectedCatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateSelectedCatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
