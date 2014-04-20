package com.jasvideo.ao.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.jasvideo.ao.model.FileUploadForm;
import com.jasvideo.ao.model.FilesHolder;
@Component
public class UploadValidator implements Validator {

	@Value("${accepted_video_content_types}")
	private String[] ACCEPTED_VIDEO_CONTENT_TYPES;
	@Value("${accepted_video_extensions}")
	private String[] ACCEPTED_VIDEO_EXTENSIONS;
	@Value("${accepted_image_content_types}")
	private String[] ACCEPTED_IMAGE_CONTENT_TYPES;
	@Value("${accepted_image_extensions}")
	private String[] ACCEPTED_IMAGE_EXTENSIONS;

	private static final Logger logger = LoggerFactory
			.getLogger(UploadValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {

		return FileUploadForm.class.equals(clazz);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		
		logger.info("Accepted content types:");
		
		
		for(String contentType : ACCEPTED_VIDEO_CONTENT_TYPES){
			logger.info(contentType);
		}
		
		
		FileUploadForm uploadItem = (FileUploadForm) arg0;
		List<FilesHolder> files = uploadItem.getFiles();
		for (FilesHolder file : files) {
			
			MultipartFile video = file.getVideoFile();
			MultipartFile thumbnail = file.getThumbnail();
			try {

				if (video == null || video.getBytes().length == 0) {
					errors.reject("error.upload.video.null",
							"Video File can't be empty");
					return;
				}
				if(thumbnail ==null || thumbnail.getBytes().length ==0){
					errors.reject("error.upload.thumb.null",
							"Thumbnail File can't be empty");
				}
				
			} catch (IOException e) {
				logger.error(e.getMessage());
			}

			// Check content type
			boolean acceptableVideoContentType = false;
			String incomingContentType = video.getContentType();
			logger.info("FileName = " + video.getName());
			logger.info("incomingContentType = " + incomingContentType);
			// This related to bug when on Vista and using Firefox content type
			// is 'application/octet-stream'
			// Instead of one of the allowed ones
			if ("application/octet-stream"
					.equalsIgnoreCase(incomingContentType)) {
				int index = video.getOriginalFilename().lastIndexOf('.');
				String incomingExtension = video.getOriginalFilename()
						.substring(index + 1);
				for (String extendsion : ACCEPTED_VIDEO_EXTENSIONS) {
					if (extendsion.equalsIgnoreCase(incomingExtension)) {
						acceptableVideoContentType = true;
						break;
					}
				}
			} else {
				for (String contentType : ACCEPTED_VIDEO_CONTENT_TYPES) {
					logger.debug("Comparing " + incomingContentType + " to "
							+ contentType);
					if (contentType.equalsIgnoreCase(incomingContentType)) {
						acceptableVideoContentType = true;
						break;
					}
				}
			}
			// Check content type
						boolean acceptableImageContentType = false;
						String incomingImageContentType = thumbnail.getContentType();
						logger.info("THumb FileName = " + thumbnail.getName());
						logger.info("Thumb incomingContentType = " + incomingImageContentType);
						// This related to bug when on Vista and using Firefox content type
						// is 'application/octet-stream'
						// Instead of one of the allowed ones
						if ("application/octet-stream"
								.equalsIgnoreCase(incomingImageContentType)) {
							int index = thumbnail.getOriginalFilename().lastIndexOf('.');
							String incomingExtension = thumbnail.getOriginalFilename()
									.substring(index + 1);
							for (String extendsion : ACCEPTED_IMAGE_EXTENSIONS) {
								if (extendsion.equalsIgnoreCase(incomingExtension)) {
									acceptableImageContentType = true;
									break;
								}
							}
						} else {
							for (String contentType : ACCEPTED_IMAGE_CONTENT_TYPES) {
								logger.debug("Comparing " + incomingContentType + " to "
										+ contentType);
								if (contentType.equalsIgnoreCase(incomingImageContentType)) {
									acceptableImageContentType = true;
									break;
								}
							}
						}
			
			
			if (!acceptableVideoContentType) {
				errors.reject(
						"error.upload.video.contenttype",
						"Please upload a video file with one of the following file types; .mp4, .flv, .mov, .wmv, .3gp .");
			}
			if (!acceptableImageContentType) {
				errors.reject(
						"error.upload.image.contenttype",
						"Please upload a thumbnail file with one of the following file types; .jpg, .jpeg, .png, .tif, .bmp .");
			}
		}

	}

}
