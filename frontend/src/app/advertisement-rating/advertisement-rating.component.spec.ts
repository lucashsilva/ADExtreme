import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisementRatingComponent } from './advertisement-rating.component';

describe('AdvertisementRatingComponent', () => {
  let component: AdvertisementRatingComponent;
  let fixture: ComponentFixture<AdvertisementRatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvertisementRatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvertisementRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
