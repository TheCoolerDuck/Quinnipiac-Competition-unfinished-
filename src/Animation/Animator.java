package Animation;

import java.util.ArrayList;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

public class Animator {
	
	public boolean active = true;
	
	private double v = 0;	
	private double goal = 0;
	
	private double k1 = 0;
	private double k2 = 0;
	
	private double naturalFrequency;
	private double dampingCoefficient;

	private DoubleConsumer set;
	private DoubleSupplier get;
	
	private final double minError = 0.01;
	
	private static ArrayList<Animator> instances = new ArrayList<Animator>();
	
	//no idea how this works, just saw it in a youtube video, hope that helps :) 
	//https://youtu.be/KPoeNZZ6H4s?si=2GaFefMPx0tdasUM
	public Animator(DoubleConsumer set, DoubleSupplier get)
	{
		this.set = set;
		this.get = get;

		setNaturalFrequency(1);
		setDampingCoefficient(0.5);
		
		instances.add(this);
	}
	
	public static void UpdateAll()
	{	
		for (Animator i : instances)
			if (i.active)
				i.Update();
	}
	
	private void Update()
	{
		double p = get.getAsDouble();
		
		p += v * Source.Source.deltaTime;
		v += (goal - p - k1 * v) * Source.Source.deltaTime / k2;
		
		if (Math.abs(v / goal) > minError)
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
		
		ReCalcK1();
		ReCalcK2();
	}

	public double getDampingCoefficient() {
		return dampingCoefficient;
	}

	public void setDampingCoefficient(double dampingCoefficient) {
		this.dampingCoefficient = dampingCoefficient;
		
		ReCalcK1();
	}

	private void ReCalcK1()
	{
		k1 = dampingCoefficient / (Math.PI * naturalFrequency);
	}
	
	private void ReCalcK2()
	{
		k2 = 1 / (2 * Math.PI * naturalFrequency * 2 * Math.PI * naturalFrequency);
	}
}
