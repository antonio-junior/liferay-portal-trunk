/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
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

package com.liferay.counter.service.persistence;

import com.liferay.counter.model.Counter;
import com.liferay.portal.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.spring.hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * <a href="CounterPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class CounterPersistence extends BasePersistence {

	public CounterPersistence() {
	}

	public List getNames() throws SystemException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();

			List list = new ArrayList();

			Query q = session.createQuery("FROM " + Counter.class.getName());

			Iterator itr = q.iterate();

			while (itr.hasNext()) {
				Counter counter = (Counter)itr.next();

				list.add(counter.getName());
			}

			Collections.sort(list);

			return list;
		}
		catch (HibernateException he) {
			throw new SystemException(he);
		}
		finally {
			session.close();
		}
	}

	public long increment(String name)
		throws SystemException {

		return increment(name, _MINIMUM_INCREMENT_SIZE);
	}

	public synchronized long increment(String name, int size)
		throws SystemException {

		if (size < _MINIMUM_INCREMENT_SIZE) {
			size = _MINIMUM_INCREMENT_SIZE;
		}

		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();

			Counter counter = (Counter)session.get(Counter.class, name);

			if (counter == null) {
				counter = new Counter();

				counter.setName(name);
				counter.setCurrentId(_DEFAULT_CURRENT_ID);

				session.save(counter);

				session.flush();
			}

			long currentId = counter.getCurrentId() + size;

			/*System.out.println("Sleep\t" + name);

			try {
                Thread.sleep(5 * 1000);
            }
			catch (Exception e) {
			}*/

			counter.setCurrentId(currentId);

			session.flush();

			//System.out.println("Return\t" + name + " " + currentId);

			return currentId;
		}
		catch (HibernateException he) {
			throw new SystemException(he);
		}
		finally {
			session.close();
		}
	}

	public synchronized void rename(String oldName, String newName)
		throws SystemException {

		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();

			Counter counter = (Counter)session.load(Counter.class, oldName);

			long currentId = counter.getCurrentId();

			session.delete(counter);

			counter = new Counter();

			counter.setName(newName);
			counter.setCurrentId(currentId);

			session.save(counter);

			session.flush();
		}
		catch (ObjectNotFoundException onfe) {
		}
		catch (HibernateException he) {
			throw new SystemException(he);
		}
		finally {
			session.close();
		}
	}

	public synchronized void reset(String name) throws SystemException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();

			Counter counter = (Counter)session.load(Counter.class, name);

			session.delete(counter);

			session.flush();
		}
		catch (ObjectNotFoundException onfe) {
		}
		catch (HibernateException he) {
			throw new SystemException(he);
		}
		finally {
			session.close();
		}
	}

	private static final int _DEFAULT_CURRENT_ID = 0;

	private static final int _MINIMUM_INCREMENT_SIZE = 1;

}