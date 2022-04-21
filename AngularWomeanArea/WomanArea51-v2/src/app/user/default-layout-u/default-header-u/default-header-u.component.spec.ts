import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultHeaderUComponent } from './default-header-u.component';

describe('DefaultHeaderUComponent', () => {
  let component: DefaultHeaderUComponent;
  let fixture: ComponentFixture<DefaultHeaderUComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DefaultHeaderUComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DefaultHeaderUComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
