package patterns;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccessImplementation;

public class BlFactory {
	public DataAccessImplementation createBl() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		DataAccessImplementation dbManager;
		if(c.getDataBaseOpenMode().equals("initialize")) {
			dbManager=new DataAccessImplementation(true);
			dbManager.initializeDB();
		}
		else {
			dbManager=new DataAccessImplementation(false);
		}
		return dbManager;
	}
}
