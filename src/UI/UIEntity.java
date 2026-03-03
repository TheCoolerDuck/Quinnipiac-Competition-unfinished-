package UI;

public interface UIEntity {
	
	public abstract int GetWidth();
	public abstract int GetHeight();
	
	
	//Location is the center of the rectangle the UI is bounded by
	public abstract int GetLocX();
	public abstract int GetLocY();
	
	public abstract void SetLocX(int x);
	public abstract void SetLocY(int y);
	
	public default void MoveToLeftOf(UIEntity other)
	{
		MoveToLeftOf(other, 0);
	}
	
	public default void MoveToLeftOf(UIEntity other, int separation)
	{
		int newX = other.GetLocX() - (other.GetWidth() / 2) - (this.GetWidth() / 2) - separation;
		this.SetLocX(newX);
	}

}
