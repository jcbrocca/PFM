package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Platform;

public class PlatformDaoImpl extends HibernateDaoSupport implements PlatformDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Platform> getAllPlatforms() {
		return getHibernateTemplate().find("from Platform as Platform ");
	}

	@Override
	public Platform getPlatform(int idPlatform) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Platform.class);
		criteria.add(Restrictions.eq("idPlatform", idPlatform));
		return (Platform) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}
}