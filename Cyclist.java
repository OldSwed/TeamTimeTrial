
public class Cyclist{ //see html at C:/Home/Java/TTT/file

	private String name;
	private int ftp;
	private int weight;
	private int height;
	private double velocity;
	private double powerFront;
	private double energyRace;
	//fields which is equal for all riders and hence not included 
	//in the constructor, need to be static
	private static double ftpFactor;
	private static double approxRaceDuration;

	public Cyclist(String name, int ftp, int weight, int height) {
		this.name = name;
		this.ftp = ftp;
		this.weight = weight;
		this.height = height;
		this.powerFront = this.ftp * ftpFactor;//power of front Rider
		//energy spend during the race, assume riders can sustain 0.9 of their's FTP
		this.energyRace = this.ftp * approxRaceDuration * 60 * 0.95;
		calcSpeed();
	}
	public static void setFtpFactor(double factor) {
		ftpFactor = factor;
	}
	public static double getFtpFactor() {
		return ftpFactor;
	}
	public static void setDuration(double duration){
		approxRaceDuration = duration;
	}
	public static double getDuration() {
		return approxRaceDuration;
	}
	public String getName() {
		return name;
	}
	public double getFtp() {
		return ftp;
	}
	public double getWeight() {
		return weight;
	}
	public double getHeight() {
		return height;
	}
	public double getPowerFront() {
		return powerFront;
	}
	public double getVelocity() {
		return velocity;
	}
	public double getEnergyRace() {
		return energyRace;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public double calcEnergy(int duration, double raceSpeed) {
		double power = (1.86e-2 * weight* raceSpeed) - (5.37e-4 * Math.pow(raceSpeed, 3)) + (2.23e-5 * weight * Math.pow(raceSpeed, 3)) + (1.33e-5 * height * Math.pow(raceSpeed, 3));
		return power * duration;
	}
	//formula for calculating the power needed to hold a specific velocity
	public double powerCalc() {
		double power = (1.86e-2 * weight* velocity) - (5.37e-4 * Math.pow(velocity, 3)) + (2.23e-5 * weight * Math.pow(velocity, 3)) + (1.33e-5 * height * Math.pow(velocity, 3));
		return power;
	}
	//calculates the speed for a frontRider at frontPower
	public void calcSpeed() {
		velocity = 30;  //start value of iteration, assuming v always > 30 km/h
		double diff;
		do {
			double power = powerCalc();
			diff = powerFront-power;
			velocity = velocity + 0.01;//field variable velocity get it value here
		}
		while(diff >  0.1);
	}
	//sort the ArrayList by velocity, see html at C:/Home/Java/TTT/file
	public static class CompByVelocity implements java.util.Comparator<Cyclist> {
		@Override
		public int compare(Cyclist o1, Cyclist o2) {
			return Double.compare(o2.getVelocity(), o1.getVelocity());//compare the double values but return an int
	}
	}
}
