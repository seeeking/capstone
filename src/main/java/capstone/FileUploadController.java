package capstone;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
    
	private final AtomicLong counter = new AtomicLong();
	
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name, 
    		@RequestParam("descrip") String desc,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
            	// convert counter to a 4 digit string
            	String id = String.format("%04d", counter.incrementAndGet());
            	String filename = "ID=" + id + "_" + name;
            	String picpath = filename+".jpg";
            	String txtpath = filename+" DESCRIPTION.txt";
            	Pic picture = new Pic(id, name, picpath, txtpath);
                SqlConnect con = new SqlConnect();
                con.insertPic(picture);
                byte[] bytes = file.getBytes();
                // rename the file, adding ID
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(picpath)));
                stream.write(bytes);
                stream.close();
                PrintWriter toFile = new PrintWriter(txtpath);
                toFile.write(desc);
                toFile.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
    
}
