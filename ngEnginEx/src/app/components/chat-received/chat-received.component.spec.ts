import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatReceivedComponent } from './chat-received.component';

describe('ChatReceivedComponent', () => {
  let component: ChatReceivedComponent;
  let fixture: ComponentFixture<ChatReceivedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChatReceivedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatReceivedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
