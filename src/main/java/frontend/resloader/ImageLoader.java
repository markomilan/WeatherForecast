package frontend.resloader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public final class ImageLoader {

    private ImageLoader() {
        throw new AssertionError("Must not instantiate an element of ImageLoader class!");
    }

    public static BufferedImage readImage(String path) throws IOException {
        try (final InputStream inputStream = ImageLoader.class.getResourceAsStream(String.valueOf(path))) {
            final BufferedImage image = ImageIO.read(inputStream);
            if (image == null) {
                throw new IOException("No registered ImageReader claims to be able to read the resulting stream." +
                        "(ImageIO.read(...) returns null)");
            }
            return image;
        }
    }
}