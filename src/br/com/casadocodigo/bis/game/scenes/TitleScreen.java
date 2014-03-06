package br.com.casadocodigo.bis.game.scenes;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenHeight;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.casadocodigo.bis.config.Assets;
import br.com.casadocodigo.bis.game.control.MenuButtons;
import br.com.casadocodigo.bis.screens.ScreenBackground;


public class TitleScreen extends CCLayer {
	
	private ScreenBackground background;
	
	public TitleScreen() {
		this.background = new ScreenBackground(Assets.BACKGROUND);
		this.background.setPosition(
			screenResolution(
				CGPoint.ccp(
					CCDirector.sharedDirector().winSize().width / 2.0f,
					CCDirector.sharedDirector().winSize().height / 2.0f)));
		this.addChild(this.background);
		
		CCSprite title = CCSprite.sprite(Assets.LOGO);
		title.setPosition(
			screenResolution(
				CGPoint.ccp(
					screenWidth() / 2,
					screenHeight() - 130 )));
		this.addChild(title);
		
		MenuButtons menuLayer = new MenuButtons();
		this.addChild(menuLayer);
		
	}
	
	public CCScene scene() {
		CCScene scene = CCScene.node();
		scene.addChild(this);
		return scene;
	}
	
	
}
