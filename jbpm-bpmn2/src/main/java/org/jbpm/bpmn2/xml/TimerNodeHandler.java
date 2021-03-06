/**
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.bpmn2.xml;

import org.drools.compiler.xml.XmlDumper;
import org.jbpm.workflow.core.Node;
import org.jbpm.workflow.core.node.TimerNode;
import org.xml.sax.Attributes;

public class TimerNodeHandler extends AbstractNodeHandler {
    
    protected Node createNode(Attributes attrs) {
        throw new IllegalArgumentException("Reading in should be handled by intermediate catch event handler");
    }
    
    @SuppressWarnings("unchecked")
	public Class generateNodeFor() {
        return TimerNode.class;
    }

	public void writeNode(Node node, StringBuilder xmlDump, int metaDataType) {
	    TimerNode timerNode = (TimerNode) node;
		writeNode("intermediateCatchEvent", timerNode, xmlDump, metaDataType);
		xmlDump.append(">" + EOL);
		xmlDump.append("      <timerEventDefinition>" + EOL);
		if (timerNode.getTimer() != null && timerNode.getTimer().getDelay() != null) {
		    xmlDump.append("        <timeCycle>" + XmlDumper.replaceIllegalChars(timerNode.getTimer().getDelay()) + "</timeCycle>" + EOL);
		}
		xmlDump.append("      </timerEventDefinition>" + EOL);
		endNode("intermediateCatchEvent", xmlDump);
	}

}
