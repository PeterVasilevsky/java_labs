package ru.spbstu.telematics.ivanov722.lab03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CriticalSection {
	private int NS_WE;
	private int ES_WE;
	private int SN_WE;
	private int ES_SN;

	private Lock lockNS_WE = new ReentrantLock();
	private Condition condNS_WE = lockNS_WE.newCondition();
	private Lock lockES_WE = new ReentrantLock();
	private Condition condES_WE = lockES_WE.newCondition();
	private Lock lockSN_WE = new ReentrantLock();
	private Condition condSN_WE = lockSN_WE.newCondition();
	private Lock lockES_SN = new ReentrantLock();
	private Condition condES_SN = lockES_SN.newCondition();

	public CriticalSection() {
		NS_WE = 0;
		ES_WE = 0;
		SN_WE = 0;
		ES_SN = 0;

	}

	// resolving collisions
	public void setNS_WE() {
		lockNS_WE.lock();
		int f = 0;
		if (NS_WE == 1) {
			try {
				condNS_WE.await();
			} catch (InterruptedException e) {

				e.printStackTrace();
			} finally {
				NS_WE = 1;
				lockNS_WE.unlock();
				f = 1;
			}
		}
		if (f != 1) {
			NS_WE = 1;
			lockNS_WE.unlock();
		}
	}

	public void freeNS_WE() {
		lockNS_WE.lock();
		NS_WE = 0;
		condNS_WE.signal();
		lockNS_WE.unlock();
	}

	public void setES_WE() {
		int f = 0;
		lockES_WE.lock();
		if (ES_WE == 1) {
			try {
				condES_WE.await();
			} catch (InterruptedException e) {

				e.printStackTrace();
			} finally {
				ES_WE = 1;
				lockES_WE.unlock();
				f = 1;
			}
		}
		if (f != 1) {
			ES_WE = 1;
			lockES_WE.unlock();
		}

	}

	public void freeES_WE() {
		lockES_WE.lock();
		ES_WE = 0;
		condES_WE.signal();
		lockES_WE.unlock();
	}

	public void setSN_WE() {
		lockSN_WE.lock();
		int f = 0;
		if (SN_WE == 1) {
			try {
				condSN_WE.await();
			} catch (InterruptedException e) {

				e.printStackTrace();
			} finally {
				SN_WE = 1;
				lockSN_WE.unlock();
				f = 1;
			}
		}
		if (f != 1) {
			SN_WE = 1;
			lockSN_WE.unlock();
		}

	}

	public void freeSN_WE() {
		lockSN_WE.lock();
		SN_WE = 0;
		condSN_WE.signal();
		lockSN_WE.unlock();
	}

	public void setES_SN() {
		lockES_SN.lock();
		int f = 0;
		if (ES_SN == 1) {
			try {
				condES_SN.await();
			} catch (InterruptedException e) {

				e.printStackTrace();
			} finally {
				ES_SN = 1;
				lockES_SN.unlock();
				f = 1;
			}
		}
		if (f != 1) {
			ES_SN = 1;
			lockES_SN.unlock();
		}

	}

	public void freeES_SN() {
		lockES_SN.lock();
		ES_SN = 0;
		condES_SN.signal();
		lockES_SN.unlock();
	}

	public synchronized int getCollision(int num) {
		switch (num) {
		case 0:
			return NS_WE;
		case 1:
			return ES_WE;
		case 2:
			return SN_WE;
		case 3:
			return ES_SN;
		}
		return -1;

	}

}