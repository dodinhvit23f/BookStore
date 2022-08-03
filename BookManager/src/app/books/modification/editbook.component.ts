import { Component, OnInit, OnDestroy, ViewEncapsulation } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { FormPagination } from 'src/app/service/FormPagination';
import { Editor } from 'ngx-editor';
import { Book } from 'src/app/model/Book';
import { BookResponse } from 'src/app/model/response/BookResponse';
import { FileService } from 'src/app/service/FileService';
import { ConfirmDialog } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';

import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Response } from 'src/app/model/Response';

@Component({
  selector: 'app-edit-book',
  templateUrl: '../addition/add-book.component.html',
  styleUrls: ['../addition/add-book.component.css'],
  encapsulation: ViewEncapsulation.None,
})

export class EditBookComponent extends FormPagination implements OnInit, OnDestroy {

  book!: Book;
  Title = "Modification Boook"
  private routeSub!: Subscription;

  constructor(private http: HttpClient,
    public dialog: MatDialog,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar) {
    super();
  }

  BookUrl = "http://localhost:8080/api/admin/books"
  BookImageUrl = "http://localhost:8080/api/images"

  filesDisplay: any[] = [];
  fileSent: File[] = [];
  fileDeleted: String[] = [];
  html: string = ""
  imageLimmit = 4;
  bookId!: number

  form = new FormGroup({
    authorName: new FormControl('', Validators.required),
    bookName: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
    editorContent: new FormControl('', Validators.required),
    bookImage: new FormControl()
  });

  ngOnInit(): void {
    this.editor = new Editor();
    this.showTab();

    this.routeSub = this.route.params.subscribe(params => {

      this.bookId = params['id']

      this.http.get<BookResponse>(`${this.BookUrl}/id/${this.bookId}`).subscribe({
        next: (response) => {

          this.book = response.book
          this.html = this.book!.description
          this.form.controls['authorName'].setValue(this.book.authorName)
          this.form.controls['bookName'].setValue(this.book.bookName)
          this.form.controls['price'].setValue(this.book.price)

          if (this.book.bookImage != null) {
            this.filesDisplay = this.book.bookImage
              .map(imageName => `${this.BookImageUrl}/${imageName}`)
          }
        },
        error: (error) => {
          this.snackBar.open(error.message, "", {
            duration: this.durationInSeconds * 1000,
          });
        },
      });
    })
  }


  ngOnDestroy(): void {
    this.editor.destroy();
  }

  onImageChangeFromFile(event: any) {
    if (this.filesDisplay.length >= this.imageLimmit) {
      console.log(this.filesDisplay.length)
      return
    }

    let imageLeft = Math.abs(this.imageLimmit - this.filesDisplay.length)

    console.log(imageLeft)

    for (let index = 0; index < imageLeft; index++) {

      if (FileService.checkImageFile(event.target.files[index])) {


        let reader = new FileReader();
        if (event.target.files && event.target.files.length) {
          let file = event.target.files[index];

          reader.readAsDataURL(file);
          reader.onload = () => {
            this.filesDisplay.push(reader.result);
            this.fileSent.push(event.target.files[index])
          }
        }
      }

    }
  }

  submit(): void {

    let postData = new FormData()
    postData.append("bookId", this.bookId.toString());
    postData.append("authorName", this.form.controls['authorName'].value);

    this.fileSent.forEach(element => {
      postData.append("imageFiles", element, element.name)
    });

    postData.append("bookName", this.form.controls['bookName'].value);
    postData.append("description", this.html?.toString());
    postData.append("price", this.form.controls['price'].value)

    this.fileDeleted.forEach(element => {
      let imagePath = element.toString().split("/")
      let imageName = imagePath[imagePath.length - 1]
      postData.append("deletedImageFiles", imageName)
    });


    var options = {
      'method': 'POST',
      'hostname': 'localhost',
      'port': 8080,
      'path': '/api/admin/books',
      'headers': {
      },
      'maxRedirects': 20
    };


    this.http.put<Response>(this.BookUrl, postData, options).subscribe({
      next: (response) => {
        this.snackBar.open(response.message, "", {
          duration: this.durationInSeconds * 1000,
        })
      },
      error: (error) => {
        this.snackBar.open(error.message, "", {
          duration: this.durationInSeconds * 1000,
        })
      },
    });
  }

  async deleteImage(index: any) {
    if (index !== -1) {


      if (index !== -1) {

        const dialogRef = this.dialog.open(ConfirmDialog, {
          data: {
            title: "Cảnh báo",
            dialog: this.dialog,
          }
        })

        await dialogRef.beforeClosed().subscribe(result => {
          if (result) {
            if (this.book.bookImage.indexOf(this.filesDisplay[index])) {
              this.fileDeleted.push(this.filesDisplay[index])
            }

            this.filesDisplay.splice(index, 1);
            this.fileSent.splice(index, 1);
          }
        })
      }
    }
  }

}


