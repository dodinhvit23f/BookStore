import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookDetailComponent } from './books/book-detail/book-detail.component';
import { AddBookComponent } from './books/addition/add-book.component';
import { DisplayBookComponent } from './books/display/display.component';
import { EditBookComponent } from './books/modification/editbook.component';

const routes: Routes = [
  { path: "books", component: DisplayBookComponent },
  { path: "books/add", component: AddBookComponent },
  { path: "books/edit/:id", component: EditBookComponent },
  { path: "books/page/:page", component: DisplayBookComponent },
  { path: "books/detail/:id", component: BookDetailComponent },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponent = [DisplayBookComponent,
  AddBookComponent,
  EditBookComponent,
  BookDetailComponent,
]