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
}
