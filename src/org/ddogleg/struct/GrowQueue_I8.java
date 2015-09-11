/*
 * Copyright (c) 2012-2015, Peter Abeles. All Rights Reserved.
 *
 * This file is part of DDogleg (http://ddogleg.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ddogleg.struct;


import java.util.Arrays;

/**
 * This is a queue that is composed of bytes.  Elements are added and removed from the tail
 *
 * @author Peter Abeles
 */
public class GrowQueue_I8 {

	public byte data[];
	public int size;

	public GrowQueue_I8(int maxSize) {
		data = new byte[ maxSize ];
		this.size = 0;
	}

	public GrowQueue_I8() {
		this(10);
	}

	public void reset() {
		size = 0;
	}

	public void addAll( GrowQueue_I8 queue ) {
		if( size+queue.size > data.length ) {
			byte temp[] = new byte[ (size+queue.size) * 2];
			System.arraycopy(data,0,temp,0,size);
			data = temp;
		}
		System.arraycopy(queue.data,0,data,size,queue.size);
		size += queue.size;
	}

	public void addAll( byte[] array , int startIndex , int endIndex ) {
		if( endIndex > array.length )
			throw new IllegalAccessError("endIndex is larger than input array");

		int arraySize = endIndex-startIndex;

		if( size+arraySize > data.length ) {
			byte temp[] = new byte[ (size+arraySize) * 2];
			System.arraycopy(data,0,temp,0,size);
			data = temp;
		}
		System.arraycopy(array,startIndex,data,size,arraySize);
		size += arraySize;
	}

	public void add(int value) {
		push(value);
	}

	public void push( int val ) {
		if( size == data.length ) {
			byte temp[] = new byte[ size * 2];
			System.arraycopy(data,0,temp,0,size);
			data = temp;
		}
		data[size++] = (byte)val;
	}

	public int get( int index ) {
		return data[index];
	}

	public void set( int index , int value ) {
		data[index] = (byte)value;
	}

	public byte removeTail() {
		if( size > 0 ) {
			size--;
			return data[size];
		} else {
			throw new RuntimeException("Size zero, no tail");
		}
	}

	public void fill( byte value ) {
		Arrays.fill(data, 0, size, value);
	}

	public void resize( int size ) {
		if( data.length < size ) {
			data = new byte[size];
		}
		this.size = size;
	}

	public void setMaxSize( int size ) {
		if( data.length < size ) {
			data = new byte[size];
		}
	}

	public int size() {
		return size;
	}

	public int pop() {
		return data[--size];
	}
}
