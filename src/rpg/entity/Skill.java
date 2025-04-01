/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.entity;

import rpg.GamePanel;

/**
 *
 * @author earlg
 */
public class Skill extends Entity{
    public Entity user;
    
    public Skill(GamePanel gp) {
        super(gp);
        this.gp = gp;
    }
    
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX + gp.tileSize/2;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
        
    }
    
    @Override
    public void updateFirstSkillImages() {
        if(user == gp.player){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex, attack);
                alive = false;
            }
        }
        
        if(user == gp.player) {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(gp.player.invincible == false && contactPlayer == true) {
                damagePlayer(attack);
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
    
}
