/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.fhir.api.db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.Order;
import org.openmrs.module.fhir.api.db.FHIRDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;

/**
 * It is a default implementation of  {@link FHIRDao}.
 */
public class HibernateFHIRDao implements FHIRDao {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private SessionFactory sessionFactory;

	/**
	 * @see FHIRDao#getOrdersByAccessionNumber(String)
	 */
	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public <Ord extends Order> List<Ord> getOrdersByAccessionNumber(String accessionNumber) {
		return getCurrentSession().createQuery("from Order o where o.accessionNumber = :accessionNumber").setString("accessionNumber", accessionNumber).list();
	}

	/**
	 * @see FHIRDao#getEncounterIdForObsOrder(int)
	 */
	@Override
	@Transactional(readOnly = true)
	public Integer getEncounterIdForObsOrder(final int orderId) {
		List<Integer> results = getCurrentSession().createSQLQuery("select distinct encounter_id from obs where order_id = :orderId")
				.setInteger("orderId", orderId)
				.setMaxResults(1)
				.list();

		if (results == null || results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}

	private Session getCurrentSession() {
		try {
			return sessionFactory.getCurrentSession();
		}  catch (NoSuchMethodError ex) {
			try {
				Method method = sessionFactory.getClass().getMethod("getCurrentSession", null);
				return (Session) method.invoke(sessionFactory, null);
			}
			catch (Exception e) {
				throw new RuntimeException("Failed to get the current hibernate session", e);
			}
		}
	}

	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	@SuppressWarnings("unused")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
