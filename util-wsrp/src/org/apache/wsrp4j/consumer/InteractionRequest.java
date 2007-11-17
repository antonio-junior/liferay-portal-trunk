/*
 * Copyright 2000-2001,2004 The Apache Software Foundation.
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

package org.apache.wsrp4j.consumer;

import oasis.names.tc.wsrp.v1.types.NamedString;
import oasis.names.tc.wsrp.v1.types.UploadContext;

/**
 * This is the abstraction for a interaction request at a integrated
 * remote portlet at the consumer side.
 * 
 **/
public interface InteractionRequest extends WSRPBaseRequest {
	/**
	 * Get all the interaction state
	 * 
	 * @return Interaction state
	 **/
	public String getInteractionState();

	/**
	 * Get all name/value pairs aa result of processing an form
	 * 
	 * @return Array of name/value pairs
	 **/
	public NamedString[] getFormParameters();
	
	public UploadContext[] getUploadContexts();
}