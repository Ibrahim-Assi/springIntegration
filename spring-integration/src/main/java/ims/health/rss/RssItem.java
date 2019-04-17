package ims.health.rss;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class RssItem  implements Serializable{
	  
	 private String uri; 
	 private String title;  
	 private String description; 
	 private String link;  
	 private Date   pubDate; 
	 private String comments; 
	 
	 
	public RssItem() {
		super();
	}
	
	  

	public RssItem(String uri,String title, String description, String link, Date pubDate, 
			String comments) {
		super();
		this.uri = uri;
		this.title = title;
		this.description = description;
		this.link = link;
		this.pubDate = pubDate;
		this.comments = comments;
	}

	

	public String getUri() {
		return uri;
	}
 
	@XmlAttribute(name="uri")
	public void setUri(String uri) {
		this.uri = uri;
	}
 
	public String getTitle() {
		return title;
	}
	 
	@XmlElement(name="title")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name="description")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	@XmlElement(name="link")
	public void setLink(String link) {
		this.link = link;
	}

	public Date getPubDate() {
		return pubDate;
	}

	@XmlElement(name="pubDate")
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}


	public String getComments() {
		return comments;
	}

	@XmlElement(name="comments")
	public void setComments(String comments) {
		this.comments = comments;
	}
 

	@Override
	public String toString() {
		return "RssItem [title=" + title + ", description=" + description + ", link=" + link
				+ ", pubDate=" + pubDate + ", comments=" + comments + "]";
	}
	 
	
	 

}
