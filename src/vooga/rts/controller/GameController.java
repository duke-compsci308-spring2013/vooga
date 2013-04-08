package vooga.rts.controller;

import vooga.rts.gamedesign.Gun;
import vooga.rts.gamedesign.sprite.rtsprite.Bullet;
import vooga.rts.gamedesign.sprite.rtsprite.Projectile;
import vooga.rts.gamedesign.sprite.rtsprite.interactive.Interactive;
import vooga.rts.gamedesign.sprite.rtsprite.interactive.units.Soldier;
import vooga.rts.gamedesign.sprite.rtsprite.interactive.units.Units;
import vooga.rts.gamedesign.strategy.attackstrategy.CanAttack;
import vooga.rts.gamedesign.strategy.attackstrategy.CannotAttack;
import vooga.rts.input.*;
import vooga.rts.player.AIPlayer;
import vooga.rts.player.HumanPlayer;
import vooga.rts.player.Player;
import vooga.rts.player.Team;
import vooga.rts.util.Location;
import vooga.rts.util.Pixmap;
import vooga.rts.util.Sound;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameController extends AbstractController {

    private Map<Integer, Team> myTeams;
    private List<Player> myPlayers;

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
    }

    @Override
    public void paint (Graphics2D pen) {
        for (Player p : myPlayers) {
            p.paint(pen);
        }
    }

    @Override
    public void onLeftMouseDown (PositionObject o) {

    }

    @Override
    public void onLeftMouseUp (PositionObject o) {
        // if it's not a gui thing
        System.out.println("Left Click");
        HumanPlayer human = (HumanPlayer) myPlayers.get(0);
        human.handleLeftClick((int) o.getX(), (int) o.getY());
    }

    @Override
    public void onRightMouseDown (PositionObject o) {

    }

    @Override
    public void onRightMouseUp (PositionObject o) {
        // If it's not a GUI thing
        System.out.println("Right Click");
        HumanPlayer human = (HumanPlayer) myPlayers.get(0);
        human.handleRightClick((int) o.getX(), (int) o.getY());
    }

    @Override
    public void activate (MainState gameState) {
        setupGame();
    }

    private void setupGame () {
        System.out.println("yolo");
        Player p1 = new HumanPlayer();
        Pixmap p = new Pixmap("vooga.rts.images.soldier.png");
        Location l = new Location(400,500);
        Dimension s = new Dimension();
        Sound soun = new Sound("vooga.rts.sounds.pikachu.wav");

        Units a = new Soldier(p,l,s,soun,20,40);
        a.setAttackStrategy(new CannotAttack());
        
        Units b = new Soldier(p,new Location(20,30),s,soun,20,50);
        Projectile proj = new Bullet(new Pixmap("vooga.rts.images.bullet.png"), b.getCenter(), new Dimension(30, 30), soun, 10, 1);
        b.setAttackStrategy(new CanAttack());
        ((CanAttack) b.getAttackstrategy()).addWeapons(new Gun(0, proj, 50, b.getCenter(),20));
        
        Units c = new Soldier(p,l,s,soun,20,40);
        c.setAttackStrategy(new CannotAttack());
        
        p1.getUnits().addUnit(a);
        p1.getUnits().addUnit(b);
        Player p2 = new HumanPlayer();
        p2.getUnits().addUnit(c);
    }
}
