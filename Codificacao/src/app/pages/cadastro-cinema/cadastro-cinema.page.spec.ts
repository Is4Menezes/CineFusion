import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CadastroCinemaPage } from './cadastro-cinema.page';

describe('CadastroCinemaPage', () => {
  let component: CadastroCinemaPage;
  let fixture: ComponentFixture<CadastroCinemaPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroCinemaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
