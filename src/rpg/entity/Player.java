/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import rpg.GamePanel;
import rpg.KeyHandler;

/**
 *
 * @author earlg
 */
public class Player extends Entity{
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp);
        
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 15;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    @Override
    public void update(){
        if(keyH.upPressed==true ||
           keyH.downPressed==true ||
           keyH.leftPressed==true ||
           keyH.rightPressed==true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed==true){
                direction = "down";
            }
            else if(keyH.leftPressed==true){
                direction = "left";
            }else if(keyH.rightPressed==true){
                direction = "right";
            }
            
            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            //Check NPC Collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            
            
            //if collision false, player can move
            if(collisionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY+=speed;
                        break;
                    case "left":
                        worldX-=speed;
                        break;
                    case "right":
                        worldX+=speed;
                        break;
                }
            }
            
            spriteCounter++;
            if(spriteCounter>12){
                if(spriteNum==1){
                    spriteNum=2;
                }
                else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter = 0;
            }
        }
        
        
    }
    
    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            
            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        gp.ui.showMessage("You opened the door!");
                        hasKey--;
                    }else{
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed Up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    break;
            }
        }
    }
    
    
    
    @Override
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        
        switch(direction){
            case "up":
                if(spriteNum==1){image=up1;}
                if(spriteNum==2){image=up2;}
                break;
            case "down":
                if(spriteNum==1){image=down1;}
                if(spriteNum==2){image=down2;}
                break;
            case "left":
                if(spriteNum==1){image=left1;}
                if(spriteNum==2){image=left2;}
                break;
            case "right":
                if(spriteNum==1){image=right1;}
                if(spriteNum==2){image=right2;}
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
