package com.chicken;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.WakeLockOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Context;
import android.util.DisplayMetrics;

public class GameActivity extends BaseGameActivity {
	public static Context context;
	public static Scene scene;

	@Override
	public Engine onLoadEngine() {
		context = this.getApplicationContext();
		// TODO Auto-generated method stub

		Camera camera = new Camera(0, 0, Options.CAMERA_WIDTH,
				Options.CAMERA_HEIGHT);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		final EngineOptions options = new EngineOptions(true,
				ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(
						(float) dm.widthPixels, (float) dm.heightPixels),
				camera).setWakeLockOptions(WakeLockOptions.SCREEN_ON);

		options.getRenderOptions().disableExtensionVertexBufferObjects();

		options.getTouchOptions().setRunOnUpdateThread(true);

		Engine engine = new Engine(options);

		return engine;
	}

	public static BitmapTextureAtlas resourcesAtlas;

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		resourcesAtlas = new BitmapTextureAtlas(1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		getTextureManager().loadTextures(resourcesAtlas);
	}

	static SquareManager squareManager;

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		mEngine.registerUpdateHandler(new FPSLogger());

		scene = new Scene();

		// Sprite b = new Sprite(0, 0,
		// BitmapTextureAtlasTextureRegionFactory.createFromAsset(
		// resourcesAtlas, getApplicationContext(), "blue.png", 0,
		// 0));
		// scene.setBackground(new ColorBackground(1, 0, 0));
		// scene.attachChild(b);
		// scene.registerUpdateHandler(new ITimerCallback(){
		// @Override
		// public void onTimePassed(final TimerHandler pTimerHandler) {
		//
		// }
		// });
		squareManager = new SquareManager();

		squareManager.create();

		scene.registerUpdateHandler(new TimerHandler(1f / 20.0f, true,
				new ITimerCallback() {

					@Override
					public void onTimePassed(TimerHandler arg0) {
						squareManager.update();
					}

				}));

		return scene;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub

	}

}