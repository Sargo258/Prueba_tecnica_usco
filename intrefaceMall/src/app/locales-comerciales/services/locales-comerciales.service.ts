import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, map, of, tap } from 'rxjs';
import { environment } from 'src/enviroments/enviroment';

@Injectable({
  providedIn: 'root',
})
export class LocalesComercialesService {
  private shops: any[] = [];
  private shopsSubject: BehaviorSubject<any[]> = new BehaviorSubject<any[]>([]);

  private _baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) {}

  private getAuthHeader(): HttpHeaders {
    const token = localStorage.getItem('token');

    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }
  getShops(): Observable<any[]> {
    return this.shopsSubject.asObservable();
  }

  loadShops(): void {
    const headers = this.getAuthHeader();

    this.http.get<any[]>(`${this._baseUrl}/shop`, { headers }).subscribe(
      (data) => {
        this.shops = data;
        this.shopsSubject.next(this.shops);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  clearShops(): void {
    this.shops = [];
    this.shopsSubject.next(this.shops);
  }

  addShop(shop: any): Observable<any> {
    const headers = this.getAuthHeader();

    return this.http.post<any>(`${this._baseUrl}/shop`, shop, { headers }).pipe(
      tap((data: any) => {
        this.shops.push(data);
        this.shopsSubject.next(this.shops);
      })
    );
  }

  deleteShop(id: any): Observable<any> {
    const headers = this.getAuthHeader();

    return this.http
      .delete<any>(`${this._baseUrl}/shop/${id}`, { headers })
      .pipe(
        tap(() => {
          const index = this.shops.findIndex((shop) => shop.id === id);
          if (index !== -1) {
            this.shops.splice(index, 1);
            this.shopsSubject.next(this.shops);
          }
        })
      );
  }

  updateShop(id: any, data: any): Observable<any> {
    const headers = this.getAuthHeader();

    return this.http
      .put<any>(`${this._baseUrl}/shop/${id}`, data, { headers })
      .pipe(
        tap((data) => {
          const index = this.shops.findIndex((shop) => shop.id === id);
          if (index !== -1) {
            this.shops[index] = data;
            this.shopsSubject.next(this.shops);
          }
        })
      );
  }
}
