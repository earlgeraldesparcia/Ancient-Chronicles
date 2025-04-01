/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import rpg.GamePanel;

/**
 *
 * @author earlg
 */
public class OBJ_Sword extends SuperObject{
    public OBJ_Sword (GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "Normal Sword";
        image = setup("/objects/sword_normal.png", gp.tileSize, gp.tileSize);
        attackValue = 1;
    }
}
