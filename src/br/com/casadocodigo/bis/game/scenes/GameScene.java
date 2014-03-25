package br.com.casadocodigo.bis.game.scenes;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenHeight;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;

import java.util.ArrayList;
import java.util.List;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.types.CGPoint;

import br.com.casadocodigo.bis.config.Assets;
import br.com.casadocodigo.bis.game.control.GameButtons;
import br.com.casadocodigo.bis.game.engines.MeteorsEngine;
import br.com.casadocodigo.bis.game.interfaces.MeteorsEngineDelegate;
import br.com.casadocodigo.bis.game.interfaces.ShootEngineDelegate;
import br.com.casadocodigo.bis.game.objects.Meteor;
import br.com.casadocodigo.bis.game.objects.Player;
import br.com.casadocodigo.bis.game.objects.Shoot;
import br.com.casadocodigo.bis.screens.ScreenBackground;

public class GameScene 
	extends CCLayer 
	implements MeteorsEngineDelegate, ShootEngineDelegate {
	
	// Layers
	private CCLayer meteorsLayer;
	//private CCLayer scoreLayer;
	private CCLayer playerLayer;
	private CCLayer shootsLayer;
	//private CCLayer topLayer;
	
	// Engines
	private MeteorsEngine meteorsEngine;
	
	// Arrays
	@SuppressWarnings("rawtypes")
	private ArrayList meteorsArray;
	@SuppressWarnings("rawtypes")
	private ArrayList playerArray;
	@SuppressWarnings("rawtypes")
	private ArrayList shootsArray;
	
	// Screens
	//private PauseScreen pauseScreen;
	
	// Game Objects
	private Player player;
	//private Score score;
	private ScreenBackground background;

	//private boolean autoCalibration;
	
	public static CCScene createGame() {
		CCScene scene = CCScene.node();
		GameScene layer = new GameScene();
		scene.addChild(layer);
		return scene;
	}
	
	private GameScene() {
		// background
		this.background = new ScreenBackground(Assets.BACKGROUND);
		this.background.setPosition(screenResolution(
			CGPoint.ccp(
				screenWidth() / 2.0f,
				screenHeight() / 2.0f)));
		this.addChild(this.background);
		
		GameButtons gameButtonsLayer = GameButtons.gameButtons();
		gameButtonsLayer.setDelegate(this);
		this.addChild(gameButtonsLayer);
		
		// create Layers
		this.meteorsLayer = CCLayer.node();
		this.playerLayer = CCLayer.node();
		//this.scoreLayer = CCLayer.node();
		
		this.shootsLayer = CCLayer.node();
		//this.layerTop = CCLayer.node();

		this.addGameObjects();
		
		// Add Layers
		this.addChild(this.meteorsLayer);		
		this.addChild(this.playerLayer);
		this.addChild(this.shootsLayer);
		
		this.setIsTouchEnabled(true);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void createMeteor(Meteor meteor) {
		this.meteorsLayer.addChild(meteor);
		meteor.start();
		this.meteorsArray.add(meteor);
	}
	
	private void addGameObjects() {
		this.meteorsArray = new ArrayList();
		this.shootsArray  = new ArrayList();
		this.meteorsEngine = new MeteorsEngine();
		
		this.player = new Player();
		this.playerLayer.addChild(this.player);
		
		this.player.setDelegate(this);
	}
	
	@Override
	public void onEnter() {
		super.onEnter(); 
		this.startEngines();
		
	}
	
	private void startEngines() {
		this.addChild(this.meteorsEngine);
		this.meteorsEngine.setDelegate(this);
	}
	
	@Override
	public void createShoot(Shoot shoot) {
		this.shootsLayer.addChild(shoot);
		shoot.setDelegate(this);
		shoot.start();
		this.shootsArray.add(shoot);
	}
	
	public boolean shoot() {
		player.shoot();
		return true;
	}
}
