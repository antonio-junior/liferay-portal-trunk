/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portlet.wiki.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.lucene.LuceneFields;
import com.liferay.portal.lucene.LuceneUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.ResourceImpl;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.wiki.NodeNameException;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiNodeLocalService;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.service.persistence.WikiNodeUtil;
import com.liferay.portlet.wiki.service.persistence.WikiPageUtil;
import com.liferay.portlet.wiki.util.Indexer;
import com.liferay.util.Validator;
import com.liferay.util.lucene.HitsImpl;

import java.io.IOException;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;

/**
 * <a href="WikiNodeLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 * @author  Charles May
 *
 */
public class WikiNodeLocalServiceImpl implements WikiNodeLocalService {

	public WikiNode addNode(
			String userId, String plid, String name, String description,
			boolean addCommunityPermissions, boolean addGuestPermissions)
		throws PortalException, SystemException {

		return addNode(
			userId, plid, name, description,
			new Boolean(addCommunityPermissions),
			new Boolean(addGuestPermissions), null, null);
	}

	public WikiNode addNode(
			String userId, String plid, String name, String description,
			String[] communityPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		return addNode(
			userId, plid, name, description, null, null, communityPermissions,
			guestPermissions);
	}

	public WikiNode addNode(
			String userId, String plid, String name, String description,
			Boolean addCommunityPermissions, Boolean addGuestPermissions,
			String[] communityPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		// Node

		User user = UserUtil.findByPrimaryKey(userId);
		String groupId = PortalUtil.getPortletGroupId(plid);
		Date now = new Date();

		validate(name);

		String nodeId = Long.toString(CounterLocalServiceUtil.increment(
			WikiNode.class.getName()));

		WikiNode node = WikiNodeUtil.create(nodeId);

		node.setGroupId(groupId);
		node.setCompanyId(user.getCompanyId());
		node.setUserId(user.getUserId());
		node.setUserName(user.getFullName());
		node.setCreateDate(now);
		node.setModifiedDate(now);
		node.setName(name);
		node.setDescription(description);

		WikiNodeUtil.update(node);

		// Resources

		if ((addCommunityPermissions != null) &&
			(addGuestPermissions != null)) {

			addNodeResources(
				node, addCommunityPermissions.booleanValue(),
				addGuestPermissions.booleanValue());
		}
		else {
			addNodeResources(node, communityPermissions, guestPermissions);
		}

		return node;
	}

	public void addNodeResources(
			String nodeId, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		WikiNode node = WikiNodeUtil.findByPrimaryKey(nodeId);

		addNodeResources(node, addCommunityPermissions, addGuestPermissions);
	}

	public void addNodeResources(
			WikiNode node, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		ResourceLocalServiceUtil.addResources(
			node.getCompanyId(), node.getGroupId(), node.getUserId(),
			WikiNode.class.getName(), node.getPrimaryKey().toString(), false,
			addCommunityPermissions, addGuestPermissions);
	}

	public void addNodeResources(
			String nodeId, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		WikiNode node = WikiNodeUtil.findByPrimaryKey(nodeId);

		addNodeResources(node, communityPermissions, guestPermissions);
	}

	public void addNodeResources(
			WikiNode node, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		ResourceLocalServiceUtil.addModelResources(
			node.getCompanyId(), node.getGroupId(), node.getUserId(),
			WikiNode.class.getName(), node.getPrimaryKey().toString(),
			communityPermissions, guestPermissions);
	}

	public void deleteNode(String nodeId)
		throws PortalException, SystemException {

		WikiNode node = WikiNodeUtil.findByPrimaryKey(nodeId);

		deleteNode(node);
	}

	public void deleteNode(WikiNode node)
		throws PortalException, SystemException {

		// Lucene

		try {
			Indexer.deletePages(node.getCompanyId(), node.getNodeId());
		}
		catch (IOException ioe) {
			_log.error("Deleting index " + node.getNodeId(), ioe);
		}
		catch (ParseException pe) {
			_log.error("Deleting index " + node.getNodeId(), pe);
		}

		// Pages

		WikiPageLocalServiceUtil.deletePages(node.getNodeId());

		// Resources

		ResourceLocalServiceUtil.deleteResource(
			node.getCompanyId(), WikiNode.class.getName(),
			ResourceImpl.TYPE_CLASS, ResourceImpl.SCOPE_INDIVIDUAL,
			node.getPrimaryKey().toString());

		// Node

		WikiNodeUtil.remove(node.getNodeId());
	}

	public void deleteNodes(String groupId)
		throws PortalException, SystemException {

		Iterator itr = WikiNodeUtil.findByGroupId(groupId).iterator();

		while (itr.hasNext()) {
			WikiNode node = (WikiNode)itr.next();

			deleteNode(node);
		}
	}

	public WikiNode getNode(String nodeId)
		throws PortalException, SystemException {

		return WikiNodeUtil.findByPrimaryKey(nodeId);
	}

	public List getNodes(String groupId) throws SystemException {
		return WikiNodeUtil.findByGroupId(groupId);
	}

	public List getNodes(String groupId, int begin, int end)
		throws SystemException {

		return WikiNodeUtil.findByGroupId(groupId, begin, end);
	}

	public int getNodesCount(String groupId) throws SystemException {
		return WikiNodeUtil.countByGroupId(groupId);
	}

	public void reIndex(String[] ids) throws SystemException {
		try {
			String companyId = ids[0];

			Iterator itr1 = WikiNodeUtil.findByCompanyId(companyId).iterator();

			while (itr1.hasNext()) {
				WikiNode node = (WikiNode)itr1.next();

				String nodeId = node.getNodeId();

				Iterator itr2 = WikiPageUtil.findByNodeId(nodeId).iterator();

				while (itr2.hasNext()) {
					WikiPage page = (WikiPage)itr2.next();

					String groupId = node.getGroupId();
					String title = page.getTitle();
					String content = page.getContent();

					try {
						Indexer.addPage(
							companyId, groupId, nodeId, title, content);
					}
					catch (Exception e1) {
						_log.error("Reindexing " + page.getPrimaryKey(), e1);
					}
				}
			}
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e2) {
			throw new SystemException(e2);
		}
	}

	public Hits search(
			String companyId, String groupId, String[] nodeIds, String keywords)
		throws SystemException {

		try {
			HitsImpl hits = new HitsImpl();

			if (Validator.isNull(keywords)) {
				return hits;
			}

			BooleanQuery contextQuery = new BooleanQuery();

			LuceneUtil.addRequiredTerm(
				contextQuery, LuceneFields.PORTLET_ID, Indexer.PORTLET_ID);
			LuceneUtil.addRequiredTerm(
				contextQuery, LuceneFields.GROUP_ID, groupId);

			if ((nodeIds != null) && (nodeIds.length > 0)) {
				BooleanQuery nodeIdsQuery = new BooleanQuery();

				for (int i = 0; i < nodeIds.length; i++) {
					Term term = new Term("nodeId", nodeIds[i]);
					TermQuery termQuery = new TermQuery(term);

					nodeIdsQuery.add(termQuery, BooleanClause.Occur.SHOULD);
				}

				contextQuery.add(nodeIdsQuery, BooleanClause.Occur.MUST);
			}

			BooleanQuery searchQuery = new BooleanQuery();

			LuceneUtil.addTerm(searchQuery, LuceneFields.CONTENT, keywords);

			BooleanQuery fullQuery = new BooleanQuery();

			fullQuery.add(contextQuery, BooleanClause.Occur.MUST);
			fullQuery.add(searchQuery, BooleanClause.Occur.MUST);

			Searcher searcher = LuceneUtil.getSearcher(companyId);

			hits.recordHits(searcher.search(fullQuery));

			return hits;
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		catch (ParseException pe) {
			_log.error("Parsing keywords " + keywords, pe);

			return new HitsImpl();
		}
	}

	public WikiNode updateNode(String nodeId, String name, String description)
		throws PortalException, SystemException {

		validate(name);

		WikiNode node = WikiNodeUtil.findByPrimaryKey(nodeId);

		node.setModifiedDate(new Date());
		node.setName(name);
		node.setDescription(description);

		WikiNodeUtil.update(node);

		return node;
	}

	protected void validate(String name) throws PortalException {
		if (!Validator.isName(name)) {
			throw new NodeNameException();
		}
	}

	private static Log _log = LogFactory.getLog(WikiNodeLocalServiceImpl.class);

}