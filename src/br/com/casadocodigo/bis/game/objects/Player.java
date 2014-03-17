package br.com.casadocodigo.bis.game.objects;

import org.cocos2d.nodes.CCSprite;

import br.com.casadocodigo.bis.config.Assets;
import br.com.casadocodigo.bis.game.interfaces.ShootEngineDelegate;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenHeight;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;

public class Player extends CCSprite {
	float positionX = screenWidth() / 2;
	float positionY = 50;
	
	private ShootEngineDelegate delegate;
	
	public Player() {
		super(Assets.NAVE);
		setPosition(positionX, positionY);
	}
	
	public void setDelegate(ShootEngineDelegate delegate) {
		this.delegate = delegate;
	}
}
