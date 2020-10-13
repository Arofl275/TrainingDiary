package ap.trainingdiary.type;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TrainingRow{
	private static final BigDecimal lbConversionFactor = new BigDecimal("0.45359237");
	private static final int scale = 3;
	private final BigDecimal weight;
	private final Integer repeats;
	public TrainingRow(final BigDecimal weight, final Integer repeats){
		this.weight = weight;
		this.repeats = repeats;
	}
	public BigDecimal getWeightKG(){
		return weight;
	}
	public String getWeightKGToString(){
		return String.format("%skg", weight.toPlainString());
	}
	public BigDecimal getWeightLB(){
		return weight.divide(lbConversionFactor, scale, RoundingMode.DOWN);
	}
	public String getWeightLBToString(){
		return String.format("%slb", getWeightLB().toPlainString());
	}
	public Integer getRepeats(){
		return repeats;
	}
	public String getRepeatsToString(){
		return String.format("%s", repeats.toString());
	}
}

