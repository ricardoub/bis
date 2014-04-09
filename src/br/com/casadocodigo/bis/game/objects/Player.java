package br.com.casadocodigo.bis.game.objects;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;

import br.com.casadocodigo.bis.config.Assets;
import br.com.casadocodigo.bis.game.interfaces.ShootEngineDelegate;

public class Player extends CCSprite {
	float positionX = screenWidth() / 2;
	float positionY = 100;
	
	private ShootEngineDelegate delegate;
	
	public Player() {
		super(Assets.NAVE);
		setPosition(positionX, positionY);
	}
	
	public void shoot() {
		delegate.createShoot(
			new Shoot(positionX, positionY)
		);
	}
	
	public void setDelegate(ShootEngineDelegate delegate) {
		this.delegate = delegate;
	}
	
	public void moveLeft() {
		if (positionX > 30) {
			positionX -= 10;
		}
		setPosition(positionX, positionY);
	}
	
	public void moveRight() {
		if (positionX < screenWidth() - 30) {
			positionX += 10;
		}
		setPosition(positionX, positionY);
	}
	
	public void explode() {
		//para o agendamento
		this.unschedule("update");
		
		// cria efeitos
		float dt = 0.2f;
		CCScaleBy a1 = CCScaleBy.action(dt, 2f);
		CCFadeOut a2 = CCFadeOut.action(dt);
		CCSpawn s1 = CCSpawn.actions(a1, a2);
		
		// roda os efeitos
		this.runAction(CCSequence.actions(s1));
	}
	
}
