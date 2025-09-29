import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginCinemaPage } from './login-cinema.page';

describe('LoginCinemaPage', () => {
  let component: LoginCinemaPage;
  let fixture: ComponentFixture<LoginCinemaPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginCinemaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
