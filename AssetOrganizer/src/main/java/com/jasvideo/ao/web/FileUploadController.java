package com.jasvideo.ao.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.jasvideo.ao.model.FileMeta;
import com.jasvideo.ao.model.FileUploadForm;
import com.jasvideo.ao.model.FilesHolder;
import com.jasvideo.ao.model.VideoInfo;

@Controller
public class FileUploadController {
	@Value("${video.dir}")
	private String videoSaveDirectory;
	@Value("${thumb.dir}")
	private String thumbSaveDirectory;
	private static final Logger log = LoggerFactory
			.getLogger(FileUploadController.class);

	@Autowired
	private AssetDAO assetDAO;

	@RequestMapping(value = "/ingest", method = RequestMethod.POST)
	private String getThumbnails(Model model, HttpServletRequest request) {

		EnrichForm uploadThumbsForm = new EnrichForm();
		List videos = new ArrayList<VideoInfo>();

		List files = (List) request.getSession().getAttribute("files");
		Iterator fileIter = files.iterator();
		while (fileIter.hasNext()) {
			FileMeta meta = (FileMeta) fileIter.next();
			VideoInfo info = new VideoInfo();
			info.setFileName(meta.getFileName());
			log.info("Setting file name:" + meta.getFileName());
			videos.add(info);

		}
		uploadThumbsForm.setVideos(videos);
		model.addAttribute("uploadThumbsForm", uploadThumbsForm);

		return "upload_thumbs";

	}

	@RequestMapping(value = "/ingest-thumbs", method = RequestMethod.POST)
	private String saveThumbs(
			@ModelAttribute("uploadThumbsForm") EnrichForm uploadThumbsForm,
			Model model, HttpServletRequest request)
			throws IllegalStateException, IOException {

		List videoList = uploadThumbsForm.getVideos();

		Iterator videoIter = videoList.iterator();
		while (videoIter.hasNext()) {
			VideoInfo info = (VideoInfo) videoIter.next();
			MultipartFile thumbnail = info.getThumbnail();
			if (thumbnail != null && !thumbnail.isEmpty()) {
				String fileName = thumbnail.getOriginalFilename();
				info.setThumbName(fileName);
				thumbnail.transferTo(new File(thumbSaveDirectory + fileName));
				info.setThumbName(fileName);
				info.setThumbnail(null);// removing the file from the info

			}
		}

		EnrichForm enrichForm = new EnrichForm();
		enrichForm.setVideos(videoList);
		model.addAttribute("enrichForm", enrichForm);

		Map<Integer, String> categories = assetDAO.getCategoryMap();
		Map<Integer, String> genres = assetDAO.getGenreMap();

		model.addAttribute("categories", categories);
		model.addAttribute("genres", genres);
		log.info("No of videos in the form:" + enrichForm.getVideos().size());
		request.getSession().removeAttribute("files");

		return "enrich_page_new";

	}

}
