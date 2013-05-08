package in.ac.vit.twicks.entities;

import in.ac.vit.twicks.search.fetchers.Sources;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "daily_score")
public class DailyScore implements Serializable {

	@Id
	@SequenceGenerator(name = "DAILY_SCORE_ID_GENERATOR", sequenceName = "daily_score_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DAILY_SCORE_ID_GENERATOR")
	private Integer id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sources source;
	@NotNull
	private double score;
	@NotNull
	private int count;
	@NotNull
	@Column(name = "data_date")
	@Temporal(TemporalType.DATE)
	private Date date;
	@ManyToOne
	@NotNull
	private Product product;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Sources getSource() {
		return this.source;
	}

	public void setSource(Sources source) {
		this.source = source;
	}

	public double getScore() {
		return this.score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}