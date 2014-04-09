package br.com.casadocodigo.bis.game.objects;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.casadocodigo.bis.config.Assets;
import br.com.casadocodigo.bis.game.interfaces.ShootEngineDelegate;

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
	
	public void explode() {
		// remove do arrray de tiro
		this.delegate.removeShoot(this);
		
		// para o agendamento
		this.unschedule("update");
		
		//cria efeitos
		float dt = 0.2f;
		CCScaleBy a1 = CCScaleBy.action(dt, 2f);
		CCFadeOut a2 = CCFadeOut.action(dt);
		CCSpawn s1 = CCSpawn.actions(a1,a2);
		
		//funcao a ser executada após o efeito
		CCCallFunc c1 = CCCallFunc.action(this, "removeMe");
		
		// roda efeito
		this.runAction(CCSequence.actions(s1, c1));
	}
	
	public void removeMe() {
		this.removeFromParentAndCleanup(true);
	}
}
