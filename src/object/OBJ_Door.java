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
public class OBJ_Door extends SuperObject{
    public OBJ_Door(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Door";
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
