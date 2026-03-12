package com.darklord.url_shortener.util;

import org.hashids.Hashids;
import org.springframework.stereotype.Service;

@Service
public class HashidUtil {
    private final Hashids hashids;

    public HashidUtil() {
        this.hashids = new Hashids("darklord",6);
    }

    public String encode(Long id) {
        return hashids.encode(id);
    }
}
