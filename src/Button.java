import processing.core.PApplet;

public class Button implements ProcessingReq {

	static PApplet parent;

	float xCoor;
	float yCoor;

	float width;
	float height;
	float borderWidth;
	float radius;

	int colorDown;
	int colorHover;
	int colorRest;

	boolean buttonDown = false;
	boolean buttonHover = false;
	boolean buttonRest = false;

	boolean currentButtonState = false;
	boolean lastButtonState = false;

	public Button() {

	}

	public Button(float w, float h, float x, float y, float bw, int[] c, float r) {
		width = w;
		height = h;
		borderWidth = bw;

		xCoor = x;
		yCoor = y;

		radius = r;

		colorDown = c[0];
		colorHover = c[1];
		colorRest = c[2];
	}

	public static void setParent(PApplet p) {
		parent = p;
	}

	public void drawButton() {
		parent.rectMode(parent.CENTER);
		parent.stroke((int) colorHover);
		parent.strokeWeight(borderWidth);
		if (((parent.mouseX > (xCoor - (width / 2))) && (parent.mouseX < (xCoor + (width / 2)))
				&& (parent.mouseY > (yCoor - (height / 2))) && (parent.mouseY < (yCoor + (height / 2))))) {
			if (parent.mousePressed) {
				// buttonDown
				parent.fill((int) colorDown);
				buttonDown = true;
				buttonHover = false;
				buttonRest = false;
				lastButtonState = currentButtonState;
				currentButtonState = buttonDown;
			} else {
				// buttonHover
				parent.fill((int) colorHover);
				buttonDown = false;
				buttonHover = true;
				buttonRest = false;
				lastButtonState = currentButtonState;
				currentButtonState = buttonDown;
			}
			parent.rect(xCoor, yCoor, width, height, radius);
		} else {
			// buttonResting
			parent.fill((int) colorHover);
			buttonDown = false;
			buttonHover = false;
			buttonRest = true;
			parent.rect(xCoor, yCoor, width, height, radius);
		}
	}

	public boolean buttonDown() {

		return buttonDown;
	}

	public boolean buttonClicked() {
		return currentButtonState && !lastButtonState;
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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public boolean isButtonDown() {
		return buttonDown;
	}

	public boolean isButtonHover() {
		return buttonHover;
	}

	public boolean isButtonRest() {
		return buttonRest;
	}

	public boolean isClicked() {
		return !lastButtonState && currentButtonState;
	}

}
