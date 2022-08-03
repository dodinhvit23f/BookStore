import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from "@angular/material/dialog"
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { ConfirmDialog } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';

import { Book } from 'src/app/model/Book';
import { BookPagnationResponse } from 'src/app/model/response/BookPagnationResponse';

@Component({
  selector: 'app-booksDisplay',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayBookComponent implements OnInit {

  BookUrl = "http://localhost:8080/api/admin/books"

  public books: Book[] = [];
  public currentPage!: number;
  public totalPage!: number;
  public sortCodition: String = "id";

  constructor(private http: HttpClient,
    private dialog: MatDialog,
    private route: ActivatedRoute) {

    route.params.subscribe(params => {
      let page = params['page']
      
      if (page != null) {

        let url = `${this.BookUrl}?currentPage=${page}&sortBy=${this.sortCodition}`
        this.sendRequest(url)
        return 
      }
      this.sendRequest(this.BookUrl)
    })

   
  }

  sendRequest(url: string) {
    this.http.get<BookPagnationResponse>(url).subscribe({
      next: (response) => {
        this.books = response.books
        this.currentPage = response.currentPage
        this.totalPage = response.totalPage
      },
      error: (error) => console.log(error),
    });
  }

  ngOnInit(): void {

  }

  async TurnOff(index: any, event: any) {
    const dialogRef = this.dialog.open(ConfirmDialog, {
      data: {
        title: "Cảnh báo",
        dialog: this.dialog,
      }
    })
    const isDelete = event.checked
    console.log(isDelete)

    await dialogRef.beforeClosed().subscribe(result => {

      if (result) {
        let book = this.books[index];
        book.isDelete = true
        event.checked = !isDelete

        this.http.delete<BookPagnationResponse>(`${this.BookUrl}/id/${book.id}`).subscribe({
          next: (response) => console.log(response),
          error: (error) => console.log(error),
        });
        return
      }
      event.checked = isDelete
    })
  }

  Filter(selector: any) {
    this.sortCodition = selector.value
    this.currentPage = 1

    let url = `${this.BookUrl}?currentPage=${this.currentPage}&sortBy=${this.sortCodition}`
    this.sendRequest(url)
  }

  getPage(page:number){
    let url = `${this.BookUrl}?currentPage=${page}&sortBy=${this.sortCodition}`
    this.sendRequest(url)
  }
}
