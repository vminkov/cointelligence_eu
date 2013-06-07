package eu.cointelligence.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_statement")
public class Statement {
	// private static transient Gson gson = new Gson(); ?
	@Column(length=130)
	private String title;
	@Column(length=130)
	private String description1;
	@Column(length=130)
	private String description2;
	@Column(length=130)
	private String description3;
	@Column(length=130)
	private String description4;
	@Basic
	private Boolean voteStarted;
	@Basic 
	private Long currentValue;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	public Statement(){
		//setId(UUID.randomUUID().getMostSignificantBits());
		description1 = "";
		description2 = "";
		description3 = "";
		description4 = "";
	}

	public void setTitle(String param) {
		if(param == null) return;
		if(param.length() > 130){
			param = param.substring(0, 130);
		}
		this.title = param;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String param) {
		if(param == null) return;
		//a for cycle would be perfect but these vars...
		int length = param.length();
		if(length > 520){
			param = param.substring(0, 520);
		}

		int begin = 0;
		int end = 130;
		
		if(length < 130){
			end = length % 130;;
		}
		this.description1 = param.substring(begin, end);
		if(length > 130){
			begin = 130;
			end = 260;
			if(length < 260){
				end = (length % 130) + 130;
			}
			this.description2 = param.substring(begin, end);
			if(length > 260){
				begin = 260;
				end = 390;
				if(length < 390){
					end = (length % 130) + 260;
				}
				this.description3 = param.substring(begin, end);
			}
			if(length > 390){
				begin = 390;
				end = 520;
				if(length < 520){
					end = (length % 130) + 390;
				}
				this.description4 = param.substring(begin, end);
			}
		}
	}

	public String getDescription() {
		return description1 + description2 + description3 + description4;
	}

	public void setVoteStarted(Boolean param) {
		this.voteStarted = param;
	}

	public Boolean getVoteStarted() {
		return voteStarted;
	}

	public void setCurrentValue(Long param) {
		this.currentValue = param;
	}

	public Long getCurrentValue() {
		return currentValue;
	}

	public void setId(Long param) {
		this.id = param;
	}

	public Long getId() {
		return id;
	}
}
