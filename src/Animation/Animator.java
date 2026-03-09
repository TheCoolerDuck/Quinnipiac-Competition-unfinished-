package Animation;


import UI.UIEntity;

public class Animator {
	
	private double vx = 0;
	private double vy = 0;
	
	private double px = 0;
	private double py = 0;
	
	public int getGoalX() {
		return goalX;
	}

	public void setGoalX(int goalX) {
		this.goalX = goalX;
	}

	public int getGoalY() {
		return goalY;
	}

	public void setGoalY(int goalY) {
		this.goalY = goalY;
	}

	private int goalX;
	private int goalY;
	
	private UIEntity entity;
	
	private double k1 = 0;
	private double k2 = 0;
	
	//no idea how this works, just saw it in a youtube video, hope that helps :) 
	//https://youtu.be/KPoeNZZ6H4s?si=2GaFefMPx0tdasUM
	public Animator(UIEntity entity, int goalX, int goalY, double naturalFrequency, double dampingCoeffecant)
	{
		this.entity = entity;
		this.goalX = goalX;
		this.goalY = goalY;
		
		px = entity.getCentralX();
		py = entity.getCentralY();
		
		k1 = dampingCoeffecant / (Math.PI * naturalFrequency);
		k2 = 1 / (2 * Math.PI * naturalFrequency * 2 * Math.PI * naturalFrequency);
	}
	
	public void Update()
	{
		px += vx * Source.Source.deltaTime;
		py += vy * Source.Source.deltaTime;
		
		vx += (goalX - px - k1 * vx) * Source.Source.deltaTime / k2;
		vy += (goalY - py - k1 * vy) * Source.Source.deltaTime / k2;
		
		entity.setCentralX((int)px);
		entity.setCentralY((int)py);
	}
	

}
