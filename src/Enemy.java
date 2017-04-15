import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ADMIN on 13/04/2017.
 */
public class Enemy {
    private int x;
    private int y;
    private Image image;
    private  ArrayList<Bullet> enemyBullets;
    Graphics backBufferGraphic;

    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public void setEnemyBullets(ArrayList<Bullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }



    public Enemy(int x, int y, Image image, ArrayList<Bullet> enemyBullets) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.enemyBullets = enemyBullets;
        enemyBullets = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }

    public void update(){
        y+=5;
    }

    public void shoot(){
        Bullet ebullet = null ;
        try {
            ebullet = new Bullet(getX(),getY(),Ultilities.loadImage("res/enemy_bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        enemyBullets.add(ebullet);

        for (Bullet b :  getEnemyBullets()) {
            b.updateEnemies();
//            b.draw(backBufferGraphic);
        }


//
    }


}