/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import rpg.GamePanel;
import rpg.UtilityTool;

/**
 *
 * @author earlg
 */
public class WorldOneGround {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    
    public WorldOneGround(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap("/maps/World1_Ground.txt");
    }
    
    public void getTileImage(){
        
        //Ground Tiles
        tile[0] = new Tile();
        tile[0].image = setup("/tiles/ground/blank.png", gp.tileSize, gp.tileSize);
        tile[0].collision = false;
        tile[1] = new Tile();
        tile[1].image = setup("/tiles/ground/ground_pillar.png", gp.tileSize, gp.tileSize);
        tile[1].collision = false;
        tile[2] = new Tile();
        tile[2].image = setup("/tiles/ground/ground_bottom_wall.png", gp.tileSize, gp.tileSize);
        tile[2].collision = false;
        tile[3] = new Tile();
        tile[3].image = setup("/tiles/ground/normal_grass.png", gp.tileSize, gp.tileSize);
        tile[3].collision = false;
        tile[4] = new Tile();
        tile[4].image = setup("/tiles/ground/ground_pillar_left.png", gp.tileSize, gp.tileSize);
        tile[4].collision = false;
        tile[5] = new Tile();
        tile[5].image = setup("/tiles/ground/ground_pillar_right.png", gp.tileSize, gp.tileSize);
        tile[5].collision = false;
        
        
        
        
        
//            tile[4] = new Tile();
//            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
//            tile[4].collision = true;
    }
    
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split("\t");
                    
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            
        }
    }
    
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            
            worldCol++;
          
            if(worldCol == gp.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }
    
    public BufferedImage setup(String imagePath, int tileSizeX, int tileSizeY){
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        
        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
            scaledImage = uTool.scaleImage(scaledImage, tileSizeX, tileSizeY);
        }catch(IOException e){
            System.out.println("Ang setup naguba!");
        }
        return scaledImage;
    }
}
