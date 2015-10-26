package org.soen387.domain.model.pilot;

import org.soen387.domain.model.player.IPlayer;

public interface IPilot {
	public abstract int getVersion();

	public abstract void setVersion(int version);
	
	public abstract long getId();
	
	public abstract IPlayer getPlayer();

	public abstract void setPlayer(IPlayer player);
	
	public abstract String getPilotName();

	public abstract void setPilotName(String pilotName);
}
