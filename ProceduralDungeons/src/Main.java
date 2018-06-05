public class Main
{
	private static int Width = 40;
	private static int Height = 20;
	private static float FillChance = 0.5f;
	private static int Iterations = 3;
	
	private static boolean[][] Chunk = new boolean[Width][Height]; 
	
	private static String Fill = "#"; 
	private static String Empty = " ";
	
	private static int BirthLimit = 4;
	private static int DeathLimit = 3;
	
	public static void RandomizeChunk() 
	{
		for(int y = 0; y < Height; y++)
			for(int x = 0; x < Width; x++) 
				Chunk[x][y] = (Math.random() <= FillChance) ? true : false;
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
			System.out.println("");
		}
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
	
	public static void GenerateChunk() 
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
						if(GetNeighbors(x,y) < DeathLimit)
							temp[x][y] = false;
						else
							temp[x][y] = true;
					}
					
					else 
					{
						if(GetNeighbors(x,y) > BirthLimit)
							temp[x][y] = true;
						else
							temp[x][y] = false;
					}
				}
			}
			Chunk = temp;
		}
	}
	
	public static void main(String args[]) 
	{
		RandomizeChunk();
		PrintChunk();
		
		System.out.println("");
		
		GenerateChunk();
		PrintChunk();
	} 
}
