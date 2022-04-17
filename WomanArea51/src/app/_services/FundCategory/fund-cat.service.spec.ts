import { TestBed } from '@angular/core/testing';

import { FundCatService } from './fund-cat.service';

describe('FundCatService', () => {
  let service: FundCatService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FundCatService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
