package ru.itis.photoserviсe.controllers;

import com.drew.imaging.ImageProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.photoserviсe.model.EdgeAttributes;
import ru.itis.photoserviсe.model.NoiseAttributes;
import ru.itis.photoserviсe.model.PhotoModel;
import ru.itis.photoserviсe.model.SharpnessAttributes;
import ru.itis.photoserviсe.services.MetadataService;
import ru.itis.photoserviсe.services.PhotoService;
import ru.itis.photoserviсe.utils.FileUploadUtil;
import ru.itis.photoserviсe.utils.FiltersUtil;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private MetadataService metadataService;

    @Value("${path.checked-photo}")
    private String checkPhotoPath;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Autowired
    private FiltersUtil fileUtil;


    @GetMapping("/")
    public String getIndexPage(Model model) {
        return "index";
    }

    private String photoName = null;
    private Double alpha = 0.0;
    private Double beta = 0.0;
    private Double gamma = -3.0;

    private int edge = 0;
    private Double thresh = 1.0;

    @PostMapping(value = "/upload")
    public String savePhoto(PhotoModel photo, @RequestParam("photo") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "."
                    + StringUtils.cleanPath((Objects.requireNonNull(multipartFile.getOriginalFilename())).split("\\.")[1]);
            System.out.println(checkPhotoPath + fileName);
            photo.setPath(checkPhotoPath + fileName);
            photoService.savePhoto(photo);
            photoName = checkPhotoPath + fileName;

            fileUploadUtil.saveFile(checkPhotoPath, photoName, multipartFile);
        }
        return "redirect:/photo_analysis";
    }

    @GetMapping("/photo_analysis")
    public String getProfilePage(Model model) throws ImageProcessingException, IOException {
        if (photoName != null) {
            model.addAttribute("photoname", photoName);
            model.addAttribute("metadata", metadataService.getMetadata(photoName));
            model.addAttribute("alpha", alpha);
            model.addAttribute("beta", beta);
            model.addAttribute("edge", edge);
            model.addAttribute("thresh", thresh);
            model.addAttribute("sharped", fileUtil.sharpness(alpha, beta, gamma, photoName));
            model.addAttribute("edged", fileUtil.borderHighlighting(edge, photoName));
            model.addAttribute("threshed", fileUtil.noiseAnalysis(thresh, photoName));
        }
        //model.addAttribute("users", usersService.findAllByOrderById());
        return "photo_analysis";
    }

    @PostMapping("/sharp")
    public String getProfilePage(SharpnessAttributes sharpnessAttributes, Model model) {
        alpha = sharpnessAttributes.getAlpha();
        beta = sharpnessAttributes.getBeta();
        return "redirect:/photo_analysis";
    }

    @PostMapping("/edge")
    public String getProfilePage(EdgeAttributes edgeAttributes, Model model) {
        edge = edgeAttributes.getEdge();
        return "redirect:/photo_analysis";
    }

    @PostMapping("/noise")
    public String getProfilePage(NoiseAttributes noiseAttributes, Model model) {
        thresh = noiseAttributes.getThresh();
        return "redirect:/photo_analysis";
    }
}
