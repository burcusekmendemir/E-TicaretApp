package com.burcu.service;

import com.burcu.dto.request.ProductAddRequestDto;
import com.burcu.entity.Image;
import com.burcu.entity.Product;
import com.burcu.repository.ImageRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final Cloudinary cloudinary;
    private final ImageRepository imageRepository;
    private final ProductService productService;


    /**
     * Bu upload metodu, bir MultipartFile nesnesini alır ve bu dosyayı Cloudinary servisine yükler.
     * Bu metodun kullanım amacı, kullanıcıdan veya uygulama tarafından sağlanan dosyaları bir bulut depolama
     * sistemine yüklemek ve bu yüklemenin sonucunda elde edilen bilgileri almaktır.
     * Bir hata ile karşılaşılırsa da hata mesajını verir.
     */

    public Image upload(Long productId,MultipartFile file) {
        try {

           Map uploadResult = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
           String imageUrl= (String) uploadResult.get("url");

           Image mainImage=imageRepository.save(Image.builder().imageUrl(imageUrl).product(productService.findById(productId).get()).isMainImage(true).build());
           return mainImage;

        } catch (IOException e) {
            throw new RuntimeException("Image uploading failed...");
        }
    }

    public void addSideImages(Long productId, List<MultipartFile> files) {
        // Ana resmi bul
        Optional<Product> product = productService.findById(productId);
        List<Image> sideImages = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                Map uploadResult = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String imageUrl = (String) uploadResult.get("url");

                // Yan resim olarak kaydet
                sideImages.add(imageRepository.save(Image.builder().imageUrl(imageUrl).product(productService.findById(productId).get()).isMainImage(false).build()));
            } catch (IOException e) {
                throw new RuntimeException("Image uploading failed...");
            }
        }

        // Yan resimleri ürüne ekle
        product.get().setSideImages(sideImages);
        productService.save(product.get());
    }


    /**
     * baseImage ve overlayImage adlı iki MultipartFile nesnesi parametre olarak alınır.
     * Bu nesneler, Spring Framework tarafından sunulan MultipartFile tipinde dosyaları temsil eder.
     */

    public String addOverlayImage(MultipartFile baseImage, MultipartFile overlayImage) {

        /**
         * Bu nesneler, Cloudinary'ye yüklenen baseImage ve overlayImage'in
         * yükleme işleminden sonra elde edilen sonuçlarıdır.
         */
        Map baseUploadResult = null;
        Map overlayUploadResult = null;
        try {
            /**
             * baseImage ve overlayImage dosyaları Cloudinary'ye yüklenir.
             * Bu işlem, Cloudinary SDK'sının uploader().upload() metodunu kullanarak gerçekleştirilir.
             */
            baseUploadResult = cloudinary.uploader().upload(baseImage.getBytes(), ObjectUtils.emptyMap());
            overlayUploadResult = cloudinary.uploader().upload(overlayImage.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**
         * baseImageUrl ve overlayImageUrl değişkenleri, baseimage ve overlayImage'in
         * URL'lerini içerir. Bu URL'ler, baseUploadResult ve overlayUploadResult map'lerinden "url"
         * anahtarına karşılık gelen değerlerdir.
         */
        String baseImageUrl = (String) baseUploadResult.get("url");
        String overlayImageUrl = (String) overlayUploadResult.get("url");

        /**
         * Transformation adlı bir Transformation nesnesi oluşturulur. Bu nesne, Cloudinary
         * üzerinde uygulanacak dönüşüm ayarlarını içerir. overlay() metodu, resmin üzerine
         * eklenecek resmin URL'sini belirler ve gravity() metodu ise resmin hangi bölgesine
         * ekleneceğini belirler. Burada "center" yani merkeze eklenmiştir.
         */

        Transformation transformation = new Transformation()
                .overlay(overlayImageUrl.replace("https://", ""))
                .gravity("center");

        /**
         * cloudinary.url().transformation(transformation) ile Transformation nesnesi Cloudinary'nin
         * URL dönüşüm metoduna uygulanır. Bu, dönüştürülmüş URL'yi elde etmek için kullanılır.
         *
         * imageTag(baseImageUrl) metodu ile temel resmin URL'si, Cloudinary'nin imageTag metodu ile
         * kullanılarak bir HTML resim etiketi oluşturulur.
         *
         * Oluşturulan HTML resim etiketi, finalImageUrl olarak geri döndürülür. Bu, üzerine eklenecek
         *  resmin belirli bir dönüşüm ile birleştirilmiş ve bu birleştirilmiş resmin HTML etiketini içerir.
         */
        String finalImageUrl = cloudinary.url().transformation(transformation).imageTag(baseImageUrl);

        return finalImageUrl;
    }



}
