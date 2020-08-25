import processing.core.PApplet;
import processing.core.PGraphics;

public class GameObject implements ProcessingReq {

	static PApplet parent;

	float xCoor, yCoor;

	PGraphics sprite;

	public GameObject() {
	}

	public GameObject(float x, float y, PGraphics img) {
		sprite = img;

		xCoor = x;
		yCoor = y;
	}

	public static void setParent(PApplet p) {
		parent = p;
	}

	public void drawObject() {
		parent.image(sprite, xCoor, yCoor);
	}

	public float getXCoor() {
		return xCoor;
	}

	public void setXCoor(float xCoor) {
		this.xCoor = xCoor;
	}

	public float getYCoor() {
		return yCoor;
	}

	public void setYCoor(float yCoor) {
		this.yCoor = yCoor;
	}

}
