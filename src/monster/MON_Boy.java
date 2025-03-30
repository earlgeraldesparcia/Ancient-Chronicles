/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monster;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import rpg.GamePanel;
import rpg.entity.Entity;

/**
 *
 * @author earlg
 */
public class MON_Boy extends Entity{
    GamePanel gp;
    
    public MON_Boy (GamePanel gp) {
        super(gp);
        
        direction = "down";
        name = "Boy";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        
        solidArea.x = 15;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;
        
        getBoyImage();
    }
    
    public void getBoyImage() {
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster1.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/monsterImage/monster2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
//          up1 = setup("/monsterImage/monster1.png");
//          up2 = setup("/monsterImage/monster2.png");
//          down1 = setup("/monsterImage/monster1.png");
//          down2 = setup("/monsterImage/monster2.png");
//          left1 = setup("/monsterImage/monster1.png");
//          left2 = setup("/monsterImage/monster2.png");
//          left1 = setup("/monsterImage/monster1.png");
//          left2 = setup("/monsterImage/monster2.png");
    }
    
    @Override
    public void setAction() {
        actionLockCounter++;
        
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i >0 && i <= 25){
                direction = "down";
            }
            if(i > 25 && i <= 50){
                direction = "up";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            
            actionLockCounter = 0;
        }
    }
    
//    @Override
//    public void update(){
//        setAction();
//        
//        collisionOn = false;
//        gp.cChecker.checkTile(this);
//        gp.cChecker.checkObject(this, false);
//        gp.cChecker.checkPlayer(this);
//        
//        if(collisionOn == false){
//                switch(direction){
//                    case "up":
//                        worldY -= speed;
//                        break;
//                    case "down":
//                        worldY+=speed;
//                        break;
//                    case "left":
//                        worldX-=speed;
//                        break;
//                    case "right":
//                        worldX+=speed;
//                        break;
//                }
//            }
//            
//            spriteCounter++;
//            if(spriteCounter>12){
//                if(spriteNum==1){
//                    spriteNum=2;
//                }
//                else if(spriteNum==2){
//                    spriteNum=1;
//                }
//                
//                spriteCounter = 0;
//            }
//    }
//    
//    @Override
//    public void draw(Graphics2D g2){
//        BufferedImage image = null;
//        int screenX = worldX - gp.player.worldX + gp.player.screenX;
//        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//            
//            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
//               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
//                
//                switch(direction){
//                    case "up":
//                        if(spriteNum==1){image=up1;}
//                        if(spriteNum==2){image=up2;}
//                        break;
//                    case "down":
//                        if(spriteNum==1){image=down1;}
//                        if(spriteNum==2){image=down2;}
//                        break;
//                    case "left":
//                        if(spriteNum==1){image=left1;}
//                        if(spriteNum==2){image=left2;}
//                        break;
//                    case "right":
//                        if(spriteNum==1){image=right1;}
//                        if(spriteNum==2){image=right2;}
//                        break;
//                }
//                
//                g2.drawImage(image, screenX, screenY, null);
//            }
//    }
    
}
