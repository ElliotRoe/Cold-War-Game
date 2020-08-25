import processing.core.PGraphics;

public class AnimGameObject extends GameObject {

	PGraphics[] sprites;

	private float increment;
	float i = 0;
	int j = 0;

	public AnimGameObject(float x, float y, float increm, PGraphics[] imgs) {
		xCoor = x;
		yCoor = y;

		increment = increm;

		sprites = new PGraphics[imgs.length];
		int i = 0;
		for (PGraphics pg : imgs) {
			sprites[i] = pg;
			i++;
		}
	}

	@Override
	public void drawObject() {
		i = ((float) i) + increment;
		j = (int) Math.floor(i);
		parent.image(sprites[j], xCoor, yCoor);
		if (j == 5) {
			i = 0;
			j = 0;
		}

	}

}
