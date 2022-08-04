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
    selector: 'edit-user',
    templateUrl: './modification-user.component.html',
    styleUrls: ['./modification-user.component.css'],
    encapsulation: ViewEncapsulation.None,
  })


export class ModificationUserComponent implements OnInit, OnDestroy {
  
  
  form = new FormGroup({
    userName: new FormControl('', Validators.required),
    userEmail: new FormControl('', Validators.required),
    userPassword: new FormControl('', Validators.required),
    userPasswordRe: new FormControl('', Validators.required),
  });


  constructor(private http: HttpClient,
    private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }


  ngOnDestroy(): void {
  }

  submit(){

  }

}
