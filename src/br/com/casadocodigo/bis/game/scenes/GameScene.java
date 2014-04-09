package br.com.casadocodigo.bis.game.scenes;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenHeight;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import br.com.casadocodigo.bis.config.Assets;
import br.com.casadocodigo.bis.game.control.GameButtons;
import br.com.casadocodigo.bis.game.engines.MeteorsEngine;
import br.com.casadocodigo.bis.game.interfaces.MeteorsEngineDelegate;
import br.com.casadocodigo.bis.game.interfaces.ShootEngineDelegate;
import br.com.casadocodigo.bis.game.objects.Meteor;
import br.com.casadocodigo.bis.game.objects.Player;
import br.com.casadocodigo.bis.game.objects.Score;
import br.com.casadocodigo.bis.game.objects.Shoot;
import br.com.casadocodigo.bis.screens.ScreenBackground;

public class GameScene 
	extends CCLayer 
	implements MeteorsEngineDelegate, ShootEngineDelegate {
	
	// Layers
	private CCLayer meteorsLayer;
	private CCLayer scoreLayer;
	private CCLayer playersLayer;
	private CCLayer shootsLayer;
	//private CCLayer topLayer;
	
	// Engines
	private MeteorsEngine meteorsEngine;
	
	// Arrays
	@SuppressWarnings("rawtypes")
	private ArrayList meteorsArray;
	@SuppressWarnings("rawtypes")
	private ArrayList playersArray;
	@SuppressWarnings("rawtypes")
	private ArrayList shootsArray;
	
	// Screens
	//private PauseScreen pauseScreen;
	
	// Game Objects
	private Player player;
	private Score score;
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
		this.playersLayer = CCLayer.node();
		this.scoreLayer = CCLayer.node();
		
		this.shootsLayer = CCLayer.node();
		//this.layerTop = CCLayer.node();

		this.addGameObjects();
		
		// Add Layers
		this.addChild(this.meteorsLayer);		
		this.addChild(this.playersLayer);
		this.addChild(this.shootsLayer);
		this.addChild(this.scoreLayer);
		
		this.setIsTouchEnabled(true);		
	}
	
	private void addGameObjects() {
		this.meteorsArray = new ArrayList();
		this.shootsArray  = new ArrayList();
		this.meteorsEngine = new MeteorsEngine();
		
		// player
		this.player = new Player();
		this.playersLayer.addChild(this.player);
		
		// placar
		this.score = new Score();
		this.scoreLayer.addChild(this.score);
		
		this.player.setDelegate(this);
		this.playersArray = new ArrayList();
		this.playersArray.add(this.player);
	}
	
	@Override
	public void onEnter() {
		super.onEnter(); 
		this.schedule("checkHits");
		this.startEngines();		
	}
	
	private void startEngines() {
		this.addChild(this.meteorsEngine);
		this.meteorsEngine.setDelegate(this);
	}

	public boolean shoot() {
		player.shoot();
		return true;
	}

	@Override
	public void createShoot(Shoot shoot) {
		this.shootsLayer.addChild(shoot);
		shoot.setDelegate(this);
		shoot.start();
		this.shootsArray.add(shoot);
	}
	public void removeShoot(Shoot shoot) {
		this.shootsArray.remove(shoot);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void createMeteor(Meteor meteor) {
		meteor.setDelegate(this);
		this.meteorsLayer.addChild(meteor);
		meteor.start();
		this.meteorsArray.add(meteor);
	}
	@Override
	public void removeMeteor(Meteor meteor) {
		this.meteorsArray.remove(meteor);		
	}
	
	public void moveLeft() {
		player.moveLeft();
	}
	
	public void moveRight() {
		player.moveRight();
	}
	

	public CGRect getBoarders(CCSprite object) {
		CGRect rect = object.getBoundingBox();
		CGPoint GLpoint = rect.origin;
		CGRect GLrect = CGRect.make(GLpoint.x, GLpoint.y, rect.size.width, rect.size.height);
		
		return GLrect;
	}
	
	private boolean checkRadiusHitsOfArray(List<? extends CCSprite> array1,
			List<? extends CCSprite> array2, GameScene gameScene, String hit) {
		
		boolean result = false;
		for (int i = 0; i < array1.size(); i++) {
			// pega oobjeto do primeiro array
			CGRect rect1 = getBoarders(array1.get(i));
			
			for (int j = 0; j < array2.size(); j++) {
				//pega o objeto do segundo array
				CGRect rect2 = getBoarders(array2.get(j));
				
				// verifica a colisão
				if (CGRect.intersects(rect1, rect2)) {
					System.out.println("Colision Detected: " + hit);
					result = true;
					
					Method method;
					try {
						method = GameScene.class.getMethod(hit, CCSprite.class, CCSprite.class);
						method.invoke(gameScene, array1.get(i), array2.get(j));
					} catch (SecurityException e1) {
						e1.printStackTrace();
					} catch (NoSuchMethodException e1) {
						e1.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
	public void checkHits(float dt) {
		this.checkRadiusHitsOfArray(this.meteorsArray, this.shootsArray, this, "meteoroHit");
		this.checkRadiusHitsOfArray(this.meteorsArray, this.playersArray, this, "playerHit");
	}
	
	public void meteoroHit(CCSprite meteor, CCSprite shoot) {
		((Meteor) meteor).shooted();
		((Shoot) shoot).explode();
		this.score.increase();
	}
	
	public void playerHit(CCSprite meteor, CCSprite player) {
		((Meteor) meteor).shooted();
		((Player) player).explode();
	}
	

	
}
