import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ADMIN on 4/12/2017.
 */
public class Plane {
    private int x;
    private int y;
    private Image image;
    private ArrayList<Bullet> bullets;

    public Plane(int x, int y, Image image, ArrayList<Bullet> bullets) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.bullets = bullets;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics graphics){
        graphics.drawImage(image,x,y,null);
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
