package com.burcu.controller;

import com.burcu.entity.Image;
import com.burcu.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.burcu.constants.RestApiUrls.*;

@RestController
@RequestMapping(IMAGE)
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    /**
     * Bu methodun amacı, bir HTTP POST isteği içindeki multipart/form-data formatındaki
     * dosyaları bir Cloudinary servise yüklemeyi sağlamaktır.
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file,
                                              @RequestParam("side_image") List<MultipartFile> fileList,
                                              @RequestParam("productId") Long productId) {
        //Ana resim yüklenir
        Image mainImage = imageService.upload(productId, file);

        // Ana resmi yükledikten sonra yan resimler yüklenir
        imageService.addSideImages(productId, fileList);

        return ResponseEntity.ok(
                String.format("File uploaded successfully: %s", mainImage.getImageUrl()));
    }



    /**
     *  Bu metodun amacı baseImage üzerine overlayImage'i ekleyip,
     *  bu işlemin sonucunda elde edilen yeni resmin Cloudinary servis
     *  üzerine yüklenmiş URL'ini dönmektir.
     *
     * @param baseImage : ana resim
     * @param overlayImage : üzerine eklenen resim
     * @return : elde edilen yeni resmin Cloudinary servis üzerine yüklenmiş URL'i.
     */
    @PostMapping(value = "/addOverlay", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addOverlayImage(@RequestPart("baseImage") MultipartFile baseImage,
                                                  @RequestPart("overlayImage") MultipartFile overlayImage){
        String finalImageUrl = imageService.addOverlayImage(baseImage, overlayImage);
        return ResponseEntity.ok(finalImageUrl);
    }

}
