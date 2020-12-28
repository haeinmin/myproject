package article.model;

import java.util.Date;

public class Article {
	private Integer number;
	private Writer writer;
	private String title;
	private Date regDate;
	private Date modDate;
	private int readCount;
	private String type;
	
	public Article(Integer number, Writer writer, String title, Date regDate, Date modDate, int readCount, String type) {
		super();
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.modDate = modDate;
		this.readCount = readCount;
		this.type = type;
	}
	
	public Integer getNumber() {
		return number;
	}
	public Writer getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public Date getRegDate() {
		return regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public String getType() {
		return type;
	}
}
	
	
