
public class EllipseCollider extends Collider {

	float radius;

	public EllipseCollider(float x, float y, float r) {
		xCoor = x;
		yCoor = y;

		radius = r;

	}

	@Override
	public boolean contains(float x, float y) {
		return Math.hypot(x - xCoor, y - yCoor) <= radius;
	}

	@Override
	public void drawCollider() {
		if (colliderVisible) {
			parent.ellipseMode(parent.RADIUS);
			parent.stroke(97, 255, 0);
			parent.strokeWeight(2);
			parent.noFill();

			parent.ellipse(xCoor, yCoor, radius, radius);
		}
	}
}
