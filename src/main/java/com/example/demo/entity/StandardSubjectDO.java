package com.example.demo.entity;

public class StandardSubjectDO {

	private Long std_id;
	private Long sub_id;
	private Long teacher_id;
	private String status;

	public StandardSubjectDO() {

	}

	public StandardSubjectDO(Long std_id, Long sub_id, Long teacher_id, String status) {
		super();
		this.std_id = std_id;
		this.sub_id = sub_id;
		this.teacher_id = teacher_id;
		this.status = status;
	}

	public Long getStd_id() {
		return std_id;
	}

	public void setStd_id(Long std_id) {
		this.std_id = std_id;
	}

	public Long getSub_id() {
		return sub_id;
	}

	public void setSub_id(Long sub_id) {
		this.sub_id = sub_id;
	}

	public Long getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Long teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
