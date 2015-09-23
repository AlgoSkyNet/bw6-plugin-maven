package com.tibco.bw.studio.maven.pom.builders;

import org.apache.maven.model.Build;
import org.apache.maven.model.Model;

import com.tibco.bw.studio.maven.helpers.ModuleHelper;
import com.tibco.bw.studio.maven.modules.BWModule;
import com.tibco.bw.studio.maven.modules.BWProject;

public class ApplicationPOMBuilder extends AbstractPOMBuilder implements IPOMBuilder 
{

	@Override
	public void build(BWProject project, BWModule module) throws Exception
	{
		if( !module.isOverridePOM() )
		{
			return;
		}
		this.project = project;
		this.module = module;
		initializeModel();
		
		addPrimaryTags();
		addParent( ModuleHelper.getParentModule( project.getModules() ));
		addBuild();
		addProperties();
		
		generatePOMFile();
		
	}
	
	protected void addBuild()
	{
    	Build build = new Build();

    	addBW6MavenPlugin( build );
    	
    	model.setBuild(build);
	}


	@Override
	protected String getPackaging() 
	{
		return "bwear";
	}

}
