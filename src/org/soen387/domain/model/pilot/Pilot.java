package org.soen387.domain.model.pilot;

import org.soen387.domain.model.player.IPlayer;

public class Pilot implements IPilot {
	
	long id;
	int version;
	String pilotName;
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
	public IPlayer getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(IPlayer player) {
		this.player = player;
	}

	@Override
	public String getPilotName() {
		return pilotName;
	}

	@Override
	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}

	public Pilot(long id, int version, String pilotName, IPlayer player) {
		super();
		this.id = id;
		this.version = version;
		this.pilotName = pilotName;
		this.player = player;
	}
	
	@Override
	public boolean equals(Object p) {
		return p instanceof IPilot && this.id==((IPilot)(p)).getId();
	}

}
