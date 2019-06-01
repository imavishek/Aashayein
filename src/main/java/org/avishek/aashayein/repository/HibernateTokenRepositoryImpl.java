package org.avishek.aashayein.repository;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.avishek.aashayein.entities.PersistentLogin;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HibernateTokenRepositoryImpl implements PersistentTokenRepository {

	@Autowired
	SessionFactory sessionFactory;

	private static final Logger logger = LogManager.getLogger(HibernateTokenRepositoryImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {

		logger.info("Creating Token for user : " + token.getUsername());

		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLastUsed(token.getDate());
		sessionFactory.getCurrentSession().persist(persistentLogin);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {

		logger.info("Updating Token for series : " + series);

		PersistentLogin persistentLogin = (PersistentLogin) sessionFactory.getCurrentSession()
				.get(PersistentLogin.class, series);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLastUsed(lastUsed);
		sessionFactory.getCurrentSession().update(persistentLogin);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String series) {

		logger.info("Fetch Token if any for series : " + series);

		PersistentLogin persistentLogin = (PersistentLogin) sessionFactory.getCurrentSession()
				.get(PersistentLogin.class, series);

		if (persistentLogin == null)
			return null;

		return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
				persistentLogin.getToken(), persistentLogin.getLastUsed());
	}

	@Override
	public void removeUserTokens(String username) {

		logger.info("Removing Token if any for user : " + username);

		String hql = "DELETE FROM PersistentLogin persistentLogin WHERE persistentLogin.username=?1";
		Query<?> query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(1, username);
		Integer recordDeleted = query.executeUpdate();

		if (recordDeleted > 0) {
			logger.info("RememberMe was delected for user : " + username);
		}
	}

}
