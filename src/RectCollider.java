
public class RectCollider extends Collider {

	float width = 1, height = 1;

	public RectCollider(float x, float y, float w, float h) {
		colliderType = "Rect";
		xCoor = x;
		yCoor = y;
		width = w;
		height = h;
	}

	@Override
	public boolean contains(float x, float y) {
		return false;
	}

	@Override
	public void drawCollider() {

	}
}
