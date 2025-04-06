/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import rpg.GamePanel;

/**
 *
 * @author earlg
 */
public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp){
        super(gp);
        
        direction = "down";
        speed = 1;
        
        getOldManImage();
        setDialogue();
    }
    
    public void getOldManImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void setDialogue(){
        dialogues[0] = "Hello, lad!";
        dialogues[1] = "Good Luck on your journey\nmy Child!";
        dialogues[2] = "Is there something\nI can help you with?";
        dialogues[3] = "Woah! Watch where you going\nmy boy.";
    }
    
    @Override
    public void setAction(){
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
    
    @Override
    public void speak(){
        super.speak();
    }
    
    @Override
    public void update(){
        setAction();
        
        collisionOn = false;
        gp.cChecker.checkTileWorld1(this);
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
}
