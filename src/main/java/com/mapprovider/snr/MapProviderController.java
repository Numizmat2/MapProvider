package com.mapprovider.snr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class MapProviderController {

    @Autowired
    private MapProviderService mapProviderService;

    @RequestMapping(value = "/mapByPixels", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getMapFragmentByPixels(@RequestParam Integer x,@RequestParam Integer y,
                                                         @RequestParam Integer w, @RequestParam Integer h) throws IOException {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(mapProviderService.getMapFragmentByPixels(x, y, w, h));
    }

    @RequestMapping(value = "/mapByCords", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getMapFragmentByCords(@RequestParam Double lx,@RequestParam Double rx,
                                                        @RequestParam Double by,@RequestParam Double ty) throws IOException {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(mapProviderService.getMapFragmentByCords(lx, rx, by, ty));
    }
}
