package ap.trainingdiary.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.TreeSet;

public class CheckBigDecimal{
	private static final TreeSet<String> residues;
	static{
		residues = new TreeSet<String>();
		residues.add("0.25");
		residues.add("0.5");
		residues.add("0.75");
	}
	public static boolean wrongFormat(final BigDecimal bd){
		if(bd.signum() == 1){
			if(bd.scale() == 0){
				return false;
			}
			else if(bd.scale() > 0 && bd.scale() < 3){
				BigDecimal r = bd.subtract(bd.setScale(0, RoundingMode.DOWN));
				if(residues.contains(r.toPlainString())){
					return false;
				}
			}
		}
		return true;
	}
}
