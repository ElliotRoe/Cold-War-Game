import processing.core.PApplet;

public class Collider implements ProcessingReq {

	static PApplet parent;

	final String[] colliderTypes = { "RECT", "CIRCLE", "ELLIPSE" };
	String colliderType = "";

	float xCoor = 0, yCoor = 0;
	boolean colliderVisible = false;

	public static void setParent(PApplet p) {
		parent = p;
	}

	public boolean isVisible() {
		return colliderVisible;
	}

	public void setVisible(boolean colliderVisible) {
		this.colliderVisible = colliderVisible;
	}

	public boolean contains(float x, float y) {
		System.out.println("Oops");
		return false;
	}

	public void drawCollider() {

	}

	public float getxCoor() {
		return xCoor;
	}

	public void setxCoor(float xCoor) {
		this.xCoor = xCoor;
	}

	public float getyCoor() {
		return yCoor;
	}

	public void setyCoor(float yCoor) {
		this.yCoor = yCoor;
	}

}
