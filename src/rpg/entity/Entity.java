/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import rpg.GamePanel;
import rpg.UtilityTool;

/**
 *
 * @author earlg
 */
public class Entity {
    
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1,up2,up3,up4,up5,up6,up7,up8,down1,down2,down3,down4,down5,down6,down7,down8,left1,left2,left3,left4,left5,left6,left7,left8,right1,right2,right3,right4,right5,right6,right7,right8;
    public String direction;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    
    String dialogues[] = new String[100];
    int dialogueIndex = 0;
    
    public String name;
    public int maxLife;
    public int life;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        
        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            System.out.println("Ang setup naguba!");
        }
        return scaledImage;
    }
    
    public void setAction(){
        
    }
    
    public void speak() {
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    
    public void update(){
        setAction();
        
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkPlayer(this);
        
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
    
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                
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
    
}
