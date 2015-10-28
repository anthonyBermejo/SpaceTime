package org.soen387.domain.model.team.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.PlayerProxy;
import org.soen387.domain.model.team.ITeam;
import org.soen387.domain.model.team.Team;
import org.soen387.domain.model.team.tdg.TeamTDG;

public class TeamMapper {
	public static ThreadLocal<Map<Long, Team>> IM = new ThreadLocal<Map<Long,Team>>() {
		protected java.util.Map<Long,Team> initialValue() {
			return new HashMap<Long, Team>();
		};
	};
	
	public static void insert(ITeam t) throws SQLException {
		TeamTDG.insert(t.getId(), t.getVersion(), t.getName(), t.getPlayer().getId());
	}

	public static void update(ITeam t) throws SQLException, LostUpdateException {
		int count = TeamTDG.update(t.getId(), t.getVersion(), t.getName(), t.getPlayer().getId());
		if(count==0) throw new LostUpdateException("Lost Update editing Team with id " + t.getId());
		t.setVersion(t.getVersion()+1);
	}
	
	public static void delete(ITeam t) throws SQLException, LostUpdateException {
		int count = TeamTDG.delete(t.getId(), t.getVersion());
		if(count==0) throw new LostUpdateException("Lost Update deleting Team with id " + t.getId());
		//
		// What's the process for deleting a Team... do we need to delete users and games?
		// More on that when we discuss referential integrity.
		//
	}

	public static Team find(long id) throws SQLException, DomainObjectNotFoundException {
		Team p = IM.get().get(id);
		if(p!=null) return p;
		
		ResultSet rs = TeamTDG.find(id);
		if(rs.next()) {
			p = buildTeam(rs);
			rs.close();
			IM.get().put(id, p);
			return p;
		}
		throw new DomainObjectNotFoundException("Could not create a Team with id \""+id+"\"");
	}
	
	public static List<ITeam> find(IPlayer p) throws SQLException, MapperException {
        try {
        	ResultSet rs = TeamTDG.findByPlayer(p.getId());
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}

	public static List<ITeam> buildCollection(ResultSet rs)
		    throws SQLException {
		    ArrayList<ITeam> l = new ArrayList<ITeam>();
		    while(rs.next()) {
		    	long id = rs.getLong("id");
		    	Team c = IM.get().get(id);
		    	if(c == null) {
		    		c = buildTeam(rs);
		    		IM.get().put(id, c);
		    	}
		    	l.add(c);
		    }
		    return l;
		}

	public static List<ITeam> findAll() throws MapperException {
        try {
            ResultSet rs = TeamTDG.findAll();
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	
	private static Team buildTeam(ResultSet rs) throws SQLException  {
		// TODO Auto-generated method stub
		return new Team(rs.getLong("id"),
				rs.getInt("version"),
				rs.getString("name"),
				new PlayerProxy(rs.getLong("player"))
				);
	}
	
}
