/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author earlg
 */
public class KeyHandler implements KeyListener{
    GamePanel gp;
    
    public boolean upPressed,downPressed, leftPressed, rightPressed, enterPressed;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        //playState
        if(gp.gameState == gp.playState){
            if(code==KeyEvent.VK_W){
                upPressed = true;
            }
            if(code==KeyEvent.VK_S){
                downPressed = true;
            }
            if(code==KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code==KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code==KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if(code==KeyEvent.VK_ENTER){
                enterPressed = true;
            }
        }
        
        //pauseState
        else if(gp.gameState == gp.pauseState){
            if(code==KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
        }
        
        //dialogueState
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            upPressed = false;
        }
        if(code==KeyEvent.VK_S){
            downPressed = false;
        }
        if(code==KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code==KeyEvent.VK_D){
            rightPressed = false;
        }
    }
    
}
