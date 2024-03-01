package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

/**
 * La clase AndroidLauncher es responsable
 * de iniciar el juego en dispositivos Android.
 */
public class AndroidLauncher extends AndroidApplication {
	/**
	 * Se llama cuando la actividad se está iniciando.
	 * @param savedInstanceState Si la actividad se está reinicializando después
	 *                           de haber sido cerrada previamente, entonces este
	 *                           Bundle contiene los datos que suministró
	 *                           más recientemente en onSaveInstanceState(Bundle).
	 *                           Nota: De lo contrario, es nulo.
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new BrickBreakerGame(), config);
	}
}
