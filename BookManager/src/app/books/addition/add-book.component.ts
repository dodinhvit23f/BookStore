import { Component, OnInit, OnDestroy, ViewEncapsulation, AfterContentInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FileService } from 'src/app/service/FileService';
import { HttpClient } from '@angular/common/http';
import { FormPagination } from 'src/app/service/FormPagination';
import { Editor } from 'ngx-editor';
import { ConfirmDialog } from 'src/app/dialog/confirm-dialog/confirm-dialog.component';
import { MatDialog } from "@angular/material/dialog"
import { Response } from 'src/app/model/Response';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css'],
  encapsulation: ViewEncapsulation.None,
})


export class AddBookComponent extends FormPagination implements OnInit, OnDestroy {
  Bookurl = "http://localhost:8080/api/admin/books"
  filesDisplay: any[] = [];
  fileSent: File[] = [];
  imageLimmit = 4;
  html: string = ""
  Title = "New Book"
  
  form = new FormGroup({
    authorName: new FormControl('', Validators.required),
    bookName: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
    editorContent: new FormControl('', Validators.required),
    bookImage: new FormControl()
  });


  constructor(private http: HttpClient,
    public dialog: MatDialog,
    private snackBar: MatSnackBar) {
    super();
  }

  ngOnInit(): void {
    this.editor = new Editor();
    this.showTab();
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

    var postData = new FormData()

    postData.append("authorName", this.form.controls['authorName'].value);

    this.fileSent.forEach(element => {
      postData.append("imageFiles", element, element.name)
    });

    postData.append("bookName", this.form.controls['bookName'].value);
    postData.append("description", this.html?.toString());
    postData.append("price", this.form.controls['price'].value)

    var options = {
      'method': 'POST',
      'hostname': 'localhost',
      'port': 8080,
      'path': '/api/admin/books',
      'headers': {
      },
      'maxRedirects': 20
    };


    this.http.post<Response>(this.Bookurl, postData, options).subscribe({
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

      const dialogRef = this.dialog.open(ConfirmDialog, {
        data: {
          title: "Cảnh báo",
          dialog: this.dialog,
        }
      })

      await dialogRef.beforeClosed().subscribe(result => {
        if (result) {
          this.filesDisplay.splice(index, 1);
          this.fileSent.splice(index, 1);
        }
      })
    }
  }

}
