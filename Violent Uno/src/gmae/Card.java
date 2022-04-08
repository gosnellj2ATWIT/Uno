package gmae;

public class Card {
	//comment so I can push
	private final Color color;
	private final Value value;
	enum Color {
		Red, Blue, Green, Yellow, Wild;
		private static final Color[] colors = Color.values();
		public static Color getColor(int i) {
			return Color.colors[i];
		}
	}
	enum Value {
		Zero, One, Two, Tree, Four, Five, Six, Seven, Eight, Nine, DrawTwo, Skip, Reverse, Wild, WildDrawFour;
		private static final Value[] values = Value.values();
		public static Value getValue(int i) {
			return Value.values[i];
		}
	}
	public Card(final Color color, final Value value) {
		this.color = color;
		this.value = value;
	}
	public Color getColor() {
		return this.color;
	}
	public Value getValue() {
		return this.value;
	}
	public String toString() {
		return color + "_" + value;
	}
}
