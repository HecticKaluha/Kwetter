import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnkweetsComponent } from './ownkweets.component';

describe('OwnkweetsComponent', () => {
  let component: OwnkweetsComponent;
  let fixture: ComponentFixture<OwnkweetsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnkweetsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnkweetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
