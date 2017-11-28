package com.training.convert;

public interface Convert<K, V> {

	public V createTarget();

	public V convert(K source);
}
