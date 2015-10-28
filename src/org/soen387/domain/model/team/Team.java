package org.soen387.domain.model.team;

import org.soen387.domain.model.pilot.Pilot;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.Player;

public class Team implements ITeam {

	long id;
	int version;
	String name;
	IPlayer player;
	
	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public Team(long id, int version, String name, IPlayer player) {
		super();
		this.id = id;
		this.version = version;
		this.name = name;
		this.player = player;
	}

	@Override
	public void setPlayer(IPlayer player) {
		this.player = player;
	}

	@Override
	public IPlayer getPlayer() {
		return player;
	}
	
	@Override
	public boolean equals(Object t) {
		return t instanceof ITeam && this.id==((ITeam)(t)).getId();
	}
	
	public boolean validateTeam() {
		return false;
	}
	
	public void addPilot(Pilot p) {
		
	}
	
	public void removePilot(Pilot p) {
		
	}

}
