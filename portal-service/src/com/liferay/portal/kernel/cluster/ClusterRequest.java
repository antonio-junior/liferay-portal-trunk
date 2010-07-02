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

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.util.MethodWrapper;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <a href="ClusterRequest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Tina Tian
 */
public class ClusterRequest implements Serializable {

	public static ClusterRequest createClusterRequest(
		ClusterMessageType clusterMessageType,
		ClusterNode originatingClusterNode) {

		ClusterRequest clusterRequest = new ClusterRequest();

		clusterRequest.setClusterMessageType(clusterMessageType);
		clusterRequest.setMulticast(true);
		clusterRequest.setOriginatingClusterNode(originatingClusterNode);
		clusterRequest.setSkipLocal(false);
		clusterRequest.setUuid(PortalUUIDUtil.generate());

		return clusterRequest;
	}

	public static ClusterRequest createMulticastRequest(
		MethodWrapper methodWrapper) {

		return createMulticastRequest(methodWrapper, false);
	}

	public static ClusterRequest createMulticastRequest(
		MethodWrapper methodWrapper, boolean skipLocal) {

		ClusterRequest clusterRequest = new ClusterRequest();

		clusterRequest.setClusterMessageType(ClusterMessageType.EXECUTE);
		clusterRequest.setMethodWrapper(methodWrapper);
		clusterRequest.setMulticast(true);
		clusterRequest.setSkipLocal(skipLocal);
		clusterRequest.setUuid(PortalUUIDUtil.generate());

		return clusterRequest;
	}

	public static ClusterRequest createMulticastRequest(
		MethodWrapper methodWrapper, long timeOut) {
		return createMulticastRequest(methodWrapper, timeOut, false);
	}

	public static ClusterRequest createMulticastRequest(
		MethodWrapper methodWrapper, long timeOut, boolean skipLocal) {

		ClusterRequest clusterRequest = new ClusterRequest();

		clusterRequest.setClusterMessageType(ClusterMessageType.EXECUTE);
		clusterRequest.setMethodWrapper(methodWrapper);
		clusterRequest.setMulticast(true);
		clusterRequest.setSkipLocal(skipLocal);
		clusterRequest.setTimeOut(timeOut);
		clusterRequest.setUuid(PortalUUIDUtil.generate());

		return clusterRequest;
	}

	public static ClusterRequest createUnicastRequest(
		MethodWrapper methodWrapper, long timeOut,
		String... targetClusterNodeIds) {

		ClusterRequest clusterRequest = new ClusterRequest();

		clusterRequest.addTargetClusterNodeIds(targetClusterNodeIds);
		clusterRequest.setClusterMessageType(ClusterMessageType.EXECUTE);
		clusterRequest.setMethodWrapper(methodWrapper);
		clusterRequest.setMulticast(false);
		clusterRequest.setSkipLocal(false);
		clusterRequest.setTimeOut(timeOut);
		clusterRequest.setUuid(PortalUUIDUtil.generate());

		return clusterRequest;
	}

	public static ClusterRequest createUnicastRequest(
		MethodWrapper methodWrapper, String... targetClusterNodeIds) {

		ClusterRequest clusterRequest = new ClusterRequest();

		clusterRequest.addTargetClusterNodeIds(targetClusterNodeIds);
		clusterRequest.setClusterMessageType(ClusterMessageType.EXECUTE);
		clusterRequest.setMethodWrapper(methodWrapper);
		clusterRequest.setMulticast(false);
		clusterRequest.setSkipLocal(false);
		clusterRequest.setUuid(PortalUUIDUtil.generate());

		return clusterRequest;
	}

	public void addTargetClusterNodeIds(String... targetClusterNodeIds) {
		if (_targetClusterNodeIds == null) {
			_targetClusterNodeIds = new HashSet<String>();
		}

		_targetClusterNodeIds.addAll(Arrays.asList(targetClusterNodeIds));
	}

	public ClusterMessageType getClusterMessageType() {
		return _clusterMessageType;
	}

	public MethodWrapper getMethodWrapper() {
		return _methodWrapper;
	}

	public ClusterNode getOriginatingClusterNode() {
		return _originatingClusterNode;
	}

	public Collection<String> getTargetClusterNodeIds() {
		return _targetClusterNodeIds;
	}

	public long getTimeOut() {
		return _timeOut;
	}

	public TimeUnit getTimeUnit() {
		return _timeUnit;
	}

	public String getUuid() {
		return _uuid;
	}

	public boolean isFireAndForget() {
		return _fireAndForget;
	}

	public boolean isMulticast() {
		return _multicast;
	}

	public boolean isSkipLocal() {
		return _skipLocal;
	}

	public void setClusterMessageType(ClusterMessageType clusterMessageType) {
		_clusterMessageType = clusterMessageType;
	}

	public void setFireAndForget(boolean fireAndForget) {
		_fireAndForget = fireAndForget;
	}

	public void setMethodWrapper(MethodWrapper methodWrapper) {
		_methodWrapper = methodWrapper;
	}

	public void setMulticast(boolean multicast) {
		_multicast = multicast;
	}

	public void setOriginatingClusterNode(ClusterNode originatingClusterNode) {
		_originatingClusterNode = originatingClusterNode;
	}

	public void setSkipLocal(boolean skipLocal) {
		_skipLocal = skipLocal;
	}

	public void setTimeOut(long timeOut) {
		_timeOut = timeOut;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		_timeUnit = timeUnit;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{clusterMessageType=");
		sb.append(_clusterMessageType);
		sb.append(", multicast=");
		sb.append(_multicast);
		sb.append(", skipLocal=");
		sb.append(_skipLocal);
		sb.append(", uuid=");
		sb.append(_uuid);

		if (_clusterMessageType.equals(ClusterMessageType.NOTIFY) ||
			_clusterMessageType.equals(ClusterMessageType.UPDATE)) {

			sb.append(", originatingClusterNode=");
			sb.append(_originatingClusterNode);
		}
		else {
			sb.append(", methodWrapper=");
			sb.append(_methodWrapper);
		}

		sb.append("}");

		return sb.toString();
	}

	private ClusterRequest() {
	}

	private ClusterMessageType _clusterMessageType;
	private boolean _fireAndForget;
	private MethodWrapper _methodWrapper;
	private boolean _multicast;
	private ClusterNode _originatingClusterNode;
	private boolean _skipLocal;
	private Set<String> _targetClusterNodeIds;
	private long _timeOut;
	private TimeUnit _timeUnit = TimeUnit.MILLISECONDS;
	private String _uuid;

}