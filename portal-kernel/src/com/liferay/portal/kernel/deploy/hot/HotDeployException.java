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

package com.liferay.portal.kernel.deploy.hot;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * <a href="HotDeployException.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public class HotDeployException extends PortalException {

	public HotDeployException() {
		super();
	}

	public HotDeployException(String msg) {
		super(msg);
	}

	public HotDeployException(Throwable cause) {
		super(cause);
	}

	public HotDeployException(String msg, Throwable cause) {
		super(msg, cause);
	}

}