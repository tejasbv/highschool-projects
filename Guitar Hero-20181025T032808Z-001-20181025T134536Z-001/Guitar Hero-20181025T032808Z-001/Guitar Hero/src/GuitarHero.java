import java.awt.Color;
import java.util.*;

public class GuitarHero {
	public static void main(String[] args) throws InterruptedException {

		// Create two guitar strings, for concert A and C
		boolean pressed = true;
		StdDraw.setScale(1,3700);
		StdDraw.setCanvasSize(1891, 260);
		StdDraw.setPenRadius(.1);
		StdDraw.picture(0.5,0.5,"keyboard.png",1,1.2);
		String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		GuitarString[] frequency = new GuitarString[keyboard.length()];
		HashMap<Integer, Double> rect = new HashMap<>();
		for (int i=0; i<keyboard.length();i++)
		{
			frequency[i] = new GuitarString(440*Math.pow(1.05956, i-24));
		}
		
		// the main input loop
		while (true) {

			// check if the user has typed a key, and, if so, process it
			if (StdDraw.hasNextKeyTyped()) {

				// the user types this character
				char key = StdDraw.nextKeyTyped();

				// pluck the corresponding string
				
				if (keyboard.indexOf(key) != -1)
					
					frequency[keyboard.indexOf(key)].pluck();
				StdDraw.clear();
				StdDraw.picture(0.5,0.5,"keyboard.png",1,1.2);
				StdDraw.setPenColor(Color.RED);;
				StdDraw.rectangle(.0178+(.0267*keyboard.indexOf(key)), .52,.006,.47);
				//StdDraw.filledRectangle(.0178, .52,.0125,.47);
			}
			
			if (StdDraw.isMousePressed() && pressed)
			{
				double x = StdDraw.mouseX()*3700;
				//double y = StdDraw.mouseY();
				System.out.println(x);
//				if (!StdDraw.isMousePressed())
//				{
				int i = (int)(x/100);
				//if (i<37)
					frequency[i].pluck();
					pressed = false;
					//Thread.sleep(1);
				//}
			}
			if (!StdDraw.isMousePressed())
				pressed = true;


			// compute the superposition of the samples
			double sample = 0; 
			for (int i=0; i<keyboard.length();i++)
			{
				sample += frequency[i].sample();
			}
			// send the result to standard audio
			StdAudio.play(sample);

			// advance the simulation of each guitar string by one step
			for (int i=0; i<keyboard.length();i++)
			{
				frequency[i].tic();
			}
			
		}
	}
}
