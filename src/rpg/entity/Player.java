/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg.entity;

import rpg.entity.Skills.FirstSkill;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import rpg.GamePanel;
import rpg.KeyHandler;
import rpg.UtilityTool;

/**
 *
 * @author earlg
 */
public class Player extends Entity{
    KeyHandler keyH;
    Graphics2D g2;
    
    private int previousLife;
    int scale;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    
    int baseAttack = 5;
    int baseDefense = 5;
    
    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp);
        
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 38;
        solidArea.y = 45;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 18;
        solidArea.height = 18;
        
        attackArea.width = 36;
        attackArea.height = 36;
        
        setDefaultValues();
        getPlayerImage();
        getAttackImage();
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        scale = 5;
        
        //status
        name = "Player";
        maxLife = 10;
        life = maxLife;
        previousLife = maxLife;
        exp = 0;
        maxExp = 10;
        money = 0;
        attack = getAttack();
        defense = getDefense();
        //skills
        firstSkill = new FirstSkill(gp);

        //level
        level = 1;
    }
    
    public int getAttack() {
        return baseAttack + attack;
        //chuchu pa ni kay need pa tag pickable items
    }
    public int getDefense() {
        return baseDefense + defense;
        //plus item attributes
    }
    
    @Override
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
    
    public void getPlayerImage(){
        up1 = setup("/res/player/Vampire/Vampire_back1.png", gp.tileSize*2, gp.tileSize*2);
        up2 = setup("/res/player/Vampire/Vampire_back2.png", gp.tileSize*2, gp.tileSize*2);
        up3 = setup("/res/player/Vampire/Vampire_back3.png", gp.tileSize*2, gp.tileSize*2);
        up4 = setup("/res/player/Vampire/Vampire_back4.png", gp.tileSize*2, gp.tileSize*2);
        up5 = setup("/res/player/Vampire/Vampire_back5.png", gp.tileSize*2, gp.tileSize*2);
        up6 = setup("/res/player/Vampire/Vampire_back6.png", gp.tileSize*2, gp.tileSize*2);
        up7 = setup("/res/player/Vampire/Vampire_back7.png", gp.tileSize*2, gp.tileSize*2);
        up8 = setup("/res/player/Vampire/Vampire_back8.png", gp.tileSize*2, gp.tileSize*2);
        down1 = setup("/res/player/Vampire/Vampire_front1.png", gp.tileSize*2, gp.tileSize*2);
        down2 = setup("/res/player/Vampire/Vampire_front2.png", gp.tileSize*2, gp.tileSize*2);
        down3 = setup("/res/player/Vampire/Vampire_front3.png", gp.tileSize*2, gp.tileSize*2);
        down4 = setup("/res/player/Vampire/Vampire_front4.png", gp.tileSize*2, gp.tileSize*2);
        down5 = setup("/res/player/Vampire/Vampire_front5.png", gp.tileSize*2, gp.tileSize*2);
        down6 = setup("/res/player/Vampire/Vampire_front6.png", gp.tileSize*2, gp.tileSize*2);
        down7 = setup("/res/player/Vampire/Vampire_front7.png", gp.tileSize*2, gp.tileSize*2);
        down8 = setup("/res/player/Vampire/Vampire_front8.png", gp.tileSize*2, gp.tileSize*2);
        left1 = setup("/res/player/Vampire/Vampire_left1.png", gp.tileSize*2, gp.tileSize*2);
        left2 = setup("/res/player/Vampire/Vampire_left2.png", gp.tileSize*2, gp.tileSize*2);
        left3 = setup("/res/player/Vampire/Vampire_left3.png", gp.tileSize*2, gp.tileSize*2);
        left4 = setup("/res/player/Vampire/Vampire_left4.png", gp.tileSize*2, gp.tileSize*2);
        left5 = setup("/res/player/Vampire/Vampire_left5.png", gp.tileSize*2, gp.tileSize*2);
        left6 = setup("/res/player/Vampire/Vampire_left6.png", gp.tileSize*2, gp.tileSize*2);
        left7 = setup("/res/player/Vampire/Vampire_left7.png", gp.tileSize*2, gp.tileSize*2);
        left8 = setup("/res/player/Vampire/Vampire_left8.png", gp.tileSize*2, gp.tileSize*2);
        right1 = setup("/res/player/Vampire/Vampire_right1.png", gp.tileSize*2, gp.tileSize*2);
        right2 = setup("/res/player/Vampire/Vampire_right2.png", gp.tileSize*2, gp.tileSize*2);
        right3 = setup("/res/player/Vampire/Vampire_right3.png", gp.tileSize*2, gp.tileSize*2);
        right4 = setup("/res/player/Vampire/Vampire_right4.png", gp.tileSize*2, gp.tileSize*2);
        right5 = setup("/res/player/Vampire/Vampire_right5.png", gp.tileSize*2, gp.tileSize*2);
        right6 = setup("/res/player/Vampire/Vampire_right6.png", gp.tileSize*2, gp.tileSize*2);
        right7 = setup("/res/player/Vampire/Vampire_right7.png", gp.tileSize*2, gp.tileSize*2);
        right8 = setup("/res/player/Vampire/Vampire_right8.png", gp.tileSize*2, gp.tileSize*2);
    }
    public void getAttackImage() {
        aUp1 = setup("/res/player/Vampire/attack/Vampire_Attack_back1.png", gp.tileSize*2, gp.tileSize*2);
        aUp2 = setup("/res/player/Vampire/attack/Vampire_Attack_back2.png", gp.tileSize*2, gp.tileSize*2);
        aUp3 = setup("/res/player/Vampire/attack/Vampire_Attack_back3.png", gp.tileSize*2, gp.tileSize*2);
        aUp4 = setup("/res/player/Vampire/attack/Vampire_Attack_back4.png", gp.tileSize*2, gp.tileSize*2);
        aUp5 = setup("/res/player/Vampire/attack/Vampire_Attack_back5.png", gp.tileSize*2, gp.tileSize*2);
        aUp6 = setup("/res/player/Vampire/attack/Vampire_Attack_back6.png", gp.tileSize*2, gp.tileSize*2);
        aUp7 = setup("/res/player/Vampire/attack/Vampire_Attack_back7.png", gp.tileSize*2, gp.tileSize*2);
        aUp8 = setup("/res/player/Vampire/attack/Vampire_Attack_back8.png", gp.tileSize*2, gp.tileSize*2);
        aDown1 = setup("/res/player/Vampire/attack/Vampire_Attack_front1.png", gp.tileSize*2, gp.tileSize*2);
        aDown2 = setup("/res/player/Vampire/attack/Vampire_Attack_front2.png", gp.tileSize*2, gp.tileSize*2);
        aDown3 = setup("/res/player/Vampire/attack/Vampire_Attack_front3.png", gp.tileSize*2, gp.tileSize*2);
        aDown4 = setup("/res/player/Vampire/attack/Vampire_Attack_front4.png", gp.tileSize*2, gp.tileSize*2);
        aDown5 = setup("/res/player/Vampire/attack/Vampire_Attack_front5.png", gp.tileSize*2, gp.tileSize*2);
        aDown6 = setup("/res/player/Vampire/attack/Vampire_Attack_front6.png", gp.tileSize*2, gp.tileSize*2);
        aDown7 = setup("/res/player/Vampire/attack/Vampire_Attack_front7.png", gp.tileSize*2, gp.tileSize*2);
        aDown8 = setup("/res/player/Vampire/attack/Vampire_Attack_front8.png", gp.tileSize*2, gp.tileSize*2);
        aLeft1 = setup("/res/player/Vampire/attack/Vampire_Attack_left1.png", gp.tileSize*2, gp.tileSize*2);
        aLeft2 = setup("/res/player/Vampire/attack/Vampire_Attack_left2.png", gp.tileSize*2, gp.tileSize*2);
        aLeft3 = setup("/res/player/Vampire/attack/Vampire_Attack_left3.png", gp.tileSize*2, gp.tileSize*2);
        aLeft4 = setup("/res/player/Vampire/attack/Vampire_Attack_left4.png", gp.tileSize*2, gp.tileSize*2);
        aLeft5 = setup("/res/player/Vampire/attack/Vampire_Attack_left5.png", gp.tileSize*2, gp.tileSize*2);
        aLeft6 = setup("/res/player/Vampire/attack/Vampire_Attack_left6.png", gp.tileSize*2, gp.tileSize*2);
        aLeft7 = setup("/res/player/Vampire/attack/Vampire_Attack_left7.png", gp.tileSize*2, gp.tileSize*2);
        aLeft8 = setup("/res/player/Vampire/attack/Vampire_Attack_left8.png", gp.tileSize*2, gp.tileSize*2);
        aRight1 = setup("/res/player/Vampire/attack/Vampire_Attack_right1.png", gp.tileSize*2, gp.tileSize*2);
        aRight2 = setup("/res/player/Vampire/attack/Vampire_Attack_right2.png", gp.tileSize*2, gp.tileSize*2);
        aRight3 = setup("/res/player/Vampire/attack/Vampire_Attack_right3.png", gp.tileSize*2, gp.tileSize*2);
        aRight4 = setup("/res/player/Vampire/attack/Vampire_Attack_right4.png", gp.tileSize*2, gp.tileSize*2);
        aRight5 = setup("/res/player/Vampire/attack/Vampire_Attack_right5.png", gp.tileSize*2, gp.tileSize*2);
        aRight6 = setup("/res/player/Vampire/attack/Vampire_Attack_right6.png", gp.tileSize*2, gp.tileSize*2);
        aRight7 = setup("/res/player/Vampire/attack/Vampire_Attack_right7.png", gp.tileSize*2, gp.tileSize*2);
        aRight8 = setup("/res/player/Vampire/attack/Vampire_Attack_right8.png", gp.tileSize*2, gp.tileSize*2);
    }
    
    
    @Override
    public void update(){
        if(keyH.basicAttack == true){
            attacking = true;
            attacking();
        }
        else if(keyH.upPressed==true ||
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
            interactNPC(npcIndex);
            
            //check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interactMonster(monsterIndex);
            
            //check event
            gp.eHandler.checkEvent();
            
            
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
        else{
            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            
            //Check NPC Collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            
            //check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interactMonster(monsterIndex);
            
            //check event
            gp.eHandler.checkEvent();
            
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
        
        if(keyH.firstSkill == true && firstSkill.alive == false && firstSkillCooldown == 120){
            
            //set default coordinates, direction and user
            firstSkill.set(worldX, worldY, direction, true, this);
            gp.skills[0] = firstSkill;
            
            firstSkillCooldown = 0;
        }
        
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        
        if(firstSkillCooldown < 120) {
            firstSkillCooldown++;
        }
        
        
    }
    
    public void attacking() {
        spriteCounter++;
        int i = 3;

        if(spriteCounter <= i){
            spriteNum = 1;
        }
        else if(spriteCounter > i && spriteCounter <= i*2){
            spriteNum = 2;
        }
        else if(spriteCounter > i*2 && spriteCounter <= i*3){
            spriteNum = 3;
        }
        else if(spriteCounter > i*3 && spriteCounter <= i*4){
            spriteNum = 4;
        }
        else if(spriteCounter > i*4 && spriteCounter <= i*5){
            spriteNum = 5;
            
            //save
            int currentWorldX = worldX;
            int currentWOrldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            
            //adjust player x y
            switch(direction) {
                case "up": worldY -= attackArea.height - 3; break;
                case "down": worldY += attackArea.height + 3; break;
                case "left": worldX -= attackArea.width - 3; break;
                case "right": worldX += attackArea.width + 3; break;
            }
            
            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            
            //check monster collision with update x, y, solidArea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);
            
            worldX = currentWorldX;
            worldY = currentWOrldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        else if(spriteCounter > i*5 && spriteCounter <= i*6){
            spriteNum = 6;
        }
        else if(spriteCounter > i*6 && spriteCounter <= i*7){
            spriteNum = 7;
        }
        else if(spriteCounter > i*7 && spriteCounter <= i*8){
            spriteNum = 8;
        }
        else if(spriteCounter > i*8) {
            spriteNum = 1; 
            spriteCounter = 0;
            attacking = false;
            keyH.basicAttack = false;
        }
    }
    
    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            
            switch (objectName){
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.addMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        gp.ui.addMessage("You opened the door!");
                        hasKey--;
                    }else{
                        gp.ui.addMessage("You need a key!");
                    }
                    break;
                case "Boots":
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.addMessage("Speed Up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    break;
            }
        }
    }
    
    public void interactNPC(int i){
        if(i != 999){
            if(invincible == false){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
                invincible = true;
            }
            
        }
    }
    
    public void interactMonster(int i){
        if(i != 999) {
            if(invincible == false && gp.monster[i].dying == false){
                life -= gp.monster[i].attack;
                invincible = true;
                isDamaged = true;
            }
        }
    }
    
    public void damageMonster(int i, int attack) {
        if(i != 999){
            if(gp.monster[i].invincible == false) {
                int damage = attack - gp.monster[i].defense;
                if(damage < 0 ){
                    damage = 0;
                }
                
                gp.monster[i].isDamaged = true;
                gp.monster[i].life -= damage;
                gp.monster[i].invincible = true;
                gp.monster[i].monsterDamageReaction();
                
                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
                    gp.ui.addMessage("Killed "+gp.monster[i].name+"!");
                    exp += gp.monster[i].exp;
                    gp.ui.addMessage("Gained "+exp+" exp.");
                }
            }
        }
    }
    
    public void displayDamage(Graphics2D g2, String message, int screenX, int screenY, int counter) {
        int messageX = screenX + 40;
        int messageY = screenY - 5;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
        
        g2.setColor(Color.BLACK);
        g2.drawString(message, messageX+1, messageY+1);
        g2.setColor(Color.RED);
        g2.drawString(message, messageX, messageY);
        messageY += 30;
        if(counter > 29){
            messageY = screenY - 5;
        }
    }
    
    @Override
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        
        switch(direction){
            case "up":
                if(attacking == false){
                    if(spriteNum==1){image=up1;}
                    if(spriteNum==2){image=up2;}
                    if(spriteNum==3){image=up3;}
                    if(spriteNum==4){image=up4;}
                    if(spriteNum==5){image=up5;}
                    if(spriteNum==6){image=up6;}
                    if(spriteNum==7){image=up7;}
                    if(spriteNum==8){image=up8;}
                }
                if(attacking == true){
                    if(spriteNum==1){image=aUp1;}
                    if(spriteNum==2){image=aUp2;}
                    if(spriteNum==3){image=aUp3;}
                    if(spriteNum==4){image=aUp4;}
                    if(spriteNum==5){image=aUp5;}
                    if(spriteNum==6){image=aUp6;}
                    if(spriteNum==7){image=aUp7;}
                    if(spriteNum==8){image=aUp8;}
                }
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum==1){image=down1;}
                    if(spriteNum==2){image=down2;}
                    if(spriteNum==3){image=down3;}
                    if(spriteNum==4){image=down4;}
                    if(spriteNum==5){image=down5;}
                    if(spriteNum==6){image=down6;}
                    if(spriteNum==7){image=down7;}
                    if(spriteNum==8){image=down8;}
                }
                if(attacking == true){
                    if(spriteNum==1){image=aDown1;}
                    if(spriteNum==2){image=aDown2;}
                    if(spriteNum==3){image=aDown3;}
                    if(spriteNum==4){image=aDown4;}
                    if(spriteNum==5){image=aDown5;}
                    if(spriteNum==6){image=aDown6;}
                    if(spriteNum==7){image=aDown7;}
                    if(spriteNum==8){image=aDown8;}
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum==1){image=left1;}
                    if(spriteNum==2){image=left2;}
                    if(spriteNum==3){image=left3;}
                    if(spriteNum==4){image=left4;}
                    if(spriteNum==5){image=left5;}
                    if(spriteNum==6){image=left6;}
                    if(spriteNum==7){image=left7;}
                    if(spriteNum==8){image=left8;}
                }
                if(attacking == true) {
                    if(spriteNum==1){image=aLeft1;}
                    if(spriteNum==2){image=aLeft2;}
                    if(spriteNum==3){image=aLeft3;}
                    if(spriteNum==4){image=aLeft4;}
                    if(spriteNum==5){image=aLeft5;}
                    if(spriteNum==6){image=aLeft6;}
                    if(spriteNum==7){image=aLeft7;}
                    if(spriteNum==8){image=aLeft8;}
                }
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum==1){image=right1;}
                    if(spriteNum==2){image=right2;}
                    if(spriteNum==3){image=right3;}
                    if(spriteNum==4){image=right4;}
                    if(spriteNum==5){image=right5;}
                    if(spriteNum==6){image=right6;}
                    if(spriteNum==7){image=right7;}
                    if(spriteNum==8){image=right8;}
                }
                if(attacking == true) {
                    if(spriteNum==1){image=aRight1;}
                    if(spriteNum==2){image=aRight2;}
                    if(spriteNum==3){image=aRight3;}
                    if(spriteNum==4){image=aRight4;}
                    if(spriteNum==5){image=aRight5;}
                    if(spriteNum==6){image=aRight6;}
                    if(spriteNum==7){image=aRight7;}
                    if(spriteNum==8){image=aRight8;}
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
        
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
        
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        g2.drawImage(image, screenX, screenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    
}
