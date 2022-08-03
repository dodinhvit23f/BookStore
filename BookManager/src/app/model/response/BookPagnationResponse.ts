import { Book } from "../Book";
import { PagnationResponse} from "./PagnationResponse";

export class BookPagnationResponse extends PagnationResponse{
    books!: Book[]
}