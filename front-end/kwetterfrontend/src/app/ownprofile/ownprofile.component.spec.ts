import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnprofileComponent } from './ownprofile.component';

describe('OwnprofileComponent', () => {
  let component: OwnprofileComponent;
  let fixture: ComponentFixture<OwnprofileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OwnprofileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OwnprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
