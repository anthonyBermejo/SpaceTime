package org.soen387.test;

import java.sql.SQLException;

import org.soen387.app.AbstractPageController;
import org.soen387.domain.model.pilot.tdg.PilotTDG;
import org.soen387.domain.model.player.tdg.PlayerTDG;
import org.soen387.domain.model.user.tdg.UserTDG;

public class Teardown {

	public static void main(String[] args) throws InterruptedException {
		AbstractPageController.setupDb();
		try {
			PlayerTDG.dropTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			UserTDG.dropTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PilotTDG.dropTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
