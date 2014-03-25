package br.com.casadocodigo.bis.game.objects;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.casadocodigo.bis.config.Assets;
import br.com.casadocodigo.bis.game.interfaces.ShootEngineDelegate;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenHeight;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;

public class Shoot extends CCSprite {
	private ShootEngineDelegate delegate;
	float positionX, positionY;
	public Shoot(float positionX, float positionY){
		super(Assets.SHOOT);
		this.positionX = positionX;
		this.positionY = positionY;
		setPosition(this.positionX, this.positionY);
		this.schedule("update)");
	}
	
	public void update(float dt) {
		positionY += 2;
		this.setPosition(screenResolution(
			CGPoint.ccp(positionX, positionY)
		));
	}
	
	public void setDelegate(ShootEngineDelegate delegate) {
		this.delegate = delegate;
	}
	
	public void start() {
		//System.out.println("shoot moving!");
		this.schedule("update");
	}
}
