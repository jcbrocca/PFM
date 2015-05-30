package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.Skill;

public interface SkillDao {
	
	public List<Skill> getAllSkills();
	public Skill getSkill(int idSkill);
}

