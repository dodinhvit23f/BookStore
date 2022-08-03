import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Book } from 'src/app/model/Book';
import { BookResponse } from 'src/app/model/response/BookResponse';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {

  BookUrl = "http://localhost:8080/api/admin/books"
  BookImageUrl = "http://localhost:8080/api/images"
  book!:Book
  selectedImage! : string
  isCollpase = false
  collpaseText:string = "Xem Thêm"


  constructor(private http: HttpClient,
    private route: ActivatedRoute) {

    route.params.subscribe(params => {
      this.http.get<BookResponse>(`${this.BookUrl}/id/${params['id']}`).subscribe({
        next: (response) => {
          this.book = response.book

          if (this.book.bookImage != null) {
            this.book.bookImage = this.book.bookImage
              .map(imageName => `${this.BookImageUrl}/${imageName}`)
            
            this.selectedImage = this.book.bookImage[0]
          }
        },
        error: (error) => console.log(error),
      });
    })
  }

  collapse(id:string){
    this.isCollpase = !this.isCollpase
    let content = document.getElementById(id) as HTMLElement
    console.log(content)
    if(!this.isCollpase){
      this.collpaseText = "Xem Thêm"
    
      content.style.height = '300px';
      return
    }

    this.collpaseText = "Rút Gọn"
    content.style.height = '100vh';
  }

  swapImage(imageUrl:string){
    this.selectedImage = imageUrl
  }

  ngOnInit(): void {

  }

}
