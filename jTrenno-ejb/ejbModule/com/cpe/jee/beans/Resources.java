package com.cpe.jee.beans;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

public class Resources {
	// Expose an entity manager using the resource producer pattern
	@SuppressWarnings("unused")
	@PersistenceContext
	@Produces
	private EntityManager em;

	@Produces
	Logger getLogger(InjectionPoint ip) {
		String category = ip.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}
}