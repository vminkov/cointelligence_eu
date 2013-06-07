package eu.cointelligence.model;

import java.util.UUID;

import javax.persistence.*;

@Table(name = "T_IDEA")
@Entity
public class Idea {

	@ManyToOne
	private User user;
	@Id
	private Long id;
	@Column(length = 130)
	private String title;
	@Column(length = 130)
	private String description1;
	@Column(length = 130)
	private String description2;
	@Column(length = 130)
	private String description3;
	@Column(length = 130)
	private String description4;
	@Column(length = 130)
	private String company;

	public Idea() {
		description1 = "";
		description2 = "";
		description3 = "";
		description4 = "";
		id = UUID.randomUUID().getLeastSignificantBits();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setTitle(String param) {
		if (param != null && param.length() > 130) {
			param = param.substring(0, 130);
		}
		this.title = param;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String param) {
		// a for cycle would be perfect but these var names...
		if(param == null) return;
		int length = param.length();
		if (length > 520) {
			param = param.substring(0, 520);
		}

		int begin = 0;
		int end = 130;

		if (length < 130) {
			end = length % 130;
			;
		}
		this.description1 = param.substring(begin, end);
		if (length > 130) {
			begin = 130;
			end = 260;
			if (length < 260) {
				end = (length % 130) + 130;
			}
			this.description2 = param.substring(begin, end);
			if (length > 260) {
				begin = 260;
				end = 390;
				if (length < 390) {
					end = (length % 130) + 260;
				}
				this.description3 = param.substring(begin, end);
			}
			if (length > 390) {
				begin = 390;
				end = 520;
				if (length < 520) {
					end = (length % 130) + 390;
				}
				this.description4 = param.substring(begin, end);
			}
		}

	}

	public String getDescription() {
		return description1 + description2 + description3 + description4;
	}

	public void setCompany(String param) {
		if (param != null && param.length() > 130) {
			param = param.substring(0, 130);
		}
		this.company = param;
	}

	public String getCompany() {
		return company;
	}

}