package BackEnd;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {

    private static Map<String, String> pathToName = new HashMap<>();
    private static Map<String, BufferedImage> imageMap  = new HashMap<>();

    public static BufferedImage get(String s)
    {
        return imageMap.get(s);
    }

    public static String load(String imagePath, String nameIfBlank) throws IOException {
        if (pathToName.containsKey(imagePath))
        {
            return pathToName.get(imagePath);
        }

        pathToName.put(imagePath, nameIfBlank);
        imageMap.put(nameIfBlank, ImageIO.read(new File(imagePath)));
        return nameIfBlank;
    }


}
