
let imageSize = 5039917;

export class FileService {

     
 
    public static checkImageFile(file: File): Boolean {
        if(file == null){
            return false;
        }

        if (file.type.toLowerCase() === "image/jpeg" ||
            file.type.toLowerCase() === "image/jpg" ||
            file.type.toLowerCase() === "image/png") {

            if (file.size > imageSize) {
                return false;
            }
            return true
        }
        return false;
    }
}