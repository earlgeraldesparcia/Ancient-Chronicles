/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.entity.MonsterAttack;

import java.awt.Graphics2D;
import rpg.GamePanel;
import rpg.entity.Skill;

/**
 *
 * @author earlg
 */
public class MonsterWeapon extends Skill{
    
    public MonsterWeapon(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "First Skill";
        speed = 7;
        maxLife = 100;
        life = maxLife;
        attack = 1;
        useCost = 1;
        alive = false;
        firstSkillCooldown = 120;
        
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
        
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(gp.player.invincible == false && contactPlayer) {
            damagePlayer(attack);
            alive = false;
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

        }
    }
}