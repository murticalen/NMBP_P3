import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resizer {
	
	public static void main(String[] args) throws Exception {
		int i = 3;
		while(i <= 70){
	    	File image = new File("D:\\FER\\Documents\\Java\\NBMP_Projekt3\\R dataset\\images\\"+i+".RandomImagesO2-UWTB.png");
	    	System.out.println(image);
	    	BufferedImage img = ImageIO.read(image);
	    	int h = (int)(200.00/img.getWidth()*img.getHeight());
	    	BufferedImage newimg = scale(img, 200, h);
	    	image = new File("D:\\FER\\Documents\\Java\\NBMP_Projekt3\\R dataset\\images\\"+i+".Scaled.png");
	    	ImageIO.write(newimg, "png", image);
	    	i++;
		}
	}
	
	static BufferedImage scale(BufferedImage src, int w, int h)
	{
	  BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	  int x, y;
	  int ww = src.getWidth();
	  int hh = src.getHeight();
	  for (x = 0; x < w; x++) {
	    for (y = 0; y < h; y++) {
	      int col = src.getRGB(x * ww / w, y * hh / h);
	      img.setRGB(x, y, col);
	    }
	  }
	  return img;
	}
}
