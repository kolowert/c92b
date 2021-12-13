package fun.kolowert.c92b.utility;

import fun.kolowert.c92b.bean.Operator;

public class Report {

	private String type;
	private long reportPeriodTimeFrom;
	private long reportPeriodTimeTo;
	private long reportMadeTime;
	private String reportMadeTimeStamp;
	private int receiptQuantity;
	private double totalSum;
	private Operator operator;

	public Report(String type, long periodFrom, long periodTo, int receiptQuantity, double totalSum,
			Operator operator) {
		this.type = type;
		this.reportPeriodTimeFrom = periodFrom;
		this.reportPeriodTimeTo = periodTo;
		this.reportMadeTime = System.currentTimeMillis();
		reportMadeTimeStamp = Utils.unixTimeToTimeStamp(reportMadeTime);
		this.receiptQuantity = receiptQuantity;
		this.totalSum = totalSum;
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "Report [type=" + type + ", reportPeriodTimeFrom=" + reportPeriodTimeFrom + ", reportPeriodTimeTo="
				+ reportPeriodTimeTo + ", reportMadeTime=" + reportMadeTime + ", reportMadeTimeStamp="
				+ reportMadeTimeStamp + ", receiptQuantity=" + receiptQuantity + ", totalSum=" + totalSum
				+ ", operator=" + operator.briefInfo() + "]";
	}

	public String toHTML() {
		String[] lines = new String[10];
		
		String br = "<br />";
		String br2 = br + br;
		String sp = "&ensp;";
		String sp2 = sp + sp;
		
		lines[0] = "<h4 style='color :Gray'>" + type + "</h4>" + br;
		
		lines[1] = "<h5>" + "Sales" + "</h5>";
		
		lines[2] = "Receipt quantity:" + sp2 + receiptQuantity + br;
		
		lines[3] = "Total Sum:" + sp2 + Utils.norm(totalSum) + br2;
		
		lines[4] = "Report period:" + br;
		
		lines[5] = "from" + sp + Utils.unixTimeToTimeStamp(reportPeriodTimeFrom) + br;
		
		lines[6] = "to" + sp2 + Utils.unixTimeToTimeStamp(reportPeriodTimeTo) + br2;
		
		lines[7] = "Operator:" + br + operator.briefInfo() + br;
		
		lines[8] = "Report time:" + br + reportMadeTimeStamp + br2;
		
		lines[9] = "~ ~ ~";
		
		
		StringBuilder result = new StringBuilder();
		for (String line : lines) {
			result.append(line);
		}
		
		return result.toString();
	}

}
