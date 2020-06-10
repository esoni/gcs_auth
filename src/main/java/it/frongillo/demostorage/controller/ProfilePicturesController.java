package it.frongillo.demostorage.controller;

import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import it.frongillo.demostorage.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.storage.GoogleStorageResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ProfilePicturesController {


    @Value("${cloud.storage.bucket}")
    private String bucketName;

    @Value("${signer_storage_key.location}")
    private String signerStorageKey;

    @Value("${admin_storage_key.location}")
    private String adminStorageKey;

    @RequestMapping(value = "/profile-picture", method = RequestMethod.POST)
    final @ResponseBody
    void uploadProfileImage(@RequestParam("picture") MultipartFile picture) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        //Storage storage = StorageOptions.getDefaultInstance().getService();
        //Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("D:\\keys\\key.json"));
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(adminStorageKey));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        GoogleStorageResource resource = new GoogleStorageResource(storage, bucketName + "/" + user.getUserId() + ".png", true);
        resource.createBlob(picture.getBytes());


    }
    @RequestMapping(value = "/profile-picture", method = RequestMethod.GET)
    final @ResponseBody void geturl(HttpServletResponse httpServletResponse) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Credentials cr = StorageOptions.getDefaultInstance().getCredentials();
        ServiceAccountCredentials sr = ServiceAccountCredentials.fromStream(new FileInputStream(signerStorageKey));

        GoogleStorageResource resource = new GoogleStorageResource(storage, bucketName + "/" + user.getUserId() + ".png", true);
        URL url = resource.createSignedUrl(TimeUnit.MINUTES, 10, Storage.SignUrlOption.httpMethod(HttpMethod.GET), Storage.SignUrlOption.signWith(sr));
        String urlStr = url.toString();
        httpServletResponse.setStatus(302);
        httpServletResponse.setHeader("Location", urlStr);

    }

}
