import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Bullet {
    private int bulletX;
    private int bulletY;
    private Image bulletImage;

    public Bullet(int bulletX, int bulletY, Image bulletImage) {
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.bulletImage = bulletImage;
    }

    public int getBulletX() {
        return bulletX;
    }

    public int getBulletY() {
        return bulletY;
    }

    public Image getBulletImage() {
        return bulletImage;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(bulletImage, bulletX, bulletY, null);
    }

    public void update() {
        bulletY -= 10;
    }

    public void updateEnemies(){
        bulletY += 100;
    }
}

