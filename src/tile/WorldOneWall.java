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
public class WorldOneWall {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    
    public WorldOneWall(GamePanel gp){
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap("/maps/World1_Wall.txt");
    }
    
    public void getTileImage(){
        
        tile[0] = new Tile();
        tile[0].image = setup("/tiles/walls/blank.png", gp.tileSize, gp.tileSize);
        tile[0].collision = false;
        tile[1] = new Tile();
        tile[1].image = setup("/tiles/walls/ground.png", gp.tileSize, gp.tileSize);
        tile[1].collision = true;
        tile[2] = new Tile();
        tile[2].image = setup("/tiles/walls/wall_up.png", gp.tileSize, gp.tileSize);
        tile[2].collision = true;
        tile[3] = new Tile();
        tile[3].image = setup("/tiles/walls/wall_down_1.png", gp.tileSize, gp.tileSize);
        tile[3].collision = true;
        tile[4] = new Tile();
        tile[4].image = setup("/tiles/walls/wall_left.png", gp.tileSize, gp.tileSize);
        tile[4].collision = true;
        tile[5] = new Tile();
        tile[5].image = setup("/tiles/walls/wall_down_2.png", gp.tileSize, gp.tileSize);
        tile[5].collision = true;
        tile[6] = new Tile();
        tile[6].image = setup("/tiles/walls/blank.png", gp.tileSize, gp.tileSize);
        tile[6].collision = false;
        tile[7] = new Tile();
        tile[7].image = setup("/tiles/walls/wall_right.png", gp.tileSize, gp.tileSize);
        tile[7].collision = true;
        tile[8] = new Tile();
        tile[8].image = setup("/tiles/walls/pillar_bottom_left_1.png", gp.tileSize, gp.tileSize);
        tile[8].collision = true;
        tile[9] = new Tile();
        tile[9].image = setup("/tiles/walls/pillar_bottom_left_2.png", gp.tileSize, gp.tileSize);
        tile[9].collision = true;
        tile[10] = new Tile();
        tile[10].image = setup("/tiles/walls/top_right.png", gp.tileSize, gp.tileSize);
        tile[10].collision = true;
        tile[11] = new Tile();
        tile[11].image = setup("/tiles/walls/pillar_bottom_left_2.png", gp.tileSize, gp.tileSize);
        tile[11].collision = true;
        tile[12] = new Tile();
        tile[12].image = setup("/tiles/walls/bottom_left.png", gp.tileSize, gp.tileSize);
        tile[12].collision = true;
        tile[13] = new Tile();
        tile[13].image = setup("/tiles/walls/pillar_bottom_right_1.png", gp.tileSize, gp.tileSize);
        tile[13].collision = true;
        tile[14] = new Tile();
        tile[14].image = setup("/tiles/walls/top_left.png", gp.tileSize, gp.tileSize);
        tile[14].collision = true;
        tile[15] = new Tile();
        tile[15].image = setup("/tiles/walls/pillar_bottom_right_2.png", gp.tileSize, gp.tileSize);
        tile[15].collision = true;
        tile[16] = new Tile();
        tile[16].image = setup("/tiles/walls/11.png", gp.tileSize, gp.tileSize);
        tile[16].collision = true;
        tile[17] = new Tile();
        tile[17].image = setup("/tiles/walls/12.png", gp.tileSize, gp.tileSize);
        tile[17].collision = true;
        tile[18] = new Tile();
        tile[18].image = setup("/tiles/walls/13.png", gp.tileSize, gp.tileSize);
        tile[18].collision = true;
        tile[19] = new Tile();
        tile[19].image = setup("/tiles/walls/14.png", gp.tileSize, gp.tileSize);
        tile[19].collision = true;
        tile[20] = new Tile();
        tile[20].image = setup("/tiles/walls/15.png", gp.tileSize, gp.tileSize);
        tile[20].collision = true;
        tile[21] = new Tile();
        tile[21].image = setup("/tiles/walls/21.png", gp.tileSize, gp.tileSize);
        tile[21].collision = true;
        tile[22] = new Tile();
        tile[22].image = setup("/tiles/walls/22.png", gp.tileSize, gp.tileSize);
        tile[22].collision = true;
        tile[23] = new Tile();
        tile[23].image = setup("/tiles/walls/23.png", gp.tileSize, gp.tileSize);
        tile[23].collision = true;
        tile[24] = new Tile();
        tile[24].image = setup("/tiles/walls/24.png", gp.tileSize, gp.tileSize);
        tile[24].collision = true;
        tile[25] = new Tile();
        tile[25].image = setup("/tiles/walls/25.png", gp.tileSize, gp.tileSize);
        tile[25].collision = true;
        tile[26] = new Tile();
        tile[26].image = setup("/tiles/walls/31.png", gp.tileSize, gp.tileSize);
        tile[26].collision = true;
        tile[27] = new Tile();
        tile[27].image = setup("/tiles/walls/32.png", gp.tileSize, gp.tileSize);
        tile[27].collision = true;
        tile[28] = new Tile();
        tile[28].image = setup("/tiles/walls/33.png", gp.tileSize, gp.tileSize);
        tile[28].collision = true;
        tile[29] = new Tile();
        tile[29].image = setup("/tiles/walls/34.png", gp.tileSize, gp.tileSize);
        tile[29].collision = true;
        tile[30] = new Tile();
        tile[30].image = setup("/tiles/walls/35.png", gp.tileSize, gp.tileSize);
        tile[30].collision = true;
        tile[31] = new Tile();
        tile[31].image = setup("/tiles/walls/41.png", gp.tileSize, gp.tileSize);
        tile[31].collision = true;
        tile[32] = new Tile();
        tile[32].image = setup("/tiles/walls/42.png", gp.tileSize, gp.tileSize);
        tile[32].collision = true;
        tile[33] = new Tile();
        tile[33].image = setup("/tiles/walls/43.png", gp.tileSize, gp.tileSize);
        tile[33].collision = true;
        tile[34] = new Tile();
        tile[34].image = setup("/tiles/walls/44.png", gp.tileSize, gp.tileSize);
        tile[34].collision = true;
        tile[35] = new Tile();
        tile[35].image = setup("/tiles/walls/45.png", gp.tileSize, gp.tileSize);
        tile[35].collision = true;
        tile[36] = new Tile();
        tile[36].image = setup("/tiles/walls/51.png", gp.tileSize, gp.tileSize);
        tile[36].collision = false;
        tile[37] = new Tile();
        tile[37].image = setup("/tiles/walls/52.png", gp.tileSize, gp.tileSize);
        tile[37].collision = false;
        tile[38] = new Tile();
        tile[38].image = setup("/tiles/walls/53.png", gp.tileSize, gp.tileSize);
        tile[38].collision = false;
        tile[39] = new Tile();
        tile[39].image = setup("/tiles/walls/54.png", gp.tileSize, gp.tileSize);
        tile[39].collision = false;
        tile[40] = new Tile();
        tile[40].image = setup("/tiles/walls/55.png", gp.tileSize, gp.tileSize);
        tile[40].collision = false;
        tile[41] = new Tile();
        tile[41].image = setup("/tiles/walls/bottom_right.png", gp.tileSize, gp.tileSize);
        tile[41].collision = true;
        tile[42] = new Tile();
        tile[42].image = setup("/tiles/walls/pillar_top_left.png", gp.tileSize, gp.tileSize);
        tile[42].collision = true;
        tile[43] = new Tile();
        tile[43].image = setup("/tiles/walls/pillar_top_right.png", gp.tileSize, gp.tileSize);
        tile[43].collision = true;
        tile[44] = new Tile();
        tile[44].image = setup("/tiles/walls/pillar_top_left.png", gp.tileSize, gp.tileSize);
        tile[44].collision = true;
        tile[45] = new Tile();
        tile[45].image = setup("/tiles/walls/wall_left_wall.png", gp.tileSize, gp.tileSize);
        tile[45].collision = true;
        tile[46] = new Tile();
        tile[46].image = setup("/tiles/walls/wall_right_wall.png", gp.tileSize, gp.tileSize);
        tile[46].collision = true;
        tile[47] = new Tile();
        tile[47].image = setup("/tiles/walls/pillar_bottom_left_2_wall.png", gp.tileSize, gp.tileSize);
        tile[47].collision = true;
        tile[48] = new Tile();
        tile[48].image = setup("/tiles/walls/pillar_bottom_left_custom.png", gp.tileSize, gp.tileSize);
        tile[48].collision = true;
        
        
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
