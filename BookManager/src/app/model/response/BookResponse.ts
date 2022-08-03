import { Book } from "../Book";
import {Response} from "../Response"

export interface BookResponse extends Response{
  book:Book;
}