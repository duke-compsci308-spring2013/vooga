package vooga.towerdefense.action.attack;

import vooga.towerdefense.action.TargetedAction;
import vooga.towerdefense.attributes.Attribute;
import vooga.towerdefense.factories.elementfactories.GameElementFactory;
import vooga.towerdefense.gameelements.GameElement;
import vooga.towerdefense.model.GameMap;
import util.Location;

/**
 * Creates projectiles aimed at a target. Targets info pass on to projectiles
 * created. Projectiles created gave its own follow up action predfined by XML.
 * 
 * @author Xu Rui
 */
public class ShootProjectile extends TargetedAction {

	private Attribute myUnitsRemaining;
	private Attribute myNumShots;
	private Attribute myCooldown;
	private GameElementFactory myProjectileFactory;

	private Location mySource;
	private GameMap myMap;
	private double myTimer;
	private boolean targetDetectionOn; // true if fire only upon target
										// detection

	public ShootProjectile(Attribute cooldown, Attribute numProjectiles,
			Attribute numShots, Location startLocation,
			GameElementFactory projectileFactory, GameMap map,
			boolean targetdetection) {
		super();
		myCooldown = cooldown;
		myUnitsRemaining = numProjectiles;
		myNumShots = numShots;
		mySource = startLocation;
		myProjectileFactory = projectileFactory;
		myMap = map;
		myTimer = 0;
		targetDetectionOn = targetdetection;
	}

	@Override
	public void update(double elapsedTime) {
		setEnabled(checkLaunchCondition());
		if (isEnabled()) {
			executeAction(elapsedTime);
		}
	}

	@Override
	public void executeAction(double elapsedTime) {
		if (myTimer > myCooldown.getValue()) {
			spawnProjectile((int) myNumShots.getValue());
			myTimer = 0;
		}
		myTimer += elapsedTime;
	}

	/**
	 * Returns true only if remaining projectiles >0 and there are targets.
	 * 
	 * @return
	 */
	public boolean checkLaunchCondition() {
		boolean checker = !(myUnitsRemaining.getValue() <= 0);
		if (targetDetectionOn) {
			checker = checker && !getTargets().isEmpty();
		}
		return checker;
	}

	/**
	 * Creates a projectile with action of shooting at targets.
	 * 
	 * @param elapsedTime
	 */
	public void spawnProjectile(int num) {
		int n = num;
		while (n > 0) {
			GameElement projectile = myProjectileFactory
					.createElement(mySource);
			for (TargetedAction a : projectile.getTargetedActions()) {
				a.setSingleTarget(getSingleTarget());
			}
			myMap.addGameElement(projectile);
			myUnitsRemaining.modifyValue(-1);
			n--;
		}
	}
}
