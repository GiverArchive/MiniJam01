package com.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.main.Game;
import com.main.Valores;

public class World {

	private Tile[] tiles;
	public static int WIDTH, HEIGHT;

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));

			int[] pixels = new int[map.getWidth() * map.getHeight()]; // Array que calcula qtos pixels tem na imagem;
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];

			for (int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy * map.getWidth())];

					if (pixelAtual == 0xFF99e550) {
						tiles[xx + (yy * map.getWidth())] = new FloorTile(xx * Valores.TILE_SIZE, yy * Valores.TILE_SIZE, Tile.TILE_FLOOR);
						// Floor
					} else if (pixelAtual == 0xFFe95ad8) {
						// Player
						Game.player.setX(xx * Valores.TILE_SIZE);
						Game.player.setY(yy * Valores.TILE_SIZE);
						tiles[xx + (yy * map.getWidth())] = new AirTile(xx * Valores.TILE_SIZE, yy * Valores.TILE_SIZE, Tile.TILE_SKY);
					} else if (pixelAtual == 0xFF5ab6e9) {
						// Background
						tiles[xx + (yy * map.getWidth())] = new AirTile(xx * Valores.TILE_SIZE, yy * Valores.TILE_SIZE, Tile.TILE_SKY);
					}

				}

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		int xStart = Camera.x / Valores.TILE_SIZE;
		int yStart = Camera.y / Valores.TILE_SIZE;

		int xFinal = xStart + (Game.WIDTH / Valores.TILE_SIZE);
		int yFinal = yStart + (Game.HEIGHT / Valores.TILE_SIZE);

		for (int xx = xStart; xx <= xFinal; xx++) {
			for (int yy = yStart; yy <= yFinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) 
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}

}
