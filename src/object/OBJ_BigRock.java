/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author earlg
 */
public class OBJ_BigRock extends SuperObject{
    public OBJ_BigRock(){
        name = "BigRock";
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Tree_idol_human.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
