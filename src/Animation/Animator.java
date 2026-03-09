package Animation;


import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import UI.UIEntity;

public class Animator {
	
	private double v = 0;
	
	private double p = 0;
	

	private double goal = 0;
	
	private double k1 = 0;
	private double k2 = 0;
	

	private double naturalFrequency;
	private double dampingCoefficient;

	private DoubleConsumer set;
	
	private final double minError = 0.01;
	
	//no idea how this works, just saw it in a youtube video, hope that helps :) 
	//https://youtu.be/KPoeNZZ6H4s?si=2GaFefMPx0tdasUM
	public Animator(DoubleConsumer set, double originalValue)
	{
		this.set = set;
		
		p = originalValue;
		
		setNaturalFrequency(1);
		setDampingCoefficient(0.5);
	}
	
	public void Update()
	{
		p += v * Source.Source.deltaTime;
		v += (goal - p - k1 * v) * Source.Source.deltaTime / k2;
		
		if (Math.abs((goal - p) / goal) > minError)
			set.accept(p);
	}
	
	public double getGoal() {
		return goal;
	}

	public void setGoal(double goal) {
		this.goal = goal;
	}
	
	public double getNaturalFrequency() {
		return naturalFrequency;
	}

	public void setNaturalFrequency(double naturalFrequency) {
		this.naturalFrequency = naturalFrequency;
		
		k1 = dampingCoefficient / (Math.PI * naturalFrequency);
		k2 = 1 / (2 * Math.PI * naturalFrequency * 2 * Math.PI * naturalFrequency);
	}

	public double getDampingCoefficient() {
		return dampingCoefficient;
	}

	public void setDampingCoefficient(double dampingCoefficient) {
		this.dampingCoefficient = dampingCoefficient;
		
		k1 = dampingCoefficient / (Math.PI * naturalFrequency);
	}


}
