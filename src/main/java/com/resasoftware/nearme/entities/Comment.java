package com.resasoftware.nearme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="comments")
@NamedQueries({
	@NamedQuery(name="Comment.commentByEnterpriseId",
			query="select c from Comment c where c.enterpriseId.id=?1")
})
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="enterprise_id")
	private Enterprise enterpriseId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userId;
	@Size(min=5, message="The comment must have a minimum 10 characteres")
	@Column(name="comment", nullable=false, length=500)
	private String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Enterprise getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Enterprise enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
