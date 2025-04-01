/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.entity.Skills;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import rpg.GamePanel;
import rpg.entity.Skill;
import rpg.entity.Skill;

/**
 *
 * @author earlg
 */
public class FirstSkill extends Skill{
    public FirstSkill(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "First Skill";
        speed = 7;
        maxLife = 100;
        life = maxLife;
        attack = 5;
        useCost = 1;
        alive = false;
        getFirstSkillImage();
    }
    
    public void getFirstSkillImage() {
        up1 = setup("/objects/firstskill/fireball_up_1.png", gp.tileSize, gp.tileSize);
        up2 = setup("/objects/firstskill/fireball_up_2.png", gp.tileSize, gp.tileSize);
        down1 = setup("/objects/firstskill/fireball_down_1.png", gp.tileSize, gp.tileSize);
        down2 = setup("/objects/firstskill/fireball_down_2.png", gp.tileSize, gp.tileSize);
        left1 = setup("/objects/firstskill/fireball_left_1.png", gp.tileSize, gp.tileSize);
        left2 = setup("/objects/firstskill/fireball_left_2.png", gp.tileSize, gp.tileSize);
        right1 = setup("/objects/firstskill/fireball_right_1.png", gp.tileSize, gp.tileSize);
        right2 = setup("/objects/firstskill/fireball_right_2.png", gp.tileSize, gp.tileSize);
    }
    
    @Override
    public void updateFirstSkillImages() {
        if(user == gp.player){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex, this.attack);
                alive = false;
            }
        }
        
        switch(direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }
        
        life--;
        if(life <= 0) {
            alive = false;
        }
        
        spriteCounter++;
            if(spriteCounter>8){
                if(spriteNum==1){
                    spriteNum=2;
                }
                else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter = 0;
            }
    }
    
    @Override
    public void drawTwoImages(Graphics2D g2){
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
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
                }
                if(dying == true) {
                    dyingAnimation(g2);
                }
                g2.drawImage(image, screenX, screenY, null);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
    }
}
