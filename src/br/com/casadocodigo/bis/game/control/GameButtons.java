package br.com.casadocodigo.bis.game.control;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenHeight;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;

import br.com.casadocodigo.bis.config.Assets;
import br.com.casadocodigo.bis.game.interfaces.ButtonDelegate;
import br.com.casadocodigo.bis.game.scenes.GameScene;

public class GameButtons extends CCLayer implements ButtonDelegate {
	
	private Button leftButton;
	private Button rightButton;
	private Button shootButton;
	//private Button pauseButton;

	private GameScene delegate;

	public static GameButtons gameButtons() {
		return new GameButtons();
	}
	
	public GameButtons() {
		// Habilita o toque na tela
		this.setIsTouchEnabled(true);
		
		// cria os botões
		this.leftButton  = new Button(Assets.LEFTBUTTON);
		this.rightButton = new Button(Assets.RIGHTBUTTON);
		this.shootButton = new Button(Assets.SHOOTBUTTON);
		//this.pauseButton = new Button(Assets.PAUSEBUTTON);
		
		// configura as delegações
		this.leftButton.setDelegate(this);
		this.rightButton.setDelegate(this);
		this.shootButton.setDelegate(this);
		//this.pauseButton.setDelegate(this);		
		
		// set position
		setButtonsPosition();
		
		
		// adiciona os botões na tela
		addChild(this.leftButton);
		addChild(this.rightButton);
		addChild(this.shootButton);
		//addChild(this.pauseButton);
	}
	
	private void setButtonsPosition() {
		// Buttons positions
		this.leftButton.setPosition(screenResolution(CGPoint.ccp(40, 40)));
		this.rightButton.setPosition(screenResolution(CGPoint.ccp(100, 40)));
		this.shootButton.setPosition(screenResolution(CGPoint.ccp(screenWidth() - 40, 40)));
		//this.pauseButton.setPosition(screenResolution(CGPoint.ccp(40, screenHeight() - 30)));
	}

	@Override
	public void buttonClicked(Button sender) {
		if (sender.equals(this.leftButton)) {
			System.out.println("Button clicked: Left");
			//this.delegate.moveLeft();
		}
		if (sender.equals(this.rightButton)) {
			System.out.println("Button clicked: Right");
			//this.delegate.moveRight();
		}
		if (sender.equals(this.shootButton)) {
			//System.out.println("Button clicked: Shoot");
			this.delegate.shoot();
		}
		/*
		if (sender.equals(this.pauseButton)) {
			System.out.println("Button clicked: Pause");
			//this.delegate.pauseGameAndShowLayer();
		}
		*/
	}
	
	public void setDelegate(GameScene gameScene) {
		this.delegate = gameScene;
	}
	
}
