package MyDungeon;

import java.security.SecureRandom;


public class MyDungeon
{
	public static int levelWidth = 59;
	public static int levelHeight = 59;
	
	public static boolean[][] level = new boolean[levelWidth][levelHeight];
	
	public static Edge[] EdgeHeap1;
	public static Edge[] EdgeHeap2;
	public static Vertice[] VertHeap1 = new Vertice[5];
	
	public enum Direction
	{
		North, South, West, East, 
		NorthEast, NorthWest, SouthEast, SouthWest
	}
	
	public static class Vertice
	{
		static int width;
		static int height;
		
		int middleX;
		int middleY;
		int prevRoomDistance;
		
		public static void InitRoom(int MinWidth, int MinHeight, int MaxWidth, int MaxHeight) 
		{
			SecureRandom srandom = new SecureRandom();
			int newWidth = srandom.nextInt(MaxWidth+1);
			if(newWidth < MinWidth)
				newWidth = MinWidth;
			
			int newHeight = srandom.nextInt(MaxHeight+1);
			if(newHeight < MinHeight)
				newHeight = MinHeight;
			
			width = newWidth;
			height = newHeight;
		}

		
		Vertice(int MinWidth, int MinHeight, int MaxWidth, int MaxHeight) 
		{
			InitRoom(MinWidth, MinHeight, MaxWidth, MaxHeight);
		}
	}
	
	public static class Edge
	{
		public static void RandomizeDirection() 
		{
			SecureRandom rand = new SecureRandom();
			int index = rand.nextInt(8);
			switch(index) 
			{
				case 0:	{
					distCoord = Direction.North;
					break;
				}
				case 1:{
					distCoord = Direction.South;
					break;
				}
				case 2:{
					distCoord = Direction.West;
					break;
				}
				case 3:{
					distCoord = Direction.East;
					break;
				}
				case 4:	{
					distCoord = Direction.NorthEast;
					break;
				}
				case 5:{
					distCoord = Direction.NorthWest;
					break;
				}
				case 6:{
					distCoord = Direction.SouthEast;
					break;
				}
				case 7:{
					distCoord = Direction.SouthWest;
					break;
				}
			}
		}
		
		Vertice A;
		Vertice B;
		
		int distance;
		static Direction distCoord;
		
		Edge(Vertice A, Vertice B, int distance)
		{
			RandomizeDirection();
			this.distance = distance;
		}
	}

	public static void InitLevel() 
	{
		for(int y = 0; y < levelHeight; y++)
			for (int x = 0; x < levelWidth; x++)
				if( (y == 0 || x == 0) || (y == levelHeight-1 || x == levelWidth-1) )
					level[x][y] = true;
	}
	
	public static void PlaceStarterRoom() 
	{
		int middleX = (levelWidth/2);
		int middleY = (levelHeight/2);
		
		for(int y = middleY-2; y <= middleY+2; y++)
			for(int x = middleX-2; x <= middleX+2; x++)
				if(x == middleX-2 || y == middleY-2 || x == middleX+2 || y == middleY+2)
					level[x][y] = true;
	}
	
	public static void PlaceFirstRoom() 
	{
		SecureRandom srandom = new SecureRandom();
		Vertice NewRoom = new Vertice(5,5,6,7);
		
		NewRoom.prevRoomDistance = 6; 
		int distance = NewRoom.prevRoomDistance;
		int EndX;
		int EndY;
		
		//place corridor
		int i = srandom.nextInt(8);
		switch(1) 
		{
		//north
		case 0:{
			for(int y = levelHeight/2 -2 - distance; y < levelHeight/2 -1; y++)
				level[levelWidth/2][y] = true;
			EndY = levelHeight/2 -2;
			EndX = levelWidth/2;
			break;
		}
		case 1://south
			for(int y = levelHeight/2 + 2; y <= levelHeight/2 + 2 + distance; y++)
				level[levelWidth/2][y] = true;
			EndY = levelHeight/2 + 2 + distance;
			EndX = levelWidth/2;
			break;
		case 2: //west
			for(int x = levelWidth/2 -2 -distance; x < levelWidth/2 -1; x++)
				level[x][levelHeight/2] = true;
			break;
		case 3: //east
			for(int x = levelWidth/2 + 2; x <= levelWidth/2 + 2 + distance; x++)
				level[x][levelHeight/2] = true;
			break;
		case 4: //NorthEast
			for(int y = levelHeight/2 -2 - distance/2; y < levelHeight/2 -1; y++)
				level[levelWidth/2][y] = true;
			
			for(int x = levelWidth/2 + 1; x < levelWidth/2 + 1 + distance/2; x++)
				level[x][levelHeight/2 -2 - distance/2] = true;
			break;
		case 5: //NorthWest
			for(int y = levelHeight/2 -2 - distance/2; y < levelHeight/2 -1; y++)
				level[levelWidth/2][y] = true;
			
			for(int x = levelWidth/2 -distance/2; x < levelWidth/2; x++)
				level[x][levelHeight/2 -2 - distance/2] = true;
			break;
		case 6://SouthEast
			for(int y = levelHeight/2 + 2; y <= levelHeight/2 + 2 + distance/2; y++)
				level[levelWidth/2][y] = true;
			
			for(int x = levelWidth/2 + 1; x <= levelWidth/2  + distance/2; x++)
				level[x][levelHeight/2 + 2 + distance/2] = true;
			break;
		case 7://SouthWest
			for(int y = levelHeight/2 + 2; y <= levelHeight/2 + 2 + distance/2; y++)
				level[levelWidth/2][y] = true;
			
			for(int x = levelWidth/2 -distance/2; x < levelWidth/2; x++)
				level[x][levelHeight/2 + 2 + distance/2] = true;
			break;
		}
		
		//place room;
		//for(int y = ; y <)
	}
	
 	public static void PlaceRoom() 
	{
		/*Vertice NewRoom = new Vertice(4,5,6,7);
		int roomSizeX = NewRoom.width;
		int roomSizeY = NewRoom.height;
		int roomMiddle = (levelHeight/2) - 5 - roomSizeY/2 ;
		int roomMiddleX = levelWidth/2;
		NewRoom.middleX = roomMiddleX;
		NewRoom.middleY = roomMiddle + roomSizeY/2 -1;
		VertHeap1[0] = NewRoom;
		
		//create corridor (upwards)
		for(int y = (roomMiddle + (roomSizeY/2)) -1 ; y < levelHeight/2 - 1; y++)
			level[levelWidth/2][y] = true;
		
		/*for(int y = roomMiddle - (roomSizeY/2) - 1; y <= roomMiddle + (roomSizeY/2) - 2; y++)
			for(int x = levelWidth/2 - (roomSizeX/2); x <=  levelWidth/2 + (roomSizeX/2); x++)
				if(x == levelWidth/2 - (roomSizeX/2) || y == roomMiddle-(roomSizeY/2) -1|| x == levelWidth/2 + roomSizeX/2 || y == (roomMiddle+roomSizeY/2) - 2) 
					level[x][y] = true;*/
		
		//Place Corridor
	}
	
	public static void PrintLevel() 
	{
		for(int y = 0; y < levelHeight; y++) {
			for (int x = 0; x < levelWidth; x++) {
				if(level[x][y])
					System.out.print("#");
				else
					System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String args[]) 
	{
		InitLevel();
		PlaceStarterRoom();
		PlaceFirstRoom();
		PrintLevel();
	}
}
