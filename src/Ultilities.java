import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by ADMIN on 4/15/2017.
 */
public class Ultilities
{
	public static Image loadImage(String path) throws IOException {
		return ImageIO.read(new File(path));
	}
}
