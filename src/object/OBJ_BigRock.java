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
public class OBJ_BigRock extends SuperObject{
    public OBJ_BigRock(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "BigRock";
        
        image = setup("/objects/bigrock.png", gp.tileSize/10, gp.tileSize/10);
    }
}
