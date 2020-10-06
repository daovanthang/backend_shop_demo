package com.thang.webshop.entity.type;

/**
 * The interface of all persistable enums.<br/>
 * Ref:
 * https://stackoverflow.com/questions/23564506/is-it-possible-to-write-a-generic-enum-converter-for-jpa
 * 
 * @author minhpham
 *
 * @param <T>
 */
public interface PersistableEnum<T> {
	T getId();
}
