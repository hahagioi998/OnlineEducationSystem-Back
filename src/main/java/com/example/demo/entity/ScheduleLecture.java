package com.example.demo.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


public class ScheduleLecture {
	
		@JsonInclude(JsonInclude.Include.NON_NULL)
		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
		String time;
	    String LectureLink;
	    String subject;
	    String teacherName;
        String teacherMail;
        private Long id;
        private Long standard;
        public ScheduleLecture(String time, String lectureLink, String subject, String teacherName, String teacherMail,Long id,Long standard) {
    		
    		this.time = time;
    		this.LectureLink = lectureLink;
    		this.subject = subject;
    		this.teacherName = teacherName;
    		this.teacherMail = teacherMail;
    		this.id=id;
    		this.standard=standard;
    	}
        
        
       

    	public ScheduleLecture() {
			
		}




		public Long getStandard() {
			return standard;
		}




		public void setStandard(Long standard) {
			this.standard = standard;
		}




		public Long getId() {
			return id;
		}




		public void setId(Long id) {
			this.id = id;
		}




		public String getTime() {
			return time;
		}




		public void setTime(String time) {
			this.time = time;
		}




		public String getLectureLink() {
    		return LectureLink;
    	}

    	public void setLectureLink(String lectureLink) {
    		LectureLink = lectureLink;
    	}

    	public String getSubject() {
    		return subject;
    	}

    	public void setSubject(String subject) {
    		this.subject = subject;
    	}

    	public String getTeacherName() {
    		return teacherName;
    	}

    	public void setTeacherName(String teacherName) {
    		this.teacherName = teacherName;
    	}

    	public String getTeacherMail() {
    		return teacherMail;
    	}

    	public void setTeacherMail(String teacherMail) {
    		this.teacherMail = teacherMail;
    	}
		
}