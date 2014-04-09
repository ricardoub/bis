package br.com.casadocodigo.bis.game.interfaces;

import br.com.casadocodigo.bis.game.objects.Meteor;

public interface MeteorsEngineDelegate {
	/*
	public void createMeteor(
		Meteor meteor, float x, float y, float vel, double ang, int vl);
	*/
	public void createMeteor(Meteor meteor);
	public void removeMeteor(Meteor meteor);
}
