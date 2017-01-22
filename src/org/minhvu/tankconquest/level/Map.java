package org.minhvu.tankconquest.level;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.minhvu.tankconquest.Game;
import org.minhvu.tankconquest.sprites.essentials.Sprite;

public class Map
{
	private int tilesize;
	private Dimension dimensions;

	private Point tile;
	
	public static enum MAPS
	{
		FOUR_CORNERS,
		ICE_AGE,
		FORGOTTEN_HERO,
		SQUAD_LIFE
	}
	
	private final int[][] fourcorners = 
	{
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 6, 0, 1, 2, 0, 1, 2, 3, 0, 0, 0, 0, 1, 2, 0, 0, 0, 3, 3, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 4, 0, 0, 3, 1, 2, 0, 3, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 0, 0, 0, 0 },
		{ 0, 6, 6, 0, 0, 0, 0, 0, 0, 1, 2, 1, 2, 0, 0, 0, 1, 2, 3, 0, 4, 0, 4 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 0, 6, 0, 6 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 1, 2, 3, 0, 0, 0, 1, 2, 1, 2, 1, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 3, 3, 3, 0, 0, 4, 1, 2, 1, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3 }
	};
	
	private final int[][] iceage = 
	{
		{ 0, 0, 0, 3, 0, 0, 0, 0, 1, 2, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3 },
		{ 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 4 },
		{ 0, 0, 0, 0, 0, 8, 0, 0, 4, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 8, 0, 6 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 3, 0, 0, 0, 0 },
		{ 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 2, 0, 0, 0 },
		{ 0, 3, 0, 3, 0, 0, 0, 0, 0, 7, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 7, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 1, 2, 0, 4, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0 }
	};
	
	private final int[][] forgottenhero = 
	{
		{ 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 3 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 2 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 6, 0 },
		{ 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 6, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 1, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0 },
		{ 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0 },
		{ 5, 4, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 5, 6, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 5, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 6, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, 3, 2, 0, 0, 0, 0 }
	};
	
	private int[][] map;
	
	private BufferedImage[][] imageemap;

	public Map(MAPS map)
	{
		tilesize = 84;
		
		dimensions = new Dimension(23, 13);
		
		tile = new Point();

		imageemap = new BufferedImage[dimensions.height][dimensions.width];
		
		switch (map)
		{
			case FOUR_CORNERS:
				this.map = fourcorners;
				break;
			case ICE_AGE:
				this.map = iceage;
				break;
			case FORGOTTEN_HERO:
				this.map = forgottenhero;
				break;
			default:
				break;
		}
		
		for (int i = 0; i < dimensions.height; ++i)
		{
			for (int j = 0; j < dimensions.width; ++j)
			{
				switch (this.map[i][j])
				{
					case 0:
						tile.x = 0;
						tile.y = 0;
						break;
					case 1:
						tile.x = 0;
						tile.y = 3;
						break;
					case 2:
						tile.x = 1;
						tile.y = 3;
						break;
					case 3:
						tile.x = 2;
						tile.y = 3;
						break;
					case 4:
						tile.x = 3;
						tile.y = 3;
						break;
					case 5:
						tile.x = 4;
						tile.y = 3;
						break;
					case 6:
						tile.x = 5;
						tile.y = 3;
						break;
					case 7:
						tile.x = 6;
						tile.y = 3;
						break;
					case 8:
						tile.x = 7;
						tile.y = 3;
						break;
					default:
						break;
				}

				imageemap[i][j] = Sprite.getSprite(tile.x, tile.y, tilesize);
			}
		}
	}

	public void paint(Graphics g2d)
	{
		for (int i = 0; i < dimensions.height; ++i)
		{
			for (int j = 0; j < dimensions.width; ++j)
			{
				g2d.drawImage(imageemap[i][j], j * tilesize, i * tilesize, Game.getInstance());
			}
		}
	}
	
	public int[][] getMap()
	{
		return map;
	}
}
