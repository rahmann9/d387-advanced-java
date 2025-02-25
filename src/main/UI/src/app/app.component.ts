import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, forkJoin } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private httpClient: HttpClient) {}

  private baseURL: string = 'http://localhost:8080';
  private getUrl: string = `${this.baseURL}/room/reservation/v1/`;
  private postUrl: string = `${this.baseURL}/room/reservation/v1`;

  roomsearch!: FormGroup;
  rooms!: Room[];
  request!: ReserveRoomRequest;
  currentCheckInVal!: string;
  currentCheckOutVal!: string;
  welcomeMessages: string[] = [];
  timeMessage: string = "";

  ngOnInit() {
    this.fetchWelcomeMessages();
    this.displayPresentationTime();
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl('')
    });

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;
    roomsearchValueChanges$.subscribe(x => {
      this.currentCheckInVal = x.checkin;
      this.currentCheckOutVal = x.checkout;
    });
  }

  onSubmit(form: FormGroup) {
    if (form.valid) {
      this.getAll().subscribe(rooms => {
        this.rooms = <Room[]>Object.values(rooms)[0];
      });
    } else {
      console.log('Form is not valid');
    }
  }

  reserveRoom(roomId: string) {
    this.request = new ReserveRoomRequest(roomId, this.currentCheckInVal, this.currentCheckOutVal);
    this.createReservation(this.request);
  }

  createReservation(body: ReserveRoomRequest) {
    const bodyString = JSON.stringify(body);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    const options = {
      headers: headers,
    };

    this.httpClient.post(this.postUrl, bodyString, options)
      .subscribe(res => console.log(res));
  }

  fetchWelcomeMessages() {
    forkJoin([
      this.httpClient.get(`${this.baseURL}/welcome?lang=en_US`, { responseType: 'text' }),
      this.httpClient.get(`${this.baseURL}/welcome?lang=fr_CA`, { responseType: 'text' })
    ]).subscribe({
      next: ([englishMessage, frenchMessage]) => {
        this.welcomeMessages = [`English: ${englishMessage}`, `French: ${frenchMessage}`];
      },
      error: (error) => {
        console.error('Error fetching welcome messages:', error);
        this.welcomeMessages = ['Error fetching welcome messages.'];
      }
    });
  }

  displayPresentationTime() {
    const currentTime = new Date();
    const timeZones = [
      { zone: 'America/New_York', label: 'ET' },
      { zone: 'America/Denver', label: 'MT' },
      { zone: 'UTC', label: 'UTC' }
    ];

    const times = timeZones.map(tz => {
      return this.formatTimeForZone(currentTime, tz.zone, tz.label);
    });

    this.timeMessage = `Live Presentation at Landon Hotel:\n${times.join(' | ')}`;
  }

  formatTimeForZone(date: Date, zone: string, label: string): string {
    const options: Intl.DateTimeFormatOptions = { hour: '2-digit', minute: '2-digit', timeZoneName: 'short' };
    const formattedTime = new Intl.DateTimeFormat('en-US', { ...options, timeZone: zone }).format(date);
    return `${label}: ${formattedTime}`;
  }

  getAll(): Observable<any> {
    return this.httpClient.get(this.baseURL + '/room/reservation/v1', {
      params: {
        checkin: this.currentCheckInVal,
        checkout: this.currentCheckOutVal
      },
      responseType: 'json'
    });
  }
}

export interface Roomsearch {
  checkin: string;
  checkout: string;
}

export interface Room {
  id: string;
  roomNumber: string;
  price: string;
  links: string;
}

export class ReserveRoomRequest {
  roomId: string;
  checkin: string;
  checkout: string;

  constructor(roomId: string, checkin: string, checkout: string) {
    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}
