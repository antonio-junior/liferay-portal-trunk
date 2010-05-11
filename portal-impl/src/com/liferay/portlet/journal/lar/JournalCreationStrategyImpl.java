/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.journal.lar;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portlet.journal.model.JournalArticle;

/**
 * <a href="JournalCreationStrategyImpl.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * Provides the strategy for creating new content when new Journal content is
 * imported into a layout set from a LAR. The default strategy implemented by
 * this class is to return zero for the author and approval user ids, which
 * causes the default user id import strategy to be used. Content will be added
 * as is with no transformations.
 * </p>
 *
 * @author Joel Kozikowski
 * @see	   com.liferay.portlet.journal.lar.JournalContentPortletDataHandlerImpl
 * @see	   com.liferay.portlet.journal.lar.JournalPortletDataHandlerImpl
 */
public class JournalCreationStrategyImpl implements JournalCreationStrategy {

	public long getAuthorUserId(PortletDataContext context, Object journalObj)
		throws Exception {

		return JournalCreationStrategy.USE_DEFAULT_USER_ID_STRATEGY;
	}

	public String getTransformedContent(
			PortletDataContext context, JournalArticle newArticle)
		throws Exception {

		return JournalCreationStrategy.ARTICLE_CONTENT_UNCHANGED;
	}

	public boolean addCommunityPermissions(
			PortletDataContext context, Object journalObj)
		throws Exception {

		return true;
	}

	public boolean addGuestPermissions(
			PortletDataContext context, Object journalObj)
		throws Exception {

		return true;
	}

}