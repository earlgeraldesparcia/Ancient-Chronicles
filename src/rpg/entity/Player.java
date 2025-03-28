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
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_back1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_back2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_back3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_back4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_back5.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_back6.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_back7.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_back8.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_front1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_front2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_front3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_front4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_front5.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_front6.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_front7.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_front8.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_left5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_left6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_left7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_left8.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_right5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_right6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_right7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/res/player/Vampire/Vampire_right8.png"));
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
                    spriteNum=3;
                }
                else if(spriteNum==3){
                    spriteNum=4;
                }
                else if(spriteNum==4){
                    spriteNum=5;
                }
                else if(spriteNum==5){
                    spriteNum=6;
                }
                else if(spriteNum==6){
                    spriteNum=7;
                }
                else if(spriteNum==7){
                    spriteNum=8;
                }
                else if(spriteNum==8){
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
                if(spriteNum==3){image=up3;}
                if(spriteNum==4){image=up4;}
                if(spriteNum==5){image=up5;}
                if(spriteNum==6){image=up6;}
                if(spriteNum==7){image=up7;}
                if(spriteNum==8){image=up8;}
                break;
            case "down":
                if(spriteNum==1){image=down1;}
                if(spriteNum==2){image=down2;}
                if(spriteNum==3){image=down3;}
                if(spriteNum==4){image=down4;}
                if(spriteNum==5){image=down5;}
                if(spriteNum==6){image=down6;}
                if(spriteNum==7){image=down7;}
                if(spriteNum==8){image=down8;}
                break;
            case "left":
                if(spriteNum==1){image=left1;}
                if(spriteNum==2){image=left2;}
                if(spriteNum==3){image=left3;}
                if(spriteNum==4){image=left4;}
                if(spriteNum==5){image=left5;}
                if(spriteNum==6){image=left6;}
                if(spriteNum==7){image=left7;}
                if(spriteNum==8){image=left8;}
                break;
            case "right":
                if(spriteNum==1){image=right1;}
                if(spriteNum==2){image=right2;}
                if(spriteNum==3){image=right3;}
                if(spriteNum==4){image=right4;}
                if(spriteNum==5){image=right5;}
                if(spriteNum==6){image=right6;}
                if(spriteNum==7){image=right7;}
                if(spriteNum==8){image=right8;}
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
