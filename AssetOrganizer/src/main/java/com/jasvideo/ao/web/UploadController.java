package com.jasvideo.ao.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jasvideo.ao.model.FileMeta;
@RestController
@RequestMapping("/video")
public class UploadController {
	
	@Value("${video.dir}")
	private String videoSaveDirectory;
	
	private static final Logger log = LoggerFactory
			.getLogger(UploadController.class);
	
    /***************************************************
     * URL: /rest/controller/upload  
     * upload(): receives files
     * @param request : MultipartHttpServletRequest auto passed
     * @param response : HttpServletResponse auto passed
     * @return LinkedList<FileMeta> as json format
     ****************************************************/
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public @ResponseBody ArrayList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
    	
    	ArrayList<FileMeta> files;
    	HttpSession session = request.getSession();
    	
    	if(session.getAttribute("files")!=null){
    		 
    		files = (ArrayList<FileMeta>) session.getAttribute("files");
    	}else{
    		files = new ArrayList<FileMeta>();
    	}
    	
    	
        FileMeta fileMeta = null;
        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
            log.info(mpf.getOriginalFilename() +" uploaded! "+files.size());
 
            
             //2.3 create new fileMeta
             fileMeta = new FileMeta();
             fileMeta.setFileName(mpf.getOriginalFilename());
             fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
             fileMeta.setFileType(mpf.getContentType());
 
             try {
            	 
            log.info("Copying file to disk");	
              
             //   InputStream iStr = mpf.getInputStream();
             //  FileOutputStream foStr =  new FileOutputStream(videoSaveDirectory+mpf.getOriginalFilename());
             //  BufferedOutputStream bout= new BufferedOutputStream (foStr);
             //  BufferedInputStream bin= new BufferedInputStream(iStr);
                
                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists)            
            //     FileCopyUtils.copy(bin,bout);
                 
                 mpf.transferTo(new File(videoSaveDirectory+mpf.getOriginalFilename()));
                 
                 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             files.add(fileMeta);
         }
         
         request.getSession().setAttribute("files", files);
         log.info("Upload successfull, trying to return");
         
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
    }

}
