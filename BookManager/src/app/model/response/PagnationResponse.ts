import {Response} from "../Response"
export  class PagnationResponse extends Response {
    currentPage!: number
    size!: number
    totalPage!: number
}