package org.soen387.domain.model.team;

import org.soen387.domain.model.player.IPlayer;

public interface ITeam {
	public abstract int getVersion();

	public abstract void setVersion(int version);
	
	public abstract long getId();
	
	public abstract void setName(String name);
	
	public abstract String getName();
	
	public abstract void setPlayer(IPlayer player);
	
	public abstract IPlayer getPlayer();
}
