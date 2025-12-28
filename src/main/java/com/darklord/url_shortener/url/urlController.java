package com.darklord.url_shortener.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/url")
public class urlController {

    private final UrlShortener urlShortener;
    @Autowired
    public urlController(UrlShortener urlShortener) {
        this.urlShortener = urlShortener;
    }

    

}
