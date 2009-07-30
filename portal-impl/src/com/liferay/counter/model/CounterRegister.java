/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.counter.model;

import com.liferay.portal.kernel.concurrent.CompeteLatch;

/**
 * <a href="CounterRegister.java.html"><b><i>View Source</i></b></a>
 *
 * @author Harry Mark
 * @author Shuyang Zhou
 */
public class CounterRegister {

	public CounterRegister(
		String name, long rangeMin, long rangeMax, int rangeSize) {

		_name = name;
		_rangeSize = rangeSize;
		_holder = new CounterHolder(rangeMin, rangeMax);
		_latch = new CompeteLatch();
	}

	public CompeteLatch getCompeteLatch() {
		return _latch;
	}

	public CounterHolder getCounterHolder() {
		return _holder;
	}

	public String getName() {
		return _name;
	}

	public int getRangeSize() {
		return _rangeSize;
	}

	public void setCounterHolder(CounterHolder holder) {
		_holder = holder;
	}

	public void setName(String name) {
		_name = name;
	}

	private volatile CounterHolder _holder;
	private final CompeteLatch _latch;
	private String _name;
	private final int _rangeSize;

}