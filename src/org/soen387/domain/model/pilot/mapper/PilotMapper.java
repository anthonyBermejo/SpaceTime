package org.soen387.domain.model.pilot.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.model.pilot.IPilot;
import org.soen387.domain.model.pilot.Pilot;
import org.soen387.domain.model.pilot.tdg.PilotTDG;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.PlayerProxy;

public class PilotMapper {
	public static ThreadLocal<Map<Long, Pilot>> IM = new ThreadLocal<Map<Long,Pilot>>() {
		protected java.util.Map<Long,Pilot> initialValue() {
			return new HashMap<Long, Pilot>();
		};
	};
	
	public static void insert(IPilot p) throws SQLException {
		PilotTDG.insert(p.getId(), p.getVersion(), p.getPilotName(), p.getPlayer().getId());
	}

	public static void update(IPilot p) throws SQLException, LostUpdateException {
		int count = PilotTDG.update(p.getId(), p.getVersion(), p.getPilotName(), p.getPlayer().getId());
		if(count==0) throw new LostUpdateException("Lost Update editing Pilot with id " + p.getId());
		p.setVersion(p.getVersion()+1);
	}
	
	public static void delete(IPilot p) throws SQLException, LostUpdateException {
		int count = PilotTDG.delete(p.getId(), p.getVersion());
		if(count==0) throw new LostUpdateException("Lost Update deleting Pilot with id " + p.getId());
		//
		// What's the process for deleting a Pilot... do we need to delete users and games?
		// More on that when we discuss referential integrity.
		//
	}

	public static Pilot find(long id) throws SQLException, DomainObjectNotFoundException {
		Pilot p = IM.get().get(id);
		if(p!=null) return p;
		
		ResultSet rs = PilotTDG.find(id);
		if(rs.next()) {
			p = buildPilot(rs);
			rs.close();
			IM.get().put(id, p);
			return p;
		}
		throw new DomainObjectNotFoundException("Could not create a Pilot with id \""+id+"\"");
	}
	
	public static Pilot find(IPlayer p) throws SQLException, DomainObjectNotFoundException {
		ResultSet rs = PilotTDG.findByPlayer(p.getId());
		if(rs.next()) {
			long id = rs.getLong("id");
			Pilot pilot = IM.get().get(id);
			if(p!=null) return pilot;
			pilot = buildPilot(rs);
			rs.close();
			IM.get().put(id, pilot);
			return pilot;
		}
		throw new DomainObjectNotFoundException("Could not create a Pilot from Player with id \""+p.getId()+"\"");
	}

	public static List<IPilot> buildCollection(ResultSet rs)
		    throws SQLException {
		    ArrayList<IPilot> l = new ArrayList<IPilot>();
		    while(rs.next()) {
		    	long id = rs.getLong("id");
		    	Pilot c = IM.get().get(id);
		    	if(c == null) {
		    		c = buildPilot(rs);
		    		IM.get().put(id, c);
		    	}
		    	l.add(c);
		    }
		    return l;
		}

	public static List<IPilot> findAll() throws MapperException {
        try {
            ResultSet rs = PilotTDG.findAll();
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	
	private static Pilot buildPilot(ResultSet rs) throws SQLException  {
		// TODO Auto-generated method stub
		return new Pilot(rs.getLong("id"),
				rs.getInt("version"),
				rs.getString("pilotname"),
				new PlayerProxy(rs.getLong("player"))
				);
	}
	
}
