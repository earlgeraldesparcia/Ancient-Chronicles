/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import object.SuperObject;
import rpg.entity.Entity;
import rpg.entity.Monsters.MON_BlueBoy;
import rpg.entity.Player;
import rpg.entity.Skill;
import tile.TileManager;
import tile.WorldOneGround;
import tile.WorldOneWall;

public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTileSize = 16;
    final int scale = 4;
    
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    int FPS = 60;
    
    TileManager tile = new TileManager(this);
    WorldOneGround world1Ground = new WorldOneGround(this);
    WorldOneWall world1Wall = new WorldOneWall(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this, keyH);
    public EventHandler eHandler = new EventHandler(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[] = new Entity[10];
    public MON_BlueBoy monster[] = new MON_BlueBoy[20];
    public Skill skills[] = new Skill[10];
    public ArrayList<Skill> monsterAttack = new ArrayList<>();
    
    
    //GameState
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int escapeState = -1;
    public final int titleState = 0;
    
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setObject();
        aSetter.setMonster();
        gameState = titleState;
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null){
            
            
            update();
            repaint();
            
            try {
                double remainingTime = nextDrawTime-System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if(remainingTime<0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void update() {
        
        
        
        if(gameState == playState){
            //Player
            player.update();
            
            //NPC
            for(int i=0; i<npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            
            //Monster
            for(int i=0; i<monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive == true && monster[i].dying == false){
                        monster[i].updateTwoImages();
                    }
                    if(monster[i].alive == false){
                        monster[i] = null;
                    }
                }
            }
            
            //firstSkill
            for(int i=0; i<skills.length; i++){
                if(skills[i] != null){
                    if(skills[i].alive == true){
                        skills[i].updateFirstSkillImages();
                    }
                    if(skills[i].alive == false){
                        skills[i] = null;
                    }
                }
            }
            //monsterAttack
            for(int i=0; i<monsterAttack.size(); i++){
                if(monsterAttack.get(i) != null) {
                    if(monsterAttack.get(i).alive == true) {
                        monsterAttack.get(i).updateFirstSkillImages();
                    }
                    if(monsterAttack.get(i).alive == false) {
                        monsterAttack.clear();
                    } 
                }
            }
            
            
            
        }
        if(gameState == pauseState){
            
        }
        
        
    }

    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        //Title Screen
        if(gameState == titleState){
            ui.draw(g2);
        }else{
            //TILE
            world1Ground.draw(g2);
            world1Wall.draw(g2);
//            tile.draw(g2);
            
            //Object
            for(int i=0; i<obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }
            
            //firstSkill
            for(int i=0; i<skills.length; i++){
                if(skills[i] != null){
                    if(skills[i].alive == true){
                        skills[i].drawTwoImages(g2);
                    }
                    if(skills[i].alive == false){
                        skills[i] = null;
                    }
                }
            }
            
            //Monster
            for(int i=0; i<monster.length; i++){
                if(monster[i] != null){
                    monster[i].drawTwoImages(g2);
                }
            }
            //monsterAttack
            for(int i=0; i<monsterAttack.size(); i++){
                if(monsterAttack.get(i) != null) {
                    if(monsterAttack.get(i).alive == true) {
                        monsterAttack.get(i).drawTwoImages(g2);
                    }
                    if(monsterAttack.get(i).alive == false) {
                        monsterAttack.clear();
                    }
                }
            }
            
            //NPC
            for(int i=0; i<npc.length; i++){
                if(npc[i] != null){
                    npc[i].draw(g2);
                }
            }
            
            //Player
            player.draw(g2);

            //UI
            ui.draw(g2);
        }
        
        
        g2.dispose();
    }
}
