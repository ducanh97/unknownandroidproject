import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 123 on 09/04/2017.
 */
public class GameWindow extends Frame {

    Image backgroundImage, planeImage;
   private Plane plane;
   private int pX;
   private int pY;
   private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private int enemyTime = 3000;
    private int coolDownTime = 2000;
    private boolean isEnemyAppear;
private ArrayList<Bullet> enemiesBullets;
private Enemy enemy;
public Ultilities ul;

    /**
     *
     */
    BufferedImage backBufferImage;
    Graphics backBufferGraphic;

    private boolean isUpPressed;
    private boolean isDownPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isCooledDown;
    private boolean isSpacePressed = false;

    public GameWindow() {
        setVisible(true);
        setSize(400, 600);

        backBufferImage = new BufferedImage(400,600, BufferedImage.TYPE_INT_ARGB);
        backBufferGraphic = backBufferImage.getGraphics();

        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemiesBullets = new ArrayList<>();
        ul = new Ultilities();

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                    isRightPressed = true;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
                    isLeftPressed = true;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                    isDownPressed = true;
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP){
                    isUpPressed = true;
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
                    isSpacePressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                    isRightPressed = false;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
                    isLeftPressed = false;
                } else if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN){
                    isDownPressed = false;
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP){
                    isUpPressed = false;
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
                    isSpacePressed = false;
                }
            }
        });

        try {
            backgroundImage = ImageIO.read(new File("res/background.png"));
            planeImage = ImageIO.read(new File("res/plane3.png"));
            plane = new Plane(200,200,planeImage,bullets);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (isCooledDown) {
                      coolDownTime -= 17;
                        if (coolDownTime < 0) {
                            System.out.println("Cooldown");
                            coolDownTime = 2000;
                            isCooledDown = false;
                        }
                    }

                    int dy = 0, dx = 0;
                    if(isUpPressed){
                        dy -= 3;
                    }
                    if(isDownPressed){
                        dy += 3;
                    }
                    if(isLeftPressed){
                        dx -= 3;
                    }
                    if(isRightPressed){
                        dx += 3;
                    }

                    plane.setX(plane.getX() + dx);
                    plane.setY(plane.getY() + dy);


                    if (isSpacePressed && !isCooledDown) {

                        Bullet bullet = null ;
                        try {
                            bullet = new Bullet(plane.getX(), plane.getY(), Ultilities.loadImage("res/bullet.png"));
                            System.out.println("Cos bullet");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        plane.getBullets().add(bullet);
                    }
                    for (Bullet bullet : plane.getBullets()) {
                        bullet.update();
                    }
                    isSpacePressed = false;


                    Random rand = new Random();
                    int xEnemy = rand.nextInt(600);
                    int yEnemy = 0;

                    enemyTime -= 17;
                    if (enemyTime < 0) {
                        enemyTime = 1000;
                        isEnemyAppear = true;
                    }

                    if (isEnemyAppear) {
                        Enemy enemy = null;
                        try {
                            enemy = new Enemy(xEnemy, yEnemy, Ultilities.loadImage("res/enemy_plane_white_1.png"), new ArrayList<Bullet>());
                            enemies.add(enemy);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        isEnemyAppear = false;
                    }
                    for (Enemy en : enemies) {
                        en.update();
                        Bullet ebullet = null ;
                        try {
                           ebullet = new Bullet(en.getX(), en.getY(),Ultilities.loadImage("res/enemy_bullet.png"));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        en.shoot();

//                        ArrayList<Bullet> enemyBuls = en.getEnemyBullets();
//                       enemyBuls.add(ebullet);
//                       en.setEnemyBullets(enemyBuls);

//                    for (Bullet b :  en.getEnemyBullets()) {
//                        if(b.getBulletX()<400) {
//                            b.updateEnemies();
//                        } else {
//                            ArrayList<Bullet> temp = en.getEnemyBullets();
//                            temp.remove(temp.indexOf(b));
//                            en.setEnemyBullets(temp);
//                        }
//                        if(b.getBulletX() > 400){
//                            en.getEnemyBullets().remove(b);
//                        }


//                    }
                    }




                    repaint();
                }
            }
        });

        thread.start();
    }



    @Override
    public void update(Graphics g) {
        backBufferGraphic.drawImage(backgroundImage, 0, 0, 400, 600, null);
        plane.draw(backBufferGraphic);
        for (Bullet bullet : plane.getBullets()) {
            bullet.draw(backBufferGraphic);

        }
        for (int i =0; i <enemies.size();i++) {
            enemies.get(i).draw(backBufferGraphic);
            for(int j =0;j <enemies.get(i).getEnemyBullets().size();j++){
                enemies.get(i).getEnemyBullets().get(j).draw(backBufferGraphic);
            }

        }
//
//        for (Bullet b : enemiesBullets){
//            b.draw(backBufferGraphic);
//        }

        g.drawImage(backBufferImage,0,0,null);
    }
}
