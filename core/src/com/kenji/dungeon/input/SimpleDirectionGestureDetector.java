package com.kenji.dungeon.input;

import com.badlogic.gdx.input.GestureDetector;

public class SimpleDirectionGestureDetector extends GestureDetector {
	public interface DirectionListener {
		void onLeft();

		void onRight();

		void onUp();

		void onDown();

		void stop();
	}

	public SimpleDirectionGestureDetector(DirectionListener directionListener) {
		super(new DirectionGestureListener(directionListener));
	}

	private static class DirectionGestureListener extends GestureAdapter {
		DirectionListener directionListener;

		public DirectionGestureListener(DirectionListener directionListener) {
			this.directionListener = directionListener;
		}

		@Override
		public boolean pan(float x, float y, float deltaX, float deltaY) {

			if (Math.abs(deltaX) > Math.abs(deltaY)) {
				if (deltaX > 1) {
					directionListener.onRight();
				} else {
					directionListener.onLeft();
				}
			} else {
				if (deltaY > 1) {
					directionListener.onDown();
				} else {
					directionListener.onUp();
				}
			}

			return super.pan(x, y, deltaX, deltaY);
		}

		@Override
		public boolean panStop(float x, float y, int pointer, int button) {
			directionListener.stop();
			return super.panStop(x, y, pointer, button);
		}

		@Override
		public boolean fling(float velocityX, float velocityY, int button) {
			// if (Math.abs(velocityX) > Math.abs(velocityY)) {
			// if (velocityX > 0) {
			// directionListener.onRight();
			// } else {
			// directionListener.onLeft();
			// }
			// } else {
			// if (velocityY > 0) {
			// directionListener.onDown();
			// } else {

			// directionListener.onUp();
			// }
			// }
			return super.fling(velocityX, velocityY, button);
		}

	}
}
