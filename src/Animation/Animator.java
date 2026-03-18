package Animation;

import java.util.ArrayList;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

public class Animator {

	//Video explaining this insanity: https://youtu.be/KPoeNZZ6H4s?si=2GaFefMPx0tdasUM

	private static final ArrayList<Animator> instances = new ArrayList<Animator>();

	public boolean active = true;
	public boolean debug = false;

	private final DoubleConsumer set;

	private double value = 0;
	private double velocity = 0;
	private double goal = 0;

	private double k1 = 0;
	private double k2 = 0;

	private double naturalFrequency;
	private double dampingCoefficient;

	//region Constructors
	public Animator(DoubleConsumer set) { this(set, 0, 1, 1); }

	public Animator(DoubleConsumer set, double originalValue) { this(set, originalValue, 1, 1); }

	public Animator(DoubleConsumer set, double originalValue, double naturalFrequency, double dampingCoefficient)
	{
		this.set = set;

		setNaturalFrequency(naturalFrequency);
		setDampingCoefficient(dampingCoefficient);

		value = originalValue;

		instances.add(this);
	}

	//endregion
	
	public static void UpdateAll()
	{	
		for (Animator i : instances)
			if (i.active)
				i.Update();
	}
	
	private void Update()
	{
		//Update
		value += velocity * Source.Source.deltaTime;
		velocity += (goal - value - k1 * velocity) * Source.Source.deltaTime / k2;

		if (debug)
			System.out.println("Goal: " + goal + "; Value: " + value + "; Velocity: " + velocity );

		set.accept(value);
	}

	//region Getters/Setters

	public double getGoal() {
		return goal;
	}

	public void setGoal(double goal) {
		this.goal = goal;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getNaturalFrequency() {
		return naturalFrequency;
	}

	public void setNaturalFrequency(double naturalFrequency) { this.naturalFrequency = naturalFrequency; ReCalcK1(); ReCalcK2(); }

	public double getDampingCoefficient() {
		return dampingCoefficient;
	}

	public void setDampingCoefficient(double dampingCoefficient) { this.dampingCoefficient = dampingCoefficient; ReCalcK1(); }

	//endregion

	private void ReCalcK1()
	{
		k1 = dampingCoefficient / (Math.PI * naturalFrequency);
	}

	private void ReCalcK2()
	{
		k2 = 1 / (2 * Math.PI * naturalFrequency * 2 * Math.PI * naturalFrequency);
	}
}
