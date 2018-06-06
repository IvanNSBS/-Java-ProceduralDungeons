import java.security.SecureRandom;

public class DrunkardWalk 
{
	public enum Direction
	{
		North, South, West, East
	}
	
	private static SecureRandom srandom = new SecureRandom();
	
	private static int Width = 64;
	private static int Height = 32;
	
	private static int NumberOfSteps = 1024;
	
	private static boolean[][] Chunk = new boolean[Width][Height]; 
	private static Direction NextStep; 
	
	private static int CurX = 0;
	private static int CurY = 0;
	private static int CurrentIndex = 0;
	
	public static void FillChunk() 
	{
		for(int y = 0; y < Height; y++)
			for(int x = 0; x < Width; x++) 
				Chunk[x][y] = true;
	}
	
	public static void PrintChunk() 
	{
		for(int y = 0; y < Height; y++) 
		{
			for(int x = 0; x < Width; x++) 
			{
				if(Chunk[x][y])
					System.out.print("##");
				else
					System.out.print("  ");
			}
			if(y != Height-1)
				System.out.println("");
		}
		System.out.println("\n\n");
	}
	
	public static void GetNextDirection() 
	{
		SecureRandom rand = new SecureRandom();
		int index = rand.nextInt(4);
		switch(index) 
		{
			case 0:	{
				NextStep = Direction.North;
				break;
			}
			case 1:{
				NextStep = Direction.South;
				break;
			}
			case 2:{
				NextStep = Direction.West;
				break;
			}
			case 3:{
				NextStep = Direction.East;
				break;
			}
		}
	}
	
	public static void UpdateCurrentPosition() 
	{
		switch(NextStep)
		{
			case North:
			{
				if(CurY+1 < Height) 
				{
					CurY++;
					if(Chunk[CurX][CurY]) {
						Chunk[CurX][CurY] = false;
						CurrentIndex++;
					}
				}
				break;
			}
			
			case South:
			{
				if(CurY-1 >= 0) 
				{
					CurY--;
					if(Chunk[CurX][CurY]) {
						Chunk[CurX][CurY] = false;
						CurrentIndex++;
					}
				}
				break;
			}
			
			case East:
			{
				if(CurX+1 < Width) 
				{
					CurX++;
					if(Chunk[CurX][CurY]) {
						Chunk[CurX][CurY] = false;
						CurrentIndex++;
					}
				}
				break;
			}
			
			case West:
			{
				if(CurX-1 >= 0) 
				{
					CurX--;
					if(Chunk[CurX][CurY]) {
						Chunk[CurX][CurY] = false;
						CurrentIndex++;
					}
				}
				break;
			}
		}
	}
	
	public static void DrunkWalk() 
	{
		CurX = srandom.nextInt(Width);
		CurY = srandom.nextInt(Height);
		
		Chunk[CurX][CurY] = false;
		
		CurrentIndex = 0;
		while( CurrentIndex < NumberOfSteps ) 
		{
			//GetNextDirection();
			GetNextDirection();
			UpdateCurrentPosition();
			//System.out.println("(" + CurX + ", " + CurY + ")");
			//PrintChunk();
		}
		
	}
	
}
