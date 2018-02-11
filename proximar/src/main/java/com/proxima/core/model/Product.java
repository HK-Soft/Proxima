package com.proxima.core.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedNativeQueries;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.proxima.core.dto.ProductDTO;

@Entity
@SqlResultSetMapping(name = "StockedProductMapping",
	classes = @ConstructorResult(targetClass = ProductDTO.class, 
	columns = {
		@ColumnResult(name = "id", type = Long.class), 
		@ColumnResult(name = "code", type = String.class),
		@ColumnResult(name = "description", type = String.class), 
		@ColumnResult(name = "category", type = String.class),
		@ColumnResult(name = "basicUoM", type = String.class), 
		@ColumnResult(name = "price", type = Double.class),
		@ColumnResult(name = "cost", type = Double.class), 
		@ColumnResult(name = "stockLevel", type = Double.class),
		@ColumnResult(name = "startStockLevel", type = Double.class),
		@ColumnResult(name = "reorederLevel", type = Double.class),
		@ColumnResult(name = "picture", type = String.class) 
		}
	)
)

@NamedNativeQueries(value = {

		@NamedNativeQuery(name = "getAllStockedProducts", 
			query = "SELECT " 
						+ "p.id as id, " 
						+ "p.code as code, "
						+ "p.description as description, " 
						+ "(p.start_stock_level + ISNULL(p_a.diff,0) )  as stockLevel, "
						+ "p.start_stock_level  as startStockLevel, " 
						+ "p.reoreder_level as reorederLevel, "
						+ "c.code as category, " 
						+ "u.code as basicUoM, " 
						+ "c_r.rate as cost," 
						+ "p_r.rate as price, "
						+ "CONCAT(docs.current_name,'.',docs.type) as picture " 
					+ "FROM products as p "
						+ "LEFT JOIN categories as c ON p.category_id = c.id "
						+ "LEFT JOIN units_of_measure as u ON p.basic_uom_id = u.id "
						+ "LEFT JOIN rates as p_r on p_r.id = u.price_id " 
						+ "LEFT JOIN rates as c_r on c_r.id = u.cost_id "
						+ "LEFT JOIN (SELECT product_id, (SUM(new_quantity - old_quantity)) as diff FROM product_adjustments GROUP BY product_adjustments.product_id) as p_a ON p_a.product_id = p.id "
						+ "LEFT JOIN documents as docs ON p.picture_id = docs.id ", 
			resultSetMapping = "StockedProductMapping"
		),

		@NamedNativeQuery(name = "getStockedProductByCode", query = "SELECT " + "p.id as id, " + "p.code as code, "
				+ "p.description as description, " + "(p.start_stock_level + ISNULL(p_a.diff,0) )  as stockLevel, "
				+ "p.start_stock_level  as startStockLevel, " + "p.reoreder_level as reorederLevel, "
				+ "c.code as category, " + "u.code as basicUoM, " + "c_r.rate as cost," + "p_r.rate as price, "
				+ "CONCAT(docs.current_name,'.',docs.type) as picture " + "FROM products as p "
				+ "LEFT JOIN categories as c ON p.category_id = c.id "
				+ "LEFT JOIN units_of_measure as u ON p.basic_uom_id = u.id "
				+ "LEFT JOIN rates as p_r on p_r.id = u.price_id " + "LEFT JOIN rates as c_r on c_r.id = u.cost_id "
				+ "LEFT JOIN (SELECT product_id, (SUM(new_quantity - old_quantity)) as diff FROM product_adjustments GROUP BY product_adjustments.product_id) as p_a ON p_a.product_id = p.id "
				+ "LEFT JOIN documents as docs ON p.picture_id = docs.id "
				+ "WHERE p.code = :code", resultSetMapping = "StockedProductMapping") })

@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column(nullable = false)
	private String description;

	@Column(nullable = true)
	private double reorederLevel = 0;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	private Category category;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "basic_uom_id", nullable = false)
	private UnitOfMeasure basicUoM;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinTable(name = "product_alternative_uoms", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = {
			@JoinColumn(name = "uom_id", referencedColumnName = "id"),
			@JoinColumn(name = "uom_code", referencedColumnName = "code") }, uniqueConstraints = {
					@UniqueConstraint(columnNames = { "product_id", "uom_code" }), })
	private Set<UnitOfMeasure> alternativeUsoM = new HashSet<>();

	@Column
	private double startStockLevel = 0;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "picture_id", nullable = true)
	private Document picture;

	public Product() {
	}

	public Product(String code, String description, UnitOfMeasure basicUoM) {
		this.code = code;
		this.description = description;
		this.basicUoM = basicUoM;
		this.picture = null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getReorederLevel() {
		return reorederLevel;
	}

	public void setReorederLevel(double reorederLevel) {
		this.reorederLevel = reorederLevel;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public UnitOfMeasure getBasicUoM() {
		return basicUoM;
	}

	public void setBasicUoM(UnitOfMeasure basicUoM) {
		this.basicUoM = basicUoM;
	}

	public Set<UnitOfMeasure> getAlternativeUsoM() {
		return alternativeUsoM;
	}

	public void setAlternativeUsoM(Set<UnitOfMeasure> alternativeUsoM) {
		this.alternativeUsoM = alternativeUsoM;
	}

	public void setStartStockLevel(double startStockLevel) {
		this.startStockLevel = startStockLevel;
	}

	public double getStartStockLevel() {
		return startStockLevel;
	}

	public Document getPicture() {
		return picture;
	}

	public void setPicture(Document picture) {
		this.picture = picture;
	}

}
