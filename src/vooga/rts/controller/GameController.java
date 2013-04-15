package vooga.rts.controller;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import vooga.rts.gamedesign.Weapon;
import vooga.rts.gamedesign.factories.Factory;
import vooga.rts.gamedesign.sprite.rtsprite.Projectile;
import vooga.rts.gamedesign.sprite.rtsprite.Resource;
import vooga.rts.gamedesign.sprite.rtsprite.interactive.buildings.Barracks;
import vooga.rts.gamedesign.sprite.rtsprite.interactive.buildings.Building;
import vooga.rts.gamedesign.sprite.rtsprite.interactive.buildings.UpgradeBuilding;
import vooga.rts.gamedesign.sprite.rtsprite.interactive.units.Soldier;
import vooga.rts.gamedesign.sprite.rtsprite.interactive.units.Unit;
import vooga.rts.gamedesign.sprite.rtsprite.interactive.units.Worker;
import vooga.rts.gamedesign.strategy.attackstrategy.CanAttack;
import vooga.rts.gamedesign.strategy.attackstrategy.CannotAttack;
import vooga.rts.gamedesign.upgrades.UpgradeTree;
import vooga.rts.input.PositionObject;
import vooga.rts.map.GameMap;
import vooga.rts.player.HumanPlayer;
import vooga.rts.player.Player;
import vooga.rts.player.Team;
import vooga.rts.resourcemanager.ResourceManager;
import vooga.rts.util.Location;
import vooga.rts.util.Pixmap;
import vooga.rts.util.Sound;


public class GameController extends AbstractController {

	private Map<Integer, Team> myTeams;
	private List<Player> myPlayers;
	private GameMap myMap; // This needs a dimension that describes the total size of the map. Not
	// made for now.
	private Resource r; 
	private Building building;
	private UpgradeBuilding upgradeBuilding;

	public GameController () {
		myTeams = new HashMap<Integer, Team>();
		myPlayers = new ArrayList<Player>();
	}

	public void addPlayer (Player player, int teamID) {
		myPlayers.add(player);
		if (myTeams.get(teamID) == null) {
			addTeam(teamID);
		}
		myTeams.get(teamID).addPlayer(player);
	}

	public void addTeam (int teamID) {
		myTeams.put(teamID, new Team(teamID));
	}

	public void connect (/* NetworkGameInfo n */) {

	}

	@Override
	public void update (double elapsedTime) {
		for (Player f : myPlayers) {
			f.update(elapsedTime);
		}
		/*
		 * Iterator<Team> it = myTeams.values().iterator();
		 * while (it.hasNext()) {
		 * 1Team t = it.next();
		 * 
		 * }
		 */
		List<Unit> p1 = myTeams.get(1).getUnits();
		List<Unit> p2 = myTeams.get(2).getUnits();
		for (Unit u1 : p1) {
			for (Unit u2 : p2) {
				if (u1.inRange(u2)) {
					u2.getAttacked(u1);
				}
				if (u2.inRange(u1)) {
					u1.getAttacked(u2);
				}
				if (u1 instanceof Worker)
				{
					((Worker)u1).gather(r);
				}
			}
		}
		building.update(elapsedTime);
		upgradeBuilding.update(elapsedTime);

	}

	@Override
	public void paint (Graphics2D pen) {
		for (Player p : myPlayers) {
			p.paint(pen);
		}
		r.paint(pen);
		building.paint(pen);
		upgradeBuilding.paint(pen);
	}

	@Override
	public void onLeftMouseDown (PositionObject o) {

	}

	@Override
	public void onLeftMouseUp (PositionObject o) {
		// if it's not a gui thing
		HumanPlayer human = (HumanPlayer) myPlayers.get(0);
		human.handleLeftClick((int) o.getX(), (int) o.getY());
	}

	@Override
	public void onRightMouseDown (PositionObject o) {

	}

	@Override
	public void onRightMouseUp (PositionObject o) {
		// If it's not a GUI thing
		HumanPlayer human = (HumanPlayer) myPlayers.get(0);
		human.handleRightClick((int) o.getX(), (int) o.getY());
	}

	@Override
	public void activate (MainState gameState) throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ParserConfigurationException, SAXException, IOException {
		setupGame();
	}

	private void setupGame () throws IllegalArgumentException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ParserConfigurationException, SAXException, IOException {
		System.out.println("Game is setup");

		Factory factory = new Factory();
		UpgradeTree resultTree = factory.loadXMLFile("/Users/Sherry/Desktop/Academics/Compsci 308/Final VOOGA/GameDesign/src/vooga/rts/gamedesign/factories/XML_Sample");
		
		upgradeBuilding = new UpgradeBuilding(new Pixmap(ResourceManager.instance().loadFile("images/barracks.jpeg")), 
				new Location(500,500), new Dimension(150,150), null, 1,300);
		
		Player p1 = new HumanPlayer();
		Pixmap p = new Pixmap(ResourceManager.instance().loadFile("images/soldier.png"));
		Dimension s = new Dimension(90, 90);
		r = new Resource(new Pixmap(ResourceManager.instance().loadFile("images/mineral.gif")), new Location (300,300), new Dimension(60, 60), 0, 80);
		Sound soun = null;// new Sound("/vooga/rts/sounds/pikachu.wav");
		Unit a = null;
		try {
			a = new Soldier(p, new Location(100, 100), s, soun, 2, 100);
			System.out.println("Player ID for a: " + a.getPlayerID());
			a.setUpgradeTree(resultTree,1);
			upgradeBuilding.addUpgradeActions(resultTree);
			Projectile proj =
					new Projectile(new Pixmap(ResourceManager.instance()
							.loadFile("images/bullet.png")), a.getCenter(), new Dimension(30, 30),
							1, 10, 1);
			a.setAttackStrategy(new CanAttack());
			a.getAttackStrategy().addWeapons(new Weapon(0, proj, 200, a.getCenter(), 25));
		}
		catch (Exception e) {
			// trollolol
		}
		
		System.out.println("HIIIIII!!!!");
		Unit b = new Soldier(p, new Location(100, 300), s, soun, 1, 50);
		System.out.println("Player ID for b: " + b.getPlayerID());
		System.out.println("Game is setup 1");
		Projectile proj2 =
				new Projectile(
						new Pixmap(ResourceManager.instance().loadFile("images/bullet.png")),
						b.getCenter(), new Dimension(30, 30), 1, 10, 1);
		b.setAttackStrategy(new CannotAttack());
		b.getAttackStrategy().addWeapons(new Weapon(0, proj2, 200, b.getCenter(), 50));

		Unit c = new Soldier(p, new Location(500, 500), s, soun, 1, 1000);
		System.out.println("Player ID for c: " + c.getPlayerID());
		System.out.println("Game is setup 2");
		Projectile proj3 =
				new Projectile(
						new Pixmap(ResourceManager.instance().loadFile("images/bullet.png")),
						c.getCenter(), new Dimension(30, 30), 1, 10, 1);
		c.setAttackStrategy(new CannotAttack());
		Unit w = new Worker(new Pixmap(ResourceManager.instance().loadFile("images/scv.gif")), new Location(500, 200), s, soun, 20, 40, 40);
		c.getAttackStrategy().addWeapons(new Weapon(0, proj3, 200, c.getCenter(), 50));
		
		p1.getUnits().addUnit(a);
		p1.getUnits().addUnit(b);
		p1.getUnits().addUnit(w);
		Player p2 = new HumanPlayer();
		p2.getUnits().addUnit(c);

		addPlayer(p1, 1);
		addPlayer(p2, 2);
		
		building = new Barracks(new Pixmap(ResourceManager.instance().loadFile("images/barracks.jpeg")), 
				new Location(800,500), new Dimension(150,150), null, 1,300);

		
	}
}
