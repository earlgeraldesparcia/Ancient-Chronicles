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
public class OBJ_Shield extends SuperObject{
    public OBJ_Shield(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        name = "Shield";
        image = setup("/objects/shield_wood.png", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
}
