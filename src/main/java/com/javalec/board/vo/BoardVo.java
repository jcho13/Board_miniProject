package com.javalec.board.vo;

public class BoardVo {
	private int no;
	private String id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int count=0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", id=" + id + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regdate=" + regdate + ", count=" + count + "]";
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
