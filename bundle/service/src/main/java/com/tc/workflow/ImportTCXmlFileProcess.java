package com.tc.workflow;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import com.tc.service.api.TCFileImportService;

@Component(label = "XML Import workflow", description = "XML Import workflow", immediate = true, metatype = true)
@Properties({
		@Property(name = "service.pid", value = "com.tc.workflow.ImportTCXmlFileProcess", propertyPrivate = false),
		@Property(name = "service.description", value = "Workflow service to import xml file as node", propertyPrivate = false) })
@Service({ WorkflowProcess.class })
public class ImportTCXmlFileProcess implements WorkflowProcess  {
	private static final Logger LOG = LoggerFactory
			.getLogger(ImportTCXmlFileProcess.class);
	
	@Reference(referenceInterface = TCFileImportService.class, bind = "setXMLImportService", unbind = "unXMLImportService", cardinality = ReferenceCardinality.MANDATORY_UNARY)
	private TCFileImportService xmlImportService;
	protected void setXMLImportService(TCFileImportService xmlImportService) {
		if (this.xmlImportService == null) {
			this.xmlImportService = xmlImportService;
		}
	}

	protected void unXMLImportService(TCFileImportService xmlImportService) {
		if (this.xmlImportService != null) {
			this.xmlImportService = null;
		}
	}	
	@Override
	public void execute(WorkItem workItem, WorkflowSession wfSession, MetaDataMap metaDataMap)
			throws WorkflowException {
		try {
			String xmlFile = workItem.getWorkflowData().getPayload().toString();
			/*
			 *  TO DO for now hardcoding the parent location, in real scenario the parent
			 *  must be the site path 
			 */
			String parentPath = "/content/tc/laction/cp";
			
			
			LOG.info("Payload in ImportTCXmlFileProcess is {} ", xmlFile);
			if (StringUtils.contains(xmlFile, ".xml") || StringUtils.contains(xmlFile, ".zip")) {
				int indexOfJcrContent = xmlFile.indexOf("/jcr:content");
				if (indexOfJcrContent != -1) {
					xmlFile = xmlFile.substring(0, indexOfJcrContent);
				}
				
				LOG.info("Actual file location " + xmlFile);
				
				xmlImportService.importFile(parentPath, xmlFile);
				
				/*
				 *  if workflow is not terminated then the workflow instances are still in RUNNING state
				 *  and prohibiting the import process when same file is uploaded again.
				 */
				
				wfSession.terminateWorkflow(workItem.getWorkflow());
			} else {
				LOG.info(xmlFile + " is ignored by ImportTCXmlFileProcess");
			}
		} catch (Exception e) {
			LOG.error("Error in ImportTCXmlFileProcess", e);
		}
		
		
		
		
	}

}
