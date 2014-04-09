package br.com.casadocodigo.bis.game.objects;

import static br.com.casadocodigo.bis.config.DeviceSettings.screenHeight;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenResolution;
import static br.com.casadocodigo.bis.config.DeviceSettings.screenWidth;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.opengl.CCBitmapFontAtlas;

public class Score extends CCLayer {
	private int score;
	private CCBitmapFontAtlas text;
	
	public Score() {
		this.score = 0;
		
		this.text = CCBitmapFontAtlas.bitmapFontAtlas(String.valueOf(this.score),"UniSansSemiBold_Numbers_240.fnt");
		this.text.setScale((float) 240 / 240);
		this.setPosition(screenWidth()-50, screenHeight()-50);
		this.addChild(this.text);
	}
	
	public void increase() {
		this.score++;
		this.text.setString(String.valueOf(this.score));
	}
}
