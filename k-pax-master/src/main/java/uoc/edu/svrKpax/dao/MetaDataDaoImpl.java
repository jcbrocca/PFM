package uoc.edu.svrKpax.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.MetaData;

public class MetaDataDaoImpl extends HibernateDaoSupport implements MetaDataDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MetaData> getAllMetaDatasGame(int idGame) {
		List<MetaData> list = new ArrayList<MetaData>();
		
		Criteria criteria = getSession().createCriteria(MetaData.class, "metadata");
		criteria.createAlias("metadata.game", "game");
		criteria.add(Restrictions.eq("game.idGame", idGame));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		list = criteria.list();
        return list;
	}

	@Override
	public List<MetaData> getAllMetaDatas() {
		Criteria criteria = getSession().createCriteria(MetaData.class);
		criteria.setProjection(Projections.distinct(Projections.property("keyMeta")));
		@SuppressWarnings("unchecked")
		List<MetaData> list = criteria.list();
        return list;
	}
	
	@Override
	public MetaData getMetaData(int idMetaData) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MetaData.class);
		criteria.add(Restrictions.eq("idMetaData", idMetaData));
		return (MetaData) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void addMetaDataGame(MetaData metaData) {
		getHibernateTemplate().saveOrUpdate(metaData);
	}

	@Override
	public void delMetaDataGame(MetaData metaData) {
		getHibernateTemplate().delete(metaData);
	}

}
