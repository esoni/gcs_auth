package it.frongillo.demostorage.controller;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
public class HelloController {
    @Value("gs://italiancoderstest/private.txt")
    private Resource gcsFile;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();

        return StreamUtils.copyToString(
                gcsFile.getInputStream(),
                Charset.defaultCharset()) + "\n";
    }

}
