package com.thang.webshop.entity.type;

import javax.persistence.AttributeConverter;

/**
 * Generic class for all enum to database field converter
 * Ref: https://stackoverflow.com/questions/23564506/is-it-possible-to-write-a-generic-enum-converter-for-jpa
 * 
 * @author minhpham
 *
 * @param <T>
 * @param <E>
 */
public abstract class AbstractEnumConverter<T extends Enum<T> & PersistableEnum<E>, E>
		implements AttributeConverter<T, E> {
	private final Class<T> clazz;

	public AbstractEnumConverter(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public E convertToDatabaseColumn(T attribute) {
		return attribute != null ? attribute.getId() : null;
	}

	@Override
	public T convertToEntityAttribute(E dbData) {
		T[] enums = clazz.getEnumConstants();

		for (T e : enums) {
			if (e.getId().equals(dbData)) {
				return e;
			}
		}

		throw new UnsupportedOperationException(
				"Cannot convert " + dbData.getClass().getName() + " to " + clazz.getName());
	}

}
