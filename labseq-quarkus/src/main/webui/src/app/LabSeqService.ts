import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LabSeqService {
  private readonly API_URL = 'http://localhost:8080/api/v1/labseq';

  constructor(private http: HttpClient) {}

  postIndexValue(index: string): Observable<string> {
    return this.http.get<string>(`${this.API_URL}/${index}`);
  }
}
