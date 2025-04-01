/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import object.OBJ_Heart;
import object.OBJ_Key;

/**
 *
 * @author earlg
 */
public class UI {
    GamePanel gp;
    Graphics2D g2;
    KeyHandler keyH;
    Font arial_40, arial_80B;
    BufferedImage heart_full, heart_half, heart_blank;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public ArrayList<String> notifs = new ArrayList<>();
    public ArrayList<Integer> notifsCounter = new ArrayList<>();
    
    public int slotCol = 0;
    public int slotRow = 0;
    
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#00.00");
    
    public UI(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        
        //HUD object
        OBJ_Heart heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }   
    
    public void addMessage(String text){
        notifs.add(text);
        notifsCounter.add(0); 
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState){
            //Time
            playTime += (double)1/60;
            
            if(keyH.showPlayerStatus == true){
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
                
                drawStatusScreen();
            }
            else{
                //timer
                g2.setColor(Color.BLACK);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
                g2.drawString(dFormat.format(playTime), gp.tileSize*7 + 2, 65 + 2);
                g2.setColor(Color.YELLOW);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
                g2.drawString(dFormat.format(playTime), gp.tileSize*7, 65);

                //message
                if(messageOn == true){

                    g2.setFont(g2.getFont().deriveFont(30F));
                    g2.drawString(message, gp.tileSize/2, gp.tileSize * 5);
                    messageCounter++;

                    if(messageCounter > 120){
                        messageCounter = 0;
                        messageOn = false;
                    }
                }
                drawPlayerLife();
                
                drawNotifs();
            }
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
            drawPlayerLife();
        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if(gp.gameState == gp.escapeState) {
            drawEscapeState();
        }
        
        //GameOver
        if(gameFinished == true){
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            
            String text;
            int textLength;
            int x;
            int y;
            
            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);
            
            text = "Your Time is: "+ dFormat.format(playTime)+"!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*4);
            g2.drawString(text, x, y);
            
            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*2);
            g2.drawString(text, x, y);
            
            gp.gameThread = null;
        }
    }
    
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED!";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        
        g2.drawString(text, x, y);
    }
    public int getXForCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length/2;
        return x;
    }
    public int getXForAlignToRight(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    
    public void drawDialogueScreen() {
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        
        drawSubWindow(x, y, width, height);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height) {
        Color black = new Color(0,0,0,150);
        Color white = new Color(255,255,255);
        g2.setColor(black);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        g2.setColor(white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    
    public void drawTitleScreen(){
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
        
        //titlename(Ancient Chronicles)
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        String text = "Ancient Chronicles:";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*3;
        g2.setColor(Color.red);
        g2.drawString(text, x, y);
        
        //titlename(Runes of the Ancient)
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
        String text1 = "Runes of the Ancient";
        x = getXForCenteredText(text);
        y = gp.tileSize*4;
        g2.setColor(Color.white);
        g2.drawString(text1, x, y);
        
        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
        text = "New Game";
        x = getXForCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        text = "Load Game";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        text = "Quit";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
    
    public void drawPlayerLife() {
        int x = (gp.tileSize /2);
        int y = (gp.tileSize /2);
        int i = 0;
        
        //blank heart
        while(i < gp.player.maxLife /2 ){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }
        
        //reset
        x = (gp.tileSize /2);
        y = (gp.tileSize /2);
        i = 0;
        
        //draw current heart
        while(i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i<gp.player.life){
                g2.drawImage(heart_full, x, y , null);
                i++;
                x += gp.tileSize;
            }
        }
    }
    
    public void drawNotifs() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
        
        for(int i=0; i<notifs.size(); i++){
            if(notifs.get(i) != null) {
                g2.setColor(Color.BLACK);
                g2.drawString(notifs.get(i), messageX+2, messageY+2);
                g2.setColor(Color.YELLOW);
                g2.drawString(notifs.get(i), messageX, messageY);
                
                int counter = notifsCounter.get(i) + 1;
                notifsCounter.set(i, counter);
                messageY += 30;
                
                if(notifsCounter.get(i) > 180) {
                    notifs.remove(i);
                    notifsCounter.remove(i);
                }
            }
        }
    }
    
    public void drawEscapeState() {
        
    }
    
    public void drawStatusScreen() {
        //leftBox
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*7;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25f));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //names
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Money", textX, textY);

        //values
        int tailX = (frameX + frameWidth) - 30;
        //reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.life+" / "+gp.player.maxLife);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.exp+" / "+gp.player.maxExp);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.money);
        textX = getXForAlignToRight(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        
        drawInventory();
                
    }
    
    public void drawInventory() {
        //frame
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
        
        //slot
        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;
        int slotX = slotXStart;
        int slotY = slotYStart;
        
        //cursor
        int cursorX = slotXStart + (gp.tileSize * slotCol);
        int cursorY = slotYStart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        //draw cursor
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
    }
}
