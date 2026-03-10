package UI;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Lock extends ImageUIEntity {
	
	public Lock(String imagePath) throws IOException {
		super(imagePath);
	}
	
	public Lock(BufferedImage image) {
		super(image);
	}

}
