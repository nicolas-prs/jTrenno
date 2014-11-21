package com.cpe.jee.controllers;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.cpe.jee.beans.EmployeeModelBean;
import com.cpe.jee.beans.ProjectModelBean;
import com.cpe.jee.facade.EmployeeManagerLocal;
import com.cpe.jee.facade.ProjectManagerLocal;

@ManagedBean
@ApplicationScoped
public class ProjectController {

	@Inject
	private ProjectManagerLocal projectManager;

	public void addProject(ProjectModelBean e) throws Exception {
		try {
			projectManager.createProject(e.getBudget(), e.getDescription(), e.getName());
		} catch (Exception ex) {
			String errorMessage = getRootErrorMessage(ex);
		}
	}

	private String getRootErrorMessage(Exception e) {
		// Default to general error message that registration failed.
		String errorMessage = "Creation failed. See server log for more information";
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}
}