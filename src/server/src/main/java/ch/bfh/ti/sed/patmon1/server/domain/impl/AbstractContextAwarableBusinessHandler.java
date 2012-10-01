package ch.bfh.ti.sed.patmon1.server.domain.impl;

import ch.bfh.ti.sed.patmon1.server.context.ApplicationContext;

public abstract class AbstractContextAwarableBusinessHandler {

	private ApplicationContext context;

	AbstractContextAwarableBusinessHandler(ApplicationContext context) {
		this.context = context;
	}

	protected ApplicationContext getApplicationContext() {
		return this.context;
	}
	
}
