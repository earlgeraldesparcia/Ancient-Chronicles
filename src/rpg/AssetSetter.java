/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg;

import monster.MON_Boy;
import object.OBJ_BigRock;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import rpg.entity.NPC_OldMan;

/**
 *
 * @author earlg
 */
public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
         gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
        
        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 38 * gp.tileSize;
        gp.obj[2].worldY = 8 * gp.tileSize;
        
        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;
        
        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;
        
        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;
        
        gp.obj[5] = new OBJ_Chest();
        gp.obj[5].worldX = 10 * gp.tileSize;
        gp.obj[5].worldY = 7 * gp.tileSize;
        
        gp.obj[6] = new OBJ_Boots();
        gp.obj[6].worldX = 37 * gp.tileSize;
        gp.obj[6].worldY = 42 * gp.tileSize;
        
        gp.obj[7] = new OBJ_BigRock();
        gp.obj[7].worldX = 21 * gp.tileSize;
        gp.obj[7].worldY = 23 * gp.tileSize;
    }
    
    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
        
    }
    
    public void setMonster() {
        gp.monster[0] = new MON_Boy(gp);
        gp.monster[0].worldX = gp.tileSize*23;
        gp.monster[0].worldY = gp.tileSize*30;
    }
}
