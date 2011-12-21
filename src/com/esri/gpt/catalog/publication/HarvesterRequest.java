/* See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * Esri Inc. licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.esri.gpt.catalog.publication;

import com.esri.gpt.catalog.management.MmdEnums;
import com.esri.gpt.framework.context.RequestContext;
import com.esri.gpt.framework.security.principal.Publisher;
import com.esri.gpt.framework.util.Val;

/**
 * Publishes harvested metadata.
 */
public class HarvesterRequest extends PublicationRequest {

// class variables =============================================================

// instance variables ==========================================================

// constructors ================================================================
/**
 * Constructs a request to publish harvested metadata document.
 * @param requestContext the request context
 * @param publisher the publisher
 * @param sourceUri the URI of the source metadata
 * @param sourceXml the XML file content
 */
public HarvesterRequest(RequestContext requestContext,
                        Publisher publisher,
                        String siteUuid,
                        String sourceUri,
                        String sourceXml) {
  super(requestContext, publisher, sourceXml);
  String sMethod = MmdEnums.PublicationMethod.harvester.toString();
  getPublicationRecord().setPublicationMethod(sMethod);
  getPublicationRecord().setSiteUuid(siteUuid);
  getPublicationRecord().setSourceUri(sourceUri);
  getPublicationRecord().setSourceFileName(sourceUri);
  getPublicationRecord().setAutoApprove(true);
  
  String sUpdateIndex = Val.chkStr(requestContext.getApplicationConfiguration().getCatalogConfiguration().getParameters().getValue("webharvester.updateindex"));
  setUpdateIndex(Val.chkBool(sUpdateIndex, true));
}

// properties ==================================================================

// methods =====================================================================
/**
 * Gets published document uuid.
 * @return uuid of the published document
 */
public String getDocUuid() {
  return getPublicationRecord().getUuid();
}
}
