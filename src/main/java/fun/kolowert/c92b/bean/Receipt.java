package fun.kolowert.c92b.bean;

public class Receipt {

	private int id;
	private long opentime;
	private long closetime;
	private int operatorId;
	private double sum;

	public Receipt(int id, long opentime, long closetime, int operatorId, double sum) {
		this.id = id;
		this.opentime = opentime;
		this.closetime = closetime;
		this.operatorId = operatorId;
		this.sum = sum;
	}

	public int getId() {
		return id;
	}

	public long getOpentime() {
		return opentime;
	}

	public long getClosetime() {
		return closetime;
	}

	public int getOperatorId() {
		return operatorId;
	}

	public double getSum() {
		return sum;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setClosetime(long closetime) {
		this.closetime = closetime;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String briff() {
		return "id:" + id + " operator:" + operatorId + " sum:" + sum;
	}

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", opentime=" + opentime + ", closetime=" + closetime + ", operatorId="
				+ operatorId + ", sum=" + sum + "]";
	}
}
