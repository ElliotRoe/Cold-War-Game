import processing.core.PImage;

public class ImageButton extends Button {

	float xCoor;
	float yCoor;

	PImage graphicDown;
	PImage graphicHover;
	PImage graphicRest;

	Collider buttonCollider = null;

	float offSetX = 0;
	float offSetY = 0;

	public ImageButton(float x, float y, PImage[] img) {

		xCoor = x;
		yCoor = y;

		graphicDown = img[0];
		graphicHover = img[1];
		graphicRest = img[2];
	}

	public void setCollider(float x, float y, float r, boolean visible) {
		// x and y are offsets not actual world coordinates
		offSetX = x;
		offSetY = y;
		buttonCollider = new EllipseCollider(x + xCoor, y + yCoor, r);
		buttonCollider.setVisible(visible);
	}

	public void setCollider(float x, float y, float width, float height, boolean visible) {
		// x and y are offsets not actual world coordinates
		offSetX = x;
		offSetY = y;
		buttonCollider = new RectCollider(x + xCoor, y + yCoor, width, height);
		buttonCollider.setVisible(visible);
	}

	public void drawButton() {
		parent.imageMode(parent.CENTER);
		if (buttonCollider.contains((float) parent.mouseX, (float) parent.mouseY)) {
			if (parent.mousePressed) {
				// buttonDown
				parent.image(graphicDown, xCoor, yCoor);
				buttonDown = true;
				buttonHover = false;
				buttonRest = false;
				lastButtonState = currentButtonState;
				currentButtonState = buttonDown;
			} else {
				// buttonHover
				parent.image(graphicHover, xCoor, yCoor);
				buttonDown = false;
				buttonHover = true;
				buttonRest = false;
				lastButtonState = currentButtonState;
				currentButtonState = buttonDown;
			}
//			parent.rect(xCoor, yCoor, width, height, radius);
		} else {
			// buttonResting
			parent.image(graphicRest, xCoor, yCoor);
			buttonDown = false;
			buttonHover = false;
			buttonRest = true;
		}
		buttonCollider.drawCollider();
	}

	public float getxCoor() {
		return xCoor;
	}

	public void setxCoor(float xCoor) {
		this.xCoor = xCoor;
		buttonCollider.setxCoor(xCoor + offSetX);
	}

	public float getyCoor() {
		return yCoor;
	}

	public void setyCoor(float yCoor) {
		this.yCoor = yCoor;
		buttonCollider.setyCoor(yCoor + offSetY);
	}

}
