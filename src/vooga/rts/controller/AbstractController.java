package vooga.rts.controller;
import java.util.Observable;

import vooga.rts.IGameLoop;
import vooga.rts.input.PositionObject;

// The methods are INTENTIONALLY implemented as empty (null behavior)
// This is to make it so that each controller only needs to implement relevant user events

public abstract class AbstractController extends Observable implements
		IGameLoop {

	public void onLeftMouseDown(PositionObject o) {
	};

	public void onLeftMouseUp(PositionObject o) {
	};

	public void onRightMouseDown(PositionObject o) {
	};

	public void onRightMouseUp(PositionObject o) {
	};

	public void onMouseDrag(PositionObject o) {
	};

	public void onMouseMove(PositionObject o) {
	};

	public abstract void activate();

	public abstract MainState getGameState();

}
