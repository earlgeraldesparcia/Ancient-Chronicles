/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.entity.Monsters;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import rpg.GamePanel;
import rpg.entity.Entity;
import rpg.entity.MonsterAttack.MonsterWeapon;
import rpg.entity.Skills.FirstSkill;

/**
 *
 * @author earlg
 */
public class MON_BlueBoy extends Entity{
    private int previousLife;
    
    public MON_BlueBoy (GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = 2;
        direction = "down";
        name = "Boy";
        speed = 1;
        exp = 1;
        maxLife = 20;
        previousLife = maxLife;
        life = maxLife;
        attack = 1;
        defense = 2;
        
        solidArea.x = 15;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 40;
        solidArea.height = 40;
        
        firstSkill = new MonsterWeapon(gp);
        
        getBoyImage();
    }
    
    public void getBoyImage() {
          up1 = setup("/monsterImage/monster1.png", gp.tileSize, gp.tileSize);
          up2 = setup("/monsterImage/monster2.png", gp.tileSize, gp.tileSize);
          down1 = setup("/monsterImage/monster1.png", gp.tileSize, gp.tileSize);
          down2 = setup("/monsterImage/monster2.png", gp.tileSize, gp.tileSize);
          left1 = setup("/monsterImage/monster1.png", gp.tileSize, gp.tileSize);
          left2 = setup("/monsterImage/monster2.png", gp.tileSize, gp.tileSize);
          right1 = setup("/monsterImage/monster1.png", gp.tileSize, gp.tileSize);
          right2 = setup("/monsterImage/monster2.png", gp.tileSize, gp.tileSize);
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
//            System.out.println(actionLockCounter++);
            actionLockCounter = 0; 
        }
        int i = new Random().nextInt(100) + 1;
        if(i > 60 && firstSkillCooldown == 120 && firstSkill.alive == false){
            System.out.println(this.name);
            firstSkill.set(worldX - gp.tileSize/2, worldY, direction, true, this);
            gp.monsterAttack.add(firstSkill);
            firstSkillCooldown = 0;
        }
        if(firstSkillCooldown < 120) {
            firstSkillCooldown++;
        }
    }
    
    @Override
    public void updateTwoImages(){
        setAction();
        
        collisionOn = false;
        gp.cChecker.checkTileWorld1(this);
        gp.cChecker.checkObject(this, false);
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
                    spriteNum=1;
                }
                
                spriteCounter = 0;
            }
            
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter > 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }    
    }
    
    @Override
    public void drawTwoImages(Graphics2D g2){
        image = null;
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
                
                g2.drawImage(image, screenX, screenY, null);
                
                //monster hp bar
                if(type == 2 && hpBarOn == true){
                    double oneScale = (double)gp.tileSize / maxLife;
                    double hpBarValue = oneScale*life;
                    
                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(screenX, screenY-16, gp.tileSize+2, 12);
                    g2.setColor(new Color(255,0,30));
                    g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
                    
                    hpBarCounter++;
                    
                    if(hpBarCounter > 600) {
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }
                
                if(invincible == true){
                    hpBarOn = true;
                    hpBarCounter = 0;
//                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
                }
                
                if(isDamaged == true) {
                    displayDamageCounter++;
                    int totalDamage = previousLife - life;
                    
                    displayDamage(g2, ""+totalDamage,screenX,screenY,displayDamageCounter);
                    if(displayDamageCounter > 30){
                        displayDamageCounter = 0;
                        previousLife = life;
                        isDamaged = false;
                    }
                }
                
                if(dying == true) {
                    dyingAnimation(g2);
                }
                g2.drawImage(image, screenX, screenY, null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                
        }
    }
    
    @Override
    public void displayDamage(Graphics2D g2, String message, int screenX, int screenY, int counter) {
        int messageX = screenX + 17;
        int messageY = screenY - 25;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
        
        g2.setColor(Color.BLACK);
        g2.drawString(message, messageX+1, messageY+1);
        g2.setColor(Color.RED);
        g2.drawString(message, messageX, messageY);
        messageY += 30;
        if(counter > 29){
            messageY = screenY - 25;
        }
    }
}
