package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Skill;

public class SkillDaoImpl extends HibernateDaoSupport implements SkillDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> getAllSkills() {
		return getHibernateTemplate().find("from Skill as Skill ");
	}

	@Override
	public Skill getSkill(int idSkill) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Skill.class);
		criteria.add(Restrictions.eq("idSkill", idSkill));
		return (Skill) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}
}