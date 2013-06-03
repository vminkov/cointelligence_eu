package eu.cointelligence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Basic;

@Entity
@Table(name = "T_STATEMENTSTAKE")
public class StatementStake {

	@Id
	private long id;
	@OneToOne
	private Statement statement;
	@Basic
	private Long sharesCount;
	@ManyToOne
	private Account account;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement param) {
		this.statement = param;
	}

	public void setSharesCount(Long param) {
		this.sharesCount = param;
	}

	public Long getSharesCount() {
		return sharesCount;
	}

	public void setAccount(Account account) {
		this.account = account;
		
	}

}