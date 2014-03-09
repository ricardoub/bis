package br.com.casadocodigo.bis.game.objects;

import java.util.Random;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenHeight;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;


public class Meteor extends CCSprite {
	private float x, y;

	public Meteor(String image) {
		super(image);
		x = new Random().nextInt(Math.round(screenWidth()));
		y = screenHeight();
	}
	
	public void start() {
		this.schedule("update");
	}
	
	public void update(float dt) {
		y -= 1;
		this.setPosition(screenResolution(CGPoint.ccp(x, y)));
	}

}
