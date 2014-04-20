package com.jasvideo.ao.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.jasvideo.ao.dao.AssetDAO;
import com.jasvideo.ao.model.EnrichForm;
import com.jasvideo.ao.model.FileUploadForm;
import com.jasvideo.ao.model.FilesHolder;
import com.jasvideo.ao.model.VideoInfo;

@Controller
public class FileUploadController  {
	@Value("${video.dir}")
	private String videoSaveDirectory;
	@Value("${thumb.dir}")
	private String thumbSaveDirectory;
	private static final Logger log = LoggerFactory
			.getLogger(FileUploadController.class);

	@Autowired
	private UploadValidator validator;

	@Autowired
	private AssetDAO assetDAO;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String displayForm() {
		return "file_upload_page";
	}

	@InitBinder("uploadForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/ingest", method = RequestMethod.POST)
	public String save(
			@ModelAttribute("uploadForm") @Valid FileUploadForm uploadForm,
			BindingResult result, Model model) throws IllegalStateException,
			IOException {

		// Check for validation error
		if (result.hasErrors()) {

			return "file_upload_page";
		}

		List<FilesHolder> videoFiles = uploadForm.getFiles();

		List<VideoInfo> videos = new ArrayList<VideoInfo>();

		if (null != videoFiles && videoFiles.size() > 0) {

			log.info("Uploaded File size:" + videoFiles.size());
			for (FilesHolder filesHolder : videoFiles) {
				MultipartFile videoFile = filesHolder.getVideoFile();
				MultipartFile thumbnail = filesHolder.getThumbnail();
				VideoInfo info = new VideoInfo();
				if (!videoFile.isEmpty()) {
					String fileName = videoFile.getOriginalFilename();

					info.setFileName(fileName);
					videoFile
							.transferTo(new File(videoSaveDirectory + fileName));

				}
				if (!thumbnail.isEmpty()) {
					String fileName = thumbnail.getOriginalFilename();
					info.setThumbName(fileName);
					thumbnail
							.transferTo(new File(thumbSaveDirectory + fileName));
				}

				videos.add(info);

			}
		}

		EnrichForm enrichForm = new EnrichForm();
		enrichForm.setVideos(videos);
		model.addAttribute("enrichForm", enrichForm);
		
		Map<Integer, String> categories = assetDAO.getCategoryMap();
		Map<Integer, String> genres = assetDAO.getGenreMap();
		
		model.addAttribute("categories", categories);
		model.addAttribute("genres", genres);
		log.info("No of videos in the form:"+enrichForm.getVideos().size());
		
		
		return "enrich_page";
	}

	

}
