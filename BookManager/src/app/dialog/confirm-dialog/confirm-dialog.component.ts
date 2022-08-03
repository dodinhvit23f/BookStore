
import { Component, OnInit, OnDestroy, ViewEncapsulation, Output, EventEmitter, Inject } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA , MatDialogRef} from '@angular/material/dialog';


@Component({
    selector: 'app-confirm-dialog',
    templateUrl: './confirm-dialog.component.html',
    styleUrls: ['./confirm-dialog.component.css'],
    encapsulation: ViewEncapsulation.None,
})
export class ConfirmDialog implements OnInit, OnDestroy {

    public answer: boolean = false;
    public title;
    public dialog: MatDialog;
   

    constructor( public dialogRef: MatDialogRef<ConfirmDialog>, @Inject(MAT_DIALOG_DATA) public data: any) {
        this.title = data.title
        this.dialog = data.dialog
    }

    ngOnInit(): void {

    }
    ngOnDestroy(): void {

    }

    Close() {
        this.dialogRef.close(this.answer)
        this.dialog.closeAll()
    }

    Confirm() {
        this.answer = true
        this.Close();
    }
}