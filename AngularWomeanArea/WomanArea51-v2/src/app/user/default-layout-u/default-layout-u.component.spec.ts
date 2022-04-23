import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultLayoutUComponent } from './default-layout-u.component';

describe('DefaultLayoutUComponent', () => {
  let component: DefaultLayoutUComponent;
  let fixture: ComponentFixture<DefaultLayoutUComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DefaultLayoutUComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DefaultLayoutUComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
