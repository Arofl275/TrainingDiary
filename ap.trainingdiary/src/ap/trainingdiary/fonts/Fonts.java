package ap.trainingdiary.fonts;
import java.io.IOException;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;
public class Fonts{
	private static final String path = "ap/fonts";
	private static final String format = "%s/%s";
	private static final PdfFont getFont(final String font) throws IOException{
		return PdfFontFactory.createFont(String.format(format, path, font), PdfEncodings.IDENTITY_H);
	}
	public static PdfFont arial() throws IOException{
		return getFont("arial.ttf");
	}
}
