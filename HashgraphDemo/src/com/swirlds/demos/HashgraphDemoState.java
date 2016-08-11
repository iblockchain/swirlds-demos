/*
 * This file is public domain.
 *
 * SWIRLDS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF 
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SWIRLDS SHALL NOT BE LIABLE FOR 
 * ANY DAMAGES SUFFERED AS A RESULT OF USING, MODIFYING OR 
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.swirlds.demos;

import java.io.IOException;
import java.time.Instant;

import com.swirlds.platform.Address;
import com.swirlds.platform.AddressBook;
import com.swirlds.platform.FCDataInputStream;
import com.swirlds.platform.FCDataOutputStream;
import com.swirlds.platform.FastCopyable;
import com.swirlds.platform.Platform;
import com.swirlds.platform.SwirldState;

/**
 * The state for the hashgraph demo. See the comments for com.swirlds.demos.HashgraphDemoMain
 */
public class HashgraphDemoState implements SwirldState {
	private AddressBook	addressBook;

	// ///////////////////////////////////////////////////////////////////

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void init(Platform platform, AddressBook addressBook) {
		this.addressBook = addressBook;
	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized AddressBook getAddressBookCopy() {
		return addressBook.copy();
	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void copyFrom(SwirldState state) {
		addressBook = ((HashgraphDemoState) state).addressBook;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void handleTransaction(long id, boolean isConsensus,
			Instant timeCreated, byte[] trans, Address address) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void freeze() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized FastCopyable copy() {
		HashgraphDemoState copy = new HashgraphDemoState();
		copy.copyFrom(this);
		return copy;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copyTo(FCDataOutputStream outStream) throws IOException {
		addressBook.copyTo(outStream);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copyFrom(FCDataInputStream inStream) throws IOException {
		addressBook.copyFrom(inStream);
	}
}
