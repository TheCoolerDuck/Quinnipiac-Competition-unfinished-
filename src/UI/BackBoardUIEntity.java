package UI;

import BackEnd.ImageManager;
import Source.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackBoardUIEntity extends UIEntity{

    public UIEntity target;

    //uses the varables below to create the backboarder
    public final static int CONSTANT_N_REPEAT = 0;
    //the backboarder will have a fluid amount of pieces (the number can change), the code tries to preserve the images base width
    public final static int FLUID_N_REPEAT = 1;

    //CONSTANT_N_REPEAT varables
    public int topNumberPieces = 1;
    public int sideNumberPieces = 1;
    public int backTopNumberPieces = 1;
    public int backSideNumberPieces = 1;


    public int scaleType = CONSTANT_N_REPEAT;
    public double pieceScale = 1;

    //the padding this entity will give to its target entity
    public int boarder = 0;

    private String corner;
    private String top;
    private String side;
    private String back;

    public BackBoardUIEntity(UIEntity target, String cornerImageName, String topImageName, String sideImageName, String backName)
    {
        super();
        this.target = target;
        this.corner = cornerImageName;
        this.top = topImageName;
        this.side = sideImageName;
        this.back = backName;
    }

    protected void Update() {
        this.setCentralX(target.getCentralX());
        this.setCentralY(target.getCentralY());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        BufferedImage cornerImg = ImageManager.get(corner);
        BufferedImage sideImg   = ImageManager.get(side);
        BufferedImage topImg    = ImageManager.get(top);
        BufferedImage backImg   = ImageManager.get(back);

        double scale = pieceScale / Camera.scale;

        int cw = (int)(cornerImg.getWidth() * scale);
        int ch = (int)(cornerImg.getHeight() * scale);
        int sw = (int)(sideImg.getWidth() * scale);
        int sh = (int)(sideImg.getHeight() * scale);
        int tw = (int)(topImg.getWidth() * scale);
        int th = (int)(topImg.getHeight() * scale);
        int bw = (int)(backImg.getWidth() * scale);
        int bh = (int)(backImg.getHeight() * scale);

        int innerW = getScreenWidth()  - cw * 2;
        int innerH = getScreenHeight() - ch * 2;

        int topN = scaleType == FLUID_N_REPEAT ? innerW / tw : topNumberPieces;
        int topW = innerW / topN;

        int sideN = scaleType == FLUID_N_REPEAT ? innerH / sh : sideNumberPieces;
        int sideH = innerH / sideN;

        int backNX = scaleType == FLUID_N_REPEAT ? innerW / bw : backTopNumberPieces;
        int backNY = scaleType == FLUID_N_REPEAT ? innerH / bh : backSideNumberPieces;

        int backW = innerW / backNX;
        int backH = innerH / backNY;

        // Draws a box in the order below:
        // 1 8 7
        // 2 9 6
        // 3 4 5
        g.drawImage(cornerImg, 0,       0,        cw,    ch,     null); // 1
        { //2
            int cursor = 0;
            int diff = innerH - (sideN * sideH);
            for (int i = 0; i < sideN; i++) {
                g.drawImage(sideImg, 0, ch + cursor, sw, sideH + (i < diff ? 1 : 0), null); // 2
                cursor += sideH + (i < diff ? 1 : 0);
            }
        }
        g.drawImage(cornerImg, 0,       getScreenHeight(), cw,   -ch,     null); // 3
        { //4
            int cursor = 0;
            int diff = innerW - (topN * topW);
            for (int i = 0; i < topN; i++) {
                g.drawImage(topImg,cw + cursor, getScreenHeight(), topW + (i < diff ? 1 : 0), -th, null);
                cursor += topW + (i < diff ? 1 : 0);
            }
        }
        g.drawImage(cornerImg, getScreenWidth(), getScreenHeight(), -cw,  -ch,     null); // 5
        { //6
            int cursor = 0;
            int diff = innerH - (sideN * sideH);
            for (int i = 0; i < sideN; i++) {
                g.drawImage(sideImg, getScreenWidth(), ch + cursor, -sw, sideH + (i < diff ? 1 : 0), null);
                cursor += sideH + (i < diff ? 1 : 0);
            }
        }
        g.drawImage(cornerImg, getScreenWidth(), 0,        -cw,   ch,     null); // 7
        { //8
            int cursor = 0;
            int diff = innerW - (topN * topW);
            for (int i = 0; i < topN; i++) {
                g.drawImage(topImg,cw + cursor, 0, topW + (i < diff ? 1 : 0), th, null);
                cursor += topW + (i < diff ? 1 : 0);
            }
        }
        {//9
            int cursorX = 0;
            int diffX = innerW - (backNX * backW);
            int cursorY = 0;
            int diffY = innerH - (backNY * backH);
            for (int x = 0; x < backNX; x++) {
                for (int y = 0; y < backNY; y++) {
                    g.drawImage(backImg, cw + cursorX, ch + cursorY, backW + (x < diffX ? 1 : 0), backH + (y < diffY ? 1 : 0), null);

                    cursorY += backH + (y < diffY ? 1 : 0);
                }
                cursorX += backW + (x < diffX ? 1 : 0);
                cursorY = 0;
            }
        }

        if (!debug)
            return;

        Font f = new Font("Arial", Font.BOLD, 18);

        g.setFont(f);
        g.drawString("best: topN:" + innerW / tw + "; sideN: " + innerH / sh + "; backXN: " + innerW / bw + "; backYN: " + innerH / bh, 0,g.getFontMetrics(f).getAscent());
    }

    @Override
    public int getWorldWidth() {
        return target.getWorldWidth() + 2 * boarder;
    }

    @Override
    public int getWorldHeight() {
        return target.getWorldHeight() + 2 * boarder;
    }


    @Override
    protected void OnDispose() {
        target = null;
        corner = null;
        top = null;
        side = null;
        back = null;
    }


}
