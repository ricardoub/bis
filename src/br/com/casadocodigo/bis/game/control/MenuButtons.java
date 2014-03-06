package br.com.casadocodigo.bis.game.control;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenHeight;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;

import br.com.casadocodigo.bis.config.Assets;
import br.com.casadocodigo.bis.game.interfaces.ButtonDelegate;

public class MenuButtons extends CCLayer implements ButtonDelegate {
	private Button playButton;
	private Button highscoredButton;
	private Button helpButton;
	private Button soundButton;
	
	public MenuButtons() {
		this.setIsTouchEnabled(true);
		
		this.playButton = new Button(Assets.PLAY);
		this.highscoredButton = new Button(Assets.HIGHSCORE);
		this.helpButton = new Button(Assets.HELP);
		this.soundButton = new Button(Assets.SOUND);
		
		// coloca os botões na posição correta
		setButtonsPosition();
		
		addChild(playButton);
		addChild(highscoredButton);
		addChild(helpButton);
		addChild(soundButton);
		
		this.playButton.setDelegate(this);
		this.highscoredButton.setDelegate(this);
		this.helpButton.setDelegate(this);
		this.soundButton.setDelegate(this);
	}
	
	private void setButtonsPosition() {
		
		// Buttons Positions
		playButton.setPosition(
			screenResolution(
				CGPoint.ccp(
					screenWidth() / 2, 
					screenHeight() - 250 ))
		);

		highscoredButton.setPosition(
			screenResolution(
				CGPoint.ccp(
					screenWidth() / 2, 
					screenHeight() - 300 ))
		);
		

		helpButton.setPosition(
			screenResolution(
				CGPoint.ccp(
					screenWidth() / 2, 
					screenHeight() - 350 ))
		);
		
		soundButton.setPosition(
			screenResolution(
				CGPoint.ccp(
					screenWidth() / 2, 
					screenHeight() - 420 ))
		);
	}

	@Override
	public void buttonClicked(Button sender) {
		if (sender.equals(this.playButton)) {
			System.out.println("Button clicked: Play");
		}

		if (sender.equals(this.highscoredButton)) {
			System.out.println("Button clicked: Highscore");
		}

		if (sender.equals(this.helpButton)) {
			System.out.println("Button clicked: help");
		}

		if (sender.equals(this.soundButton)) {
			System.out.println("Button clicked: Sound");
		}
	}

}
