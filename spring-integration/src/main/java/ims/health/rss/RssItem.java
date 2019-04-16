package ims.health.rss;

import java.util.Date;
import java.util.List;

public class RssItem {
	
	 private String title;
	 private String description;
	 private String link;
	 private Date   pubDate;
	 private List   categories;
	 private String comments; 
	 
	 
	public RssItem() {
		super();
	}
	
	

	public RssItem(String title) {
		super();
		this.title = title;
	}

//RssItem(String, SyndContent, String, Date, List<SyndCategory>, String) is undefined

	public RssItem(String title, String description, String link, Date pubDate, List categories,
			String comments) {
		super();
		this.title = title;
		this.description = description;
		this.link = link;
		this.pubDate = pubDate;
		this.categories = categories;
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public List getCategories() {
		return categories;
	}



	public void setCategories(List categories) {
		this.categories = categories;
	}



	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
 

	@Override
	public String toString() {
		return "RssItem [title=" + title + ", description=" + description + ", link=" + link
				+ ", pubDate=" + pubDate + ", categories=" + categories + ", comments=" + comments + "]";
	}
	 
	
	 

}
