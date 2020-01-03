package com.mapprovider.snr;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class MapProviderService {

    byte[] getMapFragmentByPixels(Integer x, Integer y, Integer w, Integer h) throws IOException {
        var imgFile = new ClassPathResource("image/map.png");
        BufferedImage bufferedImage = ImageIO.read(imgFile.getInputStream());
        var subImage = bufferedImage.getSubimage(x, y, w, h);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ImageIO.write(subImage, "png", byteOutputStream);
        return byteOutputStream.toByteArray();
    }

    byte[] getMapFragmentByCords(Double lx, Double rx, Double by, Double ty) throws IOException {
        var imgFile = new ClassPathResource("image/map.png");
        BufferedImage bufferedImage = ImageIO.read(imgFile.getInputStream());

        var width = bufferedImage.getWidth();
        var height = bufferedImage.getHeight();
        var scaleX = width / (Consts.rx - Consts.lx);
        var scaleY = height / (Consts.ty - Consts.by);
        var x = (lx - Consts.lx) * scaleX;
        var y = (Consts.ty - ty) * scaleY;
        var w = scaleX*(rx-lx);
        var h = scaleY*(ty-by);

        var subImage = bufferedImage.getSubimage((int)x, (int)y, (int)w, (int)h);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ImageIO.write(subImage, "png", byteOutputStream);
        return byteOutputStream.toByteArray();
    }
}
