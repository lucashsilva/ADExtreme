import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectCardComponent } from './connect-card.component';

describe('ConnectCardComponent', () => {
  let component: ConnectCardComponent;
  let fixture: ComponentFixture<ConnectCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConnectCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConnectCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
