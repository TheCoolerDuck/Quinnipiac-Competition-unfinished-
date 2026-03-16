package UI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackBoardUIEntity extends UIEntity{

    public UIEntity target;

    public final static int SCALE = 0;
    public final static int APPROXREPEAT = 1;

    public int scaleType = SCALE;

    private BufferedImage corner;
    private BufferedImage top;
    private BufferedImage side;

    public BackBoardUIEntity(UIEntity target, String cornerImagePath, String topImagePath, String sideImagePath)
    {
        super();
        this.target = target;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    //public void setImage(String imagePath) throws IOException { image = ImageIO.read(new File(imagePath)); }

    //public void setImage(BufferedImage image)
    //{
        //this.image = image;
    //}

    @Override
    protected void OnDispose() {

    }


}
