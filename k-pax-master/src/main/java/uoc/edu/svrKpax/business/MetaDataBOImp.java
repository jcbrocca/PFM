package uoc.edu.svrKpax.business;

import java.util.List;

import uoc.edu.svrKpax.dao.GameDao;
import uoc.edu.svrKpax.dao.MetaDataDao;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.MetaData;
import uoc.edu.svrKpax.vo.User;

public class MetaDataBOImp implements MetaDataBO {

	private SessionBO sBo;
	private MetaDataDao mDao;
	private GameDao gDao;

	@Override
	public List<MetaData> listMetaDatasGame(String campusSession, int idGame) {
		if (sBo.validateSession(campusSession) != null) {
			Game objGame = gDao.getGame(idGame);
			if(objGame != null)
			{
				return mDao.getAllMetaDatasGame(idGame);
			}
		}
		return null;
	}

	@Override
	public List<MetaData> listMetaDatas(String campusSession) {
		if (sBo.validateSession(campusSession) != null) {
			return mDao.getAllMetaDatas();
		}
		return null;
	}
	
	@Override
	public Boolean addMetaDatasGame(String campusSession, int idGame,
			List<MetaData> metaDatas) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null)
			{
				Game objGame = gDao.getGame(idGame);
				if(objGame != null)
				{
					//Delete existing metadata
					List<MetaData> metaDataToDelete = mDao.getAllMetaDatasGame(idGame);
					for(MetaData objMetaData : metaDataToDelete)
					{
						mDao.delMetaDataGame(objMetaData);
					}
					
					//Insert new metadata
					for(MetaData metaData : metaDatas)
					{
						MetaData objMetaData = new MetaData();
						objMetaData.setGame(objGame);
						objMetaData.setKeyMeta(metaData.getKeyMeta());
						objMetaData.setValueMeta(metaData.getValueMeta());
	
						mDao.addMetaDataGame(objMetaData);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean delMetaDatasGame(String campusSession, int idGame) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {
				Game objGame = gDao.getGame(idGame);
				if(objGame != null)
				{
					List <MetaData> metaDatas = mDao.getAllMetaDatasGame(idGame);
					if(metaDatas != null)
					{
						for(MetaData metaData : metaDatas)
						{
							mDao.delMetaDataGame(metaData);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public MetaDataDao getmDao() {
		return mDao;
	}

	public void setmDao(MetaDataDao mDao) {
		this.mDao = mDao;
	}

	public GameDao getgDao() {
		return gDao;
	}

	public void setgDao(GameDao gDao) {
		this.gDao = gDao;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public SessionBO getsBo() {
		return sBo;
	}

}