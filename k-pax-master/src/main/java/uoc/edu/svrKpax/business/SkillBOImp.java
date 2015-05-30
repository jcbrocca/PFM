package uoc.edu.svrKpax.business;

import java.util.List;

import uoc.edu.svrKpax.dao.SkillDao;
import uoc.edu.svrKpax.vo.Skill;

public class SkillBOImp implements SkillBO {

	private SessionBO sBo;
	private SkillDao skillDao;

	@Override
	public List<Skill> listSkills(String campusSession) {
		if (sBo.validateSession(campusSession) != null) {
			return skillDao.getAllSkills();
		}
		return null;
	}
	
	@Override
	public Skill getSkill(String campusSession, int idSkill) {
		if (sBo.validateSession(campusSession) != null) {
			return skillDao.getSkill(idSkill);
		}
		return null;
	}

	public SkillDao getskillDao() {
		return skillDao;
	}

	public void setskillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public SessionBO getsBo() {
		return sBo;
	}

}