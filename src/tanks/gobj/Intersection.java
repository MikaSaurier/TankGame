package tanks.gobj;

public class Intersection {
	public static enum Type {
		Right, Left, Top, Bottom;
	}
	
	public static final Intersection NONE = new Intersection(0d, 0d);
	
	private final Type[] types;
	private final double toMoveX, toMoveY;

	public Intersection(double toMoveX, double toMoveY, Type... types) {
		this.types = types;
		this.toMoveX = toMoveX;
		this.toMoveY = toMoveY;

	}
	
	public boolean is(Type type) {
		for (Type t : types) {
			if (t == type) {
				return true;
			}
		}
		return false;
	}

	public double getToMoveX() {
		return toMoveX;
	}

	public double getToMoveY() {
		return toMoveY;
	}
}
