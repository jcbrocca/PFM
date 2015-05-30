package uoc.edu.svrKpax.business;

import java.util.List;

import uoc.edu.svrKpax.dao.PlatformDao;
import uoc.edu.svrKpax.vo.Platform;

public class PlatformBOImp implements PlatformBO {

	private SessionBO sBo;
	private PlatformDao platDao;

	@Override
	public List<Platform> listPlatforms(String campusSession) {
		if (sBo.validateSession(campusSession) != null) {
			return platDao.getAllPlatforms();
		}
		return null;
	}
	
	@Override
	public Platform getPlatform(String campusSession, int idPlatform) {
		if (sBo.validateSession(campusSession) != null) {
			return platDao.getPlatform(idPlatform);
		}
		return null;
	}

	public PlatformDao getplatDao() {
		return platDao;
	}

	public void setplatDao(PlatformDao platDao) {
		this.platDao = platDao;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public SessionBO getsBo() {
		return sBo;
	}

}