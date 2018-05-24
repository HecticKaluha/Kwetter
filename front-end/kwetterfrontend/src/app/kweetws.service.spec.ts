import { TestBed, inject } from '@angular/core/testing';

import { KweetwsService } from './kweetws.service';

describe('KweetwsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [KweetwsService]
    });
  });

  it('should be created', inject([KweetwsService], (service: KweetwsService) => {
    expect(service).toBeTruthy();
  }));
});
