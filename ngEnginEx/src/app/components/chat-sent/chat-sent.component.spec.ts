import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatSentComponent } from './chat-sent.component';

describe('ChatSentComponent', () => {
  let component: ChatSentComponent;
  let fixture: ComponentFixture<ChatSentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChatSentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatSentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
