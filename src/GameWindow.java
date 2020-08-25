import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;

public class GameWindow extends PApplet {

	private static int windowScale = 0;

	PGraphics missileButtonA, missileButtonH, missileButtonR;

	PImage missileBanner;

	PGraphics[] missileButton = new PGraphics[3];
	PGraphics[] uBSprites = new PGraphics[3];
	PGraphics[] uTSprites = new PGraphics[3];
	PGraphics[] conveyorBelt = new PGraphics[6];
	PGraphics miniMissileGraphic;
	PGraphics menuOverlay;

	float homeXCoor = 0;
	float homeYCoor = 0;
	float xAlign = 480;
	boolean menuOn = false;

	ImageButton rocketButton;
	ImageButton upgradeButton;
	ImageButton upgradeTickDD;
	ImageButton upgradeTickCC;
	ImageButton upgradeTickMM;
	GameObject conveyorBeltObject;
	GameObject menuOverlayObject;
	ArrayList<GameObject> miniMissiles = new ArrayList<GameObject>();

	PFont rockwell;

	int missileCount = 0;
	int clickIncrement = 1;
	int amtAdded = 0;
	int missilePerMinute = 0;

	int fontSize = 320;

	int scale = 8;
	int size = 32 * scale;

	public static void main(String[] args) {
		PApplet.main(new String[] { "GameWindow" });
	}

	public void settings() {
		size((int) (800 / (1.5f + windowScale)), (int) (1200 / (1.5f + windowScale)));

	}

	public void setup() {

		PImage titlebaricon = loadImage("MissileButtonSprite(R).png");
		surface.setIcon(titlebaricon);

		setupCanvas();
		loadGraphics();

		missileBanner = loadImage("ButtonBannerSprite.png");

		rocketButton = new ImageButton(width / 2, height * 2 / 3, missileButton);
		rocketButton.setCollider(-1, -3, (missileButtonR.width / 2) - 12, false);

		upgradeButton = new ImageButton(60, 60, uBSprites);
		upgradeButton.setCollider(0, 0, 50, false);

		upgradeTickDD = new ImageButton(homeXCoor + xAlign + 20, homeYCoor + 60, uTSprites);
		upgradeTickDD.setCollider(0, 0, 15, false);

		upgradeTickCC = new ImageButton(homeXCoor + xAlign + 20, homeYCoor + 220, uTSprites);
		upgradeTickCC.setCollider(0, 0, 15, false);

		upgradeTickMM = new ImageButton(homeXCoor + xAlign + 20, homeYCoor + 380, uTSprites);
		upgradeTickMM.setCollider(0, 0, 15, false);

		conveyorBeltObject = new AnimGameObject((width / 2) - 5, height - 88, 0.3f, conveyorBelt);

		menuOverlayObject = new GameObject(width / 2, height / 2 + 1000, menuOverlay);

		rockwell = createFont("rockwell.ttf", 32);

	}

	public void draw() {
		clear();
		background(86, 112, 114);
		imageMode(CENTER);
		image(missileBanner, width / 2, (height * 2 / 3) + 3,
				(size * missileBanner.width / missileBanner.height) / 1.25f, size / 1.25f);

		conveyorBeltObject.drawObject();
		rocketButton.drawButton();

		if (upgradeTickDD.isClicked() && missileCount > 20) {
			missileCount -= 20;
			clickIncrement += 10;
		}

		if (upgradeTickCC.isClicked() && missileCount > 200) {
			missileCount -= 200;
			clickIncrement *= 2;
		}

		if (upgradeTickMM.isClicked() && missileCount > 10000) {
			missileCount -= 100000;
			missilePerMinute += 1;
		}

		if (rocketButton.isClicked())
			amtAdded += clickIncrement;
		if (upgradeButton.isClicked()) {
			menuOn = !menuOn;
		}

		counter();
		spawnMMissile(amtAdded);
		for (GameObject go : miniMissiles) {
			// System.out.println("Missile Spawned");
			go.setXCoor(go.getXCoor() - 5);
			go.drawObject();
			if (go.getXCoor() < 0) {
				go = null;
				miniMissiles.remove(go);
			}
		}
		// spawnMMissile(amtAdded);

		textSize(30);

		if (menuOn && menuOverlayObject.getYCoor() > height / 2) {
			menuOverlayObject.setYCoor(menuOverlayObject.getYCoor() - 100);
		} else if (!menuOn && menuOverlayObject.getYCoor() < height / 2 + 1000) {
			menuOverlayObject.setYCoor(menuOverlayObject.getYCoor() + 100);
		}

		menu();

		// text(mouseX + " : " + mouseY, 50, 30);

	}

	private void loadGraphics() {
		missileButtonR = createGraphics(size, size);
		missileButtonR.beginDraw();
		missileButtonR.noStroke();
		missileButtonR.image(loadImage("MissileButtonSprite(R).png"), 0, 0, size, size);
		missileButtonR.endDraw();
		missileButton[2] = missileButtonR;

		missileButtonH = createGraphics(size, size);
		missileButtonH.beginDraw();
		missileButtonH.noStroke();
		missileButtonH.image(loadImage("MissileButtonSprite(H).png"), 0, 0, size, size);
		missileButtonH.endDraw();
		missileButton[1] = missileButtonH;

		missileButtonA = createGraphics(size, size);
		missileButtonA.beginDraw();
		missileButtonA.noStroke();
		missileButtonA.image(loadImage("MissileButtonSprite(A).png"), 0, 0, size, size);
		missileButtonA.endDraw();
		missileButton[0] = missileButtonA;

		int j = 2;

		uBSprites[2] = createGraphics(size / j, size / j);
		uBSprites[2].beginDraw();
		uBSprites[2].noStroke();
		uBSprites[2].image(loadImage("PlusButtonSprite(R).png"), 0, 0, size / j, size / j);
		uBSprites[2].endDraw();

		uBSprites[1] = createGraphics(size / j, size / j);
		uBSprites[1].beginDraw();
		uBSprites[1].noStroke();
		uBSprites[1].image(loadImage("PlusButtonSprite(H).png"), 0, 0, size / j, size / j);
		uBSprites[1].endDraw();

		uBSprites[0] = createGraphics(size / j, size / j);
		uBSprites[0].beginDraw();
		uBSprites[0].noStroke();
		uBSprites[0].image(loadImage("PlusButtonSprite(A).png"), 0, 0, size / j, size / j);
		uBSprites[0].endDraw();

		miniMissileGraphic = createGraphics(size, size);
		miniMissileGraphic.beginDraw();
		miniMissileGraphic.noStroke();
		miniMissileGraphic.image(loadImage("MissileSprite.png"), 0, 0, size / 2.25f, size / 2.25f);
		miniMissileGraphic.endDraw();

		int h = 7;

		uTSprites[2] = createGraphics(size / h, size / h);
		uTSprites[2].beginDraw();
		uTSprites[2].noStroke();
		uTSprites[2].image(loadImage("UpgradeTick(R).png"), 0, 0, size / h, size / h);
		uTSprites[2].endDraw();

		uTSprites[1] = createGraphics(size / h, size / h);
		uTSprites[1].beginDraw();
		uTSprites[1].noStroke();
		uTSprites[1].image(loadImage("UpgradeTick(H).png"), 0, 0, size / h, size / h);
		uTSprites[1].endDraw();

		uTSprites[0] = createGraphics(size / h, size / h);
		uTSprites[0].beginDraw();
		uTSprites[0].noStroke();
		uTSprites[0].image(loadImage("UpgradeTick(A).png"), 0, 0, size / h, size / h);
		uTSprites[0].endDraw();

		miniMissileGraphic = createGraphics(size, size);
		miniMissileGraphic.beginDraw();
		miniMissileGraphic.noStroke();
		miniMissileGraphic.image(loadImage("MissileSprite.png"), 0, 0, size / 2.25f, size / 2.25f);
		miniMissileGraphic.endDraw();

		PImage temp = loadImage("ConveyorBeltSprite(1).png");
		float imageWidth = temp.width, imageHeight = temp.height;

		for (int i = 1; i <= 6; i++) {
			conveyorBelt[i - 1] = createGraphics((int) (size * imageWidth / imageHeight), size);
			conveyorBelt[i - 1].beginDraw();
			conveyorBelt[i - 1].noStroke();
			conveyorBelt[i - 1].image(loadImage("ConveyorBeltSprite(" + i + ").png"), 0, 0,
					(size * imageWidth / imageHeight) * 1.2f, size * 1.2f);
			conveyorBelt[i - 1].endDraw();
		}

		temp = loadImage("MenuOverlay.png");
		imageWidth = temp.width;
		imageHeight = temp.height;
		menuOverlay = createGraphics((int) ((size * imageWidth / imageHeight) * 6), size * 6);
		menuOverlay.beginDraw();
		menuOverlay.noStroke();
		menuOverlay.image(loadImage("MenuOverlay.png"), 0, 0, (size * imageWidth / imageHeight) * 6, size * 6);
		menuOverlay.endDraw();

	}

	private void setupCanvas() {
		Button.setParent(this);
		Collider.setParent(this);
		GameObject.setParent(this);
	}

	private void counter() {
		missileCount += amtAdded;
		fontSize = 320 - 40 * (new String("" + missileCount).length());
		textFont(rockwell);
		textSize(fontSize);
		textAlign(CENTER);
		fill(255, 255, 255, 255 * 0.15f);
		text("" + missileCount, width / 2, height / 2 - 70);
	}

	private void spawnMMissile(int count) {
		if (count > 3)
			count = 3;
		for (int i = 0; i < count; i++) {
			GameObject temp = new GameObject(conveyorBeltObject.getXCoor() + 400 + random(10),
					conveyorBeltObject.getYCoor(), miniMissileGraphic);
			miniMissiles.add(temp);

		}
		amtAdded = 0;
	}

	private void menu() {
		menuOverlayObject.drawObject();
		upgradeButton.drawButton();

		homeXCoor = menuOverlayObject.getXCoor() - width / 2;
		homeYCoor = menuOverlayObject.getYCoor() - height / 2 + 120;

		textAlign(RIGHT);
		fill(185, 67, 25);
		text("Domestically Dapper!", homeXCoor + xAlign, homeYCoor + 60);
		upgradeTickDD.setxCoor(homeXCoor + xAlign + 20);
		upgradeTickDD.setyCoor(homeYCoor + 60);
		upgradeTickDD.drawButton();
		text("Crazy Consumption!", homeXCoor + xAlign, homeYCoor + 220);
		upgradeTickCC.setxCoor(homeXCoor + xAlign + 20);
		upgradeTickCC.setyCoor(homeYCoor + 220);
		upgradeTickCC.drawButton();
		text("Middle's Menace", homeXCoor + xAlign, homeYCoor + 380);
		upgradeTickMM.drawButton();
		upgradeTickMM.setxCoor(homeXCoor + xAlign + 20);
		upgradeTickMM.setyCoor(homeYCoor + 380);
//		System.out.println(menuOn);
		textSize(17);
		fill(223, 211, 206);
		text("This upgarde allows your fellow countrymen to settle down\n with a perfect family (with many children) "
				+ "allowing them\n to forget all about their missile worries!\n Which, in turn, allows you to build "
				+ "more missiles \nper click in their ignorance!", homeXCoor + xAlign, homeYCoor + 90);
		text("This upgarde allows you to begin a consumer craze\n in support of capatilism! "
				+ "Of course it's also to defend\n against those god awful reds as well!!\n "
				+ "This floods your program with funding\n allowing you to double your missile made per click!!",
				homeXCoor + xAlign, homeYCoor + 250);
		text("This upgrade allows you to crush the middle classes\n "
				+ "diversity with an onslaught of television and multimedia\n "
				+ "propganda! This allows you to create a society that makes\n "
				+ "missiles for you! They make one missile per\n " + "second no matter if you click or not!\n",
				homeXCoor + xAlign, homeYCoor + 410);

		fill(97, 255, 0);
		textAlign(CENTER);
	}
}
