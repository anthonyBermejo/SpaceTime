package org.soen387.domain.model.team;

import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.Player;
import org.soen387.domain.model.player.mapper.PlayerMapper;
import org.soen387.domain.model.team.mapper.TeamMapper;

public class TeamProxy implements ITeam {

	long id;
	
	public TeamProxy(long id) {
		super();
		this.id = id;
	}

	public Team getInner() {
		try {
			return TeamMapper.find(id);
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
	public void setName(String name) {
		getInner().setName(name);
	}

	@Override
	public String getName() {
		return getInner().getName();
	}

	@Override
	public void setPlayer(IPlayer player) {
		getInner().setPlayer(player);
	}

	@Override
	public IPlayer getPlayer() {
		return getInner().getPlayer();
	}
	
	@Override
	public boolean equals(Object t) {
		return t instanceof ITeam && this.id==((ITeam)(t)).getId();
	}

}
