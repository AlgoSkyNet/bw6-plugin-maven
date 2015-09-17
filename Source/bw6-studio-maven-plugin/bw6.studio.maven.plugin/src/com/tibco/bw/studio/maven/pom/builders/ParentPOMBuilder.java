package com.tibco.bw.studio.maven.pom.builders;

import java.util.List;

import org.apache.maven.model.Model;

import com.tibco.bw.studio.maven.helpers.ModuleOrderBuilder;
import com.tibco.bw.studio.maven.modules.BWModule;
import com.tibco.bw.studio.maven.modules.BWProject;

public class ParentPOMBuilder extends AbstractPOMBuilder implements IPOMBuilder 
{

	@Override
	public void build(BWProject project, BWModule module) throws Exception
	{
		this.project = project;
		this.module = module;
		this.model = new Model();
		
		addPrimaryTags();
		model.setGroupId( module.getGroupId());
		model.setVersion( module.getVersion() );
		addProperties();
		addModules();
		generatePOMFile();

	}

	@Override
	protected String getPackaging() 
	{
		return "pom";
	}
	
	protected void addModules()
	{
		ModuleOrderBuilder builder = new ModuleOrderBuilder();
		List<String> list = builder.getDependencyOrder(project);
		for( String str : list )
		{
			model.getModules().add(str);
		}
	}

}
