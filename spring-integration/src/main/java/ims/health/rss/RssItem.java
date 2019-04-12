package ims.health.rss;

public class RssItem {
	
	 private String title;
	 private String description;
	 private String content;
	 private String link;
	 private String pubDate;
	 private String category;
	 private String comments;
	 private String guid;
	 
	 
	public RssItem() {
		super();
	}

	public RssItem(String title, String description, String content, String link, String pubDate, String category,
			String comments, String guid) {
		super();
		this.title = title;
		this.description = description;
		this.content = content;
		this.link = link;
		this.pubDate = pubDate;
		this.category = category;
		this.comments = comments;
		this.guid = guid;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public String toString() {
		return "RssItem [title=" + title + ", description=" + description + ", content=" + content + ", link=" + link
				+ ", pubDate=" + pubDate + ", category=" + category + ", comments=" + comments + ", guid=" + guid + "]";
	}
	 
	
	 

}
