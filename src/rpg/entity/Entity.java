/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
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
    public Graphics2D g2;
    public GamePanel gp;
    public int worldX, worldY;
    public int speed;
    
    public int screenX, screenY;
    
    //from superobject
    public BufferedImage image, image2, image3;
    public boolean collision = false;
    
    //running images
    public BufferedImage up1,up2,up3,up4,up5,up6,up7,up8,down1,down2,down3,down4,down5,down6,down7,down8,
            left1,left2,left3,left4,left5,left6,left7,left8,right1,right2,right3,right4,right5,right6,right7,right8;
    //attack images
    public BufferedImage aUp1,aUp2,aUp3,aUp4,aUp5,aUp6,aUp7,aUp8,aDown1,aDown2,aDown3,aDown4,aDown5,aDown6,aDown7,aDown8,
            aLeft1,aLeft2,aLeft3,aLeft4,aLeft5,aLeft6,aLeft7,aLeft8,aRight1,aRight2,aRight3,aRight4,aRight5,aRight6,aRight7,aRight8;
    public String direction = "down";
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public int type; //0=player, 1=npc, 2=monster, 3=boss
    
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public int hpBarCounter = 0;
    public int dyingCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter;
    String dialogues[] = new String[100];
    int dialogueIndex = 0;
    
    public String name;
    public int maxLife;
    public int life;
    public int maxManna;
    public int mana;
    public Skill firstSkill;
    public int attack;
    public int useCost;
    public int firstSkillCooldown = 0;
    public int defense;
    public int level;
    public int exp;
    public int money;
    public int maxExp;
    
    public boolean isDamaged = false;
    public int displayDamageCounter = 0;
    
    //pick-up objects attributes
    public int attackValue;
    public int defenseValue;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    
    public BufferedImage setup(String imagePath, int tileSizeX, int tileSizeY){
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        
        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
            scaledImage = uTool.scaleImage(scaledImage, tileSizeX, tileSizeY);
        }catch(IOException e){
            System.out.println("Ang setup naguba!");
        }
        return scaledImage;
    }
    
    public void setAction(){}
    
    public void monsterDamageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
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
    
    //main update method
    public void update(){
        setAction();
        
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkPlayer(this);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        
        if(this.type == 2 && contactPlayer ==  true){
            damagePlayer(attack);
        }
        
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
        
        if(firstSkillCooldown<30){
            firstSkillCooldown++;
        }
    }
    
    public void updateTwoImages(){}
    
    public void updateFirstSkillImages() {}
    
    //main function for draw
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        screenX = worldX - gp.player.worldX + gp.player.screenX;
        screenY = worldY - gp.player.worldY + gp.player.screenY;
            
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
    
    public void drawTwoImages(Graphics2D g2){}
    
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        
        int i = 5;
        
        if(dyingCounter <= i) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > i && dyingCounter <= i*2) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > i*2 && dyingCounter <= i*3) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > i*3 && dyingCounter <= i*4) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > i*4 && dyingCounter <= i*5) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > i*5 && dyingCounter <= i*6) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > i*6 && dyingCounter <= i*7) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > i*7 && dyingCounter <= i*8) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > i*8) {
            alive = false;
        }
    }
    
    public void displayDamage(Graphics2D g2, String message, int screenX, int screenY, int counter) {
        int messageX = screenX + 17;
        int messageY = screenY - 25;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15f));
        
        g2.setColor(Color.BLACK);
        g2.drawString(message, messageX+1, messageY+1);
        g2.setColor(Color.RED);
        g2.drawString(message, messageX, messageY);
        messageY += 30;
        if(counter > 29){
            messageY = screenY - 25;
        }
    }
    
    public void damagePlayer(int attack) {
        if(gp.player.invincible == false) {
                gp.player.life -= attack;
                gp.player.invincible = true;
        }
    }
}

    

