/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import rpg.GamePanel;

/**
 *
 * @author earlg
 */
public class OBJ_Key extends SuperObject{
    public OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Key";
        
        image = setup("/objects/key.png", gp.tileSize, gp.tileSize);
        
    }
}
