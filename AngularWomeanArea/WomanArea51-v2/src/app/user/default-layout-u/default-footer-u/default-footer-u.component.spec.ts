import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultFooterUComponent } from './default-footer-u.component';

describe('DefaultFooterUComponent', () => {
  let component: DefaultFooterUComponent;
  let fixture: ComponentFixture<DefaultFooterUComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DefaultFooterUComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DefaultFooterUComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
