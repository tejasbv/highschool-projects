import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;

	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}

	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}

	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}
//	public void reset(LifeView view, String fileName)
//	{
//		int r, c;
//		grid = new LifeCell[SIZE][SIZE];
//		for ( r = 0; r < SIZE; r++ )
//			for ( c = 0; c < SIZE; c++ )
//				grid[r][c] = new LifeCell();
//
//		if ( fileName == null ) //use random population
//		{                                           
//			for ( r = 0; r < SIZE; r++ )
//			{
//				for ( c = 0; c < SIZE; c++ )
//				{
//					if ( Math.random() > 0.85) //15% chance of a cell starting alive
//						grid[r][c].setAliveNow(true);
//				}
//			}
//		}
//	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		for(int i=0; i<SIZE;i++)
		{
			int neighbors = 0;
			for(int j=0; j<SIZE;j++)
			{
				neighbors = check_adjacent(i,j);
				if (grid[i][j].isAliveNow())
				{
					if (neighbors==0 || neighbors==1 || neighbors>=4)
					{
						grid[i][j].setAliveNext(false);
					}
					else 
					{
						grid[i][j].setAliveNext(true);
					}
				}
				else
				{
					neighbors+=1;
					if (neighbors==3)
					{
						grid[i][j].setAliveNext(true);
					}
				}
			}

		}
		for (int i=0; i<grid.length; i++)
		{
			for(int j=0;j<grid[0].length;j++)
			{
				if (grid[i][j].isAliveNext())
				{
					grid[i][j].setAliveNow(true);
					grid[i][j].setAliveNext(false);

				}
				else
				{
					grid[i][j].setAliveNow(false);
				}
			}
		}
	}

	private int check_adjacent(int r, int c)
	{
		int count = 0;
		for(int i=-1; i<2;i++)
		{
			for (int j=-1; j<2; j++)
			{
				if ((r+i)>=0 && (c+j)>=0 && (r+i)<grid.length && (c+j)<grid[0].length)
				{
					if (grid[r+i][c+j].isAliveNow())
					{
						count++;
					}
				}
			}
		}
		return count-1;
	}
}

