package uoc.edu.svrKpax.business;

import java.util.List;

import uoc.edu.svrKpax.vo.MetaData;

public interface MetaDataBO {

	public List<MetaData> listMetaDatasGame(String campusSession, int idGame);

	public List<MetaData> listMetaDatas(String campusSession);
	
	public Boolean addMetaDatasGame(String campusSession, int idGame,
			List<MetaData> metaDatas);

	public Boolean delMetaDatasGame(String campusSession, int idGame);

}
