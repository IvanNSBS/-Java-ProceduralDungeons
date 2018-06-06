
import java.security.SecureRandom;

public class CellularAutomata
{
	private static int Width = 50;
	private static int Height = 30;
	private static float FillChance = 0.45f;
	private static int Iterations = 2;
	
	private static SecureRandom srandom = new SecureRandom();
	
	private static boolean[][] Chunk = new boolean[Width][Height]; 
	
	private static String Fill = "██"; 
	private static String Empty = "  ";
	
	private static int M1BirthLimit = 4;
	private static int M1DeathLimit = 3;

	public static void RandomizeChunk() 
	{
		for(int y = 0; y < Height; y++)
			for(int x = 0; x < Width; x++) 
				Chunk[x][y] = (srandom.nextFloat() < FillChance) ? true : false;
	
	}
	
	public static void PrintChunk() 
	{
		for(int y = 0; y < Height; y++) 
		{
			for(int x = 0; x < Width; x++) 
			{
				if(Chunk[x][y])
					System.out.print(Fill);
				else
					System.out.print(Empty);
			}
			if(y != Height-1)
				System.out.println("");
		}
		System.out.println("\nEnded");
	}
	
	public static int GetNeighbors(int x, int y) 
	{
		int count = 0;
		for(int iy = y-1; iy <= y+1; iy++) 
			for(int ix = x-1; ix <= x+1; ix++) 
			{
				if(!(ix == x && iy == y)) 
					if( (ix >= 0 && ix < Width) && (iy >= 0 && iy < Height) )
						if(Chunk[ix][iy])
							count++;	
			}
		//System.out.println(count);
		return count;
	} 
	
	public static void GenerateChunkM1() 
	{
		for(int i = 0; i < Iterations; i++) 
		{
			boolean[][] temp = Chunk;
			for(int y = 0; y < Height; y++) 
			{
				for(int x = 0; x < Width; x++) 
				{
					if(Chunk[x][y]) 
					{						
						if(GetNeighbors(x,y) < M1DeathLimit)
							temp[x][y] = false;
						else
							temp[x][y] = true;
					}
					
					else 
					{
						if(GetNeighbors(x,y) > M1BirthLimit)
							temp[x][y] = true;
						else
							temp[x][y] = false;
					}
				}
			}
			Chunk = temp;
		}
	}

	public static void GenerateChunkM2() 
	{
		for(int i = 0; i < Iterations; i++) 
		{
			boolean[][] temp = Chunk;
			for(int y = 0; y < Height; y++) 
			{
				for(int x = 0; x < Width; x++) 
				{
					if(Chunk[x][y]) 
					{						
						if(GetNeighbors(x,y) >= 4)
							temp[x][y] = true;
						if(GetNeighbors(x,y) <= 2)
							temp[x][y] = false;
					}
					
					else 
					{
						if(GetNeighbors(x,y) >= 5)
							temp[x][y] = true;
					}
				}
			}
			Chunk = temp;
		}
	}
	
	public static void FinishChunk() 
	{
		for(int i = 0; i < 3; i++) 
		{
			for(int y = 0; y < Height; y++) 
			{
				for(int x = 0; x < Width; x++) 
				{
					int NC = GetNeighbors(x,y);
					if(Chunk[x][y]) 
					{
						if(NC <= 2)
							Chunk[x][y] = false;
					}
					else
						if(NC >= 4)
							Chunk[x][y] = false;
				}
			}
		}
	}
	
}
