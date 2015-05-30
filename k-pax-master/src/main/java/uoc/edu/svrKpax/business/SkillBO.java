package uoc.edu.svrKpax.business;

import java.util.List;

import uoc.edu.svrKpax.vo.Skill;

public interface SkillBO {

	public List<Skill> listSkills(String campusSession);
	public Skill getSkill(String campusSession, int idSkill);

}
