package com.thang.webshop.entity.type;

/**
 * 
 * @author thiep.nguyen
 *
 */
public class EnumConstants {

	public enum ProductStatus implements PersistableEnum<Integer> {
		HOT(0), BUY_MORE(1), NEW(2),NORMAL(3);

		private final int id;

		private ProductStatus(int id) {
			this.id = id;
		}

		public static ProductStatus getFromId(int id) {
			for (ProductStatus type : values()) {
				if (type.id == id) {
					return type;
				}
			}
			return null;
		}

		public Integer getId() {
			return this.id;
		}
	}

	public static class ProductStatusConverter extends AbstractEnumConverter<ProductStatus, Integer> {
		public ProductStatusConverter() {
			super(ProductStatus.class);
		}
	}

}
