package org.soen387.domain.model.pilot;

import org.soen387.domain.model.pilot.mapper.PilotMapper;
import org.soen387.domain.model.player.IPlayer;

public class PilotProxy implements IPilot {
	long id;
	
	public PilotProxy(long id) {
		super();
		this.id = id;
	}

	public Pilot getInner() {
		try {
			return PilotMapper.find(id);
		} catch (Exception e) {
			// It better be here! That null won't go over well!
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int getVersion() {
		return getInner().getVersion();
	}

	@Override
	public void setVersion(int version) {
		getInner().setVersion(version);
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public IPlayer getPlayer() {
		return getInner().getPlayer();
	}

	@Override
	public void setPlayer(IPlayer player) {
		getInner().setPlayer(player);
	}

	@Override
	public String getPilotName() {
		return getInner().getPilotName();
	}

	@Override
	public void setPilotName(String pilotName) {
		getInner().setPilotName(pilotName);
	}
	
	@Override
	public boolean equals(Object p) {
		return p instanceof IPilot && this.id==((IPilot)(p)).getId();
	}

}
