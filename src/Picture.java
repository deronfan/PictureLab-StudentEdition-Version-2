import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; 
public class Picture extends SimplePicture 
{
	public Picture ()
	{
		super();  
	}
	public Picture(String fileName)
	{
		super(fileName);
	}
	public Picture(int height, int width)
	{
		super(width,height);
	}
	public Picture(Picture copyPicture)
	{
		super(copyPicture);
	}
	public Picture(BufferedImage image)
	{
		super(image);
	}
	public String toString()
	{
		String output = "Picture, filename " + getFileName() + 
				" height " + getHeight() 
				+ " width " + getWidth();
		return output;

	}
	public void negate() {
		Pixel[][] pixels = this.getPixels2D();   
		Pixel pixel = null;     
		for (int row = 0; row < pixels.length; row++)     
		{       for (int col = 0; col < pixels[0].length; col++)       
			{        
								pixel = pixels[row][col];   
								pixel.setBlue(255-pixel.getBlue());      
		 						pixel.setRed(255-pixel.getRed());         
		 						pixel.setGreen(255-pixel.getGreen());       
			}     
		}  
	}
	public void grayScale() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel pixel = null;     
		for (int row = 0; row < pixels.length; row++)     
		{       for (int col = 0; col < pixels[0].length; col++)       
			{        
								pixel = pixels[row][col];
								int color = (int)pixel.getAverage();   
								pixel.setBlue(color);      
		 						pixel.setRed(color);         
		 						pixel.setGreen(color);       
			}     
		}  
	}

	public void setRedToHalfValueInTopHalf() {
		Pixel[][] pixels = this.getPixels2D();
		int mid = pixels.length / 2;
		for (int row = 0; row < mid; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				Pixel pixel = pixels[row][col];
				pixel.setRed(pixel.getRed() / 2);
			}
		}
	}

	public int getCountRedOverValue(int value) {
		int out = 0;
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				Pixel pixel = pixels[row][col];
				if (pixel.getRed() > value) {
					out++;
				}
			}
		}
		return out;
	}
	public void fixUnderwater() {
		Pixel[][] pixels = this.getPixels2D();
		// Pixel[][] pixels = this.getPixels2D();
		Pixel pixel = null;
		for (int row = 0; row < pixels.length; row++)
		{
			for (int col = 0; col < pixels[0].length; col++)
			{
				pixel = pixels[row][col];
				pixel.setBlue(pixel.getBlue() - 50);
				pixel.setGreen(pixel.getGreen() - 50);
				pixel.setRed(pixel.getRed() + 50);
			}
		}	
	}

  /** pixelates an image
  */

  public void pixelate() {
    
  }

	public void keepOnlyBlue()   
	{     

		 Pixel[][] pixels = this.getPixels2D();

		// Pixel[][] pixels = this.getPixels2D();     
		Pixel pixel = null;     
		for (int row = 0; row < pixels.length; row++)     
		{       for (int col = 0; col < pixels[0].length; col++)       
			{        
								pixel = pixels[row][col];         
		 						pixel.setRed(0);          
		 						pixel.setBlue(0);         
		 						//pixel.setGreen(0);       
			}     
		}  
	}
	public void mirrorVertical()
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
	}
	public void copy(Picture fromPic, 
			int startRow, int startCol)
	{
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; 
				fromRow < fromPixels.length && toRow < toPixels.length; 
				fromRow++, toRow++)
		{
			for (int fromCol = 0, toCol = startCol; 
					fromCol < fromPixels[0].length &&
					toCol < toPixels[0].length;  
					fromCol++, toCol++)
			{
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}   
	}
	public void createCollage()
	{
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1,0,0);
		this.copy(flower2,100,0);
		this.copy(flower1,200,0);
		Picture flowerNoBlue = new Picture(flower2);

		this.copy(flowerNoBlue,300,0);
		this.copy(flower1,400,0);
		this.copy(flower2,500,0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}
	public void edgeDetection(int edgeDist){
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;

    for (int row = 0; row < pixels.length; row++)
    {
        for (int col = 0; col < pixels[0].length - 1; col++)
        {
            leftPixel = pixels[row][col];
            rightPixel = pixels[row][col + 1];
            rightColor = rightPixel.getColor();
            if (leftPixel.colorDistance(rightColor) > edgeDist)
            {
                leftPixel.setColor(Color.BLACK);
            }
            else
            {
                leftPixel.setColor(Color.WHITE);
            }
        }
    }
}
	public static void main(String[] args) 
	{
		PictureTester.main(args);
	}
	public void clearBlueOverValue(int x) {
		Pixel[][] pixels = this.getPixels2D();
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[0].length; c++) {
				Pixel pixel = pixels[r][c];
				if (pixel.getBlue() > x) {
					pixel.setBlue(0);
				}
			}
		}
	}
}
